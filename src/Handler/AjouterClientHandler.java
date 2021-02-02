/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.ClientDAOImpl;
import DAO.IClientDAO;
import Entities.Client;
import IHM.AjouterClientFormWindow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author COZMET
 */
public class AjouterClientHandler {
    AjouterClientFormWindow ajouterClient;
    IClientDAO clientDAO = new ClientDAOImpl();

    public AjouterClientHandler(AjouterClientFormWindow ajouterClient) {
        this.ajouterClient = ajouterClient;
    }
    
    private boolean estNumeroTele(String num){
        Pattern pattern = Pattern.compile("^0[6-7][0-9]{8}$||\\^+212[6-7][0-9]{8}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(num);
        if(!matcher.find()){
            System.out.println("not found");
            return false;
        }
        return true;
    }
    
    private boolean verifierForm(){
        if(ajouterClient.getNomTextField().getText().equals("") ||
                ajouterClient.getPrenomTextField().getText().equals("") ||
                ajouterClient.getTeleTextField().getText().equals("") ||
                ajouterClient.getEmailTextField().getText().equals("") ||
                ajouterClient.getAdrTextField().getText().equals("")||
                ajouterClient.getDateDatePicker().getValue() == null||
                !estNumeroTele(ajouterClient.getTeleTextField().getText()))
            return false;
        return true;
    }
    
    public void addClient(){
        if(verifierForm()){
            Client c = new Client(
                    ajouterClient.getNomTextField().getText(),
                    ajouterClient.getPrenomTextField().getText(),
                    ajouterClient.getTeleTextField().getText(),
                    ajouterClient.getEmailTextField().getText(),
                    ajouterClient.getAdrTextField().getText(),
                    ajouterClient.getDateDatePicker().getValue()
            );
            //clientDAO.add(c);
        }else{
            Alert boiteDialog = new Alert(AlertType.ERROR);
            boiteDialog.setTitle("Erreur dans les champs");
            boiteDialog.setHeaderText("Valeur n'est pas validé");
            boiteDialog.setContentText("un ou pleusieur champs sont incorrectes");
            boiteDialog.showAndWait();
        }
    }
}
