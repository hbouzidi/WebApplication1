/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Operation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.OperationFacade;

/**
 *
 * @author ASUS
 */
@Named(value = "operationController")
@SessionScoped
public class OperationController implements Serializable {

    private Operation selected;
    private List<Operation> items;
    @EJB
    private OperationFacade ejbFacade;

    public String save() {
        ejbFacade.save(selected);
        return null;
    }

    /**
     * Creates a new instance of OperationController
     */
    public OperationController() {
    }

    public Operation getSelected() {
        if (selected == null) {
            selected = new Operation();
        }
        return selected;
    }

    public void setSelected(Operation selected) {
        this.selected = selected;
    }

    public List<Operation> getItems() {
        return items;
    }

    public void setItems(List<Operation> items) {
        this.items = items;
    }

    public OperationFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(OperationFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

}
