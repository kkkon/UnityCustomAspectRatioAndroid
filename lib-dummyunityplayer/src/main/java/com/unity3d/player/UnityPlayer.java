package com.unity3d.player;

public class UnityPlayer
    extends android.widget.FrameLayout
{
    private static final String TAG = "UnityPlayer";

    public static android.app.Activity currentActivity;

    public UnityPlayer(
        //@android.support.annotation.NonNull
        android.content.Context context
    )
    {
        super(context);

        this.setBackgroundColor( android.graphics.Color.argb(0xff,0x00,0x00,0xff) );
    }

    public static void UnitySendMessage(String s1, String s2, String s3)
    {
    }
}
