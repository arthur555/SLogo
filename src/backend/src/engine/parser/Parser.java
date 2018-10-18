package engine.parser;

import engine.SLogoAST;

/**
 * Take in the String input that the user types into the editor window.
 * Output an abstract syntax tree corresponding to the String command.
 * Throw CommandSyntaxException.
 * Recognize various languages.
 * (maybe) Suggest the closest command before throwing a CommandSyntaxException.
 *
 * @author Haotian Wang
 */
public interface Parser {
    /**
     * Either store the variables if rawCommand is a variable declaration or process the command into an AST.
     * @param rawCommand: A String that the user types in exactly as it appears in the editor view.
     */
    void readRawCommand(String rawCommand);

    /**
     * If the command passed in is in the form of "x = 2", store the variable in an internal map of the Parser instead of constructing an abstract syntax tree.
     */
    void storeVariable();

    /**
     * @return An SLogoAST for the interpreter to process.
     */
    SLogoAST returnAST();
}
