/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Produit;
import java.util.List;

/**
 *
 * @author COZMET
 */
public class Program {
    public static void main(String arg[]){
        IProduitDAO produitDAO = new ProduitDAOImpl();
        System.out.println("--------------------------getAll(String dis)");
        List<Produit> list = produitDAO.getAll("Sumsang");
        for(Produit p: list){
            System.out.println(p);
        }
        System.out.println("--------------------------getAll()");
        list = produitDAO.getAll("");
        for(Produit p: list){
            System.out.println(p);
        }
        System.out.println("--------------------------getOne(long id)");
        Produit p = produitDAO.getOne(5);
        System.out.println(p);
        System.out.println("--------------------------add(long id) DONE");
        //p.setDesignation("test designation");
        //p.setPrixAchat(123321);
        //produitDAO.add(p);
        System.out.println("--------------------------delete(long id DONE)");
        //produitDAO.delete(9);
        System.out.println("--------------------------update(Produit obj, long id) DONE");
        //p.setDesignation(p.getDesignation()+" *");
        //produitDAO.update(p, p.getId());
    }
}
