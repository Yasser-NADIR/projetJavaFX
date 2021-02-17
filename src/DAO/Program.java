/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Client;
import Entities.LineCommande;
import Entities.Produit;
import Entities.Vente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author COZMET
 */
public class Program {
    public static void main(String arg[]){
        IVenteDAO venteDAO = new VenteDAOImpl();
        Vente vente = venteDAO.getOne(2);
    }
}
