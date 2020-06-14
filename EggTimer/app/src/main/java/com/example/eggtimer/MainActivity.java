package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView countdownTextView;
    SeekBar timerSeekBar;
    boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTImer() {
        counterIsActive = false;
        timerSeekBar.setEnabled((true));
        goButton.setText("GO!");
        countdownTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();

    }


    public void buttonClicked(View view) {

        if(counterIsActive) {
            resetTImer();
        }
        else
            {
                counterIsActive = true;
                timerSeekBar.setEnabled(false);
                goButton.setText("STOP!!");

                countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                    @Override
                    public void onTick(long l) {
                        updateTimer((int) l / 1000);
                    }

                    @Override
                    public void onFinish() {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorne);
                        mediaPlayer.start();
                        resetTImer();
                    }
                }.start();
             }
    }

    public void updateTimer(int i) {
        int minutes = i/60;
        int seconds = i - (minutes*60);

        String secondString = Integer.toString(seconds);
        if(secondString.equals("0")) {
            secondString = "00";
        }

        countdownTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        countdownTextView = findViewById(R.id.countdownTextView);
        goButton = findViewById(R.id.goButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
