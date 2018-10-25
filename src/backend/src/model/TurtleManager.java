package model;

import java.util.List;
import java.util.function.Predicate;

/**
 *  An extension to the TurtleModel;
 *  It has all the methods within the TurtleModel but it applies the method to
 *  all the selected turtles.
 */
public interface TurtleManager extends TurtleModel {
    /**
     *  SPECIAL ID to represent ALL TURTLES within this model
     */
    int ALL = 0;

    /**
     * @return id of the LAST turtle within the selected group, always > 0
     */
    int id();

    /**
     * @return total number of turtles created
     */
    int size();

    /**
     * Selects all the turtles with given IDs
     * All operations will operate only to these selected turtles
     */
    double tell(List<Integer> turtleIDs);

    /**
     * Performs the operation given by `ops` to all the turtles with the given IDs
     * It does not alter the previous selection
     */
    double ask(List<Integer> indices, TurtleOperations<?> ops);

    /**
     * Performs the operation given by `ops` to all the turtles that satisfy the predicate
     * It does not alter the previous selection
     */
    double askWith(Predicate<TurtleModel> p, TurtleOperations<?> ops);
}
