package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.session.RemisionesProductoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="remisionesProductoController")
@ViewScoped
public class RemisionesProductoController extends AbstractController<RemisionesProducto> {

    @Inject
    private RemisionesProductoFacade ejbFacade;
    @Inject
    private RemisionesController idRemisionController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete RemisionesProducto controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RemisionesProductoController() {
        // Inform the Abstract parent controller of the concrete RemisionesProducto Entity
        super(RemisionesProducto.class);
    }


    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRemisionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Remisiones controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRemision(ActionEvent event) {
        if (this.getSelected() != null && idRemisionController.getSelected() == null) {
            idRemisionController.setSelected(this.getSelected().getIdRemision());
        }
    }
}
