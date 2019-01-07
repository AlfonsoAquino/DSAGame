package com.example.claramonaco.testdislessia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import me.grantland.widget.AutofitTextView;

public class TestDislessiaActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener,
        TextToSpeech.OnInitListener,
        AdapterView.OnItemSelectedListener {

    private FileInputStream f;
    private BufferedReader reader, readerDistrattori;
    private AutofitTextView et;
    private AutofitTextView et1;
    private TextView frasi_corrette, frasi_sbagliate, salti, livel;
    private Button audio;
    private String frase, parola, parolaBottone, paroleDistrattori;
    private String fraseArray[];
    private char lettera;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private int i, j, k, esatte, sbagliate, riga, x, position, indice, num_prove, contatore, num_corrette, livello, num_salt;
    private int errori_l1, errori_l2, errori_l3, errori_l4;
    private boolean fit = true;
    private Random random;
    //per aiutarmi a creare dinamicità ai bottoni
    private int bot[] = {9, 9, 9, 9, 9, 9, 9, 9, 9};
    private int controlla_rip[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private boolean flag;

    private String genere;
    private String eta;
    private BufferedReader in;
    private int livelloMax;
    private TextToSpeech tts;
    private String groupId, data;
    private long startTime, totalTime;
    private int secondi, minuti, ore;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dislessia);
        et = (AutofitTextView) findViewById(R.id.frase_da_copiare);
        et1 = (AutofitTextView) findViewById(R.id.textwrite2);
        audio = (Button) findViewById(R.id.ascolta_audio);
        audio.setOnClickListener(this);

        b1 = (Button) findViewById(R.id.uno_bnt);
        b2 = (Button) findViewById(R.id.due_bnt);
        b3 = (Button) findViewById(R.id.tre_bnt);
        b4 = (Button) findViewById(R.id.quattro_bnt);
        b5 = (Button) findViewById(R.id.cinque_bnt);
        b6 = (Button) findViewById(R.id.sei_bnt);
        b7 = (Button) findViewById(R.id.sette_bnt);
        b8 = (Button) findViewById(R.id.otto_bnt);
        b9 = (Button) findViewById(R.id.noveee);

        frasi_corrette = (TextView) findViewById(R.id.frasi_corrette);
        frasi_sbagliate = (TextView) findViewById(R.id.frasi_sbagliate);
        salti = (TextView) findViewById(R.id.frasi_saltate);
        livel = (TextView) findViewById(R.id.livello_frasi);


        flag = true;


        num_prove = 0;
        j = 0;
        parola = "";
        parolaBottone = "";
        random = new Random();
        secondi = 0;
        minuti = 0;
        ore = 0;
        num_salt = 0;
        errori_l1 = 0;
        errori_l2 = 0;
        errori_l3 = 0;
        errori_l4 = 0;
        livelloMax = 0;
        db = new SQLiteHandler(getApplicationContext());
        groupId="";
        data="";
        Date currentTime = Calendar.getInstance().getTime();
        data= currentTime.toString();
        //livello per file
        livello = 1;
        //numero rispose corrette per i livelli di difficoltà
        num_corrette = 0;

        generaBottoni();

        esatte = 0;
        sbagliate = 0;


        frasi_corrette.setText(+esatte + "/21");
        frasi_sbagliate.setText(+sbagliate + "/21");
        salti.setText(+num_salt + "/21");
        //  livel.setText(+livello);


        try {

            LeggiFrase();


        } catch (IOException e) {
            e.printStackTrace();
        }

        tts = new TextToSpeech(this, this);

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.test)
                .setTitle(
                        Html.fromHtml("<font color='#009688'>"
                                + ("Test Dislessia")
                                + "</font>"))
                .setMessage(("Riscrivi la frase utilizzando i bottoni corretti" ))
                .setPositiveButton("ok", null).show();

        startTime = System.currentTimeMillis();


    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ascolta_audio:
                String stringa = et.getText().toString();
                speak(stringa);
                break;
            default:
                break;
        }
    }


    public void LeggiFrase() throws IOException {

        num_prove++;

        if (num_corrette <= 3) {
            livello = 1;
            if(livelloMax<livello){
                livelloMax=livello;
            }
            //leggo la frase
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("bisillabe_piane.txt"), "UTF-8"));


            //leggo i distrattori
            readerDistrattori = new BufferedReader(
                    new InputStreamReader(getAssets().open("bisillabe_piane_distrattori.txt"), "UTF-8"));

        } else if (num_corrette > 3 && num_corrette <= 6) {
            livello = 2;
            if(livelloMax<livello){
                livelloMax=livello;
            }
            //gestisci risultato test
            //leggo la frase
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("trisillabe_piane.txt"), "UTF-8"));


            //leggo i distrattori
            readerDistrattori = new BufferedReader(
                    new InputStreamReader(getAssets().open("trisillabe_piane_distrattore.txt"), "UTF-8"));

        } else if (num_corrette > 6 && num_corrette < 10) {
            livello = 3;
            if(livelloMax<livello){
                livelloMax=livello;
            }
            //leggo la frase
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("bisillabe_trisillabe_complesse.txt"), "UTF-8"));


            //leggo i distrattori
            readerDistrattori = new BufferedReader(
                    new InputStreamReader(getAssets().open("bisillabe_trisillabe_complesse_distrattori.txt"), "UTF-8"));
        } else if (num_corrette >= 10) {
            livello = 4;
            if(livelloMax<livello){
                livelloMax=livello;
            }
            //for(i=0;i<=21;i++)controlla_rip[i]=0;
            //leggo la frase
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("diagrammi_ortografici.txt"), "UTF-8"));


            //leggo i distrattori
            readerDistrattori = new BufferedReader(
                    new InputStreamReader(getAssets().open("diagrammi_ortografici_distrattori.txt"), "UTF-8"));
        }
        if (num_prove == 22) {

            totalTime = System.currentTimeMillis() - startTime;
            secondi = (int) (totalTime / 1000);
            while (secondi >= 60) {
                minuti++;
                secondi = secondi - 60;
            }

            if (minuti > 60) {
                ore++;
            }
            try {
                InputStream inputStream = openFileInput("generalita.txt");
                InputStream gi = openFileInput("groupId.txt");
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String tempString = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((tempString = bufferedReader.readLine()) != null) {
                        if(tempString.equalsIgnoreCase("maschio")||tempString.equalsIgnoreCase("femmina")){
                            genere=tempString;
                        }else if(tempString.equalsIgnoreCase("minore di 6")||tempString.equalsIgnoreCase("piu di 9") ||tempString.equalsIgnoreCase("6")||tempString.equalsIgnoreCase("7")||tempString.equalsIgnoreCase("8")||tempString.equalsIgnoreCase("9")){
                            Log.d("----->>", "Leggieta: "+tempString);
                            eta=tempString;
                        }

                    }
                    inputStream.close();
                }else{
                    genere="non definito";
                    eta="non definita";
                }

                if(gi!=null){
                    InputStreamReader inputStreamReader = new InputStreamReader(gi);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String tempString = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((tempString = bufferedReader.readLine()) != null) {
                        groupId=tempString;
                    }
                    gi.close();
                }else{
                    groupId="non definito";
                }
                db.addStatistic(groupId,data,genere,eta,esatte, sbagliate, num_salt,livelloMax,minuti+":"+secondi,errori_l1,errori_l2,errori_l3,errori_l4);
//                new SendStatistics(getApplicationContext()).execute(genere,eta,""+livelloMax,""+sbagliate,""+num_salt,""+esatte,""+minuti+":"+secondi,""+errori_l1,""+errori_l2,""+errori_l3,""+errori_l4);

                OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
                osw.write("\n\n\n---Risultato Test---" +
                        "\nLivello raggiunto: " + livello +
                        "\nFrasi sbagliate:  " + sbagliate +
                        "\nFrasi saltate: " + num_salt +
                        "\nFrasi corrette: " + esatte +
                        " \nTempo impiegato: "
                        + minuti + "m " + secondi + "s " +

                        "\nErrori livello 1: " + errori_l1 +
                        "\nErrori livello 2: " + errori_l2 +
                        "\nErrori livello 3: " + errori_l3 +
                        "\nErrori livello 4: " + errori_l4 +
                        "\n\n\n\n");
                osw.flush();
                osw.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

           /*new AlertDialog.Builder(this)
                    .setIcon(R.drawable.test)
                    .setTitle(
                            Html.fromHtml("<font color='#009688'>"
                                    + ("Test Dislessia")
                                    + "</font>"))
                    .setMessage(("Il test è terminato 21 quesiti" +
                            "\n\nLivello raggiunto: " + livello +
                            "\n\nFrasi sbagliate:  " + sbagliate +
                            "\nFrasi saltate: " + num_salt +
                            "\nFrasi corrette: " + esatte +
                            " \n\nTempo impiegato: "
                            + minuti + "m " + secondi + "s " +

                            "\n\nErrori livello 1: " + errori_l1 +
                            "\nErrori livello 2: " + errori_l2 +
                            "\nErrori livello 3: " + errori_l3 +
                            "\nErrori livello 4: " + errori_l4))
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    //moveTaskToBack(true);*/
            Intent i = new Intent(getApplicationContext(), PostTest.class);
            startActivity(i);
            // finish();
//                                    System.exit(0);

            //    }
            //  }).show();

        }

        if (num_prove % 22 == 0) {
            for (i = 0; i <= 20; i++) controlla_rip[i] = 0;

        }
        if (num_prove % 23 == 0) System.exit(0);
        /*
        per non avere ripetizioni

         */

        while (controlla_rip[riga = random.nextInt(21)] == 1) {

            Log.d("" + riga, "random");
        }

        controlla_rip[riga] = 1;
        int val = 0;
        while (val != riga) {
            frase = reader.readLine();
            paroleDistrattori = readerDistrattori.readLine();
            val++;
        }
        parola = "";
        frase = reader.readLine();
        paroleDistrattori = readerDistrattori.readLine();
        et.setText(frase);
        j = 0;
        Clear();
        bottoniParole(frase, paroleDistrattori);


        frasi_corrette.setText(+esatte + "/21");
        frasi_sbagliate.setText(+sbagliate + "/21");
        salti.setText(+num_salt + "/21");
        livel.setText("" + livello);

    }

    public void risultatoTest(View v) {
        Intent i = new Intent(getApplicationContext(), StatisticheTestActivity.class);
        startActivity(i);
    }

    public void Salta(View v) throws IOException {
        ++num_salt;
        OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
        osw.write("\n\nlivello:" + livello + " \nsalt: " + et1.getText().toString() + "\nfrase: " + et.getText().toString());
        osw.flush();
        osw.close();
        try {
            LeggiFrase();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void Controlliamo(View v) throws IOException {


        if (et1.getText().toString().equals(et.getText().toString())) {
            esatte++;
            num_corrette++;

        } else {
            sbagliate++;
            if (num_corrette == 0) {
                num_corrette = 0;
            } else {
                num_corrette--;
            }
            if (livello == 1) errori_l1++;
            if (livello == 2) errori_l2++;
            if (livello == 3) errori_l3++;
            if (livello == 4) errori_l4++;

            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\nlivello:" + livello + " \nerror: " + et1.getText().toString() + "\nfrase: " + et.getText().toString());
            osw.flush();
            osw.close();

        }

        LeggiFrase();


    }

    public void ScriviParola(View v) {

        Button b = (Button) v;
        if (fit) {
            parolaBottone += (b.getText().toString());
            fit = false;
        } else {
            parolaBottone += (" " + b.getText().toString());
        }
        et1.setText(parolaBottone);


    }

    public void Clear() {
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
        et1.setText("");
        parolaBottone = "";
        for (i = 0; i <= 8; i++) bot[i] = 9;
        generaBottoni();
        fit = true;

        // frasi_corrette.setText(esatte);
        //  frasi_sbagliate.setText(sbagliate);

    }

    //mi serve per generare le parole in posizioni random
    public void generaBottoni() {
        contatore = 0;
        boolean val = true;
        while (val) {
            flag = true;
            position = random.nextInt(9);
            Log.d("" + position, "random");
            for (i = 0; i <= 8; i++) {
                if (position == bot[i]) {
                    flag = false;
                }
            }
            if (flag) {

                bot[contatore] = position;
                contatore++;
            }
            if (contatore == 9) val = false;

        }
    }

    public void bottoniParole(String frase, String paroleDistrattori) {


        for (i = 0; i < frase.length(); i++) {

            lettera = frase.charAt(i);

            if (lettera == ' ') {
                ScriviBottoni();


            } else {
                parola += lettera;

            }

        }
        if (i == frase.length()) ScriviBottoni();

        for (k = 0; k < paroleDistrattori.length(); k++) {
            lettera = paroleDistrattori.charAt(k);
            if (lettera == ' ') {
                ScriviBottoni();


            } else {
                parola += lettera;

            }
        }
        if (k == paroleDistrattori.length()) ScriviBottoni();
    }


    public void ScriviBottoni() {


        switch (bot[j]) {
            case 0:
                b1.setText(parola);
                j++;
                parola = "";
                break;
            case 1:
                b2.setText(parola);
                j++;
                parola = "";
                break;
            case 2:
                b3.setText(parola);
                j++;
                parola = "";
                break;
            case 3:
                b4.setText(parola);
                j++;
                parola = "";
                break;
            case 4:
                b5.setText(parola);
                j++;
                parola = "";
                break;
            case 5:
                b6.setText(parola);
                j++;
                parola = "";
                break;
            case 6:
                b7.setText(parola);
                j++;
                parola = "";
                break;
            case 7:
                b8.setText(parola);
                j++;
                parola = "";
                break;
            case 8:
                b9.setText(parola);
                j++;
                parola = "";
                break;

            default:
                break;
        }
    }

  /*  <EditText
    android:id="@+id/frase_da_copiare"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:clickable="false"
    android:cursorVisible="false"
    android:focusable="false"
    android:focusableInTouchMode="false"
    android:inputType="textMultiLine|textAutoCorrect"
            />*/


    public void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}