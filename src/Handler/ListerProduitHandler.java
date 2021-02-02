/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.IProduitDAO;
import DAO.ProduitDAOImpl;
import Entities.Produit;
import IHM.ListerProduitWindow;
import java.util.List;

/**
 *
 * @author COZMET
 */
public class ListerProduitHandler {
    ListerProduitWindow listerProduit;
    IProduitDAO produitDAO = new ProduitDAOImpl();
    public ListerProduitHandler(ListerProduitWindow listerProduit) {
        this.listerProduit = listerProduit;
    }
    
    public void updateProduitTableView(){
        List<Produit> list = produitDAO.getAll();
        float total = 0;
        for(Produit p: list){
            listerProduit.getProduitTableView().getItems().add(p);
            total += p.getTotal();
        }
        listerProduit.getTotalCalculLabel().setText(String.valueOf(total));
    }
    
}
