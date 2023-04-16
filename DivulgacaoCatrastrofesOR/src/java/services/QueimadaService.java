/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidade.Queimada;
import javax.ejb.Stateless;

/**
 *
 * @author felip
 */
@Stateless
public class QueimadaService extends EntityService<Queimada> {

    public QueimadaService() { super(Queimada.class); }
    
}
