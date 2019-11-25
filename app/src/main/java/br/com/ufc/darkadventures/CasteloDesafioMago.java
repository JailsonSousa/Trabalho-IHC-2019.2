package br.com.ufc.darkadventures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.GestureDetector;

import br.com.ufc.darkadventures.Util.PlayerBoss;
import br.com.ufc.darkadventures.Util.PlayerEffect;
import br.com.ufc.darkadventures.Util.PlayerMusic;
import br.com.ufc.darkadventures.Util.PlayerNarra;

public class CasteloDesafioMago extends AppCompatActivity {

    private Vibrator v;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castelo_desafio_final);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        PlayerMusic.addMusic(this, R.raw.mago_invoca_portal);
        PlayerNarra.addNarra(this, R.raw.mago_portal);
        PlayerMusic.removeLoop();
        PlayerMusic.play();

        v.vibrate(4000);

        try {
            Thread.sleep(2000);
            PlayerNarra.play();
            //Thread.sleep(2000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        handler.postDelayed(new Runnable() {
            public void run() {

                try {
                    PlayerMusic.stopPlay();
                    PlayerNarra.addNarra(getApplicationContext() , R.raw.mago_intro);
                    PlayerMusic.addMusic(getApplicationContext(), R.raw.music_intro_mago);
                    Thread.sleep(1000);
                    PlayerMusic.play();
                    PlayerNarra.play();
                    Thread.sleep(PlayerNarra.playerDuration());
                    PlayerMusic.stopPlay();
                    Intent i = new Intent(getApplicationContext(), CasteloChefeMago.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        }, PlayerMusic.playerDuration());

        PlayerBoss.addBossAudio(this, R.raw.guardian_last_atk);

    }
}
