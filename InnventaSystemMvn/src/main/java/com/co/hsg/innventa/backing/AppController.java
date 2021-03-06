package com.co.hsg.innventa.backing;


import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.session.UsuariosFacade;
import java.io.IOException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

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
    
    @Inject
    private UsuariosFacade ejbFacade;
   
    public enum Options{
        ORDERS
    }

    public AppController() {
        // Inform the Abstract parent controller of the concrete Usuarios Entity
        super(Usuarios.class);
    }
    
    public String getActualUserId() {
        if (getUser() != null && getUser().getPersona().getId() != null) {
            return  getUser().getPersona().getId();
        }
        return null;
    }
     
    public String login() {
        Usuarios user = ejbFacade.find(username);
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext reqCont = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        System.out.println("Usuario "+ username+ " pass: "+password);
        if (user == null || !user.getPassw().equals(password)) {
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
