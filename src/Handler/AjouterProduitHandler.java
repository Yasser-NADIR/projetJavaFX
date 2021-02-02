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
import IHM.AjouterProduitFormWindow;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author COZMET
 */
public class AjouterProduitHandler {
    AjouterProduitFormWindow ajouterProduit;
    IProduitDAO produitDAO = new ProduitDAOImpl();

    public AjouterProduitHandler(AjouterProduitFormWindow ajouterProduit) {
        this.ajouterProduit = ajouterProduit;
    }
    
    private boolean verifierForm(){
        if(ajouterProduit.getDesignationTextField().getText().equals("")|| 
            ajouterProduit.getPrixAchatTextField().getText().equals("") ||
            ajouterProduit.getPrixVenteTextField().getText().equals("") || 
            ajouterProduit.getCategorieComboBox().getValue()==null || 
            ajouterProduit.getQteTextField().getText().equals("") || 
            ajouterProduit.getDateDatePicker().getValue()==null)
            return false;
        try{
            Float.valueOf(ajouterProduit.getPrixAchatTextField().getText());
            Float.valueOf(ajouterProduit.getPrixVenteTextField().getText());
            Integer.valueOf(ajouterProduit.getQteTextField().getText());
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    
    public void addProduit(){
        //premierement je verifie que le form est bien valide
        if(verifierForm()){
            //recherche du categorie selectionné pour eu son id
            Categorie categorie = getSelectedCategorie();
            //creation du objet produit basant sur le form
            Produit p = new Produit(
                ajouterProduit.getDesignationTextField().getText(), 
                Float.valueOf(ajouterProduit.getPrixAchatTextField().getText()),
                Float.valueOf(ajouterProduit.getPrixVenteTextField().getText()), 
                //ajout id de la categorie selectionné
                categorie,
                Integer.valueOf(ajouterProduit.getQteTextField().getText()),
                ajouterProduit.getDateDatePicker().getValue());
            //ajout dans la base de donné
            produitDAO.add(p);
            //vider les inputs
            cleanFields();
        }else{
            //si un ou plusieur champs sont pas validé
            //une boite de dialog s'affiche
            Alert boiteDialog = new Alert(AlertType.ERROR);
            boiteDialog.setTitle("Erreur dans les champs");
            boiteDialog.setHeaderText("Valeur n'est pas validé");
            boiteDialog.setContentText("un ou pleusieur champs sont incorrectes");
            boiteDialog.showAndWait();
        }
                
    }
    
    private void cleanFields(){
        ajouterProduit.getDesignationTextField().clear();
        ajouterProduit.getPrixAchatTextField().clear();
        ajouterProduit.getPrixVenteTextField().clear();
        ajouterProduit.getQteTextField().clear();
    }
    
    public void initCategorieComboBox(){
        List<Categorie> list = produitDAO.getAllCategorie();
        ajouterProduit.getCategorieComboBox().getItems().clear();
        for(Categorie c: list){
            ajouterProduit.getCategorieComboBox().getItems().add(c.getIntitule());
        }
    }
    
    //return la categorie selectinné sous form objet
    private Categorie getSelectedCategorie() {
        return produitDAO.getOneCategorie(String.valueOf(ajouterProduit.getCategorieComboBox().getValue()));
    }
    
}
