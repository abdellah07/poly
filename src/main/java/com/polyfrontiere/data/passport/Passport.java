package com.polyfrontiere.data.passport;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.polyfrontiere.data.fingerprint.Fingerprint;

@JsonIgnoreProperties
public class Passport {
	String nom;
	String prenom;
	Date dateDeNaissance;
	String lieuDeNaissance;
	String sexe;
	String nationalite;
	String id;
	Date dateExpiration;
	Optional<Fingerprint> fingerprint;
	
	
	public Passport() {
		super();
	}
	public Passport(String nom, String prenom, Date dateDeNaissance, String lieuDeNaissance, String sexe,
			String nationalite, String id, Date dateExpiration,Optional<byte[]> fingerprint) throws IOException {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.sexe = sexe;
		this.nationalite = nationalite;
		this.id = id;
		this.dateExpiration = dateExpiration;
		if(fingerprint.isPresent()) {
			this.fingerprint = Optional.of(new Fingerprint(fingerprint.get()));
		}
	}
	public String getNom() {
		return nom;
	}
	public void setNon(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public Optional<Fingerprint> getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(Optional<byte[]> fingerprint) throws IOException {
		this.fingerprint = Optional.of(new Fingerprint(fingerprint.get()));
	}
}
