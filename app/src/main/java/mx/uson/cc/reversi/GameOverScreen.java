package mx.uson.cc.reversi;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import mx.uson.cc.gameframework.*;

/**
 * Created by ernesto on 29/04/16.
 */
public class GameOverScreen extends Screen {

    Paint paint,paint2;
    String ganador;

    public GameOverScreen(mx.uson.cc.gameframework.Game game,String winner) {
        super(game);
        paint = new Paint();
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);

        ganador = winner;

        paint2 = new Paint();
        paint2.setTextSize(20);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.CYAN);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if(touchEvents.size()>3){
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawRect(0,0,800,700, Color.GRAY);

        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.drawPixmap(Assets.blanca, 30 + 32 * i, 100+32*j);
            }
        }*/
        g.drawString("Doble toque para ",155,430,paint2);
        g.drawString("volver al menu",155,460,paint2);
        g.drawString("GameOver", 160, 400, paint);

        g.drawString("Ganador:", 160, 200, paint);
        g.drawString(ganador, 160, 250, paint);
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
