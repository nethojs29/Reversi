package mx.uson.cc.reversi;

import mx.uson.cc.gameframework.*;

/**
 * Created by ernesto on 27/04/16.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(mx.uson.cc.gameframework.Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.negra = g.newPixmap("negras32.png",Graphics.PixmapFormat.ARGB4444);
        Assets.blanca = g.newPixmap("blancas32.png",Graphics.PixmapFormat.ARGB4444);
        Assets.vacia = g.newPixmap("vacias32.png",Graphics.PixmapFormat.ARGB4444);

        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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
