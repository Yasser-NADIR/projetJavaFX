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
public class LineCommande {
    private long id;
    private Produit produit;
    private double prixVente;
    private long qte;
    private LocalDate date;
    private Vente vente;
    private final double TAXE = 1.2;
    
    
    public LineCommande(Produit produit, double prixVente, long qte, Vente vente, LocalDate date) {
        this.produit = produit;
        this.prixVente = prixVente;
        this.qte = qte;
        this.date = date;
        this.vente = vente;
    }

    public LineCommande(long id, Produit produit, double prixVente, long qte, Vente vente, LocalDate date) {
        this.id = id;
        this.produit = produit;
        this.prixVente = prixVente;
        this.qte = qte;
        this.date = date;
        this.vente = vente;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
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

    public double getTotal(){
        return qte*prixVente*TAXE;
    }
    
    @Override
    public String toString() {
        return "LineCommande{" + "id=" + id + ", produit=" + produit + ", prixVente=" + prixVente + ", qte=" + qte + ", date=" + date + '}';
    }
    
}
