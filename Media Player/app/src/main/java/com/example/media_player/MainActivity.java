package com.example.media_player;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    MediaPlayer player, newplayer;

    MediaPlayer createMedia() {
        newplayer = MediaPlayer.create(this, R.raw.song);
        return(newplayer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int shift = 10000;
        ImageButton play = (ImageButton) findViewById(R.id.playButton);
        ImageButton pause = (ImageButton) findViewById(R.id.pauseButton);
        ImageButton stop = (ImageButton) findViewById(R.id.stopButton);
        ImageButton revert = (ImageButton) findViewById(R.id.restartButton);
        ImageButton forward = (ImageButton) findViewById(R.id.fowardButton);
        ImageButton rewind = (ImageButton) findViewById(R.id.rewindButton);
        EditText title = (EditText) findViewById(R.id.SongTitle);
        player = createMedia();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                title.setText("Drum Solo");
                player.start();
            }
        });
        pause.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                player.pause();
            }
        }));
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                player.stop();
                player = createMedia();
            }
        });

        revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                player.reset();
                player = createMedia();
                player.start();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int curr = player.getCurrentPosition();
                int dur  = player.getDuration();

                if ((curr + shift) <= dur)
                {
                    player.seekTo(curr+shift);
                }
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int curr = player.getCurrentPosition();
                int dur  = player.getDuration();

                if ((curr - shift) >= 0)
                {
                    player.seekTo(curr+shift);
                }
            }
        });
    }
}