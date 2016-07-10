package com.co.hsg.innventa.converter;

import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.session.UsuariosFacade;
import com.co.hsg.innventa.backing.util.JsfUtil;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import org.olap4j.impl.Base64;

@Named(value = "cryptoConverter")
@RequestScoped
public class CryptoConverter implements Converter {
    
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "Innv3nt4Syst3m.X".getBytes();
    
    @Inject
    private UsuariosFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 ) {
            return null;
        }
        return this.ejbFacade.find(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        else if(object instanceof String ){
            return (String) object;
        }else if (object instanceof Usuarios) {
            Usuarios o = (Usuarios) object;
            return o.getUsuario();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
            return null;
        }
    }

    public String convertToDatabaseColumn(String valueToEncrypt) {
      Key key = new SecretKeySpec(KEY, "AES");
      try {
         Cipher c = Cipher.getInstance(ALGORITHM);
         c.init(Cipher.ENCRYPT_MODE, key);
         String enc =  Base64.encodeBytes(c.doFinal(valueToEncrypt.getBytes()));
         return enc;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
    }

    public String convertToEntityAttribute(String valueToDecrypt) {
      Key key = new SecretKeySpec(KEY, "AES");
      try {
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        String enc =  new String(c.doFinal(Base64.decode(valueToDecrypt)));
        return enc;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

}
