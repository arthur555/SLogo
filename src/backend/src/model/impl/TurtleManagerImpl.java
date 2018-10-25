package model.impl;

import engine.compiler.storage.CrudeStateMachine;
import engine.compiler.storage.StateMachine;
import model.TurtleManager;
import model.TurtleModel;
import model.TurtleOperations;

import java.util.List;
import java.util.function.Predicate;

public class TurtleManagerImpl extends TurtleModelImpl implements TurtleManager {
    private StateMachine memory;

    public TurtleManagerImpl() {
        memory = new CrudeStateMachine();
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public double tell(List<Integer> turtleIDs) {
        return 0;
    }

    @Override
    public double ask(List<Integer> indices, TurtleOperations<?> ops) {
        return 0;
    }

    @Override
    public double askWith(Predicate<TurtleModel> p, TurtleOperations<?> ops) {
        return 0;
    }
}
