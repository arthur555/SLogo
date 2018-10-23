package engine.compiler.interpreter;

import engine.commands.Command;

import engine.compiler.slogoast.*;
import engine.errors.InterpretationException;


/**
 * Take in abstract syntax tree from Parser.
 * Generate a list of TurtleAction from the abstract syntax tree.
 * Or alternatively, instead of creating a TurtleAction object, output the desired coordinates and direction for the turtle after the end of each tick cycle.
 *
 * Read in the abstract syntax tree and evaluate the return value of the expression.
 * Note this is a family of methods because it uses the Visitor pattern to employ different interpret methods for different kinds (subclasses) of Expressions.
 *
 * This method evaluates the return value of the abstract syntax tree, not executing any of the commands.
 * Note this is a family of methods because it uses the Visitor pattern to employ different evaluate methods for different kinds (subclasses) of Expressions.
 *
 * @author Haotian Wang
 */
public interface Interpreter {
    /**
     * Interpret a Binary AST node.
     *
     * @param binaryNode
     * @throws InterpretationException
     */
    void interpret(Binary binaryNode) throws InterpretationException;

    /**
     * Interpret a Condition AST node.
     *
     * @param conditionNode
     * @throws InterpretationException
     */
    void interpret(Condition conditionNode) throws InterpretationException;

    /**
     * Interpret a Direct AST node.
     *
     * @param directNode
     * @throws InterpretationException
     */
    void interpret(Direct directNode) throws InterpretationException;

    /**
     * Interpret a DoTimes AST node.
     *
     * @param doTimesNode
     * @throws InterpretationException
     */
    void interpret(DoTimes doTimesNode) throws InterpretationException;

    /**
     * Interpret an ExpressionList AST node.
     *
     * @param expressionListNode
     * @throws InterpretationException
     */
    void interpret(ExpressionList expressionListNode) throws InterpretationException;

    /**
     * Interpret a For AST node.
     *
     * @param forNode
     * @throws InterpretationException
     */
    void interpret(For forNode) throws InterpretationException;

    /**
     * Interpret a Group AST node.
     *
     * @param groupNode
     * @throws InterpretationException
     */
    void interpret(Group groupNode) throws InterpretationException;

    /**
     * Interpret an IfElse AST node.
     *
     * @param ifELseNode
     * @throws InterpretationException
     */
    void interpret(IfElse ifELseNode) throws InterpretationException;

    /**
     * Interpret a MakeUserInstruction AST ndoe.
     *
     * @param makeUserInstructionNode
     * @throws InterpretationException
     */
    void interpret(MakeUserInstruction makeUserInstructionNode) throws InterpretationException;

    /**
     * Interpret a MakeVariable AST node.
     *
     * @param makeVariableNode
     * @throws InterpretationException
     */
    void interpret(MakeVariable makeVariableNode) throws InterpretationException;

    /**
     * Interpret a Unary AST node.
     *
     * @param unaryNode
     * @throws InterpretationException
     */
    void interpret(Unary unaryNode) throws InterpretationException;

    /**
     * Interpret a Variable AST node.
     *
     * @param variableNode
     * @throws InterpretationException
     */
    void interpret(Variable variableNode) throws InterpretationException;

    /**
     * Interpret a VariableList AST node.
     *
     * @param variableListNode
     * @throws InterpretationException
     */
    void interpret(VariableList variableListNode) throws InterpretationException;

    /**
     * Interpret a UserFunction AST node.
     *
     * @param userFunctionNode
     * @throws InterpretationException
     */
    void interpret(UserFunction userFunctionNode) throws InterpretationException;

    /**
     * Evaluate a UserFunction AST node.
     *
     * @param userFunctionNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(UserFunction userFunctionNode) throws InterpretationException;

    /**
     * Evaluate a Binary AST node.
     *
     * @param binaryNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(Binary binaryNode) throws InterpretationException;

    /**
     * Evaluate a Condition AST node.
     *
     * @param conditionNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(Condition conditionNode) throws InterpretationException;

    /**
     * Evaulate a Direct AST node.
     *
     * @param directNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(Direct directNode) throws InterpretationException;

    /**
     * Evaluate a DoTimes AST node.
     *
     * @param doTimesNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(DoTimes doTimesNode) throws InterpretationException;

    /**
     * Evaluate an ExpressionList AST node.
     *
     * @param expressionListNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(ExpressionList expressionListNode) throws InterpretationException;

    /**
     * Evaluate a For AST node.
     *
     * @param forNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(For forNode) throws InterpretationException;

    /**
     * Evaluate a Group AST node.
     *
     * @param groupNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(Group groupNode) throws InterpretationException;

    /**
     * Evaluate an IfElse AST node.
     *
     * @param ifELseNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(IfElse ifELseNode) throws InterpretationException;

    /**
     * Evaluate a MakeUserInstruction AST ndoe.
     *
     * @param makeUserInstructionNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(MakeUserInstruction makeUserInstructionNode) throws InterpretationException;

    /**
     * Evaluate a MakeVariable AST node.
     *
     * @param makeVariableNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(MakeVariable makeVariableNode) throws InterpretationException;

    /**
     * Evaluate a Unary AST node.
     *
     * @param unaryNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(Unary unaryNode) throws InterpretationException;

    /**
     * Evaluate a Variable AST node.
     *
     * @param variableNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(Variable variableNode) throws InterpretationException;

    /**
     * Evaluate a VariableList AST node.
     *
     * @param variableListNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    double evaluate(VariableList variableListNode) throws InterpretationException;
}
