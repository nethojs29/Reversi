package mx.uson.cc.reversi;

import mx.uson.cc.gameframework.Screen;
import mx.uson.cc.gameframework.impl.AndroidGame;

/**
 * Created by ernesto on 27/04/16.
 */
public class Game extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
