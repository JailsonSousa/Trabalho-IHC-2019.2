package br.com.ufc.darkadventures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import br.com.ufc.darkadventures.Util.PlayerMusic;
import br.com.ufc.darkadventures.Util.PlayerNarra;

public class CasteloQuartoRei extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private Vibrator v;
    Handler handler = new Handler();
    private boolean liberar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castelo_quarto_rei);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        gestureDetector = new GestureDetector(this);

        PlayerMusic.addMusic(this, R.raw.music_castle);
        PlayerNarra.addNarra(this, R.raw.narra_bed_room);
        PlayerNarra.play();
        PlayerMusic.play();

        handler.postDelayed(new Runnable() {
            public void run() {
                liberar = true;
            }
        }, PlayerNarra.playerDuration());
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
        v.vibrate(50);
        if(e1.getY() - e2.getY() > 50){
            Toast.makeText(this, "CIMA", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e2.getY() - e1.getY() > 50){
            Toast.makeText(this, "BAIXO", Toast.LENGTH_SHORT).show();
            if(liberar){
                PlayerMusic.stopPlay();
                Intent i = new Intent(getApplicationContext(), CasteloSalaoPrincipal.class);
                startActivity(i);
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
            }
            return true;
        }
        if(e1.getX() - e2.getX() > 50){
            Toast.makeText(this, "ESQUERDA", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e2.getX() - e1.getX() > 50){
            Toast.makeText(this, "DIREITA", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
