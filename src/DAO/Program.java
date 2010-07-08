/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Client;
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
       Client client = new Client(1,  "nadir", "yasser", "0610065615", "qmdlkfj", "qmsdlkfjj", LocalDate.now());
       Vente vente = new Vente(1, client, LocalDate.now());
       System.out.println("------------------getAll()");
       List<Vente> list = venteDAO.getAll();
       for(Vente v: list){
           System.out.println(v);
       }
       System.out.println("------------------getAllByClientId(idClient)");
       list = venteDAO.getAllByClientId(6L);
       for(Vente v: list){
           System.out.println(v);
       }
       System.out.println("------------------getOne(id)");
       vente = venteDAO.getOne(2);
       System.out.println(vente);
       System.out.println("------------------getOne(nomClient)");
       vente = venteDAO.getOne("NADIR");
       System.out.println(vente);
       System.out.println("------------------add(vente)");
//       venteDAO.add(v);
       System.out.println("------------------delete(id)");
//       venteDAO.delete(1);
       System.out.println("------------------update(vente, id)");
//       v.getClient().setId(6);
//       venteDAO.update(v, 2);
    }
}
