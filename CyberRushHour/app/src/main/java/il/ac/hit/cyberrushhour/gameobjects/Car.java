package il.ac.hit.cyberrushhour.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


/** car types are as follow:
 * 0 - main car
 * 1 - short horizontal car
 * 2 - short vertical car
 * 3 - long horizontal car
 * 4 - long vertical car
 */

public class Car {
    private int x,y,width,height, type;
    private Bitmap carImage;
    private int blockWidth;
    private boolean movable = true;
    private int gridX,gridY;

    public int getGridX() {
        return gridX;
    }
    public void setGridX(int gridX) {
        this.gridX = gridX;
    }
    public int getGridY() {
        return gridY;
    }
    public void setGridY(int gridY) {
        this.gridY = gridY;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public Bitmap getCarImage() {
        return carImage;
    }
    public void setCarImage(Bitmap carImage) {
        this.carImage = carImage;
    }
    public int getBlockWidth() {
        return blockWidth;
    }
    public void setBlockWidth(int blockWidth) {
        this.blockWidth = blockWidth;
    }

    Paint paint;


    public Car(){

        paint = new Paint();
        paint.setColor(Color.WHITE);
    }


    public void Draw(Canvas canvas) {
        Rect carRect = new Rect(x,y,x+(width*blockWidth),y+(height*blockWidth));
        canvas.drawBitmap(carImage,null,carRect,paint);

    }

    //Implement that depending on the side you click, it moves there
    public boolean isInArea(int x, int y){
        boolean result =(x >= this.x && x <= this.x+(blockWidth*width) && y >=this.y && y <= this.y +(height*blockWidth));
        if(result){
            Log.d("myTag","Entered the testing area and succeded");
        }
        else{Log.d("myTag","Entered the testing area and failed");}
        return result;
    }

    public void handleMovement(int[][] matrix, float x, float y){
        switch(type){
            case(0):
                Log.d("myTag","Handling 0");
                HandleHorizontal(matrix,x);
                break;
            case(1):
                Log.d("myTag", "case 1");
                HandleHorizontal(matrix, x);
                break;
            case(2):
                Log.d("myTag", "case 2");
                HandleVertical(matrix,y);
                break;
            case(3):
                Log.d("myTag", "case 3");
                HandleHorizontal(matrix, x);
                break;
            case(4):
                Log.d("myTag", "case 4");
                HandleVertical(matrix, y);
                break;
        }

    }

    private void HandleHorizontal(int[][] matrix, float x ) {
        if (movable) {
            if (x < (this.x + (this.x + (width * blockWidth))) / 2) {
                MoveLeft(matrix);
                return;
            } else {
                MoveRight(matrix);
                return;
            }
        }
    }

    private void MoveRight(int[][] matrix) {

        if (this.x + (width * blockWidth) + blockWidth >= (7 * blockWidth)
                || matrix[gridX +width ][gridY] == 1)
        {return;}

        Log.d("mytag","Updating the matrix at " +(gridX + width )+" "+(gridY));
        matrix[gridX + width][gridY] = 1;
        this.x = this.x + blockWidth;
        Log.d("mytag","Updating the matrix at " +(gridX)+" "+(gridY));
        matrix[gridX][gridY] = 0;
        gridX++;
        movable = false;
        return;

    }

    private void MoveLeft(int[][] matrix) {
        if (this.x - blockWidth <= 0 ||matrix[gridX - 1][gridY] == 1) {
            return;
        }
            Log.d("mytag","Updating the matrix at " +(gridX - 1)+" "+gridY);
            matrix[gridX - 1][gridY] =  1;
            this.x = this.x - blockWidth;
            Log.d("mytag","Updating the matrix at " +(gridX + width -1 )+" "+(gridY));
            matrix[gridX + width -1][gridY] =  0;
            gridX--;
            movable = false;
            return;

    }

    private void HandleVertical(int[][] matrix, float y) {
        if (movable) {
            if (y < (this.y + (this.y + (height * blockWidth))) / 2) {
                MoveUp(matrix);
                return;
            } else {
                MoveDown(matrix);
                return;
            }

        }
    }

    private void MoveDown(int[][] matrix) {
        if (this.y + (height * blockWidth) + blockWidth >= (7 * blockWidth)||
                matrix[gridX][gridY+height]== 1)
        { return;}
        matrix[gridX][gridY+height-1] = 1;
        this.y = this.y + blockWidth;
        matrix[gridX][gridY] = 0;
        gridY++;
        movable = false;
        return;
    }

    private void MoveUp(int[][] matrix) {
        if (this.y - blockWidth <= 0 || matrix[gridX][gridY-1]==1) {
            return;
        }
        Log.d("mytag", "updating the matrix in "+gridX+" "+(gridY-1));
        matrix[gridX][gridY-1] =  1;
        this.y = this.y - blockWidth;
        matrix[gridX][gridY+height-1] =  0;
        gridY--;
        movable = false;
        return;
    }


    public void setMovable(){
        movable =true;
    }

}
