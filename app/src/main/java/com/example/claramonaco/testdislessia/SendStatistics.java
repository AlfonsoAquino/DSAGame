package com.example.claramonaco.testdislessia;

import android.content.Context;
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
    private String genere, eta,livelloRaggiunto, idPaziente,numCorr,numErr,numSaltate, tempo, errLiv1,errLiv2,errLiv3,errLiv4 ;
    private Context context;

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
            genere=strings[0];
            eta=strings[1];
            livelloRaggiunto=strings[2];
            numErr=strings[3];
            numSaltate=strings[4];
            numCorr=strings[5];
            tempo=strings[6];
            errLiv1=strings[7];
            errLiv2=strings[8];
            errLiv3=strings[9];
            errLiv4=strings[10];

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
