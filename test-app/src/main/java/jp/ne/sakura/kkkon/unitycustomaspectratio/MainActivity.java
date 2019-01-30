package jp.ne.sakura.kkkon.unitycustomaspectratio;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import jp.ne.sakura.kkkon.libunitycustomaspectratio.CustomUnityPlayerActivity;

public class MainActivity
    extends CustomUnityPlayerActivity
    //extends AppCompatActivity
    //extends com.unity3d.player.UnityPlayerActivity
{
    private static final String TAG = "kkUnityLetterBox";

    final float mCustomAspectRatio = 1.775f;
    LinearLayout        mLayout = null;
    View                mViewLetterBoxLeft = null;
    View                mViewLetterBoxRight = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
