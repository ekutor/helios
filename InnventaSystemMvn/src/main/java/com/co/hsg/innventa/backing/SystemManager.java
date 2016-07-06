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

    private String sequence;
    @Inject
    private ParametrosController param;

    public String getSequence(NamedQuerys query) {
            Parametros p = param.chargeItem(query,true);
            sequence = Utils.getReference(p.getClave1(), p.getClave2());
        
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
    public void saveSequence(NamedQuerys query){
        Parametros p = param.chargeItem(query,true);
        try{
        int key = Integer.parseInt(p.getClave2());
        key++;
        p.setClave2(String.valueOf(key));
        param.setSelected(p);
        param.save(query);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void decreaseSequence(NamedQuerys query){
        Parametros p = param.chargeItem(query,true);
        try{
        int key = Integer.parseInt(p.getClave2());
        key--;
        p.setClave2(String.valueOf(key));
        param.setSelected(p);
        param.save(query);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    

}
