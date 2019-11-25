package br.com.ufc.darkadventures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.ufc.darkadventures.Util.PlayerMusic;
import br.com.ufc.darkadventures.Util.PlayerNarra;

public class CasteloFim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castelo_fim);

        PlayerMusic.addMusic(this, R.raw.music_end);
        PlayerNarra.addNarra(this, R.raw.the_end);
        PlayerMusic.removeLoop();
        PlayerMusic.play();
        PlayerNarra.play();
    }
}
