package com.example.claramonaco.testdislessia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

public class IdGroupActivity extends AppCompatActivity {

    private  EditText groupId;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_group);

        groupId= (EditText) findViewById(R.id.groupId);
    }

    public void confermaGroup(View v){

        Log.d("----------->G:","GruoupId: "+groupId.getText());

        if(groupId.getText().length()>=1){
            OutputStreamWriter temp = null;
            OutputStreamWriter dat = null;
            try {

                temp = new OutputStreamWriter(this.openFileOutput("groupId.txt", Context.MODE_PRIVATE));
                temp.write(""+groupId.getText());
                temp.flush();
                temp.close();
                dat = new OutputStreamWriter(this.openFileOutput("data.txt", Context.MODE_PRIVATE));
                data="";
                Date currentTime = Calendar.getInstance().getTime();
                data= currentTime.toString();
                dat.write(""+data);
                dat.flush();
                dat.close();
                Log.d("----------------->G","data: "+data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent i = new Intent(getApplicationContext(), PreTest.class);
            startActivity(i);
        }
    }
}
