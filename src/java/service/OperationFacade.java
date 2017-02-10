/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.Operation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ASUS
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;
    @EJB
    private CompteFacade ejbFacade;

    public List<Operation> findByCompte(Compte compte) {
        return em.createQuery("SELECT o FROM Operation o WHERE o.compte.id= '" + compte.getId() + "'").getResultList();
    }

    public int save(Operation operation) {
        Compte compte = ejbFacade.find(operation.getCompte().getId());
        Double nvSolde = 0.0;
        int res = 0;
        if (compte.getSolde() < operation.getMontant() && operation.getType() == 2) {
            return -1;
        } else if (operation.getType() == 1) {
            nvSolde = compte.getSolde() + operation.getMontant();
            res = 1;
        } else if (operation.getType() == 2) {
            nvSolde = compte.getSolde() - operation.getMontant();
            res = 2;
        }
        compte.setSolde(nvSolde);
        ejbFacade.edit(compte);
      //  operation.setId(generateAttribute("Operation", "id"));
        create(operation);

        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

}
