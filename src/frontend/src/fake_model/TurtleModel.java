package fake_model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Observer;

public class TurtleModel{
    private Integer x;
    private Integer y;
    private SimpleDoubleProperty angle;
    private SimpleBooleanProperty visible;
    private SimpleBooleanProperty penDown;
    private ObservableList<Integer> points;


    public TurtleModel(){
        x = 0;
        y = 0;
        points = FXCollections.observableArrayList();
        points.add(x);
        points.add(y);
        angle = new SimpleDoubleProperty(0);
        visible = new SimpleBooleanProperty(true);
        penDown = new SimpleBooleanProperty(true);

    }


    public SimpleBooleanProperty isPenDown(){return penDown;}
    public SimpleBooleanProperty isVisible(){return visible;}
    public void setPenDown(boolean down){penDown.setValue(down);}
    public void setVisible(boolean visible){this.visible.setValue(visible);}

    public int getX(){return x;}
    public int getY(){return y;}
    public SimpleDoubleProperty getAnlge(){return angle;}

    public void setX(int x){this.x=x;
    points.set(0,x);
    points.set(1,y);}
    public void setY(int y){this.y=y;
    points.set(1,y);}
    public void setAngle(int angle){this.angle.setValue(angle);}

    public ObservableList<Integer> getPoints (){ return points;}
}
