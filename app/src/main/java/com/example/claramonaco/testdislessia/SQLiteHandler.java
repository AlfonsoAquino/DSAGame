package com.example.claramonaco.testdislessia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG= SQLiteOpenHelper.class.getSimpleName();

    //db data
    private static final int dbVersion=1;
    private static final String dbName="statistics";
    //table data
    private static final String tableName="statistica";
    private static final String keyID="id";
    private static final String keyGenere="genere";
    private static final String keyEta="eta";
    private static final String keylivMax="livelloRaggiunto";
    private static final String keyErr="sbagliate";
    private static final String keySkip="saltate";
    private static final String keyEsatte="esatte";
    private static final String keyTempo="tempo";
    private static final String keyErrLiv1="errLiv1";
    private static final String keyErrLiv2="errLiv2";
    private static final String keyErrLiv3="errLiv3";
    private static final String keyErrLiv4="errLiv4";

    private ArrayList<Statistica> statistics;

    public SQLiteHandler(Context context){

        super(context, dbName, null, dbVersion);
        statistics = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatisticTable = "CREATE TABLE IF NOT EXISTS "+ tableName +"("+keyID+" INTEGER PRIMARY KEY," +keyGenere+" TEXT, "+keyEta+" TEXT, "+keylivMax+" TEXT, "+keyErr+" TEXT, "+keySkip+" TEXT, "+keyEsatte+" TEXT, "+keyTempo+" TEXT, "+keyErrLiv1+" TEXT, "+keyErrLiv2+" TEXT, "+keyErrLiv3+" TEXT, "+keyErrLiv4+" TEXT)";
        db.execSQL(createStatisticTable);

        Log.d(TAG, "database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older version of table
        db.execSQL("DROP TABLE IF EXISTS " + tableName );

        //create new table
        onCreate(db);
    }

    /**
     * Storing statistics info
     */
    public void addStatistic(String genere, String eta,int numCorrette, int numSbagliate, int numSaltate, int livelloRaggiunto, int tempoImpiegato, int errore1, int errore2, int errore3, int errore4 ){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(keyGenere, genere);
        values.put(keyEta, eta);
        values.put(keylivMax, livelloRaggiunto);
        values.put(keyErr, numSbagliate);
        values.put(keySkip, numSaltate);
        values.put(keyEsatte, numCorrette);
        values.put(keyTempo, tempoImpiegato);
        values.put(keyErrLiv1, errore1 );
        values.put(keyErrLiv2, errore2 );
        values.put(keyErrLiv3, errore3 );
        values.put(keyErrLiv4, errore4 );

        db.insert(tableName, null, values);
        db.close();

        Log.d(TAG, "New statistic inserted into sqlite");
    }

    //RETURN METHOD
}
