package mx.uson.cc.gameframework;

/**
 * Created by ernesto on 2/11/15.
 */
public abstract class Screen {
    protected final Game game;

    public Screen(Game game){
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
