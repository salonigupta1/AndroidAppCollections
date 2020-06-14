package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int locationOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton, button0, button1, button2, button3, playAgain;
    TextView resultTextView, sumTextView;
    TextView scoreTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    int score = 0;
    int numberOfQuestions = 0;
    public void showGame(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgain));


    }

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("CORRECT!!");
            score++;
        } else {
            resultTextView.setText("WRONG!!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        locationOfCorrectAnswer = rand.nextInt(4);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        answers.clear();
        for(int i = 0 ; i< 4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == (a+b)) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        newQuestion();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.button);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgain);
        gameLayout = findViewById(R.id.GameLayout);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
