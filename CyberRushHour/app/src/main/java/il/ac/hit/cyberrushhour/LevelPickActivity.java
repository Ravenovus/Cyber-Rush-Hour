package il.ac.hit.cyberrushhour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelPickActivity extends AppCompatActivity {
    GameScreen gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_pick);

    }

    public void pickEasy(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Difficulty", 1);
        finish();
        startActivity(intent);
    }
    public void pickMedium(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Difficulty", 2);
        finish();
        startActivity(intent);
    }
    public void pickHard(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Difficulty", 3);
        finish();
        startActivity(intent);
    }

}