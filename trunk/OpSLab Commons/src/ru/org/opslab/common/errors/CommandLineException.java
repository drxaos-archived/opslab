package ru.org.opslab.common.errors;

public class CommandLineException extends Exception {

    private static final long serialVersionUID = 1L;

    public CommandLineException(String param) {
        super(param);
    }

    public CommandLineException(String param, Throwable cause) {
        super(param, cause);
    }
}
