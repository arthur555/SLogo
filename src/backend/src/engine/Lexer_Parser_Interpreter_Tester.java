package engine;

import engine.Lexer.CrudeLexer;
import engine.Lexer.Lexer;
import engine.Lexer.Token;
import engine.errors.CommandSyntaxException;
import engine.parser.CrudeParser;
import engine.parser.Parser;
import engine.slogoast.Expression;

import java.util.List;

/**
 * This class handles the preliminary testing of Lexer, Parser and Interpreter.
 *
 * @author Haotian Wang
 */
public class Lexer_Parser_Interpreter_Tester {
    /**
     * A main method to test the functionality.
     *
     * @param args
     */
    public static void main(String[] args) {
        Lexer lexer = new CrudeLexer();
        String test = "sum dotimes [:d 4] [fd sin 50 back 5 6] (sin(cos 50))";
        try {
            lexer.readString(test);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        List<Token> testSet = lexer.getTokens();
        System.out.println("The input String is:\n\n" + test + "\n");
        System.out.println("Lexer's Part\n======\nThe list of tokens is:\n");
        for (Token token : testSet) {
            System.out.println(token.toString());
        }

        Parser parser = new CrudeParser();
        try {
            parser.readTokens(testSet);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        Expression result = parser.returnAST();
        System.out.println("\nParser's Part\n======\nThe String representation of the syntax tree is:\n\n" + result.toString());
    }
}
