/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDAOImpl extends AbstractDAO implements IClientDAO {

    @Override
    public List<Client> getAll(String nom) {
        List<Client> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM client WHERE nom like ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, nom+"%");
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Client(
                        rs.getLong("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("tele"),
                        rs.getString("email"),
                        rs.getString("adresse"),
                        rs.getDate("date").toLocalDate()
                ));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Client getOne(long id) {
        Client client = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM client WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                client  = new Client(
                        rs.getLong("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("tele"),
                        rs.getString("email"),
                        rs.getString("adresse"),
                        rs.getDate("date").toLocalDate()
                );
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM client";
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Client(
                        rs.getLong("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("tele"),
                        rs.getString("email"),
                        rs.getString("adresse"),
                        rs.getDate("date").toLocalDate()
                ));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void add(Client obj) {
        PreparedStatement pst = null;
        String query = "INSERT INTO client(nom, prenom, tele, email, adresse) VALUES (?, ?, ?, ?, ?)";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, obj.getNom());
            pst.setString(2, obj.getPrenom());
            pst.setString(3, obj.getTele());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getAddr());
            pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        String query = "DELETE FROM client WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Client obj, long id) {
        PreparedStatement pst = null;
        String query = "UPDATE client SET nom=?, prenom=?, tele=?, email=?, adresse=? WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, obj.getNom());
            pst.setString(2, obj.getPrenom());
            pst.setString(3, obj.getTele());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getAddr());
            pst.setLong(6, id);
            pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
