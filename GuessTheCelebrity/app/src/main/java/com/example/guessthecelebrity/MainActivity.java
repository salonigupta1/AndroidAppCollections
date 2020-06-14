package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebUrls = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int chosenCeleb = 0;
    ImageView imageView;
    String[] answer = new String[4];
    int locationOfCorrectAnswer = 0;
    Button button0, button1,  button2, button3;

    public void answer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong! It was " + celebNames.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }
        newQuestion();
    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {

            try{

                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(inputStream);
                return mybitmap;

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection connection = null;
            String result="";
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void newQuestion() {

        try {
            Random rand = new Random();
            chosenCeleb = rand.nextInt(celebUrls.size());

            ImageDownloader imageTask = new ImageDownloader();
            Bitmap celebImage = imageTask.execute(celebUrls.get(chosenCeleb)).get();
            imageView.setImageBitmap(celebImage);

            locationOfCorrectAnswer = rand.nextInt(4);
            int incorrectAnswer;
            for(int i=0; i<4; i++){
                if(i == locationOfCorrectAnswer){
                    answer[i] = celebNames.get(chosenCeleb);
                } else {
                    incorrectAnswer = rand.nextInt(celebUrls.size());
                    while (incorrectAnswer == chosenCeleb){
                        incorrectAnswer = rand.nextInt(celebUrls.size());
                    }
                    answer[i] = celebNames.get(incorrectAnswer);
                }
            }

            button0.setText(answer[0]);
            button1.setText(answer[1]);
            button2.setText(answer[2]);
            button3.setText(answer[3]);
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("https://svenskainfluencers.nu/kandisar/").get();
            Pattern p = Pattern.compile("caption\">(.*?)</figcaption>");
            Matcher m = p.matcher(result);

            while (m.find()) {
                if(!(m.group(1).equals("Instagram: byemeliejonsson"))){
                    celebNames.add(m.group(1));
                }
            }
            p = Pattern.compile("img src=\"(.*?)\"");
            m = p.matcher(result);
            while(m.find()){
                celebUrls.add(m.group(1));
            }

            newQuestion();

        }catch (Exception e){

            e.printStackTrace();
            Log.i("msg", "Error");
        }

    }
}
