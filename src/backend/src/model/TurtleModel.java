package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;

public interface TurtleModel {
    void setPenDown(boolean down);
    void setVisible(boolean visible);
    void move(boolean move);
    void setX(double x);
    void setY(double y);
    void setAngle(double angle);
    double getX();
    double getY();
    double getAngle();
    SimpleBooleanProperty isPenDown();
    SimpleBooleanProperty isVisible();
    SimpleBooleanProperty isMove();
    ObservableList<Double> getPoints ();
}
