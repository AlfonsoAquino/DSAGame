package com.example.claramonaco.testdislessia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IdGroupActivity extends AppCompatActivity {

    private EditText groupId, idAlunno;
    private String data, regione, identAlunno;
    private LinearLayout spinnerLayout, layoutGroup, layoutAlunno;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_group);

        spinnerLayout = (LinearLayout) findViewById(R.id.layoutSpinner);
        layoutGroup = (LinearLayout) findViewById(R.id.layoutGroupId);
        groupId = (EditText) findViewById(R.id.groupId);
        spinner = (Spinner) findViewById(R.id.spinner);
        idAlunno = (EditText) findViewById(R.id.idAlunno);
        layoutAlunno = (LinearLayout) findViewById(R.id.layoutIdAlunno);

        data="";
        regione="";
        identAlunno="";

        layoutAlunno.setVisibility(View.GONE);
        layoutGroup.setVisibility(View.GONE);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void confermaRegione(View v){
        Log.d("----------->G:","Regione: "+spinner.getSelectedItem());

        if(!spinner.getSelectedItem().toString().equalsIgnoreCase("Seleziona la regione")){

            OutputStreamWriter temp = null;
            try {
                temp = new OutputStreamWriter(this.openFileOutput("regione.txt", Context.MODE_PRIVATE));
                temp.write(""+spinner.getSelectedItem().toString());
                temp.flush();
                temp.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinnerLayout.setVisibility(View.GONE);
            layoutAlunno.setVisibility(View.VISIBLE);
        }
    }

    public void confermaAlunno(View v){
        Log.d("----------->G:","idAlunno: "+idAlunno.getText());

        if(idAlunno.getText().length()>=1){
            OutputStreamWriter temp = null;
            try {
                temp = new OutputStreamWriter(this.openFileOutput("idAlunno.txt", Context.MODE_PRIVATE));
                temp.write(""+idAlunno.getText());
                temp.flush();
                temp.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            identAlunno = idAlunno.getText().toString();
            layoutAlunno.setVisibility(View.GONE);
            layoutGroup.setVisibility(View.VISIBLE);
        }
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
