package br.com.ufc.darkadventures;

import android.content.Context;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyGestureListener implements GestureDetector.OnGestureListener {


    private Context context;
    private Vibrator v;

    public MyGestureListener(Context context) {
        this.context = context;
        this.v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
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
        v.vibrate(100);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        v.vibrate(1000);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        v.vibrate(50);
        if(e1.getY() - e2.getY() > 50){
            Toast.makeText(context, "UP", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e2.getY() - e1.getY() > 50){
            Toast.makeText(context, "DOWN", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e1.getX() - e2.getX() > 50){
            Toast.makeText(context, "LEFT", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e2.getX() - e1.getX() > 50){
            Toast.makeText(context, "RIGHT", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}

