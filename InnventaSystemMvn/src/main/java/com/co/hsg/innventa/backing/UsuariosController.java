package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.converter.CryptoConverter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController extends AbstractController<Usuarios> {

    @Inject
    private PersonasController personaController;
    @Inject
    private MobilePageController mobilePageController;
    
    private CryptoConverter crypto;
    
    private String confirmationPass;
    
    public UsuariosController() {
        // Inform the Abstract parent controller of the concrete Usuarios Entity
        super(Usuarios.class);
        crypto = new CryptoConverter();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        personaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePersona(ActionEvent event) {
        if (this.getSelected() != null && personaController.getSelected() == null) {
            personaController.setSelected(this.getSelected().getPersona());
        }
    }

    public String getConfirmationPass() {
        return confirmationPass;
    }

    public void setConfirmationPass(String confirmationPass) {
        this.confirmationPass = confirmationPass;
    }

    @Override
    public void saveNew(ActionEvent event) {
        if(validatePassword()){
            selected.setPassw(crypto.convertToDatabaseColumn(confirmationPass));
            super.saveNew(event);   
        }
        
    }
    
     @Override
    public void save(ActionEvent event) {
            if(validatePassword()){
                selected.setPassw(crypto.convertToDatabaseColumn(confirmationPass));
                super.save(event); 
            }
    }
    
    public boolean validatePassword(){
       if(confirmationPass != null && confirmationPass.equals(selected.getPassw())){
           if(confirmationPass.length() < 8){ 
             JsfUtil.addErrorMessage("Datos Invalidos" ,"La contraseña debe tener por lo menos 8 caracteres"); 
             return false;
           }else{
              return true; 
           }
           
       }else{
            JsfUtil.addErrorMessage("Datos Invalidos" ,"La contraseña y su confirmación no coinciden");
            return false;
       } 
    }
    
}
