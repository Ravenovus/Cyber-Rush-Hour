package il.ac.hit.cyberrushhour.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Common {
    public static Bitmap getBitmapFromAsset(Context context, String path){
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try{
            istr = assetManager.open(path);
            bitmap = BitmapFactory.decodeStream(istr);

        }catch (IOException e){}
        return bitmap;
    }
}
