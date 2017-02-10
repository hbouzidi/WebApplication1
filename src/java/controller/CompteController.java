/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Compte;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CompteFacade;
import service.OperationFacade;

/**
 *
 * @author ASUS
 */
@Named(value = "compteController")
@SessionScoped
public class CompteController implements Serializable {

    private Compte selected;
    private List<Compte> items;
    @EJB
    private CompteFacade ejbFacade;
    @EJB
    private OperationFacade operationFacade;
    private Double montantDebit;
    private Double soldeMin;
    private Double soldeMax;
    private String idRech;

    private void initParam() {
        selected = new Compte();
    }

    public String create() {
        ejbFacade.edit(selected);
        initParam();
        return "List";
    }

    public String update(Compte compte) {
        selected = compte;
        return "/compte/Create";
    }

    public String debiter() {
        int res = ejbFacade.debiter(selected.getId(), montantDebit);
        if (res < 0) {
            return null;
        } else {
            initParam();
            return "List";
        }

    }

    public void remove(Compte compte) {
        ejbFacade.remove(compte);
        items.remove(items.indexOf(compte));
    }

    public void detail(Compte compte) {
        selected.setOperations(operationFacade.findByCompte(compte));
    }

    public void recherche() {
        items = ejbFacade.recherche(idRech, soldeMin, soldeMax);
    }

    /**
     * Creates a new instance of CompteControler
     */
    public CompteController() {
    }

    public Compte getSelected() {
        if (selected == null) {
            selected = new Compte();
        }
        return selected;
    }

    public void setSelected(Compte selected) {
        this.selected = selected;
    }

    public List<Compte> getItems() {
        items = ejbFacade.findAll();
        return items;
    }

    public void setItems(List<Compte> items) {
        this.items = items;
    }

    public CompteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CompteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Double getMontantDebit() {
        return montantDebit;
    }

    public void setMontantDebit(Double montantDebit) {
        this.montantDebit = montantDebit;
    }

    public Double getSoldeMin() {
        return soldeMin;
    }

    public void setSoldeMin(Double soldeMin) {
        this.soldeMin = soldeMin;
    }

    public Double getSoldeMax() {
        return soldeMax;
    }

    public void setSoldeMax(Double soldeMax) {
        this.soldeMax = soldeMax;
    }

    public String getIdRech() {
        return idRech;
    }

    public void setIdRech(String idRech) {
        this.idRech = idRech;
    }

}
