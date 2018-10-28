package view;

import app.SLogoApp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.TurtleModel;
import view.utils.BackgroundUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Manages multiple Turtle Views, listening to how their view list changes
 */
public class CanvasView {
    public static final int TURTLE_VIEW_WIDTH = SLogoApp.APP_SCREEN_HEIGHT;

    private Pane root;
    private SimpleDoubleProperty duration;
    private Map<Integer, TurtleView> turtleViews;
    private Rectangle selection;

    public CanvasView() {
        root = new Pane();
        setBackgroundColor(Color.WHITE);
        duration = new SimpleDoubleProperty();
        turtleViews = new HashMap<>();
        selection = new Rectangle(0, 0, 0, 0);
        selection.setOpacity(0.1);
        selection.setFill(Color.valueOf("#00ff48"));
        selection.setStroke(Color.BLACK);
        selection.setStrokeWidth(5.0);
        root.getChildren().add(selection);
    }

    public void addTurtle(int id, TurtleModel model) {
        var newView = new TurtleView(model, duration);
        turtleViews.put(id, newView);
        root.getChildren().addAll(newView.views());
    }

    public List<Integer> turtlesInSelection() {
        var indices = new ArrayList<Integer>();
        for(var e : turtleViews.entrySet()) {
            if(e.getValue().turtle().intersects(selection.getBoundsInParent())) {
                indices.add(e.getKey());
            }
        } return indices;
    }

    public void highlightSelected(List<Integer> selected) {
        for (var idx : turtleViews.keySet()) {
            turtleViews.get(idx).turtle().setOpacity(selected.contains(idx) ? 1 : 0.5);
        }
    }

    public Rectangle selection() { return selection; }
    public DoubleProperty durationProperty() { return duration; }
    public void setImage(int idx, Image img) { turtleViews.get(idx).setTurtleImage(img); }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setPenColor(int idx, Color c) { turtleViews.get(idx).setPenColor(c); }
    public Pane view() { return root; }
}
