package engine.slogoast;

/**
 * This is an abstract class that all AST nodes shall extend from.
 *
 * @author Haotian Wang
 */
public abstract class Expression {
    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    public abstract String toString();
}
