package model.impl;
import javafx.beans.property.SimpleBooleanProperty;
import model.ClearListener;
import model.PosAndAngle;
import model.TurtleModel;

import java.util.ArrayList;
import java.util.List;

public class TurtleModelImpl implements TurtleModel {
    private SimpleBooleanProperty move;
    private SimpleBooleanProperty visible;
    private SimpleBooleanProperty penDown;
    private PosAndAngle pa;
    private List<ClearListener> listeners;

    public TurtleModelImpl(){
        listeners = new ArrayList<>();
        pa = new PosAndAngle(0, 0, 0);
        visible = new SimpleBooleanProperty(true);
        penDown = new SimpleBooleanProperty(true);
        move = new SimpleBooleanProperty(false);
    }

    public double setPenDown(boolean down){
        penDown.setValue(down);
        return down ? TRUE : FALSE;
    }
    public double setVisible(boolean visible){
        this.visible.setValue(visible);
        return visible ? TRUE : FALSE;
    }

    public double moveTo(double x, double y){
        var dx = x - pa.x();
        var dy = y - pa.y();
        double dist = Math.sqrt(dx*dx + dy*dy);
        pa.setXY(x, y);
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
    public boolean isMove(){ return move.getValue(); }
    public SimpleBooleanProperty isPenDownModel(){ return penDown; }
    public SimpleBooleanProperty isVisibleModel(){ return visible; }
    public SimpleBooleanProperty isMoveModel(){ return move; }
    public PosAndAngle posAndAngleModel() { return pa;}

    public void registerClearListener(ClearListener cl) { listeners.add(cl); }
    public void clear() { listeners.forEach(ClearListener::clear); }
}
