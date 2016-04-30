package mx.uson.cc.reversi;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.List;

import mx.uson.cc.gameframework.*;

/**
 * Created by ernesto on 27/04/16.
 */
public class GameScreen extends Screen {
    enum GameState {
        pBlancas,
        pNegras,
        GameOver
    }

    float wait = 0;

    Paint paintNegro;
    Paint paintBlanco;

    int playerNegro = 2;
    int playerBlanco = 2;

    private static final int[] moverseX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] moverseY = {-1, 0, 1, -1, 1, -1, 0, 1};

    GameState state = GameState.pBlancas;
    World world;
    Celda[][] tablero;
    int ocupadas = 4;


    public GameScreen(mx.uson.cc.gameframework.Game game) {
        super(game);
        world = new World(game.getGraphics());

        paintBlanco = new Paint();
        paintBlanco.setColor(Color.WHITE);
        paintBlanco.setTextSize(30);

        paintNegro = new Paint();
        paintNegro.setColor(Color.BLACK);
        paintNegro.setTextSize(30);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();
        tablero = world.tablero;

        for (int i = 0; i < 9; i++) {
            g.drawLine(30 + 32 * i, 100, 30 + 32 * i, 355, Color.BLACK);
            g.drawLine(30, 100 + 32 * i, 30 + 32 * 8, 100 + 32 * i, Color.BLACK);
        }
        if (state == GameState.pBlancas)
            g.drawString("^", 215, 80, paintBlanco);
        else if (state == GameState.pNegras)
            g.drawString("^", 60, 80, paintNegro);

        g.drawString("Negras:" + playerNegro, 15, 50, paintNegro);
        g.drawString("Blancas:" + playerBlanco, 160, 50, paintBlanco);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.drawPixmap(tablero[i][j].getValor() == 0 ? Assets.vacia : tablero[i][j].getValor() == 1 ? Assets.blanca : Assets.negra
                        , tablero[i][j].x
                        , tablero[i][j].y);
            }

        }

    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        if (state == GameState.pNegras || state == GameState.pBlancas)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
            wait = 0;
        }
    }

    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        game.getGraphics().drawString("Ganador: Negrasss", 500, 200, paintBlanco);
    }

    private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        world.update(deltaTime);

        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            inCasilla(event.x, event.y);
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 800, 700, Color.GRAY);
        drawWorld(world);

        if (!tieneMovimientos(state))
            state = state == GameState.pNegras ? GameState.pBlancas : GameState.pNegras;

        if (seAcabo()) {
            Log.e("", "se acabo????!!");
            //state = GameState.GameOver;
            game.setScreen(new GameOverScreen(game, playerNegro > playerBlanco ? "Negras" : "Blancas"));
        } else
            Log.e("", "present: no se acaboooo");
    }

    public boolean inTablero(int x, int y) {
        return (x > 30 & x < 286 & y > 100 & y < 356);
    }

    public void inCasilla(int x, int y) {
        if (!inTablero(x, y)) return;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (x > tablero[i][j].x & x < tablero[i][j].x + 32 & y > tablero[i][j].y & y < tablero[i][j].y + 32) {
                    jugada(i, j);
                }
            }
        }
    }

    public void jugada(int row, int col) {
        int nuevoValor;
        if (state == GameState.pBlancas) nuevoValor = 1;
        else nuevoValor = -1;

        if (jugadaLegal(nuevoValor, row, col)) {
            ocupadas++;
            if (nuevoValor == 1) {
                playerBlanco++;
            } else
                playerNegro++;
            reversi(nuevoValor, row, col);
            if (state == GameState.pBlancas) state = GameState.pNegras;
            else state = GameState.pBlancas;
        }


    }

    public boolean jugadaLegal(int valor, int row, int col) {
        if (tablero[row][col].getValor() != 0) {
            return false;
        }

        boolean legal = false;

        for (int i = 0; i < 8; i++) {
            int r = row + moverseX[i];
            int c = col + moverseY[i];
            boolean inter = false;

            while (r >= 0 & r < 8 & c >= 0 & c < 8) {
                if (tablero[r][c].getValor() == valor * -1) {
                    inter = true;
                } else if ((tablero[r][c].getValor() == valor) && inter) {
                    legal = true;
                    break;
                } else {
                    break;
                }
                r += moverseX[i];
                c += moverseY[i];
            }
            if (legal) {
                break;
            }
        }

        return legal;

    }

    public void reversi(int valor, int row, int col) {
        tablero[row][col].setValor(valor);

        for (int i = 0; i < 8; i++) {
            int r = row + moverseX[i];
            int c = col + moverseY[i];
            boolean inter = false;

            while (r >= 0 & r < 8 & c >= 0 & c < 8) {
                if (tablero[r][c].getValor() == 0) {
                    break;
                }
                if (tablero[r][c].getValor() != valor) {
                    inter = true;
                }

                if ((tablero[r][c].getValor() == valor) && inter) {
                    int rx = row + moverseX[i];
                    int cy = col + moverseY[i];

                    while (rx != r || cy != c) {
                        tablero[rx][cy].setValor(valor);
                        if (valor == 1) {
                            playerBlanco++;
                            playerNegro--;
                        } else {
                            playerBlanco--;
                            playerNegro++;
                        }
                        rx += moverseX[i];
                        cy += moverseY[i];
                    }
                    break;
                }
                r += moverseX[i];
                c += moverseY[i];
            }
        }
    }

    public boolean tieneMovimientos(GameState jugador) {
        int valor = jugador == GameState.pNegras ? -1 : 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (jugadaLegal(valor, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean seAcabo() {
        boolean end = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].getValor() == 0) {
                    end = false;
                }
            }
        }
        return end;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
