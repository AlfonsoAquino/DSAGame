package com.example.claramonaco.testdislessia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ClaraMonaco on 28/05/2018.
 */

public class PreTest  extends AppCompatActivity {

    private RadioGroup genere, eta;
    private String genere_,eta_,data;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_test);

        genere= findViewById(R.id.genere_b);
        eta= findViewById(R.id.eta_b);


    }
    public void onRadioButtonClickedEta(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_menosei:
                if (checked)
                    eta_="minore di 6";
                break;
            case R.id.radio_sei:
                if (checked)
                    eta_="6";
                break;
            case R.id.radio_sette:
                if (checked)
                    eta_="7";
                break;
            case R.id.radio_otto:
                if (checked)
                    eta_="8";
                break;
            case R.id.radio_nove:
                if(checked)
                    eta_="9";
                break;
            case R.id.radio_novepiu:
                if (checked)
                    eta_="piu di 9";
//                break;
//            case R.id.radio_nove:
//                if(checked)
//                    eta_="9";
        }}


    public void onRadioButtonClickedGenere(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_femmina:
                if (checked)
                    genere_="femmina";
                break;
            case R.id.radio_maschio:
                if (checked)
                    genere_="maschio";
                break;






        }
    }
    public void test_dislessia(View v) throws IOException {

        if(genere.isSelected()){

        }
        OutputStreamWriter dat=null;
        OutputStreamWriter temp = new OutputStreamWriter(this.openFileOutput("infoUtente.txt", Context.MODE_APPEND));
        temp.write("\n"+genere_+
                "\n"+eta_+
                "\nlettura");
        temp.flush();
        temp.close();
        OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
        osw.write("\n---Utente---\nGenere: "+genere_+
                "\neta: "+eta_);
        osw.flush();
        osw.close();

        dat = new OutputStreamWriter(this.openFileOutput("data.txt", Context.MODE_PRIVATE));
        data="";
        Date currentTime = Calendar.getInstance().getTime();
        data= currentTime.toString();
        dat.write(""+data);
        dat.flush();
        dat.close();
        Log.d("----------------->G","data: "+data);

        Intent i = new Intent(getApplicationContext(), TestDislessiaActivity.class);
        startActivity(i);
        //commento system exit per tenere traccia dei log del sistema in console
//        System.exit(0);

    }
}
