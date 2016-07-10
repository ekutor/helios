package com.co.hsg.innventa.backing;


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
        
        //First submenu
        DefaultSubMenu modMenu = new DefaultSubMenu("Modulos Activos");
        
        for(Parametros modBD :listModulesBD){
            if(modBD.getClave2()== null || "".equals(modBD.getClave2())){
                   continue;
               }
            for(Modules dinaMod : Modules.values()){
               
                if(dinaMod.name().equals(modBD.getId())){
                    DefaultMenuItem item = new DefaultMenuItem(modBD.getClave1());
                    //item.setIcon("ui-icon-"+dinaMod.getIcon());
                    item.setCommand("#{navigation."+dinaMod.name().toLowerCase()+"}");
                    item.setUpdate("contentPanel labelModule");
                    modMenu.addElement(item);
                    break;
                }
            }
        }
        
        menu.addElement(modMenu);
        /*
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Informes");
        item = new DefaultMenuItem("Save");
        item.setIcon("ui-icon-disk");
        im.setCommand("#{menuBean.save}");
        item.setUpdatete(":contentPanel");
        secondSubmenu.addElement(item);
 
        item = new DefaultMenuItem("Delete");
        item.setIcon("ui-icon-close");
        item.setCommand("#{menuBean.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);
 
        item = new DefaultMenuItem("Redirect");
        item.setIcon("ui-icon-search");
        item.setCommand("#{menuBean.redirect}");
        secondSubmenu.addElement(item);
 
        menu.addElement(secondSubmenu);*/
        
        DefaultSubMenu MastersSubmenu = new DefaultSubMenu("Maestros");
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
