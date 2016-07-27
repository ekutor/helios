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
   
    private String actualPage = "/inicio";
    @Inject
    AppController app;

    @Inject
    ParametrosController parametrosController;
    
    @Inject
    ReportsController reports;
    
    @Inject
    ProductosController products;

    /**
     * Define el modulo de configuracion de maestros selecccionado
     * @param type 
     */
    public void states(String type) {
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
    }
    
    public void access() {
        actualModule = Modules.ACL;
    }
     public void users() {
        actualModule = Modules.USERS;
    }
    public void actions() {
        actualModule = Modules.ACLACTIONS;
    }

    public void orders() {
        actualModule = Modules.ORDERS;
    }

    public void createOrder() {
        this.orders();
        actualModule = Modules.CREATE_ORDER;
    }
    
    public void editOrder() {
        actualModule = Modules.EDIT_ORDER;
    }
    
    public void remissions() {
        actualModule = Modules.REMISSIONS;
    }
   public void createPurchaseOrder() {
        this.remissions();
        actualModule = Modules.CREATE_REMISSION;
    }
   
   public void editPurchaseOrder() {
        actualModule = Modules.EDIT_REMISSION;
    }
    
    public void ordersConfig() {
        parametrosController.cargarObj(Modules.ORDERS);
        actualModule = Modules.CONFIGURATION;
    }
    
     public void remissionsConfig() {
        parametrosController.cargarObj(Modules.REMISSIONS);
        actualModule = Modules.CONFIGURATION;
    }
     
    public void reports(String report) {
      
      reports.cargarObj(Modules.getModule(report));
      actualModule = Modules.REPORTS_ORDERS;
    }
    
    public void thirds() {
        actualModule = Modules.THIRDS;
    }

    public void products() {
        actualModule = Modules.PRODUCTS;
    }
    
    public void products_supply() {
        actualModule = Modules.PRODUCTS_SUPPLY;
    }
    
    public void products_sale() {
        actualModule = Modules.PRODUCTS_SALE;
    }
            

    public void accounts() {
        actualModule = Modules.ACCOUNTS;
    }

    public String getActualPage() {
        actualPage = "/" + actualModule.getModuleName() + "/index";
        return actualPage;
    }

    public String getActualModule() {
        return actualModule.getModuleName();
    }
    
     public Modules getModule() {
        return actualModule;
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
        return actualModule.getIcon();
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
                case ACL:
                    actualModuleCap = "Permisos";
                break;
                case ACLACTIONS:
                    actualModuleCap = "Permisos";
                break;
                case CREATE_REMISSION:
                    actualModuleCap = "Remisiones";
                    break;
                case EDIT_REMISSION:
                    actualModuleCap = "Remisiones";
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
