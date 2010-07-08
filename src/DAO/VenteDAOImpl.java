/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Client;
import Entities.Vente;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VenteDAOImpl extends AbstractDAO implements IVenteDAO {

    IClientDAO clientDAO = new ClientDAOImpl();
    
    @Override
    public Vente getOne(long id) {
        Vente vente = null;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM vente";
        Client client;
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            if(rs.next()){
                client = clientDAO.getOne(rs.getLong("client"));
                vente = new Vente(
                    rs.getLong("id"), client, rs.getDate("date").toLocalDate()
                );
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return vente;
    }
    
    @Override
    public Vente getOne(String nomClient){
        Vente vente = null;
        Client client;
        PreparedStatement pst;
        ResultSet rs;
        String query = "Select * FROM vente WHERE client in (SELECT id FROM client WHERE nom = ?)";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, nomClient);
            rs = pst.executeQuery();
            if(rs.next()){
                client = clientDAO.getOne(rs.getLong("client"));
                vente = new Vente(
                        rs.getLong("id"), client, rs.getDate("date").toLocalDate()
                );
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return vente;
        
    }

    @Override
    public List<Vente> getAll() {
        List<Vente> list = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM vente";
        Client client;
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                client = clientDAO.getOne(rs.getLong("client"));
                list.add(new Vente(
                        rs.getLong("id"), client, rs.getDate("date").toLocalDate()
                ));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<Vente> getAllByClientId(Long id){
        List<Vente> list = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM vente WHERE client = ?";
        Client client = clientDAO.getOne(id);
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Vente(
                        rs.getLong("id"), client, rs.getDate("date").toLocalDate()
                ));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public void add(Vente obj) {
        PreparedStatement pst;
        String query = "INSERT INTO vente(client, date) VALUES (?, ?)";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, obj.getClient().getId());
            pst.setDate(2, Date.valueOf(obj.getDate()));
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst;
        String query = "DELETE FROM vente WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Vente obj, long id) {
        PreparedStatement pst;
        String query = "UPDATE vente set client=?, date=? WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, obj.getClient().getId());
            pst.setDate(2, Date.valueOf(obj.getDate()));
            pst.setLong(3, id);
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
