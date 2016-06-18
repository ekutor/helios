package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Categorias;
import com.co.hsg.innventa.beans.UnidadesMedida;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "unidadMedidaController")
@ViewScoped
public class UnidadesMedidaController extends AbstractController<UnidadesMedida> {

    @Inject
    private MobilePageController mobilePageController;
    
     private Modules selectedModule;

    public UnidadesMedidaController() {
        super(UnidadesMedida.class);
    }


}
