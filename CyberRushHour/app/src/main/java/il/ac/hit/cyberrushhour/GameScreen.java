package il.ac.hit.cyberrushhour;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {
    GameWorld gameWorld;
    GameThread gameThread;
    int difficulty;

    public GameScreen(Context context) {
        super(context);

    }

    public GameScreen(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public GameScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public void tryDraw(SurfaceHolder holder){
        Canvas canvas = holder.lockCanvas();
        if(canvas!=null){
            draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        gameWorld.renderWorld(canvas);

    }

    public void init(){
        gameWorld = new GameWorld(getContext(), difficulty);
        setOnTouchListener(gameWorld);
        getHolder().addCallback(this);
        gameThread = new GameThread(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setWillNotDraw(false);
        gameWorld.setResolution(getWidth(),getHeight());
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
}
