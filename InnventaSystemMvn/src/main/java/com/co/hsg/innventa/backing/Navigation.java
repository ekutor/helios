package com.co.hsg.innventa.backing;



import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author hectsaga
 */
@Named(value="navigation")
@SessionScoped
public class Navigation implements Serializable {
    private String actualPage = "/pedidos/List";
    
    public void listPedidos(){
        actualPage = "/pedidos/List";
    }
    public String getActualPage() {
        return actualPage;
    }

    public void setActualPage(String actualPage) {
        this.actualPage = actualPage;
    }
    
    
}
