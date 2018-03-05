package jp.ne.sakura.kkkon.test.unitycustomaspectratio;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by kkkon on 2018/03/02.
 */

public class MockUnityPlayer extends FrameLayout
{
    public MockUnityPlayer( ContextWrapper context )
    {
        super(context);
        if ( context instanceof Activity )
        {
            final Activity activity = (Activity)context;
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void quit()
    {
    }

    public void pause()
    {
    }

    public void resume()
    {
    }

    public void configurationChanged(Configuration newConfig )
    {
    }

    public void windowFocusChanged( boolean hasFocus )
    {
    }

    public void injectEvent(KeyEvent event )
    {
    }


}


