package com.example.alfonsoaquino.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.speech.tts.TextToSpeech.SUCCESS;

/**
 * Created by ClaraMonaco on 20/03/2018.
 */

public class ImparaTastieraActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    static double speechSpeed = 1.0;
    private final String salvaPaese = "country";
    private final short DEFAULT_PAESE = 0;
    private final String salvaSpeak = "TTS";
    private final String salvaKeySound = "key_sound";
    private final String salvaKey = "key_settings";
    private final String salvaGen = "gen_settings";
    private List<Data> list;
    private TextView lavagna;
    private String lavagnaRestante = "";
    private String distrattori = "";
    private EditText edit_text;
    private CountDownTimer timer;
    private Context context;
    private int numberOfWords = 1;
    private int i = 0;
    private int errori = 0;
    private String wordNow = "";
    private int record = 0;
    private byte sircily = 0;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private short paese = 1;
    private Calendar lastWordTime;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
    private TextToSpeech mTts;
    private int mStatus = 0;
    private boolean canSpeak = true;
    private boolean speak = true;
    private byte key_settings = 0;
    private byte gen_settings = 2;
    private boolean key_sound = true;
    private boolean AdIcona = false;
    private boolean notYet = false;
    private Button prossima;
    private Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;

    private Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ;

    private MediaPlayer keycorrect, keywrong, done;

    private String readRawTextFile(Context ctx, int resId, int rigo) {

        Random random = new Random();
        if ((random.nextBoolean() && gen_settings != 1) || gen_settings == 0) {
            InputStream inputStream = ctx.getResources().openRawResource(resId);

        /*
        try {
            myCharset = getDetectedEncoding(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

            InputStreamReader inputStreamReadereader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReadereader);
            String line = "";
            int tempRigo = 0;

            try {
                while (((line = bufferedReader.readLine()) != null)
                        || (tempRigo == rigo)) {
                    tempRigo++;
                    if (tempRigo == rigo) {
                        // Log.d("Rigo", "Trovato il rigo  " + tempRigo);
                        return line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return line;
        } else {
            return randInt(0, 9999);
        }
    }

    private String randInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return String.valueOf(randomNum);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.impara_tastiera);
        context = getApplicationContext();

        if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
        } else {
            sharedPreferences = context.getSharedPreferences(
                    getDefaultSharedPreferencesName(context),
                    getDefaultSharedPreferencesMode());
        }
        editor = sharedPreferences.edit();

        // getting boolean, default true
        speak = sharedPreferences.getBoolean(salvaSpeak, true);
        key_sound = sharedPreferences.getBoolean(salvaKeySound, true);

        key_settings = (byte) sharedPreferences.getInt(salvaKey, 0);
        gen_settings = (byte) sharedPreferences.getInt(salvaGen, 2);


        // Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call


        setup();

        if (!checkLingua(context)) {
            scegliIlPaese();
        } else {
            setLocale(context,
                    (short) sharedPreferences.getInt(salvaPaese, DEFAULT_PAESE));
            setTitle(Html.fromHtml("<font color='#009688'>"
                    + getString(R.string.app_name) + "</font>"));

            // Instantiating TextToSpeech class

        }
        mTts = new TextToSpeech(context, this);

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.test)
                .setTitle(
                        Html.fromHtml("<font color='#0f0f0f'>"
                                + getString(android.R.string.dialog_alert_title)
                                + "</font>"))
                .setMessage(Html.fromHtml("<font color='#542ff9'>"
                        + "Inizia l'allenamento!! \nscrivi la parola della lavagna"
                        + "</font>"))
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                cliccaPlay();
                            }
                        }).show();


        //cliccaPlay();


    }

    private boolean checkLingua(Context cont) {
        // getting values
        short paeseScelto = (short) sharedPreferences
                .getInt(salvaPaese, DEFAULT_PAESE);
        if ((paeseScelto < 0) || (paeseScelto > 8)) {
            paeseScelto = DEFAULT_PAESE;
        }

        if (paeseScelto == 0) {
            String temp = Locale.getDefault().getCountry();
            // Testing
            // Toast.makeText(cont, temp, Toast.LENGTH_LONG).show();
            if (("BG").equals(temp)) {
                paeseScelto = 1;
            } else if (("DE").equals(temp)) {
                paeseScelto = 2;
            } else if ((("EN").equals(temp)) || (("US").equals(temp))
                    || (("GB").equals(temp))) {
                paeseScelto = 3;
            } else if (("ES").equals(temp)) {
                paeseScelto = 4;
            } else if (("FR").equals(temp)) {
                paeseScelto = 5;
            } else if (("IT").equals(temp)) {
                paeseScelto = 6;
            } else if (("PL").equals(temp)) {
                paeseScelto = 7;
            } else if ((("PT").equals(temp) || ("BR").equals(temp))) {
                paeseScelto = 8;
            }
            if (paeseScelto != 0) {
                // Salva i dati
                editor.putInt(salvaPaese, (short) (paeseScelto));
                editor.apply();
                paese = paeseScelto;
            }
        } else {
            setLocale(cont, paeseScelto);
            paese = paeseScelto;
        }

        return paeseScelto != DEFAULT_PAESE;
    }

    private String getDefaultSharedPreferencesName(Context cont) {
        return cont.getPackageName() + "_preferences";
    }

    private int getDefaultSharedPreferencesMode() {
        return Context.MODE_PRIVATE;
    }

    private void setLocale(Context cont, short Paese) {
        Locale locale = new Locale(localePaese(Paese));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        cont.getResources().updateConfiguration(config,
                cont.getResources().getDisplayMetrics());
    }

    private String localePaese(short paeseCheHaiScelto) {
        switch (paeseCheHaiScelto) {
            case 1: {
                return "bg";
            }
            case 2: {
                return "de";
            }
            case 3: {
                return "us";
            }
            case 4: {
                return "es";
            }
            case 5: {
                return "fr";
            }
            case 6: {
                return "it";
            }
            case 7: {
                return "pl";
            }
            case 8: {
                return "pt";
            }
        }
        // in caso di errore restituisce English
        return localePaese((short) 3);
    }

    private void scegliIlPaese() {
        final String[] listaLingue = {getString(R.string.BG), getString(R.string.DE),
                getString(R.string.EN), getString(R.string.ES),
                getString(R.string.FR), getString(R.string.IT),
                getString(R.string.PL), getString(R.string.PT)};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.language, listaLingue);

        AlertDialog.Builder sceltaPaese = new AlertDialog.Builder(this);

        // Se non impostata DEVI impostarla
        if (((short) sharedPreferences.getInt(salvaPaese, DEFAULT_PAESE)) == DEFAULT_PAESE) {
            sceltaPaese.setCancelable(false);
        } else {
            sceltaPaese.setCancelable(true);
        }

        sceltaPaese
                .setTitle(
                        Html.fromHtml("<font color='#009688'>"
                                + getString(R.string.action_language) + "</font>"))
                .setIcon(R.drawable.languaged).setInverseBackgroundForced(true)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // se non salvato o se diverso
                        if (((short) sharedPreferences.getInt(salvaPaese,
                                DEFAULT_PAESE) == DEFAULT_PAESE)
                                || ((short) sharedPreferences.getInt(
                                salvaPaese, DEFAULT_PAESE) != (short) (item + 1))) {
                            paese = (short) (item + 1);
                            // Salva i dati
                            editor.putInt(salvaPaese, paese);
                            editor.apply();
                            // imposta lingua
                            setLocale(context, (short) (item + 1));
                            // Instantiating TextToSpeech class
                            mTts = new TextToSpeech(context, ImparaTastieraActivity.this);
                            // riavvia
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    }
                }).show();
    }

    private void startGame() {

        numberOfWords = 1;

        record++;

        try {
            numberOfWords = countLines(context, R.raw.words);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Log.d("TAG", "Trovate " + numberOfWords + " parole");

        list = new ArrayList<Data>();
        for (int j = 0; j < numberOfWords; j++) {
            list.add(new Data(j + 1));
        }

        Collections.shuffle(list);
        i = -1;

        nextWord();
    }

    private void nextWord() {
        if (i < numberOfWords - 1) {
            i++;
        } else {
            Collections.shuffle(list);
            i = 0;
        }

        // Log.d("TAG", "I vale " + i + " ed equivale a : " + readRawTextFile(context, R.raw.words, list.get(i).toNum()));

        String tempRecuperata = "";


        //qui si legge la parola
        boolean error = false;
        while ((tempRecuperata != null) && (tempRecuperata.length() < 2) && !error) {
            try {
                tempRecuperata = readRawTextFile(context, R.raw.words, list.get(i).toNum());
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                error = true;
            }
        }
        if (null == tempRecuperata || error) {
            tempRecuperata = "errore";
        }
        wordNow = tempRecuperata.toUpperCase();
        System.out.println("word: " + wordNow);

        if (canSpeak && speak) {
            iniziaLettura(wordNow);
        }

        if (wordNow.matches(".*\\d+.*")) {
            edit_text.setHint(getString(R.string.write_number));
        } else {
            edit_text.setHint(getString(R.string.write_word));
        }

        lavagnaRestante = wordNow;

        distrattori = generaDistrattori(lavagnaRestante, 3);

        if (key_settings == 0) {
            nascondiTasti(lavagnaRestante);
        } else if (key_settings == 1) {
            nascondiTasti("1234567890QWERTYUIOPASDFGHJKLZXCVBNM");
        } else {
            nascondiTasti(lavagnaRestante + distrattori);
        }

        // add a space after each char
        lavagna.setText(wordNow.replaceAll(".(?!$)", "$0 "));


    }

    //genera i distrattori, crea una lista di caratteri, e poi ne genera tre alla sua chiamata controllando che non esistano nella parola uscita
    private String generaDistrattori(String parola, int numeroDistrattori) {
        List<String> cifreLettere = new ArrayList<>();

        cifreLettere.add("1");
        cifreLettere.add("2");
        cifreLettere.add("3");
        cifreLettere.add("4");
        cifreLettere.add("5");
        cifreLettere.add("6");
        cifreLettere.add("7");
        cifreLettere.add("8");
        cifreLettere.add("9");
        cifreLettere.add("0");

        cifreLettere.add("Q");
        cifreLettere.add("W");
        cifreLettere.add("E");
        cifreLettere.add("R");
        cifreLettere.add("T");
        cifreLettere.add("Y");
        cifreLettere.add("U");
        cifreLettere.add("I");
        cifreLettere.add("O");
        cifreLettere.add("P");
        cifreLettere.add("A");
        cifreLettere.add("S");
        cifreLettere.add("D");
        cifreLettere.add("F");
        cifreLettere.add("G");
        cifreLettere.add("H");
        cifreLettere.add("J");
        cifreLettere.add("K");
        cifreLettere.add("L");
        cifreLettere.add("Z");
        cifreLettere.add("X");
        cifreLettere.add("C");
        cifreLettere.add("V");
        cifreLettere.add("B");
        cifreLettere.add("N");
        cifreLettere.add("M");

        Collections.shuffle(cifreLettere);

        String tempDistrattori = "";
        int i = 0;
        while ((tempDistrattori.length() < numeroDistrattori)) {
            if (!parola.contains(cifreLettere.get(i))) {
                tempDistrattori = tempDistrattori + cifreLettere.get(i);
            }
            i++;
        }
        return tempDistrattori;
    }

    private void cliccaPlay() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startGame();
        edit_text.setText("");
    }

    private int countLines(Context ctx, int resId) throws IOException {
        // InputStream is = new BufferedInputStream(new
        // FileInputStream(filename));
        InputStream is = ctx.getResources().openRawResource(resId);

        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    /*
    cosa succede qui prova a dire di leggere il tasto
     */
    private void tastoPremuto(View view) {
        TextView myButton = (TextView) view;
        iniziaLettura(myButton.getText().toString());
        if (myButton != null && (myButton.getText().toString().length() > 0) && lavagnaRestante != null) {
            if (lavagnaRestante.startsWith(myButton.getText().toString())) {
                if (key_sound && keycorrect != null)
                    keycorrect.start();

                lavagnaRestante = lavagnaRestante.substring(1);
                edit_text.setText(edit_text.getText().toString() + myButton.getText().toString());
                edit_text.setSelection(edit_text.length());

                // avoid last space
                if (lavagnaRestante.length() > 0) {

                    lavagna.setText(Html.fromHtml("<font color='#009688'>" + edit_text.getText().toString().replaceAll(".(?!$)", "$0 ") + "</font><font color='#FFFFFF'> " + lavagnaRestante.replaceAll(".(?!$)", "$0 ") + "</font>"));
                } else {
                    lavagna.setText(Html.fromHtml("<font color='#009688'>"
                            + edit_text.getText().toString().replaceAll(".(?!$)", "$0 ") + "</font><font color='#FFFFFF'></font>"));
                }
            } else {
                if (key_sound && keywrong != null)
                    keywrong.start();
                errori++;
            }

            if (lavagnaRestante.length() < 1) {

                if (key_sound && done != null) {
                    done.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            cliccaPlay();
                        }
                    });
                    done.start();
                } else {


                    cliccaPlay();
                }
            }

            if (key_settings == 0) {
                nascondiTasti(lavagnaRestante);
            } else if (key_settings == 1) {
                nascondiTasti("1234567890QWERTYUIOPASDFGHJKLZXCVBNM");
            } else {
                nascondiTasti(lavagnaRestante + distrattori);
            }
        }

    }

    private void nascondiTasti(String myString) {
        if (myString.contains("1")) {
            num1.setText("1");
        } else {
            num1.setText("");
        }

        if (myString.contains("2")) {
            num2.setText("2");
        } else {
            num2.setText("");
        }

        if (myString.contains("3")) {
            num3.setText("3");
        } else {
            num3.setText("");
        }

        if (myString.contains("4")) {
            num4.setText("4");
        } else {
            num4.setText("");
        }

        if (myString.contains("5")) {
            num5.setText("5");
        } else {
            num5.setText("");
        }

        if (myString.contains("6")) {
            num6.setText("6");
        } else {
            num6.setText("");
        }

        if (myString.contains("7")) {
            num7.setText("7");
        } else {
            num7.setText("");
        }

        if (myString.contains("8")) {
            num8.setText("8");
        } else {
            num8.setText("");
        }

        if (myString.contains("9")) {
            num9.setText("9");
        } else {
            num9.setText("");
        }

        if (myString.contains("0")) {
            num0.setText("0");
        } else {
            num0.setText("");
        }

        if (myString.contains("A")) {
            btnA.setText("A");
        } else {
            btnA.setText("");
        }

        if (myString.contains("B")) {
            btnB.setText("B");
        } else {
            btnB.setText("");
        }

        if (myString.contains("C")) {
            btnC.setText("C");
        } else {
            btnC.setText("");
        }

        if (myString.contains("D")) {
            btnD.setText("D");
        } else {
            btnD.setText("");
        }

        if (myString.contains("E")) {
            btnE.setText("E");
        } else {
            btnE.setText("");
        }

        if (myString.contains("F")) {
            btnF.setText("F");
        } else {
            btnF.setText("");
        }

        if (myString.contains("G")) {
            btnG.setText("G");
        } else {
            btnG.setText("");
        }

        if (myString.contains("H")) {
            btnH.setText("H");
        } else {
            btnH.setText("");
        }

        if (myString.contains("I")) {
            btnI.setText("I");
        } else {
            btnI.setText("");
        }

        if (myString.contains("J")) {
            btnJ.setText("J");
        } else {
            btnJ.setText("");
        }

        if (myString.contains("K")) {
            btnK.setText("K");
        } else {
            btnK.setText("");
        }

        if (myString.contains("L")) {
            btnL.setText("L");
        } else {
            btnL.setText("");
        }

        if (myString.contains("M")) {
            btnM.setText("M");
        } else {
            btnM.setText("");
        }

        if (myString.contains("N")) {
            btnN.setText("N");
        } else {
            btnN.setText("");
        }

        if (myString.contains("O")) {
            btnO.setText("O");
        } else {
            btnO.setText("");
        }

        if (myString.contains("P")) {
            btnP.setText("P");
        } else {
            btnP.setText("");
        }

        if (myString.contains("Q")) {
            btnQ.setText("Q");
        } else {
            btnQ.setText("");
        }

        if (myString.contains("R")) {
            btnR.setText("R");
        } else {
            btnR.setText("");
        }

        if (myString.contains("S")) {
            btnS.setText("S");
        } else {
            btnS.setText("");
        }

        if (myString.contains("T")) {
            btnT.setText("T");
        } else {
            btnT.setText("");
        }

        if (myString.contains("U")) {
            btnU.setText("U");
        } else {
            btnU.setText("");
        }

        if (myString.contains("V")) {
            btnV.setText("V");
        } else {
            btnV.setText("");
        }

        if (myString.contains("W")) {
            btnW.setText("W");
        } else {
            btnW.setText("");
        }

        if (myString.contains("X")) {
            btnX.setText("X");
        } else {
            btnX.setText("");
        }

        if (myString.contains("Y")) {
            btnY.setText("Y");
        } else {
            btnY.setText("");
        }

        if (myString.contains("Z")) {
            btnZ.setText("Z");
        } else {
            btnZ.setText("");
        }

    }

    private void setup() {
        lavagna = (TextView) findViewById(R.id.lavagna);
        edit_text = (EditText) findViewById(R.id.edit_text);
        ImageButton menu = (ImageButton) findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenuAttach(v);
            }
        });

        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);
        num0 = (Button) findViewById(R.id.num0);

        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        btnE = (Button) findViewById(R.id.btnE);
        btnF = (Button) findViewById(R.id.btnF);
        btnG = (Button) findViewById(R.id.btnG);
        btnH = (Button) findViewById(R.id.btnH);
        btnI = (Button) findViewById(R.id.btnI);
        btnJ = (Button) findViewById(R.id.btnJ);
        btnK = (Button) findViewById(R.id.btnK);
        btnL = (Button) findViewById(R.id.btnL);
        btnM = (Button) findViewById(R.id.btnM);
        btnN = (Button) findViewById(R.id.btnN);
        btnO = (Button) findViewById(R.id.btnO);
        btnP = (Button) findViewById(R.id.btnP);
        btnQ = (Button) findViewById(R.id.btnQ);
        btnR = (Button) findViewById(R.id.btnR);
        btnS = (Button) findViewById(R.id.btnS);
        btnT = (Button) findViewById(R.id.btnT);
        btnU = (Button) findViewById(R.id.btnU);
        btnV = (Button) findViewById(R.id.btnV);
        btnW = (Button) findViewById(R.id.btnW);
        btnX = (Button) findViewById(R.id.btnX);
        btnY = (Button) findViewById(R.id.btnY);
        btnZ = (Button) findViewById(R.id.btnZ);

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tastoPremuto(view);
            }
        });

        lavagna.setTextColor(getResources().getColor(android.R.color.white));

       /* try {
            Typeface font = Typeface.createFromAsset(getAssets(),
                    "carattere.ttf");
            if (font != null) {
                lavagna.setTypeface(font);
            }
        } catch (Exception e) {
            e.getStackTrace();
        } CLARA*/

        edit_text.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        edit_text.setLongClickable(false);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            edit_text.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                public void onDestroyActionMode(ActionMode mode) {
                }

                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }
            });
            edit_text.setTextIsSelectable(false);
        } else {
            edit_text.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                @Override
                public void onCreateContextMenu(ContextMenu menu, View v,
                                                ContextMenu.ContextMenuInfo menuInfo) {
                    menu.clear();
                }
            });
        }

        edit_text.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showPopMenuAttach(View view) {
        // Log.d("base-sdk", "showPopMenu");
        // Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(this, view);

        // Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());


        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.tts) {
                    // check per le notifiche
                    if (item.isChecked()) {
                        item.setChecked(false);
                        speak = false;
                    } else {
                        item.setChecked(true);
                        speak = true;
                    }
                    editor.putBoolean(salvaSpeak, speak);
                    editor.apply();
                    return true;
                } else if (id == R.id.action_tts_settings) {
                    ttsSettings();
                } else if (id == R.id.action_key_settings) {
                    keySettings();
                } else if (id == R.id.action_gen_settings) {
                    genSettings();

                }
                return false;
            }
        });

        Menu menu = popup.getMenu();

        menu.findItem(R.id.tts).setVisible(canSpeak);
        menu.findItem(R.id.action_tts_settings).setVisible(canSpeak);

        if (speak) {
            menu.findItem(R.id.tts).setChecked(true);
            menu.findItem(R.id.tts).setTitle(
                    getString(R.string.TtsOn));
            menu.findItem(R.id.tts).setIcon(R.drawable.tts_on);
        } else {
            menu.findItem(R.id.tts).setChecked(false);
            menu.findItem(R.id.tts).setTitle(
                    getString(R.string.TtsOff));
            menu.findItem(R.id.tts).setIcon(R.drawable.tts_off);
        }

       /* if (key_sound) {
            menu.findItem(R.id.key).setChecked(true);
            menu.findItem(R.id.key).setTitle(
                    getString(R.string.key_on));
            menu.findItem(R.id.key).setIcon(R.drawable.tts_on);
        } else {
            menu.findItem(R.id.key).setChecked(false);
            menu.findItem(R.id.key).setTitle(
                    getString(R.string.key_off));
            menu.findItem(R.id.key).setIcon(R.drawable.tts_off);
        }*/

        // showing popup menu
        popup.show();
    }

    private void keySettings() {
        final View keyView = View.inflate(this, R.layout.key_settings_layout, null);
        final RadioButton only_necessary = (RadioButton) keyView.findViewById(R.id.only_necessary);
        final RadioButton all_key = (RadioButton) keyView.findViewById(R.id.all_key);
        final RadioButton distractors = (RadioButton) keyView.findViewById(R.id.distractors);

        if (key_settings == 0) {
            only_necessary.setChecked(true);
        } else if (key_settings == 1) {
            all_key.setChecked(true);
        } else {
            distractors.setChecked(true);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setIcon(R.drawable.key_settingsd)
                .setTitle(
                        Html.fromHtml("<font color='#e49800'>"
                                + getString(R.string.Key_settings)
                                + "</font>"))
                .setView(keyView)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        notYet = true;
                        if (only_necessary.isChecked()) {
                            key_settings = 0;
                        } else if (all_key.isChecked()) {
                            key_settings = 1;
                        } else {
                            key_settings = 2;
                        }
                        cliccaPlay();
                        dialog.cancel();
                    }
                })
                .setNegativeButton(getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog keyDialog = builder.create();
        keyDialog.show();
    }

    //impostazione del generatore parole
    private void genSettings() {
        final View editView = View.inflate(this, R.layout.gen_settings_layout, null);
        final RadioButton only_words = (RadioButton) editView
                .findViewById(R.id.only_words);
        final RadioButton only_numbers = (RadioButton) editView
                .findViewById(R.id.only_numbers);
        final RadioButton both = (RadioButton) editView
                .findViewById(R.id.both);

        if (gen_settings == 0) {
            only_words.setChecked(true);
        } else if (gen_settings == 1) {
            only_numbers.setChecked(true);
        } else {
            both.setChecked(true);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setIcon(R.drawable.gen_settingsd)
                .setTitle(
                        Html.fromHtml("<font color='#e49800'>"
                                + getString(R.string.Gen_settings)
                                + "</font>"))
                .setView(editView)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        notYet = true;
                        if (only_words.isChecked()) {
                            gen_settings = 0;
                        } else if (only_numbers.isChecked()) {
                            gen_settings = 1;
                        } else {
                            gen_settings = 2;
                        }
                        cliccaPlay();
                        dialog.cancel();
                    }
                })
                .setNegativeButton(getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog genDialog = builder.create();
        genDialog.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_action_exit)
                .setTitle(
                        Html.fromHtml("<font color='#009688'>"
                                + getString(android.R.string.dialog_alert_title)
                                + "</font>"))
                .setMessage(getString(R.string.num_words) + ": " + (record - 1) + "\nNumero errori:" + errori + "\n" + getString(R.string.exit))
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                if (notYet) {
                                    editor.putInt(salvaKey, key_settings);
                                    editor.putInt(salvaGen, gen_settings);
                                    editor.apply();
                                }
                                //moveTaskToBack(true);

                                finish();
                                System.exit(0);
                            }
                        }).setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public void onPause() {
        keycorrect = null;
        keywrong = null;
        done = null;

        super.onPause();
        // Remove a bright lock
        getWindow().clearFlags(
                android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        interrompiLettura();
        AdIcona = true;
    }

    /*
    qui con i file WAV di miscrosoft ibm per gestire audio
     */
    @Override
    public void onResume() {
        super.onResume();
        // Create a bright lock
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //keycorrect = MediaPlayer.create(ImparaTastieraActivity.this, R.raw.keycorrect);
        // keywrong = MediaPlayer.create(ImparaTastieraActivity.this, R.raw.keywrong);
        done = MediaPlayer.create(ImparaTastieraActivity.this, R.raw.done);


        AdIcona = false;

        if (canSpeak && speak) {
            iniziaLettura(wordNow);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onInit(int status) {
        mStatus = status;
        setTts(mTts, paese);
    }

    public void setTts(TextToSpeech tts, short paese) {
        this.mTts = tts;

        if (mStatus == SUCCESS) {
            int result = mTts.setLanguage(recuperaLocale(paese));
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                canSpeak = false;
                // buttonPlay.setEnabled(false);
                aggiornaMenu();
                // Toast.makeText(context,
                // (getString(R.string.LanguageMissing)),Toast.LENGTH_SHORT).show();
            } else {
                canSpeak = true;
                // buttonPlay.setEnabled(true);
                aggiornaMenu();
            }
        } else {
            canSpeak = false;
            // buttonPlay.setEnabled(false);
            aggiornaMenu();
            // Toast.makeText(context,
            // (getString(R.string.TtsError)),Toast.LENGTH_SHORT).show();
        }
    }

    private void aggiornaMenu() {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            invalidateOptionsMenu();
        }
    }

    private Locale recuperaLocale(short paese) {
        switch (paese) {
            case 1:
                return new Locale("bg", "BG");
            case 2:
                return Locale.GERMAN;
            case 3:
                return Locale.ENGLISH;
            case 4:
                return new Locale("es", "ES");
            case 5:
                return Locale.FRENCH;
            case 6:
                return Locale.ITALIAN;
            case 7:
                return new Locale("pl", "PL");
            case 8:
                return new Locale("pt", "PT");
        }
        return Locale.ROOT;
    }

    private void ttsSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Intent intent = new Intent();
            intent.setAction("com.android.settings.TTS_SETTINGS");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        } else {
            ComponentName componentToLaunch = new ComponentName(
                    "com.android.settings",
                    "com.android.settings.TextToSpeechSettings");
            Intent intent = new Intent();
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentToLaunch);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void iniziaLettura(String testo) {
        System.out.println("\nwords: " + canSpeak + " " + mTts + " " + AdIcona);
        if ((canSpeak) && (mTts != null) && (!AdIcona)) {
            System.out.println("sono entrato \n");
            if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
                mTts.speak(testo,
                        TextToSpeech.QUEUE_FLUSH, null);
                System.out.println("ma allora \n");
            } else {
                mTts.speak(testo,
                        TextToSpeech.QUEUE_FLUSH, null, testo);
                System.out.println(" ENTRO MA NON LEGGO\n");
            }
        }

    }

    private void interrompiLettura() {
        if ((canSpeak) && (mTts != null)) {
            mTts.stop();
        }
    }

    public class Data {
        int Num;

        Data(int N) {
            Num = N;
        }

        public int toNum() {
            return Num;
        }
    }



    /*
    private Charset getDetectedEncoding(InputStream is) throws IOException {
        UniversalDetector detector = new UniversalDetector(null);
        byte[] buf = new byte[4096];
        int nread;
        while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();

        if (null == encoding) {
            encoding = "UTF-8";
        }
        // Log.d("Charset", encoding);

        if (Charset.isSupported(encoding)) {
            return Charset.forName(encoding);
        } else {
            // Log.d("Charset", encoding + " non supportato!");
            return Charset.forName("UTF-8");
        }
    }*/

}

