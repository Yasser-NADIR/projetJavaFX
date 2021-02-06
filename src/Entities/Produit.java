/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author COZMET
 */
public class Produit {
    private long id;
    private String designation;
    private float prixAchat;
    private float prixVente;
    private Categorie categorie;
    private long qte;
    private LocalDate date;

    public Produit(long id, String designation, float prixAchat, float prixVente, Categorie categorie, long qte, LocalDate date) {
        this.id = id;
        this.designation = designation;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.qte = qte;
        this.date = date;
    }

    public Produit(String designation, float prixAchat, float prixVente, Categorie categorie, long qte, LocalDate date) {
        this.designation = designation;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.qte = qte;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public float getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(float prixVente) {
        this.prixVente = prixVente;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public long getQte() {
        return qte;
    }

    public void setQte(long qte) {
        this.qte = qte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public float getTotal(){
        return prixAchat*qte;
    }
    
    @Override
    public String toString() {
        return designation+"\t prix :"+prixAchat+"\t quantité :"+qte;
    }
}
