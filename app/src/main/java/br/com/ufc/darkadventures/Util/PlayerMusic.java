package br.com.ufc.darkadventures.Util;

import android.content.Context;
import android.media.MediaPlayer;


public class PlayerMusic{

    private static MediaPlayer playerMusic;
    private final static float volume = 0.1F;

    public static void addMusic(Context context, int resId) {
        if(playerMusic != null) playerMusic = null;
        playerMusic = MediaPlayer.create(context, resId);
    }

    public static void play(){
        // 2x volume = lef and right
        playerMusic.setVolume(volume, volume);
        playerMusic.setLooping(true);
        playerMusic.start();

        playerMusic.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
    }

    public static void removeLoop(){
        playerMusic.setLooping(false);
    }

    public  static void volume(float nivel){
        playerMusic.setVolume(nivel, nivel);
    }

    public static int playerDuration(){
        return playerMusic.getDuration();
    }

    public static void stopPlay() {
        if (playerMusic != null) {
            playerMusic.release();
            playerMusic = null;
        }
    }

}
