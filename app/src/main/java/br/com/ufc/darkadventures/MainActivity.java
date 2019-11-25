package br.com.ufc.darkadventures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import br.com.ufc.darkadventures.Util.PlayerMusic;
import br.com.ufc.darkadventures.Util.PlayerNarra;


public class MainActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    Handler handler = new Handler();
    private boolean liberar = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = (new GestureDetector(this, new MyGestureListener(this)));


        PlayerMusic.addMusic(this, R.raw.music_intro);
        PlayerNarra.addNarra(this, R.raw.narra_intro);
        PlayerMusic.play();
        PlayerNarra.play();

        handler.postDelayed(new Runnable() {
            public void run() {
                liberar = true;
            }
        }, PlayerNarra.playerDuration());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void play(View view) {
        if (liberar) {
            liberar = false;
            PlayerNarra.addNarra(this, R.raw.start_journey);
            PlayerNarra.play();
            handler.postDelayed(new Runnable() {
                public void run() {
                    PlayerNarra.stopPlay();
                    PlayerMusic.stopPlay();
                    Intent i = new Intent(getApplicationContext(), CasteloEntrada.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                    liberar = true;
                }
            }, PlayerNarra.playerDuration());


        }
    }
}
