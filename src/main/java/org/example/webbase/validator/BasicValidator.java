package org.example.webbase.validator;

public interface BasicValidator<T> {
    boolean isEmpty(T data);

    boolean isValid(T data);

}
