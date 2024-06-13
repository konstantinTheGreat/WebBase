package org.example.webbase.validator.impl;

import org.example.webbase.validator.BasicValidator;


public class BasicValidatorImpl<T> implements BasicValidator<T> {

    @Override
    public boolean isEmpty(Object data) {
        return data != null;
    }

    @Override
    public boolean isValid(Object data) {
        return false;
    }

}
