package ru.org.opslab.common.errors;

public class JustShowHelp extends Exception {

    private static final long serialVersionUID = 1L;

    public JustShowHelp(String param) {
        super(param);
    }

    public JustShowHelp(String param, Throwable cause) {
        super(param, cause);
    }
}
