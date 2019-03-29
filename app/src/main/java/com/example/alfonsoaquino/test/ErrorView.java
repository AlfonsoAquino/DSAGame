package com.example.alfonsoaquino.test;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ErrorView extends AppCompatActivity {

    private TextView tv;
    private AlertDialog closedialog;
    private String fileName="";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_view);
        tv = (TextView) findViewById(R.id.errrrr);

        fileName = getIntent().getStringExtra("fileName");
        try {
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
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void goHome(View v){
        Intent  i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

//    public void alertFumetto() throws IOException {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogLayout = inflater.inflate(R.layout.show_error, null);
//        tv = (TextView) dialogLayout.findViewById(R.id.errrrr);
//        InputStream inputStream = openFileInput(fileName);
//        if (inputStream != null) {
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String tempString = "";
//            StringBuilder stringBuilder = new StringBuilder();
//            while ((tempString = bufferedReader.readLine()) != null) {
//                stringBuilder.append(tempString);
//                stringBuilder.append('\n');
//            }
//            inputStream.close();
//            tv.setText(stringBuilder.toString());
//
//            builder.setView(dialogLayout);
//            builder.setCancelable(true);
//            closedialog = builder.create();
//            closedialog.show();
//        }
//    }
}
