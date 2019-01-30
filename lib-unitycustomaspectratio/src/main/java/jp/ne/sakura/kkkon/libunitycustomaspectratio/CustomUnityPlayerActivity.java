package jp.ne.sakura.kkkon.libunitycustomaspectratio;

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

import com.unity3d.player.UnityPlayer;

//import android.support.annotation.Nullable;

public class CustomUnityPlayerActivity
    //extends Activity
    extends com.unity3d.player.UnityPlayerActivity
{
    private static final String TAG = "UnityCustomAspectRatio";

    final float     mCustomAspectRatio = 1.775f;
    LinearLayout      mLayout = null;
    View              mViewLetterBoxLeft = null;
    View              mViewLetterBoxRight = null;

    @Override
    protected void onCreate(
        //@Nullable
        Bundle savedInstanceState
    )
    {
        super.onCreate(savedInstanceState);

        {
            final WindowManager wm = getWindowManager();
            if ( null != wm )
            {
                final Display display = wm.getDefaultDisplay();
                if ( null != display )
                {
                    final Point point = new Point();
                    if ( 17 <= Build.VERSION.SDK_INT )
                    {
                        display.getRealSize(point);
                        final Point pointSize = new Point();
                        display.getSize(pointSize);
                        Log.d( TAG, "pointSize.x=" + pointSize.x + ",pointSize.y=" + pointSize.y );
                    }
                    else
                    if ( 13 <= Build.VERSION.SDK_INT )
                    {
                        display.getSize(point);
                    }
                    else
                    {
                        point.x = display.getWidth();
                        point.y = display.getHeight();
                    }

                    float fDisplayAspectRatio = 1.86f;
                    boolean isLandscape = false;
                    if ( point.y < point.x )
                    {
                        isLandscape = true;
                    }
                    Log.d( TAG, "point.x=" + point.x + ",point.y=" + point.y );
                    fDisplayAspectRatio = ((float)Math.max(point.x,point.y))/((float)Math.min(point.x,point.y));
                    Log.d( TAG, "fDisplayAspectRatio=" + fDisplayAspectRatio );

                    if ( mCustomAspectRatio < fDisplayAspectRatio )
                    {
                        mLayout = new LinearLayout(this);

                        mViewLetterBoxLeft = new View(this);
                        mViewLetterBoxRight = new View(this);
                        mViewLetterBoxLeft.setBackgroundColor(Color.argb(0xff,0xff,0x00,0x00) );
                        mViewLetterBoxRight.setBackgroundColor(Color.argb(0xff,0x00,0xff,0x00) );

                        if ( isLandscape )
                        {
                            mLayout.setOrientation( LinearLayout.HORIZONTAL );
                            final int width = (point.x-(int)(point.y*mCustomAspectRatio))/2;
                            Log.d( TAG, "width=" + width );
                            mViewLetterBoxLeft.setLayoutParams( new LinearLayout.LayoutParams(width, point.y) );
//                            mViewLetterBoxLeft.setTop( 0 );
//                            mViewLetterBoxLeft.setBottom( point.y );
//                            mViewLetterBoxLeft.setLeft( 0 );
//                            mViewLetterBoxLeft.setRight( width );
                            mViewLetterBoxRight.setLayoutParams( new LinearLayout.LayoutParams(width, point.y) );
//                            mViewLetterBoxRight.setTop( 0 );
//                            mViewLetterBoxRight.setBottom( point.y );
//                            mViewLetterBoxRight.setLeft( point.x-width );
//                            mViewLetterBoxRight.setRight( point.x );
                            super.mUnityPlayer.setLayoutParams( new LinearLayout.LayoutParams(point.x-(width*2), point.y) );
                        }
                        else
                        {
                            mLayout.setOrientation( LinearLayout.VERTICAL );
                            final int height  = (point.y-(int)(point.x*mCustomAspectRatio))/2;
                            Log.d( TAG, "height=" + height );
                            mViewLetterBoxLeft.setLayoutParams( new LinearLayout.LayoutParams(point.x, height) );
//                            mViewLetterBoxLeft.setTop( 0 );
//                            mViewLetterBoxLeft.setBottom( height );
//                            mViewLetterBoxLeft.setLeft( 0 );
//                            mViewLetterBoxLeft.setRight( point.x );
                            mViewLetterBoxRight.setLayoutParams( new LinearLayout.LayoutParams(point.x, height) );
//                            mViewLetterBoxRight.setTop( point.y - height );
//                            mViewLetterBoxRight.setBottom( point.y );
//                            mViewLetterBoxRight.setLeft( 0 );
//                            mViewLetterBoxRight.setRight( point.x );
                            super.mUnityPlayer.setLayoutParams( new LinearLayout.LayoutParams(point.x, point.y-(height*2)) );
                        }
                        mLayout.setLayoutParams(
                                new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                        , LinearLayout.LayoutParams.WRAP_CONTENT
                                )
                        );
                        mLayout.addView( mViewLetterBoxLeft );
                        final ViewGroup viewParent = (ViewGroup)super.mUnityPlayer.getParent();
                        if ( null != viewParent )
                        {
                            viewParent.removeView( super.mUnityPlayer );
                        }
                        mLayout.addView( super.mUnityPlayer );
                        mLayout.addView( mViewLetterBoxRight );

                        setContentView( mLayout );
                    }
                }
            }
        }
    }

    @Override
    protected void onStart()
    {
        Log.d( TAG, "onStart" );
        super.onStart();
        if ( null != mLayout )
        {
            final Point point = new Point();
            point.x = mLayout.getWidth();
            point.y = mLayout.getHeight();
            Log.d( TAG, "point.x=" + point.x + ",point.y=" + point.y );
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        Log.d( TAG, "onWindowFocusChanged enter" );
        super.onWindowFocusChanged(hasFocus);
        if ( hasFocus )
        {
            resizeCustomBox();
        }
        Log.d( TAG, "onWindowFocusChanged leave" );
    }

    @Override
    public void onConfigurationChanged(Configuration configuration)
    {
        Log.d( TAG, "onConfigurationChanged enter" );
        super.onConfigurationChanged(configuration);
        resizeCustomBox();
        Log.d( TAG, "onConfigurationChanged enter" );
    }



    private void resizeCustomBox()
    {
//            {
//                final View view = getWindow().getDecorView();
//                final Point point = new Point();
//                point.x = view.getWidth();
//                point.y = view.getHeight();
//                Log.d( TAG, "decorView.w=" + point.x + ",decorView.h=" + point.y );
//            }
//            if ( null != mLayout )
//            {
//                final Point point = new Point();
//                point.x = mLayout.getWidth();
//                point.y = mLayout.getHeight();
//                Log.d( TAG, "point.x=" + point.x + ",point.y=" + point.y );
//            }

        {
            final View view = getWindow().getDecorView();
            final Point point = new Point();
            point.x = view.getWidth();
            point.y = view.getHeight();
            Log.d( TAG, "decorView.w=" + point.x + ",decorView.h=" + point.y );

            float fDisplayAspectRatio = 1.86f;
            boolean isLandscape = false;
            if ( point.y < point.x )
            {
                isLandscape = true;
            }
            {
                final int orientation = this.getResources().getConfiguration().orientation;
                Log.d( TAG, "orientation=" + orientation );
                if ( android.content.res.Configuration.ORIENTATION_LANDSCAPE == orientation )
                {
                    isLandscape = true;
                }
                else
                if ( android.content.res.Configuration.ORIENTATION_PORTRAIT == orientation )
                {
                    isLandscape = false;
                }
            }
            Log.d( TAG, "isLandscape=" + isLandscape );
            Log.d( TAG, "point.x=" + point.x + ",point.y=" + point.y );
            fDisplayAspectRatio = ((float)Math.max(point.x,point.y))/((float)Math.min(point.x,point.y));
            Log.d( TAG, "fDisplayAspectRatio=" + fDisplayAspectRatio );

            if ( mCustomAspectRatio < fDisplayAspectRatio )
            {
                if ( isLandscape )
                {
                    mLayout.setOrientation( LinearLayout.HORIZONTAL );
                    final int sizeLong = (point.y<point.x)?(point.x):(point.y);
                    final int sizeShort = (point.y<point.x)?(point.y):(point.x);
                    final int width = (sizeLong-(int)(sizeShort*mCustomAspectRatio))/2;
                    Log.d( TAG, "width=" + width );
                    mViewLetterBoxLeft.setLayoutParams( new LinearLayout.LayoutParams(width, sizeShort) );
                    mViewLetterBoxRight.setLayoutParams( new LinearLayout.LayoutParams(width, sizeShort) );
                    super.mUnityPlayer.setLayoutParams( new LinearLayout.LayoutParams(sizeLong-(width*2), sizeShort) );
                }
                else
                {
                    mLayout.setOrientation( LinearLayout.VERTICAL );
                    final int sizeLong = (point.x<point.y)?(point.y):(point.x);
                    final int sizeShort = (point.x<point.y)?(point.x):(point.y);
                    final int height  = (sizeLong-(int)(sizeShort*mCustomAspectRatio))/2;
                    Log.d( TAG, "height=" + height );
                    mViewLetterBoxLeft.setLayoutParams( new LinearLayout.LayoutParams(sizeShort, height) );
                    mViewLetterBoxRight.setLayoutParams( new LinearLayout.LayoutParams(sizeShort, height) );
                    super.mUnityPlayer.setLayoutParams( new LinearLayout.LayoutParams(sizeShort, sizeLong-(height*2)) );
                }
                //mLayout.invalidate();
            }
        }
    }

}
