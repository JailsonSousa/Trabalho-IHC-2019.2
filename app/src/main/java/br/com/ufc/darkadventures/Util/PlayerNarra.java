package br.com.ufc.darkadventures.Util;

import android.content.Context;
import android.media.MediaPlayer;


public class PlayerNarra {

    private static MediaPlayer playerNarra;
    private final static float volume = 0.3F;

    public static void addNarra(Context context, int resId) {
        if(playerNarra != null) playerNarra = null;
        playerNarra = MediaPlayer.create(context, resId);
    }

    public static void play(){
        // 2x volume = lef and right
        playerNarra.setVolume(volume, volume);
        playerNarra.start();

        playerNarra.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
    }

    public static int playerDuration(){
        return playerNarra.getDuration();
    }

    public static void stopPlay() {
        if (playerNarra != null) {
            playerNarra.release();
            playerNarra = null;
        }
    }

}
