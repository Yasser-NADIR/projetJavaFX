/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author COZMET
 */
public class Vente {
    private long id;
    private List<LineCommande> listLineCommande;
    private Client client;
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vente(Client client, LocalDate date) {
        this.client = client;
        this.date = date;
    }

    public Vente(long id, Client client, LocalDate date) {
        this.id = id;
        this.client = client;
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LineCommande> getListLineCommande() {
        return listLineCommande;
    }

    public void setListLineCommande(List<LineCommande> listLineCommande) {
        this.listLineCommande = listLineCommande;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setOne(LineCommande lineCommande){
        listLineCommande.add(lineCommande);
    }

    @Override
    public String toString() {
        String d = "Vente{" + "id=" + id + ", client=" + client + ", listLineCommande : \n";
        for(LineCommande l: listLineCommande){
            d += l+"\n";
        }
        return  d+", date=" + date + '}';
    }
    
    public double getTotal(){
        double total = 0;
        for(LineCommande line: listLineCommande){
            total += line.getTotal();
        }
        return total;
    }
    
}
