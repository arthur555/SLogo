package fake_model;

public class TurtleModel {
    private int x;
    private int y;
    private double angle;
    private boolean visible;
    private boolean penDown;

    public TurtleModel(){
        x = 0;
        y = 0;
        angle = 0;
        visible = true;
        penDown = true;
    }

    boolean isPenDown(){return penDown;}
    boolean isVisible(){return visible;}
    void setPenDown(boolean down){penDown=down;}
    void setVisible(boolean visible){this.visible=visible;}

    int getX(){return x;}
    int getY(){return y;}
    double getAnlge(){return angle;}
    void setX(int x){this.x = x;}
    void setY(int y){this.y = y;}
    void setAngle(int angle){this.angle = angle;}
}
