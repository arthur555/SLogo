package model.impl;
import engine.compiler.storage.StateMachine;
import javafx.beans.property.SimpleBooleanProperty;
import model.ClearListener;
import model.PosAndAngle;
import model.TurtleModel;

import java.util.ArrayList;
import java.util.List;

public class TurtleModelImpl implements TurtleModel {
    private SimpleBooleanProperty visible;
    private SimpleBooleanProperty penDown;
    private PosAndAngle pa;
    private List<ClearListener> listeners;
    private StateMachine memory;

    public TurtleModelImpl(StateMachine memory){
        listeners = new ArrayList<>();
        pa = new PosAndAngle(0, 0, 0);
        visible = new SimpleBooleanProperty(true);
        penDown = new SimpleBooleanProperty(true);
        this.memory = memory;
    }

    public double setPenDown(boolean down){
        penDown.setValue(down);
        return down ? TRUE : FALSE;
    }
    public double setVisible(boolean visible){
        this.visible.setValue(visible);
        return visible ? TRUE : FALSE;
    }

    @Override
    public double forward(double by) {
        double stepX = by*Math.cos(Math.toRadians(pa.angle()));
        double stepY = by*Math.sin(Math.toRadians(pa.angle()));
        return moveTo(pa.x()+stepX, pa.y()+stepY, false);
    }

    public double moveTo(double x, double y, boolean forcePenUp){
        var dx = x - pa.x();
        var dy = y - pa.y();
        double dist = Math.sqrt(dx*dx + dy*dy);
        var prevPen = penDown;
        if(forcePenUp) setPenDown(false);
        pa.setXY(x, y);
        setPenDown(prevPen.getValue());
        return dist;
    }

    public double setAngle(double angle){
        var dAngle = (angle - pa.angle()+360)%360;
        pa.setAngle(angle);
        return dAngle;
    }

    public double getX(){return pa.x();}
    public double getY(){return pa.y();}
    public double getAngle(){return pa.angle();}
    public boolean isPenDown(){ return penDown.getValue(); }
    public boolean isVisible(){ return visible.getValue(); }
    public SimpleBooleanProperty isPenDownModel(){ return penDown; }
    public SimpleBooleanProperty isVisibleModel(){ return visible; }
    public PosAndAngle posAndAngleModel() { return pa;}

    public StateMachine memory() { return memory; }

    public void registerClearListener(ClearListener cl) { listeners.add(cl); }
    public double clear() { listeners.forEach(ClearListener::clear); return 0; }
}
