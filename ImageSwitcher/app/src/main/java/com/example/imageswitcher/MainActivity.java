package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void currencyConvector(View view) {
        Log.i("Info", "Button Pressed");
        EditText editText = (EditText) findViewById(R.id.editText4);
        String amountInPounds = editText.getText().toString();
        Double amountInPoundsDouble = Double.parseDouble(amountInPounds);
        Double amountInDollarDouble = amountInPoundsDouble * 1.3;
        String amountInDollarString = String.format("%.2f", amountInDollarDouble);
        Toast.makeText(this, "Converted result is: " + amountInDollarString, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
