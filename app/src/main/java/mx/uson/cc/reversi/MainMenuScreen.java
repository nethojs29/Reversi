package mx.uson.cc.reversi;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mx.uson.cc.gameframework.*;

/**
 * Created by ernesto on 27/04/16.
 */
public class MainMenuScreen extends Screen {

    Paint paint,paint2;

    public MainMenuScreen(mx.uson.cc.gameframework.Game game) {
        super(game);
        paint = new Paint();
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);

        paint2 = new Paint();
        paint2.setTextSize(20);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.CYAN);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 800, 700)) {
                    game.setScreen(new GameScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawRect(0,0,800,700,Color.GRAY);

        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.drawPixmap(Assets.blanca, 30 + 32 * i, 100+32*j);
            }
        }*/
        g.drawString("Toque para iniciar",155,450,paint2);
        g.drawString("Reversi", 160, 400, paint);
        g.drawLine(60, 355, 60, 410, Color.CYAN);
        g.drawLine(260, 355, 260, 410, Color.CYAN);
        g.drawLine(60, 355, 260, 355, Color.CYAN);
        g.drawLine(60, 410, 260, 410, Color.CYAN);
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

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }
}
