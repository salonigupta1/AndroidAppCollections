package com.example.guessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNumber;
    public void GuessFunc(View view) {
        EditText editText = (EditText) findViewById(R.id.valueEditText);
        int guessValue = Integer.parseInt(editText.getText().toString());
        if(guessValue < randomNumber){
            Toast.makeText(this, "Too small", Toast.LENGTH_SHORT).show();
        }
        else if(guessValue > randomNumber){
            Toast.makeText(this, "too large", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Congrats, Correct guess!!", Toast.LENGTH_SHORT).show();
        }
        Log.i("Enter Value", editText.getText().toString());
        Log.i("Info", Integer.toString(randomNumber));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        randomNumber = rand.nextInt(20) + 1;
    }
}
