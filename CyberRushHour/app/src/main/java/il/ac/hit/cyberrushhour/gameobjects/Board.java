package il.ac.hit.cyberrushhour.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import il.ac.hit.cyberrushhour.common.Common;

public class Board {
    private Paint paint;
    private Bitmap tileBackground;

    private int size;
    private Rect blockRect;
    private int x,y,width;



    public Board(Context context, int size){
        paint = new Paint();
        this.size = size;
        blockRect = new Rect();
        tileBackground = Common.getBitmapFromAsset(context,"RoadBlock.png");


    }

    public void paintBoard(Canvas canvas){
        int cellWidth = width/ size;
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                blockRect.left = col * cellWidth + x;
                blockRect.top = row * cellWidth + y;
                blockRect.right = blockRect.left + cellWidth;
                blockRect.bottom = blockRect.top+cellWidth;
                canvas.drawBitmap(tileBackground,null,blockRect,paint);
            }
        }

    }

    public void setBound(int x, int y, int width){
        this.x = x;
        this.y = y;
        this.width = width;
    }
}
