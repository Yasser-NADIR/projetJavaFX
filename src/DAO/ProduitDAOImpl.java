/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Categorie;
import Entities.Produit;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProduitDAOImpl extends AbstractDAO implements IProduitDAO{

    @Override
    public List<Produit> getAll(String dis) {
        List<Produit> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM produit, categorie WHERE produit.categorie=categorie.id AND designation like ?";
        
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, dis+"%");
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Produit(rs.getLong("produit.id"), rs.getString("designation"), rs.getFloat("prix_achat"), rs.getFloat("prix_vente"), new Categorie(rs.getLong("categorie.id"), rs.getString("categorie.intitule")), rs.getLong("qte"), rs.getDate("date").toLocalDate()));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Produit getOne(long id) {
        Produit p = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM produit, categorie WHERE produit.categorie=categorie.id AND produit.id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                p = new Produit(rs.getLong("produit.id"), rs.getString("designation"), rs.getFloat("prix_achat"), rs.getFloat("prix_vente"), new Categorie(rs.getLong("categorie.id"), rs.getString("categorie.intitule")), rs.getLong("qte"), rs.getDate("date").toLocalDate());
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM produit, categorie WHERE produit.categorie=categorie.id";
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Produit(rs.getLong("produit.id"), rs.getString("designation"), rs.getFloat("prix_achat"), rs.getFloat("prix_vente"), new Categorie(rs.getLong("categorie.id"), rs.getString("categorie.intitule")), rs.getLong("qte"), rs.getDate("date").toLocalDate()));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void add(Produit obj) {
        PreparedStatement pst = null;
        String query = "INSERT INTO produit(designation, prix_achat, prix_vente, categorie, date, qte) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, obj.getDesignation());
            pst.setFloat(2, obj.getPrixAchat());
            pst.setFloat(3, obj.getPrixVente());
            pst.setLong(4, obj.getCategorie().getId());
            pst.setDate(5, Date.valueOf(obj.getDate()));
            pst.setLong(6, obj.getQte());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        String query = "DELETE FROM produit WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Produit obj, long id) {
        PreparedStatement pst = null;
        String query = "UPDATE produit SET designation=?, prix_achat=?, prix_vente=?, categorie=?, date=?, qte=? WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, obj.getDesignation());
            pst.setFloat(2, obj.getPrixAchat());
            pst.setFloat(3, obj.getPrixVente());
            pst.setLong(4, obj.getCategorie().getId());
            pst.setDate(5, Date.valueOf(obj.getDate()));
            pst.setLong(6, obj.getQte());
            pst.setLong(7, id);
            pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Categorie> getAllCategorie(){
        List<Categorie> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM categorie";
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Categorie(rs.getString("intitule")));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public Categorie getOneCategorie(String inti){
        Categorie c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT * FROM categorie WHERE intitule=?";
        try{
            pst = connection.prepareStatement(query);
            pst.setString(1, inti);
            rs = pst.executeQuery();
            if(rs.next()){
                c = new Categorie(rs.getLong("id"), rs.getString("intitule"));
            }else{
                System.out.println("pas de resultat");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
}
