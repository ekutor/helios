package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Cuentas;
import com.co.hsg.innventa.session.CuentasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="cuentasController")
@ViewScoped
public class CuentasController extends AbstractController<Cuentas> {

    @Inject
    private CuentasFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;


    public CuentasController() {
        // Inform the Abstract parent controller of the concrete Cuentas Entity
        super(Cuentas.class);
    }



    /**
    * Sets the "items" attribute with a collection of Pedidos entities that are retrieved from Cuentas?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for Pedidos page
     */
    public String navigatePedidosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pedidos_items", this.getSelected().getPedidosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedidos/index";
    }

    /**
    * Sets the "items" attribute with a collection of CuentasContactos entities that are retrieved from Cuentas?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for CuentasContactos page
     */
    public String navigateCuentasContactosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentasContactos_items", this.getSelected().getCuentasContactosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/cuentasContactos/index";
    }

    /**
    * Sets the "items" attribute with a collection of CuentasDireccion entities that are retrieved from Cuentas?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for CuentasDireccion page
     */
    public String navigateCuentasDireccionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentasDireccion_items", this.getSelected().getCuentasDireccionList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/cuentasDireccion/index";
    }

}
