package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.security.SecurityAuditor;
import com.co.hsg.innventa.backing.security.SecurityAuditorSession;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hectsaga
 */
@Named(value = "navigation")
@SessionScoped

public class Navigation implements Serializable {

    private String actualModule = "pedidos";
    private String actualPage = "/pedidos/List";
    private String actualPageCreate = "/pedidos/Create";

    public void pedidos() {
        actualModule = "pedidos";
        actualPage = "/" + actualModule + "/List";
        actualPageCreate = "/" + actualModule + "/Create";
    }

    public void remisiones() {
        actualModule = "remisiones";
        actualPage = "/" + actualModule + "/List";
        actualPageCreate = "/" + actualModule + "/Create";
    }

    public void terceros() {
        actualModule = "terceros";
        actualPage = "/" + actualModule + "/List";
        actualPageCreate = "/" + actualModule + "/Create";
    }

    public void productos() {
        actualModule = "productos";
        actualPage = "/" + actualModule + "/List";
        actualPageCreate = "/" + actualModule + "/Create";
    }

    public void clientes() {
        actualModule = "clientes";
        actualPage = "/" + actualModule + "/List";
        actualPageCreate = "/" + actualModule + "/Create";
    }

    public String getActualPage() {
        SecurityAuditorSession.getInstance().validateSession();
        return actualPage;
    }

    public String getActualModule() {
        return actualModule;
    }

    public void setActualModule(String actualModule) {
        this.actualModule = actualModule;
    }

    public String getActualPageCreate() {
        return actualPageCreate;
    }

    public void setActualPage(String actualPage) {
        this.actualPage = actualPage;
    }

}
