package engine.errors;

/**
 * This exception class handles the error that gets thrown by the Interpreter even though the statement input is parsed correctly by the Parser.
 *
 * @author Haotian Wang
 */
public class InterpretationException extends Exception {
    public InterpretationException(String message) { super(message); }

    public InterpretationException(Throwable cause) { super(cause); }

    public InterpretationException(String message, Throwable cause) { super(message, cause); }

    @Override
    public String getMessage() { return super.getMessage(); }

    @Override
    public Throwable getCause() { return super.getCause(); }
}
