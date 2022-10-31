package il.ac.hit.cyberrushhour;

public class GameThread extends Thread{
    private GameScreen gameScreen;

    public GameThread(GameScreen gS){
        gameScreen = gS;
    }

    @Override
    public void run() {
        super.run();
        long FPS = 25;
        long FRAME_TIME = 1000/FPS*1000000L;
        long startTime = System.nanoTime();
        long deltaTime;
        long sleepTime;
        while(true){
            gameScreen.tryDraw(gameScreen.getHolder());
            deltaTime = System.nanoTime() -startTime;
            sleepTime = FRAME_TIME - deltaTime;
            if(sleepTime>0){
                try{
                    sleep(sleepTime);
                }catch (Exception e){
                }
            }
            startTime = System.nanoTime();
        }
    }
}
