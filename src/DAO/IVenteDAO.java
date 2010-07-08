/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Vente;
import java.util.List;

/**
 *
 * @author COZMET
 */
public interface IVenteDAO extends IDAO<Vente>{
    public Vente getOne(String nomClient);
    public List<Vente> getAllByClientId(Long id);
}
