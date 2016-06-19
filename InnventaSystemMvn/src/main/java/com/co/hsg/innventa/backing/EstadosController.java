package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Estados;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estadosController")
@ViewScoped
public class EstadosController extends AbstractController<Estados> {

    private Modules actualModule;

    @Inject
    private MobilePageController mobilePageController;

    public EstadosController() {
        // Inform the Abstract parent controller of the concrete Estados Entity
        super(Estados.class);
    }

    /**
     * Sets the "items" attribute with a collection of Pedidos entities that are
     * retrieved from Estados?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Pedidos page
     */
    public String navigatePedidosCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pedidos_items", this.getSelected().getPedidosCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedidos/index";
    }

    public Collection<Estados> chargeItems(String moduleName) {
        actualModule = Modules.getModule(moduleName);
        String param = "modulo";
        items = this.chargeItems(NamedQuerys.STATUS, param, actualModule.getModuleName().toUpperCase());
        return items;
    }

    @Override
    public void saveNew(ActionEvent event) {
        Estados selected = this.getSelected();
        selected.setModulo(actualModule.name());
        super.saveNew(event);
    }

}
