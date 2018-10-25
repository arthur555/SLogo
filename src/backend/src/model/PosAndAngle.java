package model;

import java.util.ArrayList;
import java.util.List;

public class PosAndAngle {
    private double x, y, angle;
    private List<PosAndAngleListener> listeners;

    public PosAndAngle(double x, double y, double angle) {
        this.x = x;
        this.y = y;
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
