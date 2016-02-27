package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.session.NamedQuerys;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hector Sanchez Garcia
 */
@Named(value = "systemManager")
@ApplicationScoped
public class SystemManager implements Serializable {

    private String OCSequence;
    @Inject
    private ParametrosController param;

    public String getOCSequence() {
            Parametros p = param.chargeItem(NamedQuerys.ORDER_PARAM);
            OCSequence = Utils.getRefOC(p.getClave1(), p.getClave2());
        
        return OCSequence;
    }

    public void setOCSequence(String OCSequence) {
        this.OCSequence = OCSequence;
    }
    
    public void saveOCSequence(){
        Parametros p = param.chargeItem(NamedQuerys.ORDER_PARAM);
        try{
        int key = Integer.parseInt(p.getClave2());
        key++;
        p.setClave2(String.valueOf(key));
        param.setSelected(p);
        param.save(null);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

}
