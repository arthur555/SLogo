package model;

import engine.errors.InterpretationException;

public interface TurtleOperations<T> {
    T op(TurtleModel turtleModel) throws InterpretationException;
}
