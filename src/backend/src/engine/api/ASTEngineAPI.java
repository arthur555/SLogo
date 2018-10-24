package engine.api;

import engine.compiler.Token;
import engine.compiler.lexer.CrudeLexer;
import engine.compiler.lexer.Lexer;
import engine.compiler.parser.CrudeParser;
import engine.compiler.parser.Parser;
import engine.compiler.slogoast.Expression;
import engine.compiler.storage.CrudeStateMachine;
import engine.compiler.storage.StateMachine;
import engine.errors.CommandSyntaxException;
import engine.errors.InterpretationException;
import engine.errors.UndefinedKeywordException;
import model.TurtleModel;

import java.util.List;

/**
 * This Engine API implementation uses the AST lexer, parser, interpreter model to execute String input.
 *
 * @author Haotian Wang
 */
public class ASTEngineAPI implements EngineAPI {
    private Lexer lexer;
    private Parser parser;
    private StateMachine stateMachine;
    private TurtleModel turtle;

    public ASTEngineAPI(TurtleModel turtleModel) {
        lexer = new CrudeLexer();
        parser = new CrudeParser();
        stateMachine = new CrudeStateMachine();
        turtle = turtleModel;
    }

    @Override
    public void processString(String str) throws ClassNotFoundException, UndefinedKeywordException, CommandSyntaxException, InterpretationException {
        lexer.readString(str);
        List<Token> listOfTokens = lexer.getTokens();
        parser.readTokens(listOfTokens);
        Expression command = parser.returnAST();
        command.interpret(turtle, stateMachine);
    }

    @Override
    public StateMachine stateMachine() { return stateMachine; }
}
