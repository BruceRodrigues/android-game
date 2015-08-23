package spaceship.game.bruce.br.spaceship;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import spaceship.game.bruce.br.spaceship.spaceship.game.bruce.br.spaceship.loop.GameLoop;


public class SpaceshipGame extends ActionBarActivity implements View.OnTouchListener {

    private GameLoop view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.view = new GameLoop(this);

        this.view.setOnTouchListener(this);

        this.setContentView(this.view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.view.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.view.moveDown(10);
        this.view.addScore(100);
        return true;
    }
}
