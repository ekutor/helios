package com.co.hsg.innventa.backing.validations;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hectsaga
 */
public interface IValidation {
    public Set<String> errors = new HashSet<String>();
    public void doValidate();
}
