package br.com.ufc.darkadventures.Util;

import android.content.Context;
import android.media.MediaPlayer;


public class PlayerEffect {

    private static MediaPlayer playerEffect;
    private final static float volume = 0.6F;

    public static void addEffect(Context context, int resId) {
        if(playerEffect != null) playerEffect = null;
        playerEffect = MediaPlayer.create(context, resId);
    }

    public static void play(){
        // 2x volume = lef and right
        playerEffect.setVolume(volume, volume);
        playerEffect.start();

        playerEffect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
    }

    public static int playerDuration(){
        return playerEffect.getDuration();
    }

    public static void stopPlay() {
        if (playerEffect != null) {
            playerEffect.release();
            playerEffect = null;
        }
    }

}
