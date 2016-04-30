package mx.uson.cc.reversi;

/**
 * Created by ernesto on 27/04/16.
 */
public class Celda {
    public int x,y;
    private int valor;

    public Celda(int x, int y,int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
