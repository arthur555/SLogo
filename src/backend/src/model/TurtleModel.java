package model;

import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import javafx.beans.property.SimpleBooleanProperty;

public interface TurtleModel {
    double TRUE = 1;
    double FALSE = 0;

    double setPenDown(boolean down);
    double setVisible(boolean visible);
    double forward(double by);
    double moveTo(double x, double y, boolean forcePenUp);
    double setAngle(double angle);
    double getX();
    double getY();
    double getAngle();

    boolean isPenDown();
    boolean isVisible();
    SimpleBooleanProperty isPenDownModel();
    SimpleBooleanProperty isVisibleModel();
    PosAndAngle posAndAngleModel();

    /**
     * I honestly feel like this shouldn't be here ... but it's easy to do
     * @blame inchan hwang
     */
    int setBackground(int index) throws InterpretationException;
    int setPenColor(int index) throws InterpretationException;
    int setPenSize(int pixels);
    int setShape(int index) throws InterpretationException;

    StateMachine memory();
    void registerClearListener(ClearListener cl);
    void registerUIListener(UIListener ul);
    double clear();
}
