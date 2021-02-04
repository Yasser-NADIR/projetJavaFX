/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.ClientDAOImpl;
import DAO.IClientDAO;
import Entities.Client;
import IHM.ModifierClientFormWindow;
import IHM.RechercherClientFormWindow;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author COZMET
 */
public class RechercherClientHandler {
    RechercherClientFormWindow rechercherClient;
    IClientDAO clientDAO = new ClientDAOImpl();

    public RechercherClientHandler(RechercherClientFormWindow rechercherClient) {
        this.rechercherClient = rechercherClient;
    }
    
    public void findClient(){
        String nom = rechercherClient.getRechercherClientTextField().getText();
        List<Client> list = clientDAO.getAll(nom);
        rechercherClient.getRechercheClientTableView().getItems().clear();
        rechercherClient.getRechercheClientTableView().getItems().addAll(list);
    }
    
    public void askUpdateOrDelete(Client c){
        Alert boiteDialog = new Alert(Alert.AlertType.CONFIRMATION);
        boiteDialog.setTitle("Supprimer/Modifier");
        boiteDialog.setHeaderText(null);
        boiteDialog.setContentText("Voulez vous supprimer ou bien modifier "+c.getNom()+" "+c.getPrenom());
        ButtonType modifierButtonType = new ButtonType("Modifier");
        ButtonType supprimerButtonType = new ButtonType("Supprimer");
        ButtonType annulerButtonType = new ButtonType("Annuler");
        boiteDialog.getButtonTypes().clear();
        boiteDialog.getButtonTypes().addAll(modifierButtonType, supprimerButtonType, annulerButtonType);
        Optional<ButtonType> choix = boiteDialog.showAndWait();
        if(choix.get()==modifierButtonType){
            ModifierClientFormWindow window = new ModifierClientFormWindow(c);
            window.getModifierClientButton().setOnAction(event->{
                window.getHandler().updateClient();
                findClient();
            });
        }else if(choix.get()==supprimerButtonType){
            Alert supprimerClienttAlert = new Alert(Alert.AlertType.WARNING);
            supprimerClienttAlert.setTitle("SUPPRESION!!!");
            supprimerClienttAlert.setHeaderText(null);
            supprimerClienttAlert.setContentText("Voulez-vous vraiment supprimer "+c.getNom()+" "+c.getPrenom());
            ButtonType confimerSuppressionButtonType = new ButtonType("Confimer");
            ButtonType annulerSuppressionButtonType = new ButtonType("Annuler");
            supprimerClienttAlert.getButtonTypes().clear();
            supprimerClienttAlert.getButtonTypes().addAll(confimerSuppressionButtonType, annulerSuppressionButtonType);
            choix = supprimerClienttAlert.showAndWait();
            if(choix.get()==confimerSuppressionButtonType){
                clientDAO.delete(c.getId());
                findClient();
            }
        }
    }
    
}
