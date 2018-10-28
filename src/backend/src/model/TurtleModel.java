package model;

import engine.compiler.storage.StateMachine;
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

    StateMachine memory();
    void registerClearListener(ClearListener cl);
    double clear();
}
