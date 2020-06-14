package com.example.uielements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    public void showElement(View view){
        Button showButton = findViewById(R.id.showButton);
        textView.setVisibility(View.VISIBLE);
    }

    public void hideElement(View view){
        Button hideButton = findViewById(R.id.hideButton);
        textView.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }
}
