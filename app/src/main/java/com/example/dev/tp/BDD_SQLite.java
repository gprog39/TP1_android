package com.example.dev.tp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DEV on 14/11/2017.
 */

public class BDD_SQLite extends SQLiteOpenHelper {

    private static final String table_utilisateurs = "table_utilisateurs";
    private static final String COL_ID = "id_utilisateur";
    private static final String COL_nom = "nom";
    private static final String COL_prenom = "prenom";
    private static final String COL_mail = "email";
    private static final String COL_numTel = "numero_telephone";

    private static final String sql_create_bdd  = "CREATE TABLE " + table_utilisateurs + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_nom + " VARCHAR(100) NOT NULL , " + COL_prenom + " VARCHAR(50) NOT NULL , " + COL_mail + " VARCHAR(200), " + COL_numTel + " VARCHAR(10);";

    public BDD_SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //création de la table utilisateur à partir de la variable sql_create_bdd
        db.execSQL(sql_create_bdd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on supprime la table et on la recréé lors d'une montée de version
        db.execSQL("DROP TABLE " + table_utilisateurs + " ;" );
        onCreate(db);
    }
}
