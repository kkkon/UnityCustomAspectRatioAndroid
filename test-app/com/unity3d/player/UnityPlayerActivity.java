package com.unity3d.player;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class UnityPlayerActivity
    extends Activity
{
    protected UnityPlayer mUnityPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // immersive mode
        mUnityPlayer = new UnityPlayer( this );
    }
}
