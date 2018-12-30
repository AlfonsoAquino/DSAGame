package com.example.claramonaco.testdislessia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lanciaActivityEsercizi(View v) {
        // definisco l'intenzione di aprire l'Activity "Page1.java"

        Intent i = new Intent(getApplicationContext(), EserciziActivity.class);
        //passo all'attivazione dell'activity page1.java
        startActivity(i);
    }

    public void test_dislessia(View v) {
        Intent i = new Intent(getApplicationContext(), PreTest.class);
        startActivity(i);
    }

    public void InformazioneApp(View v) {
        Intent i = new Intent(getApplicationContext(), InfoAppActivity.class);
        startActivity(i);
    }
    public void risultatoTest(View v) {
        Intent i = new Intent(getApplicationContext(), StatisticheTestActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_action_exit)
                .setTitle(
                        Html.fromHtml("<font color='#009688'>"
                                + getString(android.R.string.dialog_alert_title)
                                + "</font>"))
                .setMessage("vuoi uscire?")
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                moveTaskToBack(true);
                                finish();
                                System.exit(0);
                            }
                        }).setNegativeButton(android.R.string.no, null).show();
    }


}