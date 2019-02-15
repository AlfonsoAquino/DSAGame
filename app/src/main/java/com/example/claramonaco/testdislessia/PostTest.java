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
    private String idAlunno;
    private int i=0;
    private String[] domande;

    private BufferedReader reader5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_test);

        frase_feedback=(TextView)findViewById(R.id.frase_feedback);

        Strongly_Disagree=(Button)findViewById(R.id.Strongly_Disagree);
        Disagree=(Button)findViewById(R.id.Disagree);
        Neutral=(Button)findViewById(R.id.Normal);
        Agree=(Button)findViewById(R.id.Agree);
        Strongly_Agree=(Button)findViewById(R.id.Strongly_Agree);
        db= new SQLiteHandler(getApplicationContext());
        idAlunno="";
        domande= new String[9];

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
//            OutputStreamWriter osw1 = new OutputStreamWriter(this.openFileOutput("gradimento_test.txt", Context.MODE_APPEND));
//            osw1.write("\nStrongly_Disagree");
//            osw1.flush();
//            osw1.close();
            domande[i]="Strongly_Disagree";
        }
        if(b.getId()==Disagree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Disagree");
            osw.flush();
            osw.close();
//            OutputStreamWriter osw1 = new OutputStreamWriter(this.openFileOutput("gradimento_test.txt", Context.MODE_APPEND));
//            osw1.write("\nDisagree");
//            osw1.flush();
//            osw1.close();
            domande[i]="Disagree";
        }

        if(b.getId()==Neutral.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Neutral");
            osw.flush();
            osw.close();
//            OutputStreamWriter osw1 = new OutputStreamWriter(this.openFileOutput("gradimento_test.txt", Context.MODE_APPEND));
//            osw1.write("\nNeutral");
//            osw1.flush();
//            osw1.close();
            domande[i]="Neutral";
        }

        if(b.getId()==Agree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Agree");
            osw.flush();
            osw.close();
//            OutputStreamWriter osw1 = new OutputStreamWriter(this.openFileOutput("gradimento_test.txt", Context.MODE_APPEND));
//            osw1.write("\nAgree");
//            osw1.flush();
//            osw1.close();
            domande[i]="Agree";
        }

        if(b.getId()==Strongly_Agree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Strongly_Agree");
            osw.flush();
            osw.close();
//            OutputStreamWriter osw1 = new OutputStreamWriter(this.openFileOutput("gradimento_test.txt", Context.MODE_APPEND));
//            osw1.write("\nStrongly_Agree");
//            osw1.flush();
//            osw1.close();
            domande[i]="Strongly_Agree";
        }
        try {
            frase=read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==8){
            finish();

//            InputStream gi = openFileInput("gradimento_test.txt");
            InputStream idA = openFileInput("idAlunno.txt");

//            if(gi!=null){
//                InputStreamReader inputStreamReader = new InputStreamReader(gi);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String tempString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//                while ((tempString = bufferedReader.readLine()) != null) {
//                    Log.d("addadadasdasdasdas>>>>>",""+tempString);
//                    domande[j]=tempString;
//                    j++;
//                }
//                gi.close();
//            }

            if(idA!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(idA);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine()) != null) {
                    idAlunno=tempString;
                }
                idA.close();
            }else{
                idAlunno="non definito";
            }

            db.addGradimento(""+idAlunno,""+domande[1],""+domande[2],""+domande[3],""+domande[4],""+domande[5],""+domande[6],""+domande[7],""+domande[8]);
//            db.addGradimento(""+ idAlunno,"","","","","","","","");
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

        }else{

            frase_feedback.setText(frase);
        }
    }

}
