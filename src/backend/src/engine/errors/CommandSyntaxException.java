package engine.errors;

/**
 * An exception class that is thrown when the user's commands cannot be parsed. This exception is thrown by the Parser.
 */
public class CommandSyntaxException extends Exception {

    public CommandSyntaxException(String message) { super(message); }

    public CommandSyntaxException(Throwable cause) { super(cause); }

    public CommandSyntaxException(String message, Throwable cause) { super(message, cause); }

    public String getMessage() { return super.getMessage(); }

    public Throwable getCause() { return super.getCause(); }
}
