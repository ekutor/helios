package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.CuentasDireccion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cuentasDireccionController")
@ViewScoped
public class CuentasDireccionController extends AbstractController<CuentasDireccion> {

    @Inject
    private CuentasController idClienteController;
    @Inject
    private MobilePageController mobilePageController;

    public CuentasDireccionController() {
        // Inform the Abstract parent controller of the concrete CuentasDireccion Entity
        super(CuentasDireccion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idClienteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Cuentas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
        if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }
    }
}
