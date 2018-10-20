package engine.commands;

/**
 * This is an functional interface that changes the states of a Turtle.
 *
 * @author Haotian Wang
 */
@FunctionalInterface
public interface TurtleCommand<T> {
    void update(T t);
}
