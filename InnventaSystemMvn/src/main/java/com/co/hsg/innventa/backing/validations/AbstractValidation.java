package com.co.hsg.innventa.backing.validations;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hectsaga
 */
public abstract class AbstractValidation implements IValidation{
     public Set<String> errors;
     
     protected AbstractValidation(){
          errors = new HashSet<>();
     }
}
