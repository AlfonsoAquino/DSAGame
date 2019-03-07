package com.example.claramonaco.testdislessia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Statistica> statistics;
    private ArrayList<Gradimento> gradimento;
    private Boolean firstTime = null;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statistics=new ArrayList<>();
        gradimento=new ArrayList<>();
        db=new SQLiteHandler(getApplicationContext());

        if(isFirstTime()){
            db.onUpgrade(db.getWritableDatabase(),1,1);
        }
        Log.d("isFirst??????????????", "onCreate: "+isFirstTime());
        statistics=db.getStatisticsDetails();

        if(statistics.size()==0){
            db.onUpgrade(db.getWritableDatabase(),1,1);
        }

        if (isOnline() && statistics.size() != 0) {

            for (Statistica a : statistics) {
                Log.i("asdadadadad....", "-------------->" + a.toString());
                new SendStatistics(getApplicationContext()).execute("" + a.getGroupId(), "" + a.getData(), "" + a.getGenere(), "" + a.getEta(), "" + a.getLivelloRaggiunto(), "" + a.getNumSbagliate(),"" + a.getNumSaltate(),"" + a.getNumCorrette(),"" + a.getTempoImpiegato(),
                        "" + a.getErrore1(),"" + a.getErrore2(),"" + a.getErrore3(),"" + a.getErrore4(),"" + a.getIdAlunno(),"" + a.getRegione(),""+a.getDomanda1(),
                        ""+a.getDomanda2(),""+a.getDomanda3(),""+a.getDomanda4(),""+a.getDomanda5(),""+a.getDomanda6(),""+a.getDomanda7(),""+a.getDomanda8(),""+a.getTipoTest());

            }
        }

    }

    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public void lanciaActivityEsercizi(View v) {
        // definisco l'intenzione di aprire l'Activity "Page1.java"

        Intent i = new Intent(getApplicationContext(), EserciziActivity.class);
        //passo all'attivazione dell'activity page1.java
        startActivity(i);
    }

    public void test_dislessia(View v) {
        Intent i = new Intent(getApplicationContext(), IdGroupActivity.class);
        startActivity(i);
    }

    public void test_ascolto(View v) {
        Intent i = new Intent(getApplicationContext(), IdGroupActivity.class);
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

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
    }
}