/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ASUS
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    public int debiter(String ribCompte, Double montantDebit) {
        Compte compte = find(ribCompte);
        if (compte.getSolde() < montantDebit) {
            return -1;
        } else {
            compte.setSolde(compte.getSolde() - montantDebit);
            edit(compte);
            return 1;
        }
    }
   public List<Compte> recherche(String idCompte,Double soldeMin,Double soldeMax){
       String req="SELECT c FROM Compte c WHERE 1=1";
       if(!idCompte.equals("")){
           req+="AND c.id = '"+idCompte+"'";
       }
       if(soldeMin!=null){
           req+="AND c.solde>= '"+soldeMin+"'";
       }
        if(soldeMax!=null){
           req+="AND c.solde<= '"+soldeMax+"'";
       }
        return getEntityManager().createQuery(req).getResultList();
   }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

}
