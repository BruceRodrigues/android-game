package spaceship.game.bruce.br.spaceship;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import spaceship.game.bruce.br.spaceship.spaceship.game.bruce.br.spaceship.loop.GameLoop;


public class SpaceshipGame extends ActionBarActivity {

    private GameLoop view;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.view = new GameLoop(this);

        this.setContentView(this.view);
    }
}