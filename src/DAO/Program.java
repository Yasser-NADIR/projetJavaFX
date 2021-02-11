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
       ILineCommandeDAO lineCommandeDAO = new LineCommandeDAOImpl();
       IProduitDAO produitDAO = new ProduitDAOImpl();
       IVenteDAO venteDAO = new VenteDAOImpl();
       
       List<LineCommande> list ;
       LineCommande line;
//       list = lineCommandeDAO.getAll();
//       for(LineCommande l : list){
//           System.out.println(l);
//       }
//       list = lineCommandeDAO.getLineCommandeByVenteId(2L);
//       for(LineCommande l : list){
//           System.out.println(l);
//       }
//        line = lineCommandeDAO.getOne(1);
//        System.out.println(line);
        line = new LineCommande(
                produitDAO.getOne(7),
                1200, 123,
                venteDAO.getOne(3), LocalDate.now()
        );
//        lineCommandeDAO.add(line);
//        lineCommandeDAO.delete(3);
//        lineCommandeDAO.update(line, 1);
    }
}
