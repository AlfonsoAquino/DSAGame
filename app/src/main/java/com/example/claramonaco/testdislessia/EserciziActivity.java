package com.example.claramonaco.testdislessia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ClaraMonaco on 20/03/2018.
 */

public class EserciziActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esercizi);
        final ListView listView = (ListView) findViewById(R.id.list_view_buttom);
        final String[] array = {"Impara la tastiera", "Trova il massimo", "prova", "prova", "prova"};
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.buttom_view, R.id.btn_buttom_view, array);
        listView.setAdapter(arrayAdapter);
    }

    public void tastoPremuto(View view) {
        Button b1 = (Button) view;

        if (b1.getText().toString().equalsIgnoreCase("Impara la tastiera")) {
            Intent i = new Intent(getApplicationContext(), ImparaTastieraActivity.class);
            startActivity(i);
        } else if (b1.getText().toString().equalsIgnoreCase("Trova il massimo")) {
            Intent i = new Intent(getApplicationContext(), TrovaMaxActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "niente", Toast.LENGTH_SHORT).show();
        }
    }

    public void home(View v) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}