/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import DAO.ClientDAOImpl;
import DAO.IClientDAO;
import Entities.Client;
import IHM.ListerClientWindow;
import java.util.List;
import javafx.scene.control.TableRow;
import javafx.util.Callback;

/**
 *
 * @author COZMET
 */
public class ListerClientHandler {
    ListerClientWindow listerClient;
    IClientDAO clientDAO = new ClientDAOImpl();

    public ListerClientHandler(ListerClientWindow listerClient) {
        this.listerClient = listerClient;
    }
    
    public void updateClientTableView(){
        List<Client> list = clientDAO.getAll();
        listerClient.getClientTableView().getItems().addAll(list);
    }
    
    //definir une fonction pour envoyer une fonction au evenement mouseClicked sur line du tableau du clientTableView
    //prend en argument une fonction pour l'appeler dans rowFactory dans row.setOnMouseClicked
    //<p, r>: p les paramettre, r le return
    public void setFunctionToEventTableRow(Callback<Client, Client> Function){
        listerClient.getClientTableView().setRowFactory(event->{
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(value->{
                Function.call(row.getItem());
                listerClient.getWindow().close();
            });
            return row;
        });
    }
}
