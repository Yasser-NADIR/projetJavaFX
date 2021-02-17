/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.IVenteDAO;
import DAO.VenteDAOImpl;
import Entities.Client;
import Entities.Vente;
import IHM.ListerVenteByClientWindow;
import IHM.ModifierVenteWindow;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author COZMET
 */
public class ListerVenteByClientHandler {
    ListerVenteByClientWindow listerVente;
    VenteDAOImpl venteDAO = new VenteDAOImpl();
    Client client;

    public ListerVenteByClientHandler(ListerVenteByClientWindow listerVente) {
        this.listerVente = listerVente;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void updateListeVentesTableView(){
        List<Vente> listVente = venteDAO.getAllByClientId(client.getId());
        for(Vente v: listVente){
            venteDAO.setLineCommandeToVente(v);
        }
        listerVente.getListeVentesTableView().getItems().clear();
        listerVente.getListeVentesTableView().getItems().addAll(listVente);
    }
    public void setNameToLabel(){
        listerVente.getNomClientLabel().setText(client.getNom()+" "+client.getPrenom());
    }
    
    public void askUpdateDeleteVente(Vente vente){
        Alert boiteDialog = new Alert(Alert.AlertType.CONFIRMATION);
        boiteDialog.setTitle("Supprimer/Modifier");
        boiteDialog.setHeaderText(null);
        boiteDialog.setContentText("Voulez vous supprimer ou bien modifier");
        ButtonType modifierButtonType = new ButtonType("Modifier");
        ButtonType supprimerButtonType = new ButtonType("Supprimer");
        ButtonType annulerButtonType = new ButtonType("Annuler");
        boiteDialog.getButtonTypes().clear();
        boiteDialog.getButtonTypes().addAll(modifierButtonType, supprimerButtonType, annulerButtonType);
        Optional<ButtonType> choix = boiteDialog.showAndWait();
        if(choix.get()==modifierButtonType){
            ModifierVenteWindow modifierVente = new ModifierVenteWindow(vente);
            modifierVente.getWindow().setOnCloseRequest(event->{
                modifierVente.getWindow().close();
                updateListeVentesTableView();
            });
        }else if(choix.get()==supprimerButtonType){
            Alert supprimerVenteAlert = new Alert(Alert.AlertType.WARNING);
            supprimerVenteAlert.setTitle("SUPPRESION!!!");
            supprimerVenteAlert.setHeaderText(null);
            supprimerVenteAlert.setContentText("Voulez-vous vraiment supprimer ce vente");
            ButtonType confimerSuppressionButtonType = new ButtonType("Confimer");
            ButtonType annulerSuppressionButtonType = new ButtonType("Annuler");
            supprimerVenteAlert.getButtonTypes().clear();
            supprimerVenteAlert.getButtonTypes().addAll(confimerSuppressionButtonType, annulerSuppressionButtonType);
            choix = supprimerVenteAlert.showAndWait();
            if(choix.get()==confimerSuppressionButtonType){
                venteDAO.delete(vente.getId());
                updateListeVentesTableView();
            }
        }
    }

    public void calculeTotalVente() {
        double total=0;
        for(Vente v: venteDAO.getAllByClientId(client.getId())){
            venteDAO.setLineCommandeToVente(v);
            total += v.getTotal();
        }
        listerVente.getTotalCalculeLabel().setText(String.valueOf(total));
    }
}
