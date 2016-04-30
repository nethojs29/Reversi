package mx.uson.cc.gameframework.impl;

import android.graphics.Bitmap;

import mx.uson.cc.gameframework.Graphics;
import mx.uson.cc.gameframework.Pixmap;

/**
 * Created by ernesto on 2/11/15.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }
    public int getWidth() {
        return bitmap.getWidth();
    }
    public int getHeight() {
        return bitmap.getHeight();
    }

    public Graphics.PixmapFormat getFormat() {
        return format;
    }
    public void dispose() {
        bitmap.recycle();
    }
}
