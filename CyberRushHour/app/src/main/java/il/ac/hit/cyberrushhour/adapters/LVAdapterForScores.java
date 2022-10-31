package il.ac.hit.cyberrushhour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import il.ac.hit.cyberrushhour.HighScoreObject;
import il.ac.hit.cyberrushhour.R;

public class LVAdapterForScores extends ArrayAdapter<HighScoreObject> {

    public LVAdapterForScores(@NonNull Context context, ArrayList<HighScoreObject> listOfHighScores){
        super(context, 0, listOfHighScores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.highscores_listview_item,
                    parent, false);
        }
        HighScoreObject highScoreObject = getItem(position);
        TextView highScoreName = listItemView.findViewById(R.id.highscore_name_id);
        TextView highScoreTime = listItemView.findViewById(R.id.highscore_time_id);
        highScoreName.setText(highScoreObject.getHighScore().get("User"));
        highScoreTime.setText(highScoreObject.getHighScore().get("Time"));



        return listItemView;
    }

}
