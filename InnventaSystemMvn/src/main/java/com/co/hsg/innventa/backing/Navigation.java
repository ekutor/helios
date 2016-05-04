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

    private Modules actualModule = Modules.INIT;
    private String actualModuleCap;
    private String icon = "cubes";
    private String actualPage = "/inicio";
    @Inject
    AppController app;

    @Inject
    ParametrosController parametrosController;

    /**
     * Define el modulo de configuracion de maestros selecccionado
     * @param type 
     */
    public void states(String type) {
        icon = "edit";
        switch (type) {
            case "orders":
                actualModule = Modules.STATES_OC;
                break;
            case "purchaseOrders":
                actualModule = Modules.STATES_RM;
                break;
            case "productCategories":
                 actualModule = Modules.CATEGORIES_PROD;
                break;
        }

    }
    
    public void init() {
        actualModule = Modules.INIT;
        icon = "cubes";
    }
    
    public void orders() {
        actualModule = Modules.ORDERS;
        icon = "clipboard";
    }

    public void createOrder() {
        this.orders();
        actualModule = Modules.CREATE_ORDER;
    }
    
    public void purchaseOrders() {
        actualModule = Modules.PURCHASE_ORDERS;
        icon = "truck";
    }
    
    
    public void purchaseOrdersConfig() {
        parametrosController.cargarObj("purchaseOrder");
        actualModule = Modules.CONFIGURATION;
        icon = "gears";
    }
    
    public void thirds() {
        actualModule = Modules.THIRDS;
        icon = "cubes";
    }

    public void products() {
        actualModule = Modules.PRODUCTS;
        icon = "shopping-cart";
    }

    public void accounts() {
        actualModule = Modules.ACCOUNTS;
        icon = "users";
    }

    public String getActualPage() {
        actualPage = "/" + actualModule.getModuleName() + "/index";
        return actualPage;
    }

    public String getActualModule() {
        return actualModule.getModuleName();
    }

    public void setActualModule(String actualModule) {
        this.actualModule = Modules.getModule(actualModule);
    }

    public void setActualPage(String actualPage) {
        this.actualPage = actualPage;
    }
    /**
     * Obtiene el tipo de presentacion de un modulo especifico
     * @return 
     */
    public String moduleType() { 
        String moduleType = actualModule.getModuleType();
        return moduleType;
    }
    /**
     * Obtiene el tipo de presentacion de un modulo especifico
     * @param moduleName
     * @return 
     */
    public String moduleType(String moduleName) { 
        String moduleType = Modules.getModule(moduleName).getModuleType();
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
            switch (actualModule){
                case INIT:
                    SexType st = SexType.getType(app.getUser().getPersona().getSexo());
                    String bnv = "";
                    switch (st) {
                        case MALE:
                            bnv = "Bienvenido ";
                            break;
                        case FEMALE:
                            bnv = "Bienvenida ";
                            break;
                    }
                    actualModuleCap = bnv + Utils.capitalizes(app.getUser().getPersona().getNombre1());
                    break;
                case STATES_OC:
                    actualModuleCap = "Registros Maestros";
                break;
                 case STATES_RM:
                    actualModuleCap = "Registros Maestros";
                break;
                 case CATEGORIES_PROD:
                    actualModuleCap = "Registros Maestros";
                break;
                 case ORDERS:
                    actualModuleCap = "Ordenes de Compra";
                break;
                default:
                    actualModuleCap = Utils.capitalizes(actualModule.getModuleName());
                break;
            }
        } catch (java.lang.NullPointerException ne) {

        }
        return actualModuleCap;
    }

    public void setActualModuleCap(String actualModuleCap) {
        this.actualModuleCap = actualModuleCap;
    }

}
