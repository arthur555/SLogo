package model;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TurtleModelImpl implements TurtleModel {
    private Double x;
    private Double y;
    private Double angle;
    private SimpleBooleanProperty move;
    private SimpleBooleanProperty visible;
    private SimpleBooleanProperty penDown;
    private ObservableList<Double> points;

    public TurtleModelImpl(){
        x = 0.0;
        y = 0.0;
        points = FXCollections.observableArrayList();
        points.add(x);
        points.add(y);
        angle = 0.0;
        points.add(angle);
        visible = new SimpleBooleanProperty(true);
        penDown = new SimpleBooleanProperty(true);
        move = new SimpleBooleanProperty(false);
    }



    public void setPenDown(boolean down){penDown.setValue(down);}
    public void setVisible(boolean visible){this.visible.setValue(visible);}
    public void move(boolean move){this.move.set(move);}
    public void setX(double x){
        this.x=x;
        points.set(0,x);
    }
    public void setY(double y){
        this.y=y;
        points.set(1,y);
    }
    public void setAngle(double angle){this.angle = angle;}

    public double getX(){return x;}
    public double getY(){return y;}
    public double getAngle(){return angle;}
    public SimpleBooleanProperty isPenDown(){return penDown;}
    public SimpleBooleanProperty isVisible(){return visible;}
    public SimpleBooleanProperty isMove(){return move;}



    public ObservableList<Double> getPoints (){ return points;}
}
