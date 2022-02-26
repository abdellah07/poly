package com.polyfrontiere.data.declaration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class Declaration {
    private String societeDeTransit;
    Conducteur conducteur;
    List<Marchandise> listeDeMarchandise;



    public Declaration() {
        super();
    }

    public Declaration(String societeDeTransit, Conducteur conducteur, List<Marchandise> listeDeMarchandise) {
        this.societeDeTransit = societeDeTransit;
        this.conducteur = conducteur;
        this.listeDeMarchandise = listeDeMarchandise;
    }

    public String getSocieteDeTransit() {
        return societeDeTransit;
    }

    public void setSocieteDeTransit(String societeDeTransit) {
        this.societeDeTransit = societeDeTransit;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        this.conducteur = conducteur;
    }

    public List<Marchandise> getListeDeMarchandise() {
        return listeDeMarchandise;
    }

    public void setListeDeMarchandise(List<Marchandise> listeDeMarchandise) {
        this.listeDeMarchandise = listeDeMarchandise;
    }
}
