/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.IProduitDAO;
import DAO.ProduitDAOImpl;
import Entities.Produit;
import IHM.ModifierProduitFormWindow;
import IHM.RechercherProduitFormWindow;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author COZMET
 */
public class RechercherProduitHandler {
    RechercherProduitFormWindow rechercherProduit;
    IProduitDAO produitDAO = new ProduitDAOImpl();

    public RechercherProduitHandler(RechercherProduitFormWindow rechercherProduit) {
        this.rechercherProduit = rechercherProduit;
    }
    
    public void findProduit(){
        String des = rechercherProduit.getRechercherProduitTextField().getText();
        rechercherProduit.getRechercherProduitTextField().clear();
        List<Produit> list = produitDAO.getAll(des);
        rechercherProduit.getRechercheProduitTableView().getItems().clear();
        rechercherProduit.getRechercheProduitTableView().getItems().addAll(list);
    }
    
    public void askUpdateOrDelete(Produit p){
        Alert boiteDialog = new Alert(AlertType.CONFIRMATION);
        boiteDialog.setTitle("Supprimer/Modifier");
        boiteDialog.setHeaderText(null);
        boiteDialog.setContentText("Voulez vous supprimer ou bien modifier "+p.getDesignation());
        ButtonType modifierButtonType = new ButtonType("Modifier");
        ButtonType supprimerButtonType = new ButtonType("Supprimer");
        ButtonType annulerButtonType = new ButtonType("Annuler");
        boiteDialog.getButtonTypes().clear();
        boiteDialog.getButtonTypes().addAll(modifierButtonType, supprimerButtonType, annulerButtonType);
        Optional<ButtonType> choix = boiteDialog.showAndWait();
        if(choix.get()==modifierButtonType){
            ModifierProduitFormWindow window = new ModifierProduitFormWindow(p);
            window.getModifierProduitButton().setOnAction(event->{
                window.getHandler().updateProduit();
                findProduit();
            });
        }else if(choix.get()==supprimerButtonType){
            Alert supprimerProduitAlert = new Alert(AlertType.WARNING);
            supprimerProduitAlert.setTitle("SUPPRESION!!!");
            supprimerProduitAlert.setHeaderText(null);
            supprimerProduitAlert.setContentText("Voulez-vous vraiment supprimer "+p.getDesignation());
            ButtonType confimerSuppressionButtonType = new ButtonType("Confimer");
            ButtonType annulerSuppressionButtonType = new ButtonType("Annuler");
            supprimerProduitAlert.getButtonTypes().clear();
            supprimerProduitAlert.getButtonTypes().addAll(confimerSuppressionButtonType, annulerSuppressionButtonType);
            choix = supprimerProduitAlert.showAndWait();
            if(choix.get()==confimerSuppressionButtonType){
                produitDAO.delete(p.getId());
                findProduit();
            }
        }
    }
    
}
