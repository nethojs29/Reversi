package mx.uson.cc.gameframework;

/**
 * Created by ernesto on 2/11/15.
 */
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
