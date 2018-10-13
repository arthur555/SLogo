package engine.errors;

/**
 * An exception class that is thrown when the user's commands cannot be parsed.
 */
public class CommandSyntaxException extends Exception {
    private String message;
    private Throwable cause;

    public CommandSyntaxException(String message) { super(message); }

    public CommandSyntaxException(Throwable cause) { super(cause); }

    public CommandSyntaxException(String message, Throwable cause) { super(message, cause); }

    public String getMessage() { return message; }

    public Throwable getCause() { return cause; }
}
