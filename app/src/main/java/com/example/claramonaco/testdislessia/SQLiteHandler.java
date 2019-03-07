package com.example.claramonaco.testdislessia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG= SQLiteOpenHelper.class.getSimpleName();

    //db data
    private static final int dbVersion=1;
    private static final String dbName="statistics";
    //table data
    private static final String tableName="statistica";
    private static final String tableGradimento="gradimento";
    private static final String keyID="id";
    private static final String keyDomanda8 ="domanda8";
    private static final String keyDomanda1 ="domanda1";
    private static final String keyDomanda2 ="domanda2";
    private static final String keyDomanda3 ="domanda3";
    private static final String keyDomanda4 ="domanda4";
    private static final String keyDomanda5 ="domanda5";
    private static final String keyDomanda6 ="domanda6";
    private static final String keyDomanda7 ="domanda7";
    private static final String keyGroupId="groupId";
    private static final String keyTipoTest="tipoTest";
    private static final String keyIdAlunno="idAlunno";
    private static final String keyRegione="regione";
    private static final String keyData="data";
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
    private ArrayList<Gradimento> gradimento;

    public SQLiteHandler(Context context){

        super(context, dbName, null, dbVersion);
        statistics = new ArrayList<>();
        gradimento = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatisticTable = "CREATE TABLE IF NOT EXISTS "+ tableName +"("+keyID+" INTEGER PRIMARY KEY, "+keyGroupId+" TEXT, "+keyIdAlunno+" TEXT, "+keyTipoTest+" TEXT, "+keyRegione+" TEXT, "+keyData+" TEXT, " +keyGenere+" TEXT, "+keyEta+" TEXT, "+keylivMax+" TEXT, "+keyErr+" TEXT, "+keySkip+" TEXT, "+keyEsatte+" TEXT, "+keyTempo+" TEXT, "+keyErrLiv1+" TEXT, "+keyErrLiv2+" TEXT, "+keyErrLiv3+" TEXT, "+keyErrLiv4+" TEXT, "
                +" TEXT, "+ keyDomanda1 +" TEXT, "+ keyDomanda2 +" TEXT, "+ keyDomanda3 +" TEXT, " + keyDomanda4 +" TEXT, "+ keyDomanda5 +" TEXT, "+ keyDomanda6 +" TEXT, "+ keyDomanda7 +" TEXT, "+ keyDomanda8 +" TEXT)";
        db.execSQL(createStatisticTable);
        Log.d(TAG, "database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older version of table
        db.execSQL("DROP TABLE IF EXISTS " + tableName );
        onCreate(db);
    }

    /**
     * Storing statistics info
     */
    public void addStatistic(String groupId, String tipoTest, String idAlunno, String regione, String data, String genere, String eta,String numCorrette, String numSbagliate, String numSaltate, String livelloRaggiunto,
                             String tempoImpiegato, String errore1, String errore2, String errore3, String errore4, String dm1, String dm2, String dm3, String dm4, String dm5, String dm6, String dm7,String dm8){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(keyGroupId, groupId );
        values.put(keyTipoTest, tipoTest );
        values.put(keyIdAlunno, idAlunno );
        values.put(keyRegione, regione );
        values.put(keyData, data);
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
        values.put(keyDomanda1, dm1);
        values.put(keyDomanda2, dm2);
        values.put(keyDomanda3, dm3);
        values.put(keyDomanda4, dm4);
        values.put(keyDomanda5, dm5);
        values.put(keyDomanda6, dm6);
        values.put(keyDomanda7, dm7);
        values.put(keyDomanda8, dm8);


        db.insert(tableName, null, values);
        db.close();

        Log.d(TAG, "New statistic inserted into sqlite");
    }
    /**
     * Storing gradimento info
     */

    //RETURN METHOD
    public ArrayList<Statistica> getStatisticsDetails() {
        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Statistica album;
        while (!cursor.isAfterLast()){

            album=new Statistica(
                    cursor.getString(cursor.getColumnIndex(keyGroupId)),
                    cursor.getString(cursor.getColumnIndex(keyTipoTest)),
                    cursor.getString(cursor.getColumnIndex(keyIdAlunno)),
                    cursor.getString(cursor.getColumnIndex(keyRegione)),
                    cursor.getString(cursor.getColumnIndex(keyData)),
                    cursor.getString(cursor.getColumnIndex(keyGenere)),
                    cursor.getString(cursor.getColumnIndex(keyEta)),
                    cursor.getString(cursor.getColumnIndex(keyEsatte)),
                    cursor.getString(cursor.getColumnIndex(keyErr)),
                    cursor.getString(cursor.getColumnIndex(keySkip)),
                    cursor.getString(cursor.getColumnIndex(keylivMax)),
                    cursor.getString(cursor.getColumnIndex(keyID)),
                    cursor.getString(cursor.getColumnIndex(keyTempo)),
                    cursor.getString(cursor.getColumnIndex(keyErrLiv1)),
                    cursor.getString(cursor.getColumnIndex(keyErrLiv2)),
                    cursor.getString(cursor.getColumnIndex(keyErrLiv3)),
                    cursor.getString(cursor.getColumnIndex(keyErrLiv4)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda1)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda2)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda3)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda4)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda5)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda6)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda7)),
                    cursor.getString(cursor.getColumnIndex(keyDomanda8)));
            Log.i(TAG,"------------->"+album.toString());

            cursor.moveToNext();
            statistics.add(album);
        }

        cursor.close();
        db.close();
        // return user
//        Log.d(TAG, "Fetching user from Sqlite: " + albums.size());

        return statistics;
    }
}
