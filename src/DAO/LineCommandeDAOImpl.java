/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.LineCommande;
import Entities.Produit;
import Entities.Vente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LineCommandeDAOImpl extends AbstractDAO implements ILineCommandeDAO {

    IProduitDAO produitDAO = new ProduitDAOImpl();
    IVenteDAO venteDAO = new VenteDAOImpl();
    
    @Override
    public List<LineCommande> getLineCommandeByVenteId(Long id) {
        PreparedStatement pst;
        ResultSet rs;
        List<LineCommande> list = new ArrayList<>();
        Produit produit;
        Vente vente;
        String query = "SELECT * FROM linecommande WHERE vente = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                vente = venteDAO.getOne(rs.getLong("vente"));
                produit = produitDAO.getOne(rs.getLong("produit"));
                list.add(new LineCommande(
                        rs.getLong("id"), produit, rs.getDouble("prixVente"), rs.getLong("qte"), vente, rs.getDate("date").toLocalDate()
                ));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public LineCommande getOne(long id) {
        PreparedStatement pst;
        ResultSet rs;
        LineCommande lineCommande = null;
        Produit produit;
        Vente vente;
        String query = "SELECT * FROM linecommande";
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            if(rs.next()){
                vente = venteDAO.getOne(rs.getLong("vente"));
                produit = produitDAO.getOne(rs.getLong("produit"));
                lineCommande = new LineCommande(
                        rs.getLong("id"), produit, rs.getDouble("prixVente"), rs.getLong("qte"), vente, rs.getDate("date").toLocalDate()
                );
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return lineCommande;
    }

    @Override
    public List<LineCommande> getAll() {
        PreparedStatement pst;
        ResultSet rs;
        List<LineCommande> list = new ArrayList<>();
        Produit produit;
        Vente vente;
        String query = "SELECT * FROM linecommande";
        try{
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                vente = venteDAO.getOne(rs.getLong("vente"));
                produit = produitDAO.getOne(rs.getLong("produit"));
                list.add(new LineCommande(
                        rs.getLong("id"), produit, rs.getDouble("prixVente"), rs.getLong("qte"), vente, rs.getDate("date").toLocalDate()
                ));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void add(LineCommande obj) {
        PreparedStatement pst;
        String query = "INSERT INTO linecommande(vente, produit, qte, prixVente, date) VALUES(?, ?, ?, ?, ?)";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, obj.getVente().getId());
            pst.setLong(2, obj.getProduit().getId());
            pst.setLong(3, obj.getQte());
            pst.setDouble(4, obj.getPrixVente());
            pst.setDate(5, Date.valueOf(obj.getDate()));
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst;
        String query = "DELETE FROM linecommande WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(LineCommande obj, long id) {
        PreparedStatement pst;
        String query = "UPDATE linecommande SET vente=?, produit=?, qte=?, prixVente=?, date=? WHERE id = ?";
        try{
            pst = connection.prepareStatement(query);
            pst.setLong(1, obj.getVente().getId());
            pst.setLong(2, obj.getProduit().getId());
            pst.setLong(3, obj.getQte());
            pst.setDouble(4, obj.getPrixVente());
            pst.setDate(5, Date.valueOf(obj.getDate()));
            pst.setLong(6, id);
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
