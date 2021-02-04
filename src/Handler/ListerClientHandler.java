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
}
