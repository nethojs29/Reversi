package mx.uson.cc.reversi;

import mx.uson.cc.gameframework.Graphics;

/**
 * Created by ernesto on 27/04/16.
 */
public class World {
    public boolean gameOver = false;
    public Celda[][] tablero = new Celda[8][8];
    Graphics g;

    public World(Graphics g) {
        this.g = g;
        inicializarTablero();
    }

    public void inicializarTablero() {
        for (int i = 0; i < tablero.length; ++i) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new Celda(30 + 32 * i, 100 + 32 * j, 0);
            }
        }
        tablero[3][3].setValor(1);
        tablero[3][4].setValor(-1);
        tablero[4][3].setValor(-1);
        tablero[4][4].setValor(1);
    }
    public void update(float deltaTime){
        if(gameOver)
            return;
    }
}
