/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.IProduitDAO;
import DAO.ProduitDAOImpl;
import Entities.Categorie;
import Entities.Produit;
import IHM.ModifierProduitFormWindow;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author COZMET
 */
public class ModifierProduitHandler {
    ModifierProduitFormWindow modifierProduit;
    IProduitDAO produitDAO = new ProduitDAOImpl();
    Produit p;
    
    public ModifierProduitHandler(ModifierProduitFormWindow modifierProduit) {
        this.modifierProduit = modifierProduit;
    }
    public void setValueToFields(Produit p){
        this.p = p;
        modifierProduit.getDesignationTextField().setText(p.getDesignation());
        modifierProduit.getPrixAchatTextField().setText(String.valueOf(p.getPrixAchat()));
        modifierProduit.getPrixVenteTextField().setText(String.valueOf(p.getPrixVente()));
        modifierProduit.getQteTextField().setText(String.valueOf(p.getQte()));
        modifierProduit.getCategorieComboBox().setValue(p.getCategorie().getIntitule());
        modifierProduit.getDateDatePicker().setValue(p.getDate());
    }
    public void initCategorieComboBox(){
        List<Categorie> list = produitDAO.getAllCategorie();
        modifierProduit.getCategorieComboBox().getItems().clear();
        for(Categorie c: list){
            modifierProduit.getCategorieComboBox().getItems().add(c.getIntitule());
        }
    }
    
    public void updateProduit(){
        if(verifierForm()){
            //recherche du categorie selectionné pour eu son id
            Categorie categorie = getSelectedCategorie();
            //creation du objet produit basant sur le form
            Produit p = new Produit(
                modifierProduit.getDesignationTextField().getText(), 
                Float.valueOf(modifierProduit.getPrixAchatTextField().getText()),
                Float.valueOf(modifierProduit.getPrixVenteTextField().getText()), 
                //ajout id de la categorie selectionné
                categorie,
                Integer.valueOf(modifierProduit.getQteTextField().getText()),
                modifierProduit.getDateDatePicker().getValue());
            
            //System.out.println(p.getId());
            produitDAO.update(p, this.p.getId());
            modifierProduit.getWindow().close();
            Alert boiteDialog = new Alert(AlertType.INFORMATION);
            boiteDialog.setTitle("Modification");
            boiteDialog.setHeaderText(null);
            boiteDialog.setContentText("la modification fiat avec succés");
            boiteDialog.showAndWait();
            
        }else{
            Alert boiteDialog = new Alert(AlertType.ERROR);
            boiteDialog.setTitle("Erreur dans les champs");
            boiteDialog.setHeaderText("Valeur n'est pas validé");
            boiteDialog.setContentText("un ou pleusieur champs sont incorrectes");
            boiteDialog.showAndWait();
        }
    }
    
    private boolean verifierForm(){
        if(modifierProduit.getDesignationTextField().getText().equals("")|| 
            modifierProduit.getPrixAchatTextField().getText().equals("") ||
            modifierProduit.getPrixVenteTextField().getText().equals("") || 
            modifierProduit.getCategorieComboBox().getValue()==null || 
            modifierProduit.getQteTextField().getText().equals("") || 
            modifierProduit.getDateDatePicker().getValue()==null)
            return false;
        try{
            Float.valueOf(modifierProduit.getPrixAchatTextField().getText());
            Float.valueOf(modifierProduit.getPrixVenteTextField().getText());
            Integer.valueOf(modifierProduit.getQteTextField().getText());
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    private Categorie getSelectedCategorie() {
        return produitDAO.getOneCategorie(String.valueOf(modifierProduit.getCategorieComboBox().getValue()));
    }
}
