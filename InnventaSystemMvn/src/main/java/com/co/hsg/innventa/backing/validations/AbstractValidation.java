package com.co.hsg.innventa.backing.validations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hectsaga
 */
public abstract class AbstractValidation implements IValidation, Serializable{
     protected Set<String> errors;
     
     public AbstractValidation(){
          errors = new HashSet<>();
     }
}
