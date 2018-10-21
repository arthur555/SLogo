package engine.Lexer;

/**
 * This class represents a Token object used by the
 *
 * @author Haotian Wang
 */
public class Token {
    private String myString;
    private String myType;

    public Token(String string, String type) {
        myString = string;
        myType = type;
    }
}
