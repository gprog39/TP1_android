package com.example.dev.tp;

/**
 * Created by DEV on 14/11/2017.
 */

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTel;

    public Utilisateur() {}
    public Utilisateur(String nom, String prenom, String mail, String numTel){
        this.nom = nom;
        this.prenom = prenom;
        this.email = mail;
        this.numeroTel = numTel;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
}
