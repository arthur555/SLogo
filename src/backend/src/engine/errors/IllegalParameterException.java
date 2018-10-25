package engine.errors;

public class IllegalParameterException extends Exception {
    public IllegalParameterException(String message) { super(message); }

    public IllegalParameterException(Throwable cause) { super(cause); }

    public IllegalParameterException(String message, Throwable cause) { super(message, cause); }

    @Override
    public String getMessage() { return super.getMessage(); }

    @Override
    public Throwable getCause() { return super.getCause(); }
}
