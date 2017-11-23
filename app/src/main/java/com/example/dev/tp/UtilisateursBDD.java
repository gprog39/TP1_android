package com.example.dev.tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DEV on 14/11/2017.
 */

public class UtilisateursBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "utilisateurs.db";
    private static final String TABLE_UTILISATEURS = "table_utilisateurs";
    private static final String COL_ID = "id_utilisateur";
    private static final int NUM_COL_ID = 0;
    private static final String COL_nom = "nom";
    private static final int NUM_COL_nom = 1;
    private static final String COL_prenom = "prenom";
    private static final int NUM_COL_prenom = 2;
    private static final String COL_mail = "email";
    private static final int NUM_COL_mail = 3;
    private static final String COL_numTel = "numero_telephone";
    private static final int NUM_COL_numTel = 4;

    private SQLiteDatabase bdd;
    private BDD_SQLite bddSQLite;


    public UtilisateursBDD(Context context) {
        //Création de la base de données et de la table utilisateur

        bddSQLite = new BDD_SQLite(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        //on ouvre la BDD en écriture
        bdd = bddSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }
    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUser(Utilisateur utilisateur){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_nom, utilisateur.getNom());
        values.put(COL_prenom, utilisateur.getPrenom());
        values.put(COL_mail, utilisateur.getEmail());
        values.put(COL_numTel, utilisateur.getNumeroTel());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_UTILISATEURS, null, values);
    }

    public int updateUser(int id, Utilisateur utilisateur){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_nom, utilisateur.getNom());
        values.put(COL_prenom, utilisateur.getPrenom());
        values.put(COL_mail, utilisateur.getEmail());
        values.put(COL_numTel, utilisateur.getNumeroTel());
        return bdd.update(TABLE_UTILISATEURS, values, COL_ID + " = " +id, null);
    }

    public int removeUserWithID(int id){
        //Suppression d'un utilisateur de la BDD grâce à l'ID
        return bdd.delete(TABLE_UTILISATEURS, COL_ID + " = " +id, null);
    }

    public Utilisateur getUserWithName(String name){
        //Récupère dans un Cursor les valeurs correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son nom)
        Cursor c = bdd.query(TABLE_UTILISATEURS, new String[] {COL_ID, COL_nom, COL_prenom, COL_mail, COL_numTel}, COL_nom + " LIKE \"" + name +"\"", null, null, null, null);
        return cursorToUser(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private Utilisateur cursorToUser(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Utilisateur user = new Utilisateur();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        user.setId(c.getInt(NUM_COL_ID));
        user.setNom(c.getString(NUM_COL_nom));
        user.setPrenom(c.getString(NUM_COL_prenom));
        user.setEmail(c.getString(NUM_COL_mail));
        user.setNumeroTel(c.getString(NUM_COL_numTel));
        //On ferme le cursor
        c.close();

        //On retourne l'utilisateur
        return user;
    }
}
