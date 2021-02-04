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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author COZMET
 */
public class ModifierClientHandler {
    ModifierClientFormWindow modifierWindow;
    IClientDAO clientDAO = new ClientDAOImpl();
    Client c;

    public ModifierClientHandler(ModifierClientFormWindow modifierWindow) {
        this.modifierWindow = modifierWindow;
    }
    
    public void setValueToFields(Client c){
        this.c = c;
        modifierWindow.getNomTextField().setText(c.getNom());
        modifierWindow.getPrenomTextField().setText(c.getPrenom());
        modifierWindow.getEmailTextField().setText(c.getEmail());
        modifierWindow.getAdrTextField().setText(c.getAddr());
        modifierWindow.getTeleTextField().setText(c.getTele());
        modifierWindow.getDateDatePicker().setValue(c.getDate());
    }
    
    public void updateClient(){
        if(verifierForm()){
            Client client = new Client(
                    modifierWindow.getNomTextField().getText(),
                    modifierWindow.getPrenomTextField().getText(),
                    modifierWindow.getEmailTextField().getText(),
                    modifierWindow.getAdrTextField().getText(),
                    modifierWindow.getTeleTextField().getText(),
                    modifierWindow.getDateDatePicker().getValue()
            );
            clientDAO.update(client, c.getId());
            modifierWindow.getWindow().close();
            Alert boiteDialog = new Alert(Alert.AlertType.INFORMATION);
            boiteDialog.setTitle("Modification");
            boiteDialog.setHeaderText(null);
            boiteDialog.setContentText("la modification fait avec succés");
            boiteDialog.showAndWait();
        }else{
            Alert boiteDialog = new Alert(Alert.AlertType.ERROR);
            boiteDialog.setTitle("Erreur dans les champs");
            boiteDialog.setHeaderText("Valeur n'est pas validé");
            boiteDialog.setContentText("un ou pleusieur champs sont incorrectes");
            boiteDialog.showAndWait();
        }
    }
    
    private boolean estNumeroTele(String num){
        Pattern pattern = Pattern.compile("^\\+212[6-7][0-9]{8}$|^0[6-7][0-9]{8}$");
        Matcher matcher = pattern.matcher(num);
        if(!matcher.find()){
            System.out.println("not found");
            return false;
        }
        return true;
    }
    
    private boolean verifierForm(){
        if(modifierWindow.getNomTextField().getText().equals("") ||
                modifierWindow.getPrenomTextField().getText().equals("") ||
                modifierWindow.getTeleTextField().getText().equals("") ||
                modifierWindow.getEmailTextField().getText().equals("") ||
                modifierWindow.getAdrTextField().getText().equals("")||
                modifierWindow.getDateDatePicker().getValue() == null||
                !estNumeroTele(modifierWindow.getTeleTextField().getText()))
            return false;
        return true;
    }
}
