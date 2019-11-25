package br.com.ufc.darkadventures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import br.com.ufc.darkadventures.Util.PlayerBoss;
import br.com.ufc.darkadventures.Util.PlayerEffect;
import br.com.ufc.darkadventures.Util.PlayerMusic;
import br.com.ufc.darkadventures.Util.PlayerNarra;

public class CasteloChefeGuardiao extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private Vibrator v;
    Handler handler = new Handler();
    public boolean liberar = false;
    private int vidaGuardiao = 220;
    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castelo_chefe_guardiao);

        init();

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        gestureDetector = new GestureDetector(this);

        PlayerMusic.addMusic(this, R.raw.music_boss_guardian);
        PlayerNarra.addNarra(this, R.raw.info_atk);
        PlayerBoss.addBossAudio(this, R.raw.guardian_begin);
        PlayerEffect.addEffect(this, R.raw.guardian_arriving);

        PlayerMusic.play();
        PlayerNarra.play();

        handler.postDelayed(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(10);
                    PlayerBoss.play();

                    //PlayerBoss.addBossAudio(getApplicationContext(), R.raw.guardiao_primeiro_ataque);
                    PlayerEffect.addEffect(getApplicationContext(), R.raw.guardian_atk_1 );

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, PlayerNarra.playerDuration());
    }

    public void onPress(View view){

        vidaGuardiao --;
        v.vibrate(50);
        if(vidaGuardiao == 150){
            PlayerNarra.addNarra(this, R.raw.guardian_will_atk);

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

        if(vidaGuardiao == 80){
            PlayerNarra.addNarra(this, R.raw.guardian_will_atk);
            PlayerBoss.addBossAudio(this, R.raw.guardian_last_atk);
            PlayerEffect.addEffect(this, R.raw.guardian_atk_2 );
            PlayerNarra.play();
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

        if(vidaGuardiao == 30){
            PlayerNarra.addNarra(this, R.raw.guardian_little_to_finish);
            PlayerNarra.play();
        }

        if(vidaGuardiao == 0){
            PlayerMusic.stopPlay();
            PlayerNarra.addNarra(this, R.raw.guardian_defeated);
            PlayerBoss.addBossAudio(this, R.raw.guardian_dead);
            PlayerBoss.play();

            try {
                Thread.sleep(3000);
                PlayerNarra.play();
                Thread.sleep(3000);

                Intent i = new Intent(getApplicationContext(), CasteloDesafioMago.class);
                startActivity(i);
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void init(){
        fl = (FrameLayout) findViewById(R.id.frameClickGuardiao);

        fl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(liberar){
                    Toast.makeText(getApplicationContext(), "Defesa...", Toast.LENGTH_SHORT).show();
                    v.vibrate(1000);
                    PlayerNarra.addNarra(getApplicationContext(), R.raw.guardian_defense_success);
                    PlayerNarra.play();
                    liberar = false;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
