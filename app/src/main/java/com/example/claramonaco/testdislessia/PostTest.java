package com.example.claramonaco.testdislessia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;

import me.grantland.widget.AutofitTextView;

/**
 * Created by ClaraMonaco on 28/05/2018.
 */

public class PostTest extends AppCompatActivity {


    private TextView frase_feedback;
    private Button Strongly_Disagree, Disagree,Neutral,Agree,Strongly_Agree;
    private String stringa, frase;
    private BufferedReader read;
    SQLiteHandler db;
    private String idAlunno, regione,groupId, genere, eta ,livelloMax,errate,saltate,corrette,tempo,errliv1,errliv2,errliv3,errliv4,data;
    private int i=0;
    private String[] domande;
    private String[] infoUtente;
    private String[] risu;
    private String fileName="";
    private BufferedReader reader5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_test);

        fileName=getIntent().getStringExtra("fileName");

        frase_feedback=(TextView)findViewById(R.id.frase_feedback);

        Strongly_Disagree=(Button)findViewById(R.id.Strongly_Disagree);
        Disagree=(Button)findViewById(R.id.Disagree);
        Neutral=(Button)findViewById(R.id.Normal);
        Agree=(Button)findViewById(R.id.Agree);
        Strongly_Agree=(Button)findViewById(R.id.Strongly_Agree);
        db= new SQLiteHandler(getApplicationContext());
        idAlunno="";
        domande= new String[10];

        infoUtente = new String[10];
        risu = new String[10];

        try {
            read= new BufferedReader(new InputStreamReader(getAssets().open("postest.txt"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            frase=read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        frase_feedback.setText(frase);
    }

    public void risposta(View v) throws IOException {
        Button b=(Button)v;
        i++;
        if(b.getId()==Strongly_Disagree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Strongly_Disagree");
            osw.flush();
            osw.close();
            domande[i]="1";
        }
        if(b.getId()==Disagree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Disagree");
            osw.flush();
            osw.close();
            domande[i]="2";
        }

        if(b.getId()==Neutral.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Neutral");
            osw.flush();
            osw.close();
            domande[i]="3";
        }

        if(b.getId()==Agree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Agree");
            osw.flush();
            osw.close();
            domande[i]="4";
        }

        if(b.getId()==Strongly_Agree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Strongly_Agree");
            osw.flush();
            osw.close();
            domande[i]="5";
        }
        try {
            frase=read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==8){
            finish();
            InputStream idA = openFileInput("infoUtente.txt");
            InputStream stat = openFileInput("risultati.txt");
            InputStream dat = openFileInput("data.txt");

            if(stat!=null){
                int q=0;
                InputStreamReader inputStreamReader = new InputStreamReader(stat);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine()) != null) {
                    risu[q]=tempString;
                    Log.d("-------->RISU", "risposta: "+risu[q]);
                    q++;
                }
                stat.close();
            }else{
                idAlunno="non definito";
            }

            if(idA!=null){
                int p=0;
                InputStreamReader inputStreamReader = new InputStreamReader(idA);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine()) != null) {
                    infoUtente[p]=tempString;
                    Log.d("-------->INFOUTENTE", "risposta: "+infoUtente[p]);
                    p++;
                }
                idA.close();
            }else{
                idAlunno="non definito";
            }
            if(dat!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(dat);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine()) != null) {
                    data=tempString;
                    Log.d("-------->DATA", "risposta: "+data);
                }
                dat.close();
            }else{
                data="non definito";
            }

             db.addStatistic(""+infoUtente[2],""+infoUtente[0],""+infoUtente[1],""+data,""+infoUtente[5],""+infoUtente[6],
                     ""+risu[3],""+risu[1],""+risu[2],""+risu[0],""+risu[4],""+risu[5],""+risu[6],""+risu[7],""+risu[8],
                     ""+domande[1],""+domande[2],""+domande[3],""+domande[4],""+domande[5],""+domande[6],""+domande[7],""+domande[8],
                     ""+infoUtente[3],""+infoUtente[4],""+fileName);

            Intent i = new Intent(getApplicationContext(), ErrorView.class);
            i.putExtra("fileName",fileName);
            startActivity(i);

        }else{

            frase_feedback.setText(frase);
        }
    }

}
