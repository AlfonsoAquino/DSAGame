package com.example.claramonaco.testdislessia;

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

public class SendGradimento extends AsyncTask<String, Integer, Void> {

    private final String TAG="GradimentoThread";
    private String idAlunno, domanda1, domanda2, domanda3, domanda4, domanda5, domanda6, domanda7, domanda8 ;
    private Context context;
    final String PREFS_NAME="check";

    public SendGradimento(Context context){
        this.context=context;
    }

    @Override
    protected Void doInBackground(String... strings) {

        if(strings == null || strings.length<7){
            throw new IllegalArgumentException("You should offer 11 params, the first for the image source url, and the other for the destination file save path");
        }else{

            for (String str: strings) {
                Log.i(TAG, "statistics: "+str);
            }
            idAlunno=strings[0];
            domanda1=strings[1];
            domanda2=strings[2];
            domanda3=strings[3];
            domanda4=strings[4];
            domanda5=strings[5];
            domanda6=strings[6];
            domanda7=strings[7];
            domanda8=strings[8];

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    Config.Gradimento, new Response.Listener<String>() {

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

                            Log.i(TAG, "------------>erroreCaricamentoGradimento");
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
                    params.put("idStatistica",idAlunno);
                    params.put("domanda1", domanda1);
                    params.put("domanda2", domanda2);
                    params.put("domanda3", domanda3);
                    params.put("domanda4", domanda4);
                    params.put("domanda5", domanda5);
                    params.put("domanda6", domanda6);
                    params.put("domanda7", domanda7);
                    params.put("domanda8", domanda8);

                    return params;
                }
            };
            // Adding request to request queue
            AppSingleton.getInstance(context).addToRequestQueue(strReq, "statistics");

            return null;
        }
    }

    protected void onPreExecute(){
        idAlunno="";
        domanda1="";
        domanda2="";
        domanda3="";
        domanda4="";
        domanda5="";
        domanda6="";
        domanda7="";
        domanda8="";
    }
}
