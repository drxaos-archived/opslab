package ru.org.opslab.common.errors;

public class ParameterMustNotBeNull extends Exception {

    private static final long serialVersionUID = 1L;

    public ParameterMustNotBeNull(String param) {
        super(param);
    }

    public ParameterMustNotBeNull(String param, Throwable cause) {
        super(param, cause);
    }
}
