package engine.commands;

/**
 * This is an functional interface that changes the states of a Turtle.
 *
 * @author Haotian Wang
 */
@FunctionalInterface
public interface Command<T> {

    /**
     * Update the states of the turtle model
     *
     * @param t
     */
    double update(T t);
}
