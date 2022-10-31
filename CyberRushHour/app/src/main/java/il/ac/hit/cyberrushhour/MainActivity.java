package il.ac.hit.cyberrushhour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToGameScene(View view){
        Intent intent = new Intent(this, LevelPickActivity.class);
        startActivity(intent);
    }

    public void switchToHighScoresLevelList(View v){
        Intent intent = new Intent(this, HighScoreLevelsListActivity.class);
        startActivity(intent);
    }

    public void quitGame(View v){
        finish();
        System.exit(0);
    }

}