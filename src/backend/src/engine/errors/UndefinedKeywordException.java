package engine.errors;

/**
 * This class is a custom exception that is thrown by the Lexer when there are unrecognized tokens in the user's input.
 *
 * @author Haotian Wang
 */
public class UndefinedKeywordException extends Exception {
    public UndefinedKeywordException(String message) { super(message); }

    public UndefinedKeywordException(Throwable cause) { super(cause); }

    public UndefinedKeywordException(String message, Throwable cause) { super(message, cause); }

    @Override
    public String getMessage() { return super.getMessage(); }

    @Override
    public Throwable getCause() { return super.getCause(); }
}
