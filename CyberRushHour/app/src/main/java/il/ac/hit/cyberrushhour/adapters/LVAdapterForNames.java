package il.ac.hit.cyberrushhour.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import il.ac.hit.cyberrushhour.HighScoresActivity;
import il.ac.hit.cyberrushhour.R;

public class LVAdapterForNames extends ArrayAdapter<String> {

    public LVAdapterForNames(@NonNull Context context, ArrayList<String> listOfLevelNames){
        super(context,0, listOfLevelNames);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.levelname_listview_item,
                    parent,false);
        }

        String string = getItem(position);
        TextView levelName = listItemView.findViewById(R.id.level_name_item_id);
        levelName.setText(string);

        listItemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HighScoresActivity.class);
                intent.putExtra("Name",string);
                getContext().startActivity(intent);
            }
        });
        return listItemView;
    }
}
