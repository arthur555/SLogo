package model;

import java.util.ArrayList;
import java.util.List;

public class PosAndAngle {
    public static final double X_BUF = 375;
    public static final double Y_BUF = (X_BUF+25)*0.7-25;

    private double x, y, angle;
    private List<PosAndAngleListener> listeners;

    public PosAndAngle(double x, double y, double angle) {
        this.x = x+X_BUF;
        this.y = y+Y_BUF;
        this.angle = angle;
        listeners = new ArrayList<>();
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
        fireChange();
    }
    public void setAngle(double angle) {
        this.angle = angle;
        fireChange();
    }

    public double x() { return x; }
    public double y() { return y; }
    public double angle() { return angle; }

    public void registerListener(PosAndAngleListener pasL) { listeners.add(pasL); }
    public void fireChange() { listeners.forEach(c -> c.changed(this));}
}
