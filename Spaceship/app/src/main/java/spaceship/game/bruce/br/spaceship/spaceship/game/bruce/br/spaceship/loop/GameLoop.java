package spaceship.game.bruce.br.spaceship.spaceship.game.bruce.br.spaceship.loop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by bruce on 23/08/15.
 */
public class GameLoop extends SurfaceView implements Runnable {

    private boolean running = false;

    private Thread render = null;

    private SurfaceHolder holder;

    private int playerY = 300;

    private Paint paint;

    public GameLoop(Context context) {
        super(context);
        this.holder = this.getHolder();
        this.paint = new Paint();
    }

    @Override
    public void run() {

        while(this.running) {
            System.out.println("game running");

            if(!this.holder.getSurface().isValid()) {
                continue;
            }

            Canvas canvas = this.holder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            this.drawPlayer(canvas);


            this.holder.unlockCanvasAndPost(canvas);
        }

    }

    private void drawPlayer(Canvas canvas) {
        this.paint.setColor(Color.GREEN);
        canvas.drawCircle(100,this.playerY,50,this.paint);
    }

    public void resume() {
        this.running = true;
        this.render = new Thread(this);
        this.render.start();
    }

    public void moveDown(int pixels) {
        this.playerY += pixels;
    }
}
