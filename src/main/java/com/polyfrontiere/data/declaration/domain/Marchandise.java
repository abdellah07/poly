package com.polyfrontiere.data.declaration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Marchandise {
    private String produit;
    private int quantite;
    private float valeurMonnaie;
    private String paysOrigine;

    public Marchandise(){
        super();
    }

    public Marchandise(String produit, int quantite, float valeurMonnaie, String paysOrigine) {
        this.produit = produit;
        this.quantite = quantite;
        this.valeurMonnaie = valeurMonnaie;
        this.paysOrigine = paysOrigine;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getvaleurMonnaie() {
        return valeurMonnaie;
    }

    public void setValeurMonnaie(float valeurMonnaie) {
        this.valeurMonnaie = valeurMonnaie;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }
}
