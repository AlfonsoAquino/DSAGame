package com.example.claramonaco.testdislessia;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendStatistics extends AsyncTask<String, Integer, Void> {

    private final String TAG="StatisticsThread";
    private String groupId, idAlunno, regione, data,genere, eta,livelloRaggiunto, idPaziente,numCorr,numErr,numSaltate, tempo, errLiv1,errLiv2,errLiv3,errLiv4 ;
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
            groupId=strings[0];
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
            idAlunno=strings[13];
            regione=strings[14];

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
                    params.put("groupId",groupId);
                    params.put("idAlunno",idAlunno);
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

                    return params;
                }
            };
            // Adding request to request queue
            AppSingleton.getInstance(context).addToRequestQueue(strReq, "statistics");

            return null;
        }
    }

    protected void onPreExecute(){

        groupId="0";
        idAlunno="0";
        regione="0";
        data ="0";
        genere="0";
        eta="0";
        livelloRaggiunto="0";
        idPaziente="0";
        numCorr="0";
        numErr="0";
        numSaltate="0";
        tempo="0";
        errLiv1="0";
        errLiv2="0";
        errLiv3="0";
        errLiv4="0";
    }

}
