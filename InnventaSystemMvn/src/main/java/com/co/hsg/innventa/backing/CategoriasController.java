package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Categorias;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "categoriasController")
@ViewScoped
public class CategoriasController extends AbstractController<Categorias> {

    @Inject
    private CategoriasController idpadreController;
    @Inject
    private MobilePageController mobilePageController;

    public CategoriasController() {
        // Inform the Abstract parent controller of the concrete Categorias Entity
        super(Categorias.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idpadreController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Categorias entities that
     * are retrieved from Categorias?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Categorias page
     */
    public String navigateCategoriasList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Categorias_items", this.getSelected().getCategoriasList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/categorias/index";
    }

    /**
     * Sets the "selected" attribute of the Categorias controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdpadre(ActionEvent event) {
        if (this.getSelected() != null && idpadreController.getSelected() == null) {
            idpadreController.setSelected(this.getSelected().getIdpadre());
        }
    }
}
