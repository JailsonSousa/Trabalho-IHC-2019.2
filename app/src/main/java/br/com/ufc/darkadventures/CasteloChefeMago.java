package br.com.ufc.darkadventures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import br.com.ufc.darkadventures.Util.PlayerBoss;
import br.com.ufc.darkadventures.Util.PlayerEffect;
import br.com.ufc.darkadventures.Util.PlayerMusic;
import br.com.ufc.darkadventures.Util.PlayerNarra;

public class CasteloChefeMago extends AppCompatActivity {

    private Vibrator v;
    Handler handler = new Handler();
    public boolean liberar = false;
    public boolean special = false;
    private int vidaMAgo = 300;
    FrameLayout fl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castelo_chefe_mago);

        init();

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        PlayerMusic.addMusic(this, R.raw.music_boss_wizard);
        PlayerNarra.addNarra(this, R.raw.info_atk);
        PlayerBoss.addBossAudio(this, R.raw.wizard_begin);
        //PlayerEffect.addEffect(this, R.raw.wiz);

        PlayerMusic.play();
        PlayerNarra.play();

        handler.postDelayed(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(10);
                    PlayerBoss.play();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, PlayerNarra.playerDuration());

    }

    public void init() {
        fl = findViewById(R.id.frameClickMago);

        fl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(liberar){
                    Toast.makeText(getApplicationContext(), "Defesa...", Toast.LENGTH_SHORT).show();
                    v.vibrate(1000);
                    PlayerNarra.addNarra(getApplicationContext(), R.raw.wizard_defense_success);
                    PlayerNarra.play();
                    liberar = false;
                }
                return false;
            }
        });
    }

    public void onPress(View view){

        vidaMAgo --;
        v.vibrate(50);
        if(vidaMAgo == 250){
            PlayerNarra.addNarra(this, R.raw.wizard_will_atk);

            PlayerBoss.addBossAudio(getApplicationContext(), R.raw.wizard_laugh);
            PlayerEffect.addEffect(getApplicationContext(), R.raw.wizard_atk_1 );


            PlayerNarra.play();
//            PlayerBoss.play();
            PlayerEffect.play();
            handler.postDelayed(new Runnable() {
                public void run() {
                    //try {
                    liberar = true;
                    //Thread.sleep(2000);

                    //} catch () {

                    //}
                }
            }, PlayerNarra.playerDuration() - 7000);

        }

        if(vidaMAgo == 170){
            PlayerNarra.addNarra(this, R.raw.wizard_will_atk);
            //PlayerBoss.addBossAudio(this, R.raw.wizard_laugh);
            PlayerEffect.addEffect(this, R.raw.wizard_atk_2 );
            PlayerNarra.play();
            //PlayerBoss.play();
            handler.postDelayed(new Runnable() {
                public void run() {
                    //try {
                    liberar = true;
                    //Thread.sleep(2000);
                    PlayerBoss.play();
                    PlayerEffect.play();
                    //} catch () {
                    //}
                }
            }, PlayerNarra.playerDuration() - 7000);
        }

        if(vidaMAgo == 80){
            PlayerNarra.addNarra(this, R.raw.wizard_will_atk);
            //PlayerBoss.addBossAudio(this, R.raw.wizard_laugh);
            PlayerEffect.addEffect(this, R.raw.wizz_atk_final );
            PlayerNarra.play();
            //PlayerBoss.play();
            handler.postDelayed(new Runnable() {
                public void run() {
                    //try {
                    liberar = true;
                    //Thread.sleep(2000);
                    //PlayerBoss.play();
                    PlayerEffect.play();
                    //} catch () {
                    //}
                }
            }, PlayerNarra.playerDuration() - 7000);
        }


        if(vidaMAgo == 0){
            PlayerMusic.stopPlay();
            PlayerNarra.addNarra(this, R.raw.wizard_defeated);
            PlayerBoss.addBossAudio(this, R.raw.wizard_dead);
            PlayerBoss.play();

            try {
                Thread.sleep(3000);
                PlayerNarra.play();
                Thread.sleep(3000);

                Intent i = new Intent(getApplicationContext(), CasteloFim.class);
                startActivity(i);
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
