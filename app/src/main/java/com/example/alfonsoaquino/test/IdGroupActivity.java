package com.example.alfonsoaquino.test;

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

public class IdGroupActivity extends AppCompatActivity {

    private EditText codicePlesso, codiceClasse, codiceRegistro;
    private String data, regione, tipoTest="";
    private LinearLayout spinnerLayout, layoutGroup, layoutClasse;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_group);

        tipoTest = getIntent().getStringExtra("tipoTest");

        spinnerLayout = (LinearLayout) findViewById(R.id.layoutSpinner);
        layoutGroup = (LinearLayout) findViewById(R.id.layoutGroupId);
        layoutClasse = (LinearLayout) findViewById(R.id.layoutCodiceClasse);
        codicePlesso = (EditText) findViewById(R.id.codicePlesso);
        spinner = (Spinner) findViewById(R.id.spinner);
        codiceClasse = (EditText) findViewById(R.id.codiceClasse);
        codiceRegistro = (EditText) findViewById(R.id.codiceRegistro);


        data="";
        regione="";


        layoutClasse.setVisibility(View.GONE);
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
                temp = new OutputStreamWriter(this.openFileOutput("infoUtente.txt", Context.MODE_PRIVATE));
                //inserimento in posizione di un idAlunno fittizzio in modo da limitare al minimo le modifiche apportate al codice
                temp.write(""+tipoTest
                        +"\n"+spinner.getSelectedItem().toString());
                temp.flush();
                temp.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinnerLayout.setVisibility(View.GONE);
            layoutGroup.setVisibility(View.VISIBLE);
        }
    }

    public void confermaGroup(View v){

        Log.d("----------->G:","GruoupId: "+ codicePlesso.getText());

        if(codicePlesso.getText().length()>=1){
            OutputStreamWriter temp = null;
            OutputStreamWriter dat = null;
            try {

                temp = new OutputStreamWriter(this.openFileOutput("infoUtente.txt", Context.MODE_APPEND));
                temp.write("\n"+ codicePlesso.getText());
                temp.flush();
                temp.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            layoutGroup.setVisibility(View.GONE);
            layoutClasse.setVisibility(View.VISIBLE);
        }
    }

    public void confermaCodiceClasse(View v){
        Log.d("----------->G:","codiceClasse: "+codiceClasse.getText()+" codice registro: "+codiceRegistro.getText());

        if(codiceClasse.getText().length()>=1 && codiceRegistro.getText().length()>=1){
            OutputStreamWriter temp = null;
            try {
                temp = new OutputStreamWriter(this.openFileOutput("infoUtente.txt", Context.MODE_APPEND));
                temp.write("\n"+codiceClasse.getText()+
                        "\n"+codiceRegistro.getText());
                temp.flush();
                temp.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent i = new Intent(getApplicationContext(), PreTest.class);
            i.putExtra("tipoTest",tipoTest);
            startActivity(i);
        }
    }
}
