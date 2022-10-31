package il.ac.hit.cyberrushhour;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import il.ac.hit.cyberrushhour.adapters.LVAdapterForScores;

public class HighScoresActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ListView highScoreLV;
    ArrayList<HighScoreObject> listOfHighScores;
    String failedToLoad;
    Intent intent;
    String levelName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        failedToLoad = getResources().getString(R.string.failed_to_load);
        highScoreLV = findViewById(R.id.highScore_LV_id);
        db = FirebaseFirestore.getInstance();
        listOfHighScores = new ArrayList<>();
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        levelName = bundle.getString("Name");

        loadDataInListView();


    }

    private void loadDataInListView() {
        listOfHighScores.removeAll(listOfHighScores);
        db.collection(levelName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d: list){
                        HighScoreObject highscore = new HighScoreObject(d.get("User").toString()
                                ,d.get("Time").toString());
                        listOfHighScores.add(highscore);
                    }
                    LVAdapterForScores lvAdapterForScores = new LVAdapterForScores(
                            HighScoresActivity.this, listOfHighScores);
                    highScoreLV.setAdapter(lvAdapterForScores);
                } else {
                    LVAdapterForScores lvAdapterForScores = new LVAdapterForScores(
                            HighScoresActivity.this, listOfHighScores);
                    highScoreLV.setAdapter(lvAdapterForScores);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HighScoresActivity.this,failedToLoad,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
