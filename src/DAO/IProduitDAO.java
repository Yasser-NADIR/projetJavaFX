/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Categorie;
import Entities.Produit;
import java.util.List;

/**
 *
 * @author COZMET
 */
public interface IProduitDAO extends IDAO<Produit>{
    public List<Produit> getAll(String dis);
    public List<Categorie> getAllCategorie();
    public Categorie getOneCategorie(String inti);
}
