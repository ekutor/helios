package com.co.hsg.innventa.backing;


import com.co.hsg.innventa.backing.util.Actions;
import com.co.hsg.innventa.backing.util.Permissions;
import com.co.hsg.innventa.beans.AclAcciones;
import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.converter.CryptoConverter;
import com.co.hsg.innventa.session.NamedQuerys;
import com.co.hsg.innventa.session.UsuariosFacade;
import static com.sun.faces.facelets.util.Path.context;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author hectsaga
 */
@Named(value="app")
@SessionScoped
public class AppController extends AbstractController<Usuarios> {
    
    private String username;
    private String password;
    private Usuarios user;
    private boolean loggedIn;
    private String hiddenbyLogging = "hide";
    private String verMaestros = "show"; 
    private String maestros ="Mostrar Maestros";
    private boolean activarMaestros;

    private boolean admin;
    
    private MenuModel menu;
    
    @Inject
    private UsuariosFacade ejbFacade;
    @Inject
    private ParametrosController params;
    
   
    
    private Map<String,AclAcciones> userAccess;

    private boolean hasAccess(Parametros modDB) {
        return this.hasAccess(modDB.getId());
    }
    
    private boolean hasAccess(Modules module) {
        return this.hasAccess(module.name());
    }
    public boolean hasAccess(String modID) {
        if(userAccess == null){
            this.logout();
            return false;
        }
        AclAcciones mod = userAccess.get(modID);
        if(mod != null){
            return mod.getAcceso() == Permissions.PERMITTED.value;     
        }else{
            return false;   
        }         
    }
    
    public Permissions getAccessType(Modules module,Actions action) {
        if(userAccess == null){
            this.logout();
        }
       
        AclAcciones mod = userAccess.get(module.name());
        if(mod != null){
            short value = 0;
            switch(action){
                case ACCESS:
                    value = mod.getAcceso();
                    break;
                case CREATE:
                    value = mod.getCrear();
                    break;
                case DELETE:
                    value = mod.getEliminar();
                    break;
                case EDIT:
                   value = mod.getEditar();
                   break;
                case EXPORT:
                   value = mod.getExportar();
                   break;
                case IMPORT:
                   value = mod.getImportar();
                   break;
                case VIEW_COSTS:
                   value = mod.getVerPrecios();
                   break;
            }
            return Permissions.getAction(value);
        }else{
            return Permissions.NOT_ALLOWED;   
        }         
    }

    private void defAccessforUser() {
        userAccess = new HashMap<>();
        for (AclRolesAccion actions : user.getRol().getAccionesList()) {
                AclAcciones actionModule = actions.getAccion();
                userAccess.put(actionModule.getModulo().getId(), actionModule);
            }
    }

    public enum Options{
        ORDERS
    }

    public AppController() {
        super(Usuarios.class);
    }
    
    private void chargeUserMenu(){
        menu = new DefaultMenuModel();
        
        Collection<Parametros> listModulesBD = params.chargeItems(NamedQuerys.PARAM_MODULES);
        
        DefaultSubMenu modMenu = new DefaultSubMenu("Modulos Activos");
        DefaultSubMenu repMenu = new DefaultSubMenu("Reportes");
        DefaultSubMenu rhumMenu = new DefaultSubMenu("Recurso Humano");
        DefaultSubMenu admMenu = new DefaultSubMenu("Administracion");
        
        for(Parametros modDB :listModulesBD){
            //No mostrar menu
            if(modDB.getClave2()== null || "".equals(modDB.getClave2())){
                   continue;
               }
            int modPos = Integer.parseInt(modDB.getClave2());
            DefaultMenuItem item = new DefaultMenuItem(modDB.getClave1());
            //item.setIcon("ui-icon-"+dinaMod.getIcon());
            item.setCommand("#{navigation."+modDB.getId().toLowerCase()+"}");
            item.setUpdate("contentPanel labelModule");
            
            if(hasAccess(modDB)){
                if(modPos >= 10  && modPos < 20){
                    //Mostrar Menu por Modulos
                    modMenu.addElement(item);    
                }else if(modPos >= 20  && modPos < 30){
                    repMenu.addElement(item);  
                }else if(modPos >= 30  && modPos < 40){
                    rhumMenu.addElement(item);  
                }
            }
        }
        
        menu.addElement(modMenu);
        if(repMenu.getElementsCount() > 0){
            menu.addElement(new DefaultSeparator());
            menu.addElement(repMenu); 
        }
         if(rhumMenu.getElementsCount() > 0){
            menu.addElement(new DefaultSeparator());
            menu.addElement(rhumMenu); 
        }
        if(isAdmin()){
            menu.addElement(new DefaultSeparator());
            DefaultMenuItem item = new DefaultMenuItem("Estados O. C.");
            item.setCommand("#{navigation.states(\"orders\")}");
            item.setUpdate("contentPanel labelModule");
            admMenu.addElement(item); 
            
            item = new DefaultMenuItem("Categorias");
            item.setCommand("#{navigation.states(\"productCategories\")}");
            item.setUpdate("contentPanel labelModule");
            admMenu.addElement(item); 
            
            item = new DefaultMenuItem("Adm. de Roles");
            item.setCommand("#{navigation.access()}");
            item.setUpdate("contentPanel labelModule");
            admMenu.addElement(item); 
            
            item = new DefaultMenuItem("Adm. de Usuarios");
            item.setCommand("#{navigation.users()}");
            item.setUpdate("contentPanel labelModule");
            admMenu.addElement(item); 
            
            menu.addElement(admMenu);
            
        }
    }

    public MenuModel getMenu() {
        if(menu == null){
           chargeUserMenu(); 
        }
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }
    
    
    public String getActualUserId() {
        if (getUser() != null && getUser().getPersona().getId() != null) {
            return  getUser().getPersona().getId();
        }
        return null;
    }
    
     public Personas getActualUser() {
        if (getUser() != null) {
            return  getUser().getPersona();
        }
        return null;
    }
     
    public String login() {
        CryptoConverter crypto = new CryptoConverter();
        Usuarios userLogin = ejbFacade.find(username);
        FacesContext context = FacesContext.getCurrentInstance();
       
        FacesMessage msg;
        admin = false;
        String encryptedLoginPass = crypto.convertToDatabaseColumn(password);
        
        if (userLogin == null || !userLogin.getPassw().equals(encryptedLoginPass)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Usuario o Clave incorrectos, Intente Nuevamente");
            context.addMessage("msgLogin", msg);
            loggedIn = false;
            username = null;
            password = null;
            
            return null;
        } else {
            loggedIn = true;
            user = userLogin;
            
            defAccessforUser();
            if (userAccess.get(Modules.ADMIN.name()).getAcceso() == Permissions.PERMITTED.value) {
                admin =  true;
            }
            
            context.getExternalContext().getSessionMap().put("user", user);
            return "/index?faces-redirect=true";
        }
    }

    public void logout() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.invalidateSession();
            context.getSessionMap().clear();
            loggedIn = false;
            System.out.println("com.co.hsg.innventa.backing.AppController.logout()");
            String path = context.getApplicationContextPath();
            context.redirect(path);
            
        }catch(java.lang.IllegalStateException ie){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest req = (HttpServletRequest) context.getRequest();
            HttpServletResponse res = (HttpServletResponse) context.getResponse();
            
            HttpSession session = req.getSession(false);
           
            loggedIn = false;
            System.out.println("com.co.hsg.innventa.backing.AppController.logout() for Illegal State");
            String path = context.getApplicationContextPath();
  
            try{
                res.sendRedirect(path);
            } catch (IOException exi) {
                exi.printStackTrace();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
    }
    
    public boolean isAdmin(){
        return admin;
    }
    
     
    public String onload() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Usuarios u = (Usuarios) sessionMap.get("user");
        if(u == null){
            System.out.println(" sin usuario");
            return "/login";
        }else{
            System.out.println("Usuario ok ");
            return null;
        }
    }

    public String getHiddenbyLogging() {
        if(loggedIn){
            hiddenbyLogging = "show";
        }else{
            hiddenbyLogging = "hide";
        }
        return hiddenbyLogging;
    }
    
     public String getShowConfig() {
        if(admin){
            hiddenbyLogging = "show";
        }else{
            hiddenbyLogging = "hide";
        }
        return hiddenbyLogging;
    }

    public void setHiddenbyLogging(String hiddenbyLogging) {
        this.hiddenbyLogging = hiddenbyLogging;
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getVerMaestros() {
        return verMaestros;
    }

    public void setVerMaestros(String verMaestros) {
        this.verMaestros = verMaestros;
    }

    public void activarMaestros() {
        if(activarMaestros){
            verMaestros = "show";
            maestros = "Ocultar Maestros";
            activarMaestros = false;
        }else{
            verMaestros = "hide";
            maestros = "Mostrar Maestros";
            activarMaestros = true;
        }
    }

    public String getMaestros() {
        return maestros;
    }

    public void setMaestros(String maestros) {
        this.maestros = maestros;
    }
    
    
    
}
