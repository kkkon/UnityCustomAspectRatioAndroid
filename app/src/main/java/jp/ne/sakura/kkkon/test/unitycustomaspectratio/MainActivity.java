package jp.ne.sakura.kkkon.test.unitycustomaspectratio;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";

    //protected UnityPlayer mUnityPlayer;
    protected MockUnityPlayer mUnityPlayer;

    static float getAspectRatio( final float width, final float height )
    {
        if ( height > width )
        {
            return height/width;
        }
        return width/height;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        final android.view.WindowManager wm = getWindowManager();
        final android.view.Display display = wm.getDefaultDisplay();
        android.util.DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
        display.getMetrics( displayMetrics );
        Log.d( TAG, "w=" + displayMetrics.widthPixels );
        Log.d( TAG, "h=" + displayMetrics.heightPixels );
        final int displayWidth = displayMetrics.widthPixels;
        final int displayHeight = displayMetrics.heightPixels;
        final float displayAspectRatio = getAspectRatio( displayWidth, displayHeight );
        Log.d( TAG, "ratio=" + displayAspectRatio );
        final float customAspectRatio = (16.0f/9.0f);

        if ( customAspectRatio < displayAspectRatio )
        {
            LinearLayout layout = new LinearLayout(this);
            View viewTop = new View(this);
            View viewBottom = new View(this);
            //mUnityPlayer = new UnityPlayer(this);
            mUnityPlayer = new MockUnityPlayer(this);

            if ( displayWidth <= displayHeight )
            {
                final int unityHeight = (int)(displayWidth * customAspectRatio);
                layout.setOrientation(LinearLayout.VERTICAL);
                final int blankHeight = (displayHeight - unityHeight)/2;
                Log.d( TAG, "blankHeight=" + blankHeight );
                viewTop.setTop( 0 );
                mUnityPlayer.setTop( blankHeight );
                viewBottom.setTop( blankHeight + unityHeight );
                final LinearLayout.LayoutParams paramsBlank = new LinearLayout.LayoutParams(displayWidth, blankHeight);
                final LinearLayout.LayoutParams paramsUnity = new LinearLayout.LayoutParams(displayWidth, unityHeight);
                viewTop.setLayoutParams( paramsBlank );
                viewBottom.setLayoutParams( paramsBlank );
                mUnityPlayer.setLayoutParams( paramsUnity );
                viewTop.setBackgroundColor(0xffff0000);
                viewBottom.setBackgroundColor(0xff00ff00);
            }
            else
            {
                final int unityWidth = (int)(displayHeight * customAspectRatio);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                final int blankWidth = (displayWidth - unityWidth)/2;
                Log.d( TAG, "blankWidth=" + blankWidth );
                viewTop.setLeft( 0 );
                mUnityPlayer.setLeft( blankWidth );
                viewBottom.setLeft( blankWidth + unityWidth );
                final LinearLayout.LayoutParams paramsBlank = new LinearLayout.LayoutParams(blankWidth, displayHeight);
                final LinearLayout.LayoutParams paramsUnity = new LinearLayout.LayoutParams(unityWidth, displayHeight);
                viewTop.setLayoutParams( paramsBlank );
                viewBottom.setLayoutParams( paramsBlank );
                mUnityPlayer.setLayoutParams( paramsUnity );
                viewTop.setBackgroundColor(0xffff0000);
                viewBottom.setBackgroundColor(0xff00ff00);
            }
            setContentView(layout);


            layout.addView( viewTop );
            layout.addView( mUnityPlayer );
            layout.addView( viewBottom );
        }
        else
        {
            //mUnityPlayer = new UnityPlayer(this);
            mUnityPlayer = new MockUnityPlayer(this);
            setContentView(mUnityPlayer);
        }

        mUnityPlayer.requestFocus();
    }
}
