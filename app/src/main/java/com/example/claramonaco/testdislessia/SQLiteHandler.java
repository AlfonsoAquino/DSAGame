package com.example.claramonaco.testdislessia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String keyGroupId="groupId";
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

    public SQLiteHandler(Context context){

        super(context, dbName, null, dbVersion);
        statistics = new ArrayList<>();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatisticTable = "CREATE TABLE IF NOT EXISTS "+ tableName +"("+keyID+" INTEGER PRIMARY KEY, "+keyGroupId+" TEXT, "+keyIdAlunno+" TEXT, "+keyRegione+" TEXT, "+keyData+" TEXT, " +keyGenere+" TEXT, "+keyEta+" TEXT, "+keylivMax+" TEXT, "+keyErr+" TEXT, "+keySkip+" TEXT, "+keyEsatte+" TEXT, "+keyTempo+" TEXT, "+keyErrLiv1+" TEXT, "+keyErrLiv2+" TEXT, "+keyErrLiv3+" TEXT, "+keyErrLiv4+" TEXT)";
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
    public void addStatistic(String groupId, String idAlunno, String regione, String data, String genere, String eta,int numCorrette, int numSbagliate, int numSaltate, int livelloRaggiunto, String tempoImpiegato, int errore1, int errore2, int errore3, int errore4 ){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(keyGroupId, groupId );
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

        db.insert(tableName, null, values);
        db.close();

        Log.d(TAG, "New statistic inserted into sqlite");
    }

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
                    cursor.getString(cursor.getColumnIndex(keyIdAlunno)),
                    cursor.getString(cursor.getColumnIndex(keyRegione)),
                    cursor.getString(cursor.getColumnIndex(keyData)),
                    cursor.getString(cursor.getColumnIndex(keyGenere)),
                    cursor.getString(cursor.getColumnIndex(keyEta)),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyEsatte))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyErr))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keySkip))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keylivMax))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyID))),
                    cursor.getString(cursor.getColumnIndex(keyTempo)),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyErrLiv1))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyErrLiv2))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyErrLiv3))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(keyErrLiv4))));
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
