/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.ClientDAOImpl;
import DAO.IClientDAO;
import DAO.IProduitDAO;
import DAO.IVenteDAO;
import DAO.ProduitDAOImpl;
import DAO.VenteDAOImpl;
import Entities.Client;
import Entities.LineCommande;
import Entities.Produit;
import Entities.Vente;
import IHM.AjouterVenteWindow;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author COZMET
 */
public class AjouterVenteHandler {
    AjouterVenteWindow ajouterVente;
    IClientDAO clientDAO = new ClientDAOImpl();
    IProduitDAO produitDAO = new ProduitDAOImpl();
    IVenteDAO venteDAO = new VenteDAOImpl();
    Vente vente;
    List<LineCommande> list = new ArrayList<>();
    Produit selectedProduit;
    Client selectedClient;

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
        ajouterVente.getNomClientLabel().setText(selectedClient.getNom());
        ajouterVente.getPrenomClientLabel().setText(selectedClient.getPrenom());
        ajouterVente.getTeleClientLabel().setText(selectedClient.getTele());
        ajouterVente.getEmailClientLabel().setText(selectedClient.getEmail());
        ajouterVente.getAdrClientLabel().setText(selectedClient.getAddr());
    }

    public AjouterVenteHandler(AjouterVenteWindow ajouterVente) {
        this.ajouterVente = ajouterVente;
    }
 
    public void updateProduitTableView(){
        List<Produit> listProduit = produitDAO.getAll();
        ajouterVente.getListeProduitsTableView().getItems().clear();
        ajouterVente.getListeProduitsTableView().getItems().addAll(listProduit);
    }
    
    public void findProduit(){
        String designation = ajouterVente.getRechercherProduitTextField().getText();
        List<Produit> listProduit = produitDAO.getAll(designation);
        ajouterVente.getListeProduitsTableView().getItems().clear();
        ajouterVente.getListeProduitsTableView().getItems().addAll(listProduit);
    }
    
    public void setSelectedProduitToLabels(Produit produit){
        ajouterVente.getDesignationProduitLabel().setText(produit.getDesignation());
        ajouterVente.getCategorieProduitLabel().setText(String.valueOf(produit.getCategorie()));
        ajouterVente.getPrixAchatProduitLabel().setText(String.valueOf(produit.getPrixAchat()));
        ajouterVente.getQteProduitLabel().setText(String.valueOf(produit.getQte()));
        ajouterVente.getQteDemandeTextField().setText(String.valueOf(produit.getQte()));
        ajouterVente.getPrixVenteTextField().setText(String.valueOf(produit.getPrixVente()));
        selectedProduit = produit;
    }
    
    public void addProduitToLineCommande(){
        if(verifyFormProduit()){
            for(LineCommande l : ajouterVente.getListeLinesCommandeTableView().getItems()){
                if(l.getProduit().equals(selectedProduit)){
                    l.setPrixVente(l.getPrixVente()+Double.valueOf(ajouterVente.getPrixVenteTextField().getText()));
                    l.setQte(l.getQte()+Long.valueOf(ajouterVente.getQteDemandeTextField().getText()));
                    clearProduitFields();
                    selectedProduit = null;
                    //calcul de total hors taxe, taxe et tout taxe comprise (ttc)
                    UpdateStatistiques();
                    ajouterVente.getListeLinesCommandeTableView().getItems().remove(l);
                    ajouterVente.getListeLinesCommandeTableView().getItems().add(l);
                    return ;
                }
            }
            ajouterVente.getListeLinesCommandeTableView().getItems().add(new LineCommande(
                selectedProduit,
                Double.valueOf(ajouterVente.getPrixVenteTextField().getText()),
                Long.valueOf(ajouterVente.getQteDemandeTextField().getText()),
                null,
                LocalDate.now()
            ));
            clearProduitFields();
            selectedProduit = null;
            //calcul de total hors taxe, taxe et tout taxe comprise (ttc)
            UpdateStatistiques();
        }else{
            Alert boiteDialog = new Alert(AlertType.ERROR);
            boiteDialog.setTitle("Erreur dans les champs");
            boiteDialog.setHeaderText("Valeur n'est pas validé");
            boiteDialog.setContentText("un ou pleusieur champs sont incorrectes");
            boiteDialog.showAndWait();
        }
    }
    
    private boolean verifyFormProduit(){
        if(ajouterVente.getPrixVenteTextField().getText().equals("") || ajouterVente.getQteDemandeTextField().getText().equals("") || selectedProduit==null)
            return false;
        try{
            Double.valueOf(ajouterVente.getPrixVenteTextField().getText());
            Long.valueOf(ajouterVente.getQteDemandeTextField().getText());
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    private void clearProduitFields() {
        ajouterVente.getDesignationProduitLabel().setText("");
        ajouterVente.getCategorieProduitLabel().setText("");
        ajouterVente.getPrixAchatProduitLabel().setText("");
        ajouterVente.getQteProduitLabel().setText("");
        ajouterVente.getPrixVenteTextField().clear();
        ajouterVente.getQteDemandeTextField().clear();
    }
    
    private void UpdateStatistiques(){
        double ht=0, taxe=0, ttc=0;
        for(LineCommande l : ajouterVente.getListeLinesCommandeTableView().getItems()){
            ht += l.getTotal();
        }
        taxe = ht*0.2;
        ttc = taxe + ht;
        ajouterVente.getTotalPrixHorsTaxeCalculerLabel().setText(String.valueOf(ht));
        ajouterVente.getTaxeCalculerLabel().setText(String.valueOf(taxe));
        ajouterVente.getTTCCalculerLabel().setText(String.valueOf(ttc));
    }
    
    public void nouveauVente(){
        selectedClient = null;
        selectedProduit = null;
        
        ajouterVente.getNomClientLabel().setText("");
        ajouterVente.getPrenomClientLabel().setText("");
        ajouterVente.getTeleClientLabel().setText("");
        ajouterVente.getEmailClientLabel().setText("");
        ajouterVente.getAdrClientLabel().setText("");
        
        ajouterVente.getRechercherProduitTextField().clear();
        findProduit();
        clearProduitFields();
        ajouterVente.getListeLinesCommandeTableView().getItems().clear();
        UpdateStatistiques();
    }
    
    public void addVente(){
        if(selectedClient!=null){
            Vente vente = new Vente(
                    selectedClient, ajouterVente.getDateDatePicker().getValue()
            );
            System.out.println(vente.getClient().getId());
            venteDAO.add(vente);
            nouveauVente();
        }else{
            selectionneClientAlert();
        }
    }
    
    private void selectionneClientAlert(){
        Alert boiteDialog = new Alert(AlertType.WARNING);
        boiteDialog.setTitle("Attention");
        boiteDialog.setHeaderText(null);
        boiteDialog.setContentText("Il faut selectionner un client");
        boiteDialog.showAndWait();
    }
    
}
