package ru.org.opslab.common.errors;

public class NoSuchAttributeException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoSuchAttributeException(String param) {
        super(param);
    }

    public NoSuchAttributeException(String param, Throwable cause) {
        super(param, cause);
    }
}
