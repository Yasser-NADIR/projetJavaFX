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
    private List<LineCommande> listLineCommande = new ArrayList<>();
    private LocalDate date;

    public Vente(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
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
        String d = "Vente{" + "id=" + id + ", listLineCommande : \n";
        for(LineCommande l: listLineCommande){
            d += l+"\n";
        }
        return  d+", date=" + date + '}';
    }
    
}
