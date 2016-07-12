package com.co.hsg.innventa.backing;

/**
 *
 * @author Hector Sanchez Garcia
 */
public enum Modules {
    ORDERS("pedidos", "warning","clipboard"),REMISSIONS("remisiones", "primary","truck"),
    ACCOUNTS("clientes","info","users"), PRODUCTS("productos","success","shopping-cart"),
    THIRDS("terceros","danger","cubes"),USERS("usuarios","info","users"),MASTERS("maestros","info","edit"),
    STATES_OC("estadosOC","info","edit"),STATES_RM("estadosRM","info","edit"),
    CATEGORIES_PROD("categoriasProd","info","shopping-cart"),CONFIGURATION("configuracion","info","gears"),
    INIT("inicio","primary","cubes"), 
    CREATE_ORDER("pedidosCreate","primary","clipboard"),
    EDIT_ORDER("pedidosEdit","primary","clipboard"),
    CREATE_REMISSION("remisionesProducto","primary","truck"),
     EDIT_REMISSION("remisionesProductoEdit","primary","truck"),
    ACL("aclRoles","info","edit"),ACLACTIONS("aclRolesAccion","info","edit"),
    ADMIN("Administracion","info","gears"),
    ;
   
    private String moduleName;
    private String moduleType;
    private String icon;
    
    Modules(String moduleName, String moduleType, String icon){
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.icon = icon;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public String getIcon() {
        return icon;
    }
    
    
    public static Modules getModule(String module){
       for (Modules m : Modules.values()){
           if(m.moduleName.toUpperCase().equals(module.toUpperCase())){
               return m;
           }
       }
       return INIT;
    }
}
