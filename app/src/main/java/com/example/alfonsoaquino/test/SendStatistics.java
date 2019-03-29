package com.example.alfonsoaquino.test;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendStatistics extends AsyncTask<String, Integer, Void> {

    private final String TAG="StatisticsThread";
    private String tipoTest, codicePlesso, regione, data,genere, eta,livelloRaggiunto,numCorr,numErr,numSaltate, tempo, errLiv1,errLiv2,errLiv3,errLiv4,
            domanda1,domanda2,domanda3,domanda4,domanda5,domanda6,domanda7,domanda8, codiceClasse, codieRegistro, fileName;
    private Context context;
    final String PREFS_NAME="check";

    public SendStatistics(Context context){
        this.context=context;
    }

    @Override
    protected Void doInBackground(String... strings) {

        if(strings == null || strings.length<10){
            throw new IllegalArgumentException("You should offer 11 params, the first for the image source url, and the other for the destination file save path");
        }else{

            for (String str: strings) {
                Log.i(TAG, "statistics: "+str);
            }
            codicePlesso=strings[0];
            data=strings[1];
            genere=strings[2];
            eta=strings[3];
            livelloRaggiunto=strings[4];
            numErr=strings[5];
            numSaltate=strings[6];
            numCorr=strings[7];
            tempo=strings[8];
            errLiv1=strings[9];
            errLiv2=strings[10];
            errLiv3=strings[11];
            errLiv4=strings[12];
            regione=strings[13];
            domanda1=strings[14];
            domanda2=strings[15];
            domanda3=strings[16];
            domanda4=strings[17];
            domanda5=strings[18];
            domanda6=strings[19];
            domanda7=strings[20];
            domanda8=strings[21];
            tipoTest=strings[22];
            codiceClasse=strings[23];
            codieRegistro=strings[24];
            fileName=strings[25];

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    Config.STATISTICA, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Register Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");

                        if (!error) {

                            Log.i(TAG, "------------>ok");
                            SQLiteHandler db= new SQLiteHandler(context);
                            db.onUpgrade(db.getWritableDatabase(),1,2);
                        } else {

                            Log.i(TAG, "------------>erroreCaricamentoStatistiche");
                            
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Login Error: " + error.getMessage());

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    // Posting params to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("codicePlesso",codicePlesso);
                    params.put("tipoTest",tipoTest);
                    params.put("regione", regione);
                    params.put("data",data);
                    params.put("genere",genere);
                    params.put("eta",eta);
                    params.put("livelloRaggiunto",livelloRaggiunto);
                    params.put("numErr",numErr);
                    params.put("numSaltate",numSaltate);
                    params.put("numCorr",numCorr);
                    params.put("tempo",tempo);
                    params.put("errLiv1",errLiv1);
                    params.put("errLiv2",errLiv2);
                    params.put("errLiv3",errLiv3);
                    params.put("errLiv4",errLiv4);
                    params.put("domanda1",domanda1);
                    params.put("domanda2",domanda2);
                    params.put("domanda3",domanda3);
                    params.put("domanda4",domanda4);
                    params.put("domanda5",domanda5);
                    params.put("domanda6",domanda6);
                    params.put("domanda7",domanda7);
                    params.put("domanda8",domanda8);
                    params.put("codiceClasse",codiceClasse);
                    params.put("codiceRegistro",codieRegistro);
                    params.put("fileName",fileName);

                    return params;
                }
            };
            // Adding request to request queue
            AppSingleton.getInstance(context).addToRequestQueue(strReq, "statistics");

            return null;
        }
    }

    protected void onPreExecute(){

        tipoTest="0";
        codicePlesso="0";
        regione="0";
        data ="0";
        genere="0";
        eta="0";
        livelloRaggiunto="0";
        numCorr="0";
        numErr="0";
        numSaltate="0";
        tempo="0";
        errLiv1="0";
        errLiv2="0";
        errLiv3="0";
        errLiv4="0";
        domanda1="0";
        domanda2="0";
        domanda3="0";
        domanda4="0";
        domanda5="0";
        domanda6="0";
        domanda7="0";
        domanda8="0";
        codieRegistro="0";
        codiceClasse="0";
        fileName="0";
    }

}
