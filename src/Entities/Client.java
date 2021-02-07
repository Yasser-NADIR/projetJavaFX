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
public class Client {
    private long id;
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    private String addr;
    private LocalDate date;

    public Client(long id, String nom, String prenom, String tele, String email, String addr, LocalDate date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tele = tele;
        this.email = email;
        this.addr = addr;
        this.date = date;
    }

    public Client(String nom, String prenom, String tele, String email, String addr, LocalDate date) {
        this.nom = nom;
        this.prenom = prenom;
        this.tele = tele;
        this.email = email;
        this.addr = addr;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return nom + " "+ prenom;
    }
    
    
}
