package org.vic.exception;

/**
 * @author vicdor
 * @create 2016-07-07 20:13
 */
public class ParameterTypeMismatchException extends RuntimeException {
    private static final long serialVersionUID = 2065276699880939135L;

    public ParameterTypeMismatchException() {
    }

    public ParameterTypeMismatchException(String message) {
        super(message);
    }

    public ParameterTypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
