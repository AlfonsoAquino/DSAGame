package com.example.alfonsoaquino.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by ClaraMonaco on 20/03/2018.
 */

public class TrovaMaxActivity extends AppCompatActivity {
    private Button btnuva, btnmela, btnpesca, melograno, livel;
    private TextView error, esatto;
    private RelativeLayout rl1;
    private String numero, n1, n2, n3, n4;
    private int nI1, nI2, nI3, nI4, max, numeroI, i;
    private int NRandom[];
    private int casuale;
    private boolean val;
    private int corretto, scorretto;
    private Animation[] animations = new Animation[5];
    private MediaPlayer keycorrect, keywrong, done;
    private boolean randomDecisione = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trova_max);

        btnuva = (Button) findViewById(R.id.btn_uva);
        btnmela = (Button) findViewById(R.id.btn_mela);
        btnpesca = (Button) findViewById(R.id.btn_pesca);
        melograno = (Button) findViewById(R.id.btn_melograno);
        livel = (Button) findViewById(R.id.livel);

        rl1 = (RelativeLayout) findViewById(R.id.rl1);

        NRandom = new int[4];


        animations[0] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotazione);
        animations[1] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.traslazione);
        animations[2] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaling);
        animations[3] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trasparenza);
        animations[4] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.combo);

        corretto = 0;
        scorretto = 0;

        esatto = (TextView) findViewById(R.id.punteggio_esatto);
        error = (TextView) findViewById(R.id.punteggio_sbagliato);


        //rl1.startAnimation(animations[2]);

        NumeriRandom();
    }

    public void NumeroMax(View v) throws IOException {

        Button b = (Button) v;
        numero = (String) b.getText();
        n1 = (String) btnpesca.getText();
        n2 = (String) btnmela.getText();
        n3 = (String) btnuva.getText();
        n4 = (String) melograno.getText();

        nI1 = Integer.parseInt(n1);
        nI2 = Integer.parseInt(n2);
        nI3 = Integer.parseInt(n3);
        nI4 = Integer.parseInt(n4);
        numeroI = Integer.parseInt(numero);


        max = nI1;
        if (max < nI2) {
            max = nI2;
        }
        if (max < nI3) {
            max = nI3;
        }
        if (max < nI4) {
            max = nI4;
        }


        if (numeroI == max) {
            //Toast.makeText(getApplication(),"Esatto ",Toast.LENGTH_SHORT).show();
            corretto++;
            esatto.setText(+corretto + "");
            // keycorrect.start();
            done.start();
            b.startAnimation(animations[2]);
            NumeriRandom();


        } else {
            scorretto++;
            keywrong.start();
            error.setText(+scorretto + "");
            NumeriRandom();
        }
        if (corretto == 10) {
            Numerocifre(v);
        }

        if (corretto == 20 || corretto + scorretto == 20 || scorretto == 20) {
            // Toast.makeText(getApplication(),"Complimenti campione ",Toast.LENGTH_SHORT).show();


            new AlertDialog.Builder(this)
                    // .setIcon(R.drawable.ic_action_exit)
                    .setTitle(
                            Html.fromHtml("<font color='#009688'>"
                                    + ("Hey campione")
                                    + "</font>"))
                    .setMessage("Numeri corretti: " + corretto + "\nNumeri sbagliati: " + scorretto + "\n Vuoi continuare?\n")
                    .setPositiveButton("si", null)
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            System.exit(0);
                        }
                    }).show();
        }
    }


    public void Numerocifre(View view) {
        if (randomDecisione == false) {
            randomDecisione = true;
            NumeriRandom();
        } else {
            randomDecisione = false;
            NumeriRandom();
        }


           /*
               <Button
            android:id="@+id/cambiacifre"
            android:layout_width="200dp"
            android:layout_height="wrap_content"
            android:layout_below="@id/btn_mela"
            android:layout_marginTop="50dp"
            android:layout_centerHorizontal="true"
            android:background="@drawable/button_clicked"
            android:onClick="Numerocifre"
            android:text="Cambia numero cifre"
            android:textColor="#000"></Button>
            */


    }

    public void risultatoTest(View v) {
        Intent i = new Intent(getApplicationContext(), StatisticheTestActivity.class);
        startActivity(i);
    }

    public void NumeriRandom() {
        i = 0;
        val = true;
        if (randomDecisione == false) {
            livel.setText("1");
            while (val) {
                casuale = (int) (Math.random() * 10);
                if ((NRandom[1] != casuale) && (NRandom[2] != casuale) && (NRandom[0] != casuale) && (NRandom[3] != casuale)) {
                    NRandom[i] = casuale;
                    i++;
                }
                if (i == 4) val = false;
            }
        }

        if (randomDecisione == true) {
            livel.setText("2");
            while (val) {
                casuale = (int) (Math.random() * 100);
                if ((NRandom[1] != casuale) && (NRandom[2] != casuale) && (NRandom[0] != casuale) && (NRandom[3] != casuale)) {
                    NRandom[i] = casuale;
                    i++;
                }
                if (i == 4) val = false;

            }
        }

        btnuva.setText("" + NRandom[0]);
        btnmela.setText("" + NRandom[1]);
        btnpesca.setText("" + NRandom[2]);
        melograno.setText("" + NRandom[3]);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Create a bright lock
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        keycorrect = MediaPlayer.create(TrovaMaxActivity.this, R.raw.keycorrect);
        keywrong = MediaPlayer.create(TrovaMaxActivity.this, R.raw.keywrong);
        done = MediaPlayer.create(TrovaMaxActivity.this, R.raw.done);
    }

}