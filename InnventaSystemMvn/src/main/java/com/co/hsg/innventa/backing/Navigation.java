package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.types.SexType;
import com.co.hsg.innventa.backing.util.Utils;
import java.io.Serializable;
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

    private String actualModule = "inicio";
    private String actualModuleCap;
    private String moduleType = "primary";
    private String icon = "cubes";
    private String actualPage = "/inicio";
    @Inject
    AppController app;

    public void inicio() {
        actualModule = "inicio";
        moduleType = "primary";
        icon = "cubes";
    }

    public void pedidos() {
        actualModule = "pedidos";
        moduleType = "warning";
        icon = "clipboard";
    }

    public void remisiones() {
        actualModule = "remisiones";
        moduleType = "primary";
        icon = "truck";
    }

    public void terceros() {
        actualModule = "terceros";
        moduleType = "";
        icon = "cubes";
    }

    public void productos() {
        actualModule = "productos";
        moduleType = "success";
        icon = "shopping-cart";
    }

    public void clientes() {
        actualModule = "clientes";
        moduleType = "info";
        icon = "users";
    }

    public String getActualPage() {
        actualPage = "/" + actualModule + "/index";
        return actualPage;
    }

    public String getActualModule() {
        return actualModule;
    }

    public void setActualModule(String actualModule) {
        this.actualModule = actualModule;
    }

    public void setActualPage(String actualPage) {
        this.actualPage = actualPage;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getActualModuleCap() {

        if (actualModule.equalsIgnoreCase("inicio")) {
            SexType st = SexType.getType(app.getUser().getPersona().getSexo());
            String bnv = "";
            switch (st) {
                case MASCULINO:
                    bnv = "Bienvenido ";
                    break;
                case FEMENINO:
                    bnv = "Bienvenida ";
                    break;
            }
            actualModuleCap = bnv + Utils.capitalizes(app.getUser().getPersona().getNombre1());
        } else {
            actualModuleCap = Utils.capitalizes(actualModule);
        }
        return actualModuleCap;
    }

    public void setActualModuleCap(String actualModuleCap) {
        this.actualModuleCap = actualModuleCap;
    }

}
