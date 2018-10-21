package engine.Lexer;

import engine.errors.CommandSyntaxException;

import java.util.List;
import java.util.Queue;

/**
 * This interface handles the tokenization of input strings into a set of tokens.
 *
 * @author Haotian Wang
 */
public interface Lexer {
    /**
     * This processes the user input raw String.
     *
     * @param input: A user input raw String.
     */
    void readString(String input) throws CommandSyntaxException;

    /**
     * Return a list of Token from the input String, after translation by two translators.
     *
     * @return A list of Token from the input String.
     */
    Queue<Token> getTokens();
}
