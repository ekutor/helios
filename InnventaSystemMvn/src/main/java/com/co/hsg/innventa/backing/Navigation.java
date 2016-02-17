package com.co.hsg.innventa.backing;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author hectsaga
 */
@Named(value = "navigation")
@SessionScoped
public class Navigation implements Serializable {

    private String actualPage = "/pedidos/List";

    public void pedidos() {
        actualPage = "/pedidos/List";
    }

    public void remisiones() {
        actualPage = "/remisiones/List";
    }

    public void terceros() {
        actualPage = "/terceros/List";
    }

    public void productos() {
        actualPage = "/productos/List";
    }

    public void clientes() {
        actualPage = "/clientes/List";
    }

    public String getActualPage() {
        return actualPage;
    }

    public void setActualPage(String actualPage) {
        this.actualPage = actualPage;
    }

}
