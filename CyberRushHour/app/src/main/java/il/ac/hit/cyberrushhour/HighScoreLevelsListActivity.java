package il.ac.hit.cyberrushhour;

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

import il.ac.hit.cyberrushhour.adapters.LVAdapterForNames;

public class HighScoreLevelsListActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ListView levelListLV;
    ArrayList<String> listOfLevelNames;
    String failedToLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levellist_high_scores);
        levelListLV = findViewById(R.id.level_select_list);
        failedToLoad = getResources().getString(R.string.failed_to_load);
        db = FirebaseFirestore.getInstance();
        listOfLevelNames = new ArrayList<>();
        
        loadDataInListView();

    }

    private void loadDataInListView() {
        listOfLevelNames.removeAll(listOfLevelNames);
        db.collection("levels").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d: list){
                        listOfLevelNames.add(d.getId());
                    }
                    LVAdapterForNames lvAdapterForNames = new LVAdapterForNames(HighScoreLevelsListActivity.this,listOfLevelNames);
                    levelListLV.setAdapter(lvAdapterForNames);
                } else{
                    LVAdapterForNames lvAdapterForNames = new LVAdapterForNames(HighScoreLevelsListActivity.this,listOfLevelNames);
                    levelListLV.setAdapter(lvAdapterForNames);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HighScoreLevelsListActivity.this,failedToLoad,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
