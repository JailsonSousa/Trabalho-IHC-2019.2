package br.com.ufc.darkadventures.Util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;


public class PlayerBoss {

    private static MediaPlayer playerBoss;
    private final static float volume = 0.7F;

    public static void addBossAudio(Context context, int resId) {
        if(playerBoss != null) playerBoss = null;
        playerBoss = MediaPlayer.create(context, resId);
    }

    public static void play(){
        // 2x volume = lef and right
        //playerBoss.setVolume(volume, volume);
        playerBoss.start();

        playerBoss.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
    }

    public static int playerDuration(){
        return playerBoss.getDuration();
    }

    public static void stopPlay() {
        if (playerBoss != null) {
            playerBoss.release();
            playerBoss = null;
        }
    }

}
