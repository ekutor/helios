package com.co.hsg.innventa.backing;


import com.co.hsg.innventa.backing.util.Actions;
import com.co.hsg.innventa.beans.AclAcciones;
import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.converter.CryptoConverter;
import com.co.hsg.innventa.session.NamedQuerys;
import com.co.hsg.innventa.session.UsuariosFacade;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Separator;

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
    
    private CryptoConverter crypto;

    public enum Options{
        ORDERS
    }

    public AppController() {
        super(Usuarios.class);
        crypto = new CryptoConverter();
    }
    
    private void chargeUserMenu(){
        menu = new DefaultMenuModel();
        
        Collection<Parametros> listModulesBD = params.chargeItems(NamedQuerys.PARAM_MODULES);
        
        DefaultSubMenu modMenu = new DefaultSubMenu("Modulos Activos");
        DefaultSubMenu repMenu = new DefaultSubMenu("Reportes");
        DefaultSubMenu rhumMenu = new DefaultSubMenu("Recurso Humano");
        DefaultSubMenu admMenu = new DefaultSubMenu("Administracion");
        
        for(Parametros modBD :listModulesBD){
            //No mostrar menu
            if(modBD.getClave2()== null || "".equals(modBD.getClave2())){
                   continue;
               }
            int modPos = Integer.parseInt(modBD.getClave2());
            DefaultMenuItem item = new DefaultMenuItem(modBD.getClave1());
            //item.setIcon("ui-icon-"+dinaMod.getIcon());
            item.setCommand("#{navigation."+modBD.getId().toLowerCase()+"}");
            item.setUpdate("contentPanel labelModule");
         
            if(modPos >= 10  && modPos < 20){
                //Mostrar Menu por Modulos
                modMenu.addElement(item);    
            }else if(modPos >= 20  && modPos < 30){
                repMenu.addElement(item);  
            }else if(modPos >= 30  && modPos < 40){
                rhumMenu.addElement(item);  
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
        Usuarios user = ejbFacade.find(username);
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext reqCont = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        
        String encryptedLoginPass = crypto.convertToDatabaseColumn(password);
        
        if (user == null || !user.getPassw().equals(encryptedLoginPass)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Usuario o Clave incorrectos, Intente Nuevamente");
            context.addMessage("msgLogin", msg);
            loggedIn = false;
            username = null;
            password = null;
            
            return null;
        } else {
            loggedIn = true;
            this.user = user;
            admin = false;
            for (AclRolesAccion actions : user.getRol().getAccionesList()) {
                AclAcciones actionModule = actions.getAccion();
                if (actionModule.getModulo().getId().equals(Modules.ADMIN.name())) {
                    if (actionModule.getAcceso() == Actions.PERMITTED.value) {
                        admin =  true;
                        break;
                    }
                }
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ ", username);
            context.getExternalContext().getSessionMap().put("user", user);
            return "/index?faces-redirect=true";
        }
    }

    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            loggedIn = false;
            System.out.println("com.co.hsg.innventa.backing.AppController.logout()");
            String path = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(path);
            
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
