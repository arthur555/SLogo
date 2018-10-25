package model;

import javafx.beans.property.SimpleBooleanProperty;


public interface TurtleModel {
    double TRUE = 1;
    double FALSE = 0;

    double setPenDown(boolean down);
    double setVisible(boolean visible);
    double moveTo(double x, double y);
    double setAngle(double angle);
    double getX();
    double getY();
    double getAngle();

    boolean isPenDown();
    boolean isVisible();
    boolean isMove();
    SimpleBooleanProperty isPenDownModel();
    SimpleBooleanProperty isVisibleModel();
    SimpleBooleanProperty isMoveModel();
    PosAndAngle posAndAngleModel();

    void registerClearListener(ClearListener cl);
    void clear();
}
