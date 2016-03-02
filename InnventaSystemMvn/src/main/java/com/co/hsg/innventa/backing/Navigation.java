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
    private String icon = "cubes";
    private String actualPage = "/inicio";
    @Inject
    AppController app;

    @Inject
    ParametrosController parametrosController;

    public void inicio() {
        actualModule = "inicio";
        icon = "cubes";
    }

    public void pedidos() {
        actualModule = "pedidos";
        icon = "clipboard";
    }

    public void createPedidos() {
        this.pedidos();
        actualModule = "pedidosCreate";
    }

    public void orders() {
        parametrosController.cargarObj("orders");
        actualModule = "configuracion";
        icon = "gears";
    }

    public void estados(String tipo) {
        icon = "edit";
        switch (tipo) {
            case "orders":
                actualModule = "estadosOC";
                break;
            case "remissions":
                actualModule = "estadosRM";
                break;
            case "productCategories":
                 actualModule = "categoriasProd";
                break;
        }

    }

    public void remisiones() {
        actualModule = "remisiones";
        icon = "truck";
    }

    public void terceros() {
        actualModule = "terceros";
        icon = "cubes";
    }

    public void productos() {
        actualModule = "productos";
        icon = "shopping-cart";
    }

    public void cuentas() {
        actualModule = "cuentas";
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

    public String moduleType(String module) {
        System.out.println("com.co.hsg.innventa.backing.Navigation.moduleType()"+module);
        if( module == null ){
            return "primary";
        }
        Modules m = Modules.getModule(module);
        String moduleType;
        switch (m) {
            case PEDIDOS:
                moduleType = "warning";
                break;
            case REMISIONES:
                moduleType = "primary";
                break;
            case CUENTAS:
                moduleType = "info";
                break;
            case PRODUCTOS:
                moduleType = "success";
                break;
            case TERCEROS:
                moduleType = "info";
                break;
            case MAESTROS:
                moduleType = "info";
                break;
            default:
                moduleType = "primary";
                break;
        }
        
        return moduleType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getActualModuleCap() {
        try {
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
            } else if (actualModule.contains("estado") || actualModule.contains("categoria")) {
                actualModuleCap = "Registros Maestros";
            } else {
                actualModuleCap = Utils.capitalizes(actualModule);
            }
        } catch (java.lang.NullPointerException ne) {

        }
        return actualModuleCap;
    }

    public void setActualModuleCap(String actualModuleCap) {
        this.actualModuleCap = actualModuleCap;
    }

}
