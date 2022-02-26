package com.polyfrontiere.data.declaration.domain;

public class PassDeclaration {
    private String passportId;
    private String origine;

    public PassDeclaration() {
        super();
    }

    public PassDeclaration(String passportId, String origine) {
        this.passportId = passportId;
        this.origine = origine;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }
}
