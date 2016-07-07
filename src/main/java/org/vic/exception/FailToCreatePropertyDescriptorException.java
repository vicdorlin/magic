package org.vic.exception;

/**
 * @author vicdor
 * @create 2016-07-07 18:05
 */
public class FailToCreatePropertyDescriptorException extends RuntimeException {

    private static final long serialVersionUID = 6978237104994882715L;

    public FailToCreatePropertyDescriptorException() {
    }

    public FailToCreatePropertyDescriptorException(String message) {
        super(message);
    }

    public FailToCreatePropertyDescriptorException(String message, Throwable cause) {
        super(message, cause);
    }
}
