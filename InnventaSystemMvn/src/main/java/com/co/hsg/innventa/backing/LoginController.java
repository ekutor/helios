package com.co.hsg.innventa.backing;


import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.session.UsuariosFacade;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hectsaga
 */
@Named(value="loginController")
@SessionScoped
public class LoginController extends AbstractController<Usuarios> {
    
    private String username;
    private String password;
    
    private boolean loggedIn;
    
    @Inject
    private UsuariosFacade ejbFacade;
    

    public LoginController() {
        // Inform the Abstract parent controller of the concrete Usuarios Entity
        super(Usuarios.class);
    }
    
    public String login() {
        Usuarios user = ejbFacade.find(username);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null || !user.getPassw().equals(password)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario o Clave incorrectos, Intente Nuevamente");
            FacesContext.getCurrentInstance().addMessage("loginForm:usuario", msg);
          
            username = null;
            password = null;
            return null;
        } else {
            context.getExternalContext().getSessionMap().put("user", user);
            return "/dashboard?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
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
}
