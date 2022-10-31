package il.ac.hit.cyberrushhour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private Context context;
    GameScreen gameScreen;
    TextView timerText;
    Timer timer;
    Bundle bundle;
    int seconds = 0, minutes = 0;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_screen);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        context = getApplicationContext();
        gameScreen = findViewById(R.id.gameSurfaceViewID);
        timerText = findViewById(R.id.timer_text_id);
        //gameScreen.gameWorld.difficulty = bundle.getInt("Difficulty");
        gameScreen.setDifficulty(bundle.getInt("Difficulty"));
        gameScreen.init();
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (seconds == 60) {
                            timerText.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                            minutes += seconds / 60;
                            seconds = seconds % 60;
                        }
                        seconds += 1;
                        timerText.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                        gameScreen.gameWorld.time = timerText.getText().toString();
                    }
                });
            }
        }, 0, 1000);
    }




    public void giveUp(View v){
        finish();
    }
}
