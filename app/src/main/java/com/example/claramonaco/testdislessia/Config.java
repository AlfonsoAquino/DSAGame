package com.example.claramonaco.testdislessia;

public class Config {
    //JSON URL
    public static final String STATISTICA = "https://bifurun.000webhostapp.com/statisticaDislessia.php";
    public static final String Gradimento = "https://bifurun.000webhostapp.com/gradimentoTest.php";
    public static final String PREFS_NAME="MyPrefsFile";


    //Tags used in the JSON String
    public static final String TAG_NOME = "nome";
    public static final String TAG_COGNOME = "cognome";
    public static final String TAG_ID = "id";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_PASSWORD = "password";
    public static final String TAG_IDACCOUNT = "idAccount";

    //JSON array name
    public static final String JSON_ARRAY = "result";

    /**
     * le statistiche sono reperite all'interno dei vari file attraverso l'uso di un array che viene riempito secondo questo ordine
     * struttura file infoUtente
     * regione      0
     * idAlunno     1
     * groupId      2
     * genere       3
     * eta          4
     *
     * struttura file risultati
     * livelloMax   0
     * errate       1
     * saltate      2
     * corrette     3
     * tempo        4
     * err liv1     5
     * err liv2     6
     * err liv3     7
     * err liv4     8
     */
}
