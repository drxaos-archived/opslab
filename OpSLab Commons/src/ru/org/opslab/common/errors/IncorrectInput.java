package ru.org.opslab.common.errors;

public class IncorrectInput extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectInput(String param) {
        super(param);
    }

    public IncorrectInput(String param, Throwable cause) {
        super(param, cause);
    }
}
