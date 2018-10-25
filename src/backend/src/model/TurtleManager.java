package model;

import engine.compiler.storage.StateMachine;
import javafx.collections.ObservableMap;

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
     * Adds a turtle with the given ID
     * @return the ID of the turtle that we just added
     */
    int addTurtle(int id);

    /**
     * Returns ObservableMap of (ID, TurtleModel)
     */
    ObservableMap<Integer, TurtleModel> turtleModels();

    /**
     *  Runs ops on every selected turtles
     */
    <T> T foreach(TurtleOperations<T> ops);

    /**
     * Selects all the turtles with given IDs
     * All operations will operate only to these selected turtles
     * @return id()
     */
    int tell(List<Integer> turtleIDs);

    /**
     * Performs the operation given by `ops` to all the turtles with the given IDs
     * It does not alter the previous selection
     * @return result of last command or 0 if there weren't any operations
     */
    <T> T ask(List<Integer> indices, TurtleOperations<T> ops);

    /**
     * Performs the operation given by `ops` to all the turtles that satisfy the predicate
     * It does not alter the previous selection
     * @return result of last command or 0 if there weren't any operations
     */
    <T> T askWith(Predicate<TurtleModel> p, TurtleOperations<T> ops);

    /**
     * @return StateMachine
     */
    StateMachine memory();
}
