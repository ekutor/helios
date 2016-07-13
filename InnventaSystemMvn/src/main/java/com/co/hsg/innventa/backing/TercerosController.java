package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Terceros;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tercerosController")
@ViewScoped
public class TercerosController extends AbstractController<Terceros> {

    @Inject
    private MobilePageController mobilePageController;
    
    @Inject
    private PersonasController personasController;

    public TercerosController() {
        // Inform the Abstract parent controller of the concrete Terceros Entity
        super(Terceros.class);
    }
    
    @Override
    public Terceros prepareCreate(ActionEvent event) {
        personasController.prepareCreate(event);
        return super.prepareCreate(event);
    }
    
    @Override
    public void saveNew(ActionEvent event) {
        if(personasController.getSelected() != null){
           selected.setContacto(personasController.getSelected());
           personasController.saveNew(event);
        }
        super.saveNew(event);
    }

}
