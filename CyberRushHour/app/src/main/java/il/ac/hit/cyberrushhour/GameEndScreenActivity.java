package il.ac.hit.cyberrushhour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class GameEndScreenActivity extends AppCompatActivity {
    FirebaseFirestore db;
    TextView levelName, timeSpent;
    String timeString, levelString;
    TextView userName;
    Intent intent;
    Context context;
    String missingName;
    int toastDuration = Toast.LENGTH_SHORT;
    HighScoreObject highscore;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_screen);
        intent = getIntent();
        db = FirebaseFirestore.getInstance();
        levelName = findViewById(R.id.level_name_textView);
        timeSpent = findViewById(R.id.time_spent_textbox);
        userName = findViewById(R.id.player_name_textbox);
        context = getApplicationContext();
        Bundle bundle = intent.getExtras();
        missingName = getResources().getString(R.string.missing_name_string);
        timeString = bundle.getString("Time");
        levelString = bundle.getString("Level");
        levelName.setText(levelString);
        timeSpent.setText(timeString);
    }

    public void backToMenu(View v){
        finish();
    }

    public void saveAndBackToMenu(View v) {
        String playerName = userName.getText().toString();
        if (playerName.isEmpty()) {
            Toast toast = Toast.makeText(context, missingName, toastDuration);
            toast.show();
            return;
        }
        highscore = new HighScoreObject(playerName, timeString);
        db.collection(levelString).document(timeString + "_" + playerName)
                .set(highscore.getHighScore()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
        finish();
    }

}
