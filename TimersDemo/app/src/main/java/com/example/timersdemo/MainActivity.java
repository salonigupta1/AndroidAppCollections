package com.example.timersdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000) {
            public void onTick(long milisLeft){
                Log.i("Milis left", String.valueOf(milisLeft/1000));
            }

            public void onFinish() {
                Log.i("We are done", "No more countdowns");
            }
        }.start();

    }
}
