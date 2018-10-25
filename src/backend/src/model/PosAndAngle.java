package model;

import javafx.beans.value.ObservableValueBase;

public class PosAndAngle extends ObservableValueBase<PosAndAngle> {
    private double x, y, angle;
    public PosAndAngle(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
        fireValueChangedEvent();
    }
    public void setAngle(double angle) {
        this.angle = angle; fireValueChangedEvent();
    }

    public double x() { return x; }
    public double y() { return y; }
    public double angle() { return angle; }

    @Override
    public PosAndAngle getValue() { return this; }
}
