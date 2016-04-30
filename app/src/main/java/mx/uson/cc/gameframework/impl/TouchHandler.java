package mx.uson.cc.gameframework.impl;

import android.view.View;

import java.util.List;

import mx.uson.cc.gameframework.Input;

/**
 * Created by ernesto on 2/11/15.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<Input.TouchEvent> getTouchEvents();
}
