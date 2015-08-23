package spaceship.game.bruce.br.spaceship.spaceship.game.bruce.br.spaceship.loop;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import spaceship.game.bruce.br.spaceship.R;

/**
 * Created by bruce on 23/08/15.
 */
public class GameLoop extends SurfaceView implements Runnable {

    private boolean running = false;

    private Thread render = null;

    private SurfaceHolder holder;

    private int playerY = 300, playerX = 300, playerRadius = 50;

    private Paint paint;

    private int enemyRadius = 50, enemyX = 0, enemyY = 0;

    private double distance;

    private boolean gameOver = false;

    private int score;

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

            canvas.drawBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sky), 0, 0, null);

            this.drawPlayer(canvas);
            this.drawEnemy(canvas);
            this.drawScore(canvas);
            this.drawButtons(canvas);

            if(this.checkCollision(canvas)) {
                this.gameOver = true;
                this.stopGame(canvas);
            }



            this.holder.unlockCanvasAndPost(canvas);

            if(this.gameOver) {
                break;
            }
        }

    }

    public void init() {
        enemyRadius = enemyX = enemyY = 0;
        playerY = this.playerX = 300;
        this.playerRadius = 50;
        this.gameOver = false;
    }

    public void addScore(int points) {
        this.score += points;
    }

    private void drawButtons(Canvas canvas) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.WHITE);
        this.paint.setTextSize(30);
        canvas.drawText("RESTART", 50, 300, this.paint);

        canvas.drawText("EXIT", 50, 500, this.paint);
    }

    private void stopGame(Canvas canvas) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.RED);
        this.paint.setTextSize(100);
        canvas.drawText("GAME OVER", 400, 400, this.paint);
    }

    private void drawPlayer(Canvas canvas) {
        this.paint.setColor(Color.GREEN);
//        canvas.drawCircle(this.playerX, this.playerY, this.playerRadius, this.paint);
        canvas.drawBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.nave), this.playerX-50, this.playerY-50, null);
    }

    public void resume() {
        this.running = true;
        this.render = new Thread(this);
        this.render.start();
    }

    public void moveDown(int pixels) {
        this.playerY += pixels;
    }

    private void drawEnemy(Canvas canvas) {
        this.paint.setColor(Color.GRAY);
        this.enemyRadius += 5;
        canvas.drawCircle(this.enemyX,this.enemyY,this.enemyRadius, this.paint);
    }

    private boolean checkCollision(Canvas canvas) {
        this.distance = Math.pow(this.playerY - this.enemyY, 2) + Math.pow(this.playerX - this.enemyX, 2);
        this.distance = Math.sqrt(this.distance);

        if(this.distance <= playerRadius + enemyRadius) {
            return true;
        }
        return false;
    }

    private void drawScore(Canvas canvas) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.WHITE);
        this.paint.setTextSize(50);
        canvas.drawText(String.valueOf(this.score), 50, 200, this.paint);
    }
}
