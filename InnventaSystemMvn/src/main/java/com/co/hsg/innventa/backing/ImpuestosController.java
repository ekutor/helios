package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Impuestos;
import com.co.hsg.innventa.session.ImpuestosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "impuestosController")
@ViewScoped
public class ImpuestosController extends AbstractController<Impuestos> {

    @Inject
    private ImpuestosFacade ejbFacade;
    @Inject
    private ImpuestosController impuestoPadreController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Impuestos controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ImpuestosController() {
        // Inform the Abstract parent controller of the concrete Impuestos Entity
        super(Impuestos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        impuestoPadreController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Impuestos entities that
     * are retrieved from Impuestos?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Impuestos page
     */
    public String navigateImpuestosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Impuestos_items", this.getSelected().getImpuestosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/impuestos/index";
    }

    /**
     * Sets the "selected" attribute of the Impuestos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareImpuestoPadre(ActionEvent event) {
        if (this.getSelected() != null && impuestoPadreController.getSelected() == null) {
            impuestoPadreController.setSelected(this.getSelected().getImpuestoPadre());
        }
    }
}
