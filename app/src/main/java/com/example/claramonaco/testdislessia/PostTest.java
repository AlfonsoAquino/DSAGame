package com.example.claramonaco.testdislessia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import me.grantland.widget.AutofitTextView;

/**
 * Created by ClaraMonaco on 28/05/2018.
 */

public class PostTest extends AppCompatActivity {


    private TextView frase_feedback;
    private Button Strongly_Disagree, Disagree,Neutral,Agree,Strongly_Agree;
    private String stringa, frase;
    private BufferedReader read;

    private int i=0;

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
        }
        if(b.getId()==Disagree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Disagree");
            osw.flush();
            osw.close();
        }

        if(b.getId()==Neutral.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Neutral");
            osw.flush();
            osw.close();
        }

        if(b.getId()==Agree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Agree");
            osw.flush();
            osw.close();
        }

        if(b.getId()==Strongly_Agree.getId()){
            OutputStreamWriter osw = new OutputStreamWriter(this.openFileOutput("statistiche_test.txt", Context.MODE_APPEND));
            osw.write("\n\n--- feedback finale---\ndomanda"+i+": "+frase+"\nrisposta: Strongly_Agree");
            osw.flush();
            osw.close();
        }
        try {
            frase=read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==8){
          finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }else{

        frase_feedback.setText(frase);
        }
    }

}
