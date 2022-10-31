package il.ac.hit.cyberrushhour;

import java.util.HashMap;
import java.util.Map;

public class HighScoreObject {

    Map<String, String> highScore;

    public HighScoreObject(String name, String time){
        highScore = new HashMap<>();
        highScore.put("User", name);
        highScore.put("Time", time);

    }
    //Standard Constructor for Firebase
    public HighScoreObject(){}

    public Map<String,String> getHighScore(){return highScore;}
}
