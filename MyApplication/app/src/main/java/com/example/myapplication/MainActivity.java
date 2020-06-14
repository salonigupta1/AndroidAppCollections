package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    class Number {
        int number;
        public boolean isTriangular() {
            int x=1;
            int triangularNumber = 1;
            while(triangularNumber < number){
                x++;
                triangularNumber = triangularNumber + x;
            }
            if(triangularNumber == number){
                return true;
            }
            else {
                return false;
            }
        }
        public boolean isSquare(){
            double squareRoot = Math.sqrt(number);
            if(squareRoot == Math.floor(squareRoot)){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public void testNumber(View view) {
        Number myNumber = new Number();
        EditText editText = (EditText) findViewById(R.id.numberEditText);
        myNumber.number = Integer.parseInt(editText.getText().toString());
        if(myNumber.isTriangular() && myNumber.isSquare())
        {
            Toast.makeText(this, "Both", Toast.LENGTH_SHORT).show();
        }
        else if(myNumber.isTriangular()){
            Toast.makeText(this, "Triangular", Toast.LENGTH_SHORT).show();
        }
        else if(myNumber.isSquare()) {
            Toast.makeText(this, "Square", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "none", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
