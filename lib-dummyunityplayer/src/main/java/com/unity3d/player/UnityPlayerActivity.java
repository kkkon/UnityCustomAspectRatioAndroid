package com.unity3d.player;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

//import android.support.annotation.Nullable;

public class UnityPlayerActivity
    extends android.app.Activity
{
    private static final String TAG = "UnityPlayerActivity";

    protected UnityPlayer mUnityPlayer;

    @Override
    protected void onCreate(
        //@Nullable
        Bundle savedInstanceState
    )
    {
        super.onCreate(savedInstanceState);

        mUnityPlayer = new UnityPlayer(this);
        mUnityPlayer.currentActivity = this;

        setContentView( mUnityPlayer );

        setFullScreen();
        /*
        {
            final View decorView = getWindow().getDecorView();
            if ( 11 <= Build.VERSION.SDK_INT )
            {
                final int current = decorView.getSystemUiVisibility(); // API11 getSystemUiVisibility
                int target = current;
                if ( 19 <= Build.VERSION.SDK_INT )
                {
                    target = (target | (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY));
                }
                if ( 16 <= Build.VERSION.SDK_INT )
                {
                    target = (target | (View.SYSTEM_UI_FLAG_FULLSCREEN));
                    //target = (target | (View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN));
                }
                if ( 14 <= Build.VERSION.SDK_INT )
                {
                    target = (target | (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION));
                }
                decorView.setSystemUiVisibility( target ); // API11 setSystemUiVisibility
            }
            else
            {
                getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
            }
        }
        */
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if ( hasFocus )
        {
            setFullScreen();
        }
    }

    private void setFullScreen()
    {
        {
            final View decorView = getWindow().getDecorView();
            if ( 11 <= Build.VERSION.SDK_INT )
            {
                final int current = decorView.getSystemUiVisibility(); // API11 getSystemUiVisibility
                int target = current;
                if ( 19 <= Build.VERSION.SDK_INT )
                {
                    target = (target | (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY));
                }
                if ( 16 <= Build.VERSION.SDK_INT )
                {
                    target = (target | (View.SYSTEM_UI_FLAG_FULLSCREEN));
                    target = (target | (View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN));
                }
                if ( 14 <= Build.VERSION.SDK_INT )
                {
                    target = (target | (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION));
                }
                decorView.setSystemUiVisibility( target ); // API11 setSystemUiVisibility

                if ( 16 <= Build.VERSION.SDK_INT )
                {
                    final android.app.ActionBar actionBar = this.getActionBar();
                    if ( null != actionBar )
                    {
                        actionBar.hide();
                    }
                }
            }
            else
            {
                getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
            }
        }
    }
}
