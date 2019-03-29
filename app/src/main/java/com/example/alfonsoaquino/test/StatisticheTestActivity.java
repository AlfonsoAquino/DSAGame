package com.example.alfonsoaquino.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by ClaraMonaco on 21/05/2018.
 */

public class StatisticheTestActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistiche_test);
        tv = (TextView) findViewById(R.id.statisiche_tm);
        Leggi();
    }


    public void Leggi(){

        try {
            InputStream inputStream = openFileInput("statistiche_test.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(tempString);
                    stringBuilder.append('\n');
                }
                inputStream.close();
                tv.setText(stringBuilder.toString());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancellaCronologia(View v) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_PRIVATE));
        try {
            osw.write(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        osw.flush();
        osw.close();
        Leggi();
        //  System.exit(0);
        //onCreate(Bundle.EMPTY);
    }

    public void condividiCronologia(View v) {
        String shareBody = tv.getText().toString();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.condividi)));
    }

}