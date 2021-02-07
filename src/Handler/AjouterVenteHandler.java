/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.ClientDAOImpl;
import DAO.IClientDAO;
import DAO.IProduitDAO;
import DAO.ProduitDAOImpl;
import Entities.Client;
import Entities.Produit;
import IHM.AjouterVenteWindow;
import java.util.List;

/**
 *
 * @author COZMET
 */
public class AjouterVenteHandler {
    AjouterVenteWindow ajouterVente;
    IClientDAO clientDAO = new ClientDAOImpl();
    IProduitDAO produitDAO = new ProduitDAOImpl();

    public AjouterVenteHandler(AjouterVenteWindow ajouterVente) {
        this.ajouterVente = ajouterVente;
    }
    
    public void setClientsToComboBox(){
        List<Client> listClients = clientDAO.getAll();
        ajouterVente.getClientComboBox().getItems().clear();
        for(Client c: listClients){
            ajouterVente.getClientComboBox().getItems().add(c);
        }
    }
    
    public void setSelectedClientToLabels(Client client){
        ajouterVente.getNomClientLabel().setText(client.getNom());
        ajouterVente.getPrenomClientLabel().setText(client.getPrenom());
        ajouterVente.getEmailClientLabel().setText(client.getEmail());
        ajouterVente.getTeleClientLabel().setText(client.getTele());
        ajouterVente.getAdrClientLabel().setText(client.getAddr());
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
    }
}
