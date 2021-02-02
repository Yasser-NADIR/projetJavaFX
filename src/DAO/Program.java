/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Client;
import Entities.Produit;
import java.util.List;

/**
 *
 * @author COZMET
 */
public class Program {
    public static void main(String arg[]){
       IClientDAO clientDAO = new ClientDAOImpl();
       System.out.println("------------------------getAll()");
        List<Client> list = clientDAO.getAll();
        for(Client c : list){
            System.out.println(c);
        }
        System.out.println("------------------------getAll(nom)");
        list = clientDAO.getAll("NADIR");
        for(Client c : list){
            System.out.println(c);
        }
        System.out.println("------------------------getOne(id)");
        Client c = clientDAO.getOne(2);
        System.out.println(c);
        System.out.println("------------------------add(obj)");
        c.setNom("nadir");
        c.setPrenom("seraphina");
//        clientDAO.add(c);
        System.out.println("------------------------delete(id)");
//        clientDAO.delete(3);
        System.out.println("------------------------update(obj, id)");
        c.setNom("nadir");
        c.setPrenom("zainab");
        clientDAO.update(c, c.getId());
        
    }
}
