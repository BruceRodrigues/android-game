package spaceship.game.bruce.br.spaceship.spaceship.game.bruce.br.spaceship.loop;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by bruce on 23/08/15.
 */
public class GameLoop extends SurfaceView implements Runnable {

    private boolean running = false;

    private Thread render = null;

    public GameLoop(Context context) {
        super(context);
    }

    @Override
    public void run() {

        while(this.running) {
            System.out.println("game running");
        }

    }

    public void resume() {
        this.running = true;
        this.render = new Thread(this);
        this.render.start();
    }
}
