package com.example.claramonaco.testdislessia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class ErrorView extends AppCompatActivity {

    private TextView tv;
    private AlertDialog closedialog;
    private String fileName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_view);
        fileName = getIntent().getStringExtra("fileName");
    }


    public void errorView(View v) throws  IOException{
        alertFumetto();
    }

    public void goHome(View v){
        Intent  i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void alertFumetto() throws IOException {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.show_error, null);
        tv = (TextView) dialogLayout.findViewById(R.id.errrrr);
        InputStream inputStream = openFileInput(fileName);
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

            builder.setView(dialogLayout);
            builder.setCancelable(true);
            closedialog = builder.create();
            closedialog.show();
        }
    }
}
