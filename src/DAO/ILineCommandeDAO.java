/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.LineCommande;
import java.util.List;

/**
 *
 * @author COZMET
 */
public interface ILineCommandeDAO extends IDAO<LineCommande>{
    public List<LineCommande> getLineCommandeByVenteId(Long id);
}
