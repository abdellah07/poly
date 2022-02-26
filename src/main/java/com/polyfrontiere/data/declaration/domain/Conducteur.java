package com.polyfrontiere.data.declaration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Conducteur {
    private String nom;
    private String prenom;
    private String mail;
    private PassDeclaration passport;

    public String getNom() {
        return nom;
    }

    public Conducteur(){
        super();
    }

    public Conducteur(String nom, String prenom, String mail, PassDeclaration passport) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.passport = passport;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public PassDeclaration getPassport() {
        return passport;
    }

    public void setPassport(PassDeclaration passport) {
        this.passport = passport;
    }
}
