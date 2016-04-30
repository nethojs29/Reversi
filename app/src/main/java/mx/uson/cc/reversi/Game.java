package mx.uson.cc.reversi;

import android.view.View;

import mx.uson.cc.gameframework.Screen;
import mx.uson.cc.gameframework.impl.AndroidGame;

/**
 * Created by ernesto on 27/04/16.
 */
public class Game extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        
        return new LoadingScreen(this);
    }
}
