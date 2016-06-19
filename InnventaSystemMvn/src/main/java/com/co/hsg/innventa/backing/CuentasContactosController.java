package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.CuentasContactos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cuentasContactosController")
@ViewScoped
public class CuentasContactosController extends AbstractController<CuentasContactos> {

    @Inject
    private PersonasController idPersonaController;
    @Inject
    private CuentasController idClienteController;
    @Inject
    private MobilePageController mobilePageController;

    public CuentasContactosController() {
        // Inform the Abstract parent controller of the concrete CuentasContactos Entity
        super(CuentasContactos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPersonaController.setSelected(null);
        idClienteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    /*public void prepareIdPersona(ActionEvent event) {
        if (this.getSelected() != null && idPersonaController.getSelected() == null) {
            idPersonaController.setSelected(this.getSelected().get);
        }
    }*/

    /**
     * Sets the "selected" attribute of the Cuentas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
      /*  if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }*/
    }
}
