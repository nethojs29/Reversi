package mx.uson.cc.gameframework.impl;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mx.uson.cc.gameframework.Input;
import mx.uson.cc.gameframework.Pool;

/**
 * Created by ernesto on 2/11/15.
 */
public class KeyboardHandler implements View.OnKeyListener {
    boolean[] pressedKeys = new boolean[128];
    Pool<Input.KeyEvent> keyEventPool;
    List<Input.KeyEvent> keyEventsBuffer = new ArrayList<Input.KeyEvent>();
    List<Input.KeyEvent> keyEvents = new ArrayList<Input.KeyEvent>();

    public KeyboardHandler(View view){
        Pool.PoolObjectFactory<Input.KeyEvent> factory= new Pool.PoolObjectFactory<Input.KeyEvent>(){
            public Input.KeyEvent createObject(){
                return new Input.KeyEvent();
            }
        };
        keyEventPool = new Pool<Input.KeyEvent>(factory,100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
        if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;
        synchronized (this) {
            Input.KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                keyEvent.type = Input.KeyEvent.KEY_DOWN;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] =  true;
            }
            if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
                keyEvent.type = Input.KeyEvent.KEY_UP;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] =  false;
            }
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode > 127)
            return false;
        return pressedKeys[keyCode];
    }

    public List <Input.KeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = keyEvents.size();
            for (int i = 0; i < len; i++) {
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
}
