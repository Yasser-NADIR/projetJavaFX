/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.ILineCommandeDAO;
import DAO.IProduitDAO;
import DAO.IVenteDAO;
import DAO.LineCommandeDAOImpl;
import DAO.ProduitDAOImpl;
import DAO.VenteDAOImpl;
import Entities.Client;
import Entities.LineCommande;
import Entities.Produit;
import Entities.Vente;
import IHM.ModifierVenteWindow;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author COZMET
 */
public class ModifierVenteHandler {
    ModifierVenteWindow modifierVente;
    Client client;
    Vente vente;
    Produit produit;
    IProduitDAO produitDAO = new ProduitDAOImpl();
    ILineCommandeDAO lineCommandeDAO = new LineCommandeDAOImpl();
    IVenteDAO venteDAO = new VenteDAOImpl();

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }
    
    public ModifierVenteHandler(ModifierVenteWindow modifierVente) {
        this.modifierVente = modifierVente;
    }
    
    public void setClientToLabels(){
        modifierVente.getNomClientLabel().setText(client.getNom());
        modifierVente.getPrenomClientLabel().setText(client.getPrenom());
        modifierVente.getTeleClientLabel().setText(client.getTele());
        modifierVente.getEmailClientLabel().setText(client.getEmail());
        modifierVente.getAdrClientLabel().setText(client.getAddr());
        modifierVente.getDateClientLabel().setText(vente.getDate().toString());
    }
    
    public void updateProduitTableView(){
        List<Produit> listProduit = produitDAO.getAll();
        modifierVente.getListeProduitsTableView().getItems().clear();
        modifierVente.getListeProduitsTableView().getItems().addAll(listProduit);
    }
    
    public void updateLineCommandeTableView(){
        List<LineCommande> list = lineCommandeDAO.getLineCommandeByVenteId(vente.getId());
        modifierVente.getListeLinesCommandeTableView().getItems().addAll(list);
    }
    
    public void findProduit(){
        String designation = modifierVente.getRechercherProduitTextField().getText();
        List<Produit> listProduit = produitDAO.getAll(designation);
        modifierVente.getListeProduitsTableView().getItems().clear();
        modifierVente.getListeProduitsTableView().getItems().addAll(listProduit);
    }
    
    public void setSelectedProduitToLabels(Produit produit){
        modifierVente.getDesignationProduitLabel().setText(produit.getDesignation());
        modifierVente.getCategorieProduitLabel().setText(String.valueOf(produit.getCategorie()));
        modifierVente.getPrixAchatProduitLabel().setText(String.valueOf(produit.getPrixAchat()));
        modifierVente.getQteProduitLabel().setText(String.valueOf(produit.getQte()));
        modifierVente.getQteDemandeTextField().setText(String.valueOf(produit.getQte()));
        modifierVente.getPrixVenteTextField().setText(String.valueOf(produit.getPrixVente()));
        this.produit = produit;
    }
    
    public void setClickedLineCommandeToProduitLabel(LineCommande line){
        modifierVente.getListeLinesCommandeTableView().getItems().remove(line);
        setSelectedProduitToLabels(line.getProduit());
    }
    
    public void addProduitToLineCommande(){
        if(verifyFormProduit()){
            for(LineCommande l : modifierVente.getListeLinesCommandeTableView().getItems()){
                if(l.getProduit().getDesignation().equals(produit.getDesignation())){
                    l.setPrixVente(l.getPrixVente()+Double.valueOf(modifierVente.getPrixVenteTextField().getText()));
                    l.setQte(l.getQte()+Long.valueOf(modifierVente.getQteDemandeTextField().getText()));
                    clearProduitFields();
                    produit = null;
                    //calcul de total hors taxe, taxe et tout taxe comprise (ttc)
                    UpdateStatistiques();
                    modifierVente.getListeLinesCommandeTableView().getItems().remove(l);
                    modifierVente.getListeLinesCommandeTableView().getItems().add(l);
                    return ;
                }
            }
            modifierVente.getListeLinesCommandeTableView().getItems().add(new LineCommande(
                produit,
                Double.valueOf(modifierVente.getPrixVenteTextField().getText()),
                Long.valueOf(modifierVente.getQteDemandeTextField().getText()),
                vente,
                LocalDate.now()
            ));
            clearProduitFields();
            produit = null;
            UpdateStatistiques();
        }else{
            Alert boiteDialog = new Alert(Alert.AlertType.ERROR);
            boiteDialog.setTitle("Erreur dans les champs");
            boiteDialog.setHeaderText("Valeur n'est pas validé");
            boiteDialog.setContentText("un ou pleusieur champs sont incorrectes");
            boiteDialog.showAndWait();
        }
    }
    private void clearProduitFields() {
        modifierVente.getDesignationProduitLabel().setText("");
        modifierVente.getCategorieProduitLabel().setText("");
        modifierVente.getPrixAchatProduitLabel().setText("");
        modifierVente.getQteProduitLabel().setText("");
        modifierVente.getPrixVenteTextField().clear();
        modifierVente.getQteDemandeTextField().clear();
    }
    
    private void UpdateStatistiques(){
        double ht=0, taxe=0, ttc=0;
        for(LineCommande l : modifierVente.getListeLinesCommandeTableView().getItems()){
            ht += l.getTotal();
        }
        taxe = ht*0.2;
        ttc = taxe + ht;
        modifierVente.getTotalPrixHorsTaxeCalculerLabel().setText(String.valueOf(ht));
        modifierVente.getTaxeCalculerLabel().setText(String.valueOf(taxe));
        modifierVente.getTTCCalculerLabel().setText(String.valueOf(ttc));
    }
    
    private boolean verifyFormProduit(){
        if(modifierVente.getPrixVenteTextField().getText().equals("") || modifierVente.getQteDemandeTextField().getText().equals("") || produit==null)
            return false;
        try{
            Double.valueOf(modifierVente.getPrixVenteTextField().getText());
            Long.valueOf(modifierVente.getQteDemandeTextField().getText());
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    
    public void updateVente(){
        List<LineCommande> list = lineCommandeDAO.getLineCommandeByVenteId(vente.getId());
        for(LineCommande l: list){
            lineCommandeDAO.delete(l.getId());
        }
        for(LineCommande l: modifierVente.getListeLinesCommandeTableView().getItems()){
            lineCommandeDAO.add(l);
        }
    }
    
}
