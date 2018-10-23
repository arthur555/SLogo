package engine.compiler.interpreter;

import engine.commands.Command;


import engine.compiler.slogoast.*;
import engine.errors.InterpretationException;
import model.TurtleModel;
import java.util.List;

/**
 * This is a crude interpreter implementation.
 *
 * @author Haotian Wang
 */
public class CrudeInterpreter implements Interpreter{

    /**
     * Read in the abstract syntax tree and evaluate the return value of the expression.
     * Note this is a family of methods because it uses the Visitor pattern to employ different interpret methods for different kinds (subclasses) of Expressions.
     *
     * @param astNode : SLogoAST representing the command tree.
     * @throws InterpretationException
     */
    @Override
    public void interpret(Expression astNode) throws InterpretationException {

    }

    /**
     * This method evaluates the return value of the abstract syntax tree, not executing any of the commands.
     * Note this is a family of methods because it uses the Visitor pattern to employ different evaluate methods for different kinds (subclasses) of Expressions.
     *
     * @param astNode : SLogoAST representing the parsed command.
     * @return A double value returned by evaluating this command, however not executing any of these commands on the turtle.
     * @throws InterpretationException
     */
    @Override
    public double evaulate(Expression astNode) throws InterpretationException {
        return 0;
    }

    /**
     * Interpret a Binary AST node.
     *
     * @param binaryNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(Binary binaryNode) throws InterpretationException {

    }

    /**
     * Interpret a Condition AST node.
     *
     * @param conditionNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(Condition conditionNode) throws InterpretationException {

    }

    /**
     * Interpret a Direct AST node.
     *
     * @param directNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(Direct directNode) throws InterpretationException {

    }

    /**
     * Interpret a DoTimes AST node.
     *
     * @param doTimesNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(DoTimes doTimesNode) throws InterpretationException {

    }

    /**
     * Interpret an ExpressionList AST node.
     *
     * @param expressionListNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(ExpressionList expressionListNode) throws InterpretationException {

    }

    /**
     * Interpret a For AST node.
     *
     * @param forNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(For forNode) throws InterpretationException {

    }

    /**
     * Interpret a Group AST node.
     *
     * @param groupNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(Group groupNode) throws InterpretationException {

    }

    /**
     * Interpret an IfElse AST node.
     *
     * @param ifELseNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(IfElse ifELseNode) throws InterpretationException {

    }

    /**
     * Interpret a MakeUserInstruction AST ndoe.
     *
     * @param makeUserInstructionNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(MakeUserInstruction makeUserInstructionNode) throws InterpretationException {

    }

    /**
     * Interpret a MakeVariable AST node.
     *
     * @param makeVariableNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(MakeVariable makeVariableNode) throws InterpretationException {

    }

    /**
     * Interpret a Unary AST node.
     *
     * @param unaryNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(Unary unaryNode) throws InterpretationException {

    }

    /**
     * Interpret a Variable AST node.
     *
     * @param variableNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(Variable variableNode) throws InterpretationException {

    }

    /**
     * Interpret a VariableList AST node.
     *
     * @param variableListNode
     * @throws InterpretationException
     */
    @Override
    public void interpret(VariableList variableListNode) throws InterpretationException {

    }

    /**
     * Evaluate a Binary AST node.
     *
     * @param binaryNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(Binary binaryNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a Condition AST node.
     *
     * @param conditionNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(Condition conditionNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaulate a Direct AST node.
     *
     * @param directNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(Direct directNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a DoTimes AST node.
     *
     * @param doTimesNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(DoTimes doTimesNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate an ExpressionList AST node.
     *
     * @param expressionListNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(ExpressionList expressionListNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a For AST node.
     *
     * @param forNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(For forNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a Group AST node.
     *
     * @param groupNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(Group groupNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate an IfElse AST node.
     *
     * @param ifELseNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(IfElse ifELseNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a MakeUserInstruction AST ndoe.
     *
     * @param makeUserInstructionNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(MakeUserInstruction makeUserInstructionNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a MakeVariable AST node.
     *
     * @param makeVariableNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(MakeVariable makeVariableNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a Unary AST node.
     *
     * @param unaryNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(Unary unaryNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a Variable AST node.
     *
     * @param variableNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(Variable variableNode) throws InterpretationException {
        return 0;
    }

    /**
     * Evaluate a VariableList AST node.
     *
     * @param variableListNode
     * @return A double value returned by evaluating this command.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(VariableList variableListNode) throws InterpretationException {
        return 0;
    }
}
