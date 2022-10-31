package il.ac.hit.cyberrushhour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;




import java.util.ArrayList;


import il.ac.hit.cyberrushhour.common.Common;
import il.ac.hit.cyberrushhour.gameobjects.Board;
import il.ac.hit.cyberrushhour.gameobjects.Car;

public class GameWorld implements View.OnTouchListener{
    private LevelsFactory levelsFactory;
    private Paint paint;
    private Board board;
    private int deviceWidth,deviceHeight;
    private int backgroundColor = Color.parseColor("#090962");
    private Bitmap tileBackground, tileMainCar, tileShortHorizontalCar, tileShortVerticalCar;
    private Bitmap tileLongHorizontalCar, tileLongVerticalCar;
    public int matrix[][];
    private Context context;
    public String time;
    private String levelName;
    private int difficulty;

    int x,y,width;



    private Car mainCar;

    private ArrayList<Car> cars;

    public GameWorld(Context context, int difficulty){
        levelsFactory = new LevelsFactory();
        board = new Board(context, 6);
        paint = new Paint();
        this.difficulty = difficulty;
        this.context = context;
        tileBackground = Common.getBitmapFromAsset(context,"RoadBlock.png");
        tileMainCar = Common.getBitmapFromAsset(context,"TheRedCar.png");
        tileShortHorizontalCar = Common.getBitmapFromAsset(context,"TheOtherCarHorizontalTwo.png");
        tileShortVerticalCar = Common.getBitmapFromAsset(context,"TheOtherCarVerticalTwo.png");
        tileLongHorizontalCar = Common.getBitmapFromAsset(context,"TheBiggerOtherCarHorizontal.png");
        tileLongVerticalCar = Common.getBitmapFromAsset(context,"TheBiggerOtherCarVertical.png");

        cars = LevelPicker(levelsFactory);
        matrix = new int[6][6];

    }

    private ArrayList<Car> LevelPicker(LevelsFactory levelsFactory) {
        switch (difficulty){
            case(1):
                levelName = "l1";
                return levelsFactory.CallForEasy();
            case(2):
                levelName = "l2";
                return levelsFactory.CallForMedium();
            case(3):
                levelName = "l3";
                return levelsFactory.CallForHard();
        }
        return levelsFactory.CallForEasy();
    }


    public void renderWorld(Canvas canvas){
        paint.setColor(backgroundColor);
        canvas.drawRect(0,0,deviceWidth, deviceHeight, paint);
        board.paintBoard(canvas);

        for (Car vehicle: cars
             ) {vehicle.Draw(canvas);
        }
    }

    public void setResolution(int deviceWidth, int deviceHeight){
        this.deviceHeight = deviceHeight;
        this.deviceWidth = deviceWidth;
        x = (int) (deviceWidth * 0.1f);
        y = (int) (deviceWidth * 0.1f);
        width = (int) (deviceWidth * 0.8f);
        board.setBound(x,y,width);

        if(!cars.isEmpty()) {
            HandleCars();
        }

    }

    private void HandleCars() {
        for (Car vehicle:cars
             ) {
            vehicle.setBlockWidth(width/6);
            vehicle.setCarImage(setTile(vehicle.getType()));
            vehicle.setX((width/6)*vehicle.getGridX()+ x);
            vehicle.setY((width/6)*vehicle.getGridY()+ y);
            if(vehicle.getType() == 0){
                mainCar = vehicle;
            }

        }

        for (Car vehicle:cars
              ) {
             for(int i=0;i<vehicle.getWidth();i++){
                 for(int j=0;j<vehicle.getHeight();j++){
                     matrix[i+vehicle.getGridX()][j+vehicle.getGridY()] = 1;
                 }
             }
         }
    }

    private Bitmap setTile(int type) {
        switch(type){
            case(0):
                return tileMainCar;
            case(1):
                return tileShortHorizontalCar;
            case(2):
                return tileShortVerticalCar;
            case(3):
                return tileLongHorizontalCar;
            case(4):
                return tileLongVerticalCar;
        }
        return null;
    }

    Car tempCar = null;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Car vehicle:cars
                 ) {
                if (vehicle.isInArea((int) event.getX(), (int) event.getY())) {
                    tempCar = vehicle;
                    break;
                }
            }
            if(tempCar != null){
                tempCar.handleMovement(matrix, event.getX(), event.getY());
            }
        }else if(event.getAction() == MotionEvent.ACTION_UP) {
            if(tempCar!=null) {
                tempCar.setMovable();
            }
            tempCar = null;
            checkWinCondition();
            v.invalidate();
        }
        return true;
    }

    public void checkWinCondition(){
        if(matrix[4][2]==1 && matrix[5][2] == 1){
            if(mainCar.getGridX() ==4 && mainCar.getGridY() == 2){
                Log.d("mytag","Win condition met");
                Intent intent = new Intent(context, GameEndScreenActivity.class);
                intent.putExtra("Time", time);
                intent.putExtra("Level", levelName);
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        }
    }



}

