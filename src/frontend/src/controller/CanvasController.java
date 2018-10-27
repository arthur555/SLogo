package controller;

import javafx.beans.property.DoubleProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.ModelModule;
import model.TurtleManager;
import model.TurtleModel;
import view.CanvasView;

/**
 *  Interfaces between the Turtle Manager and Canvas View
 */
public class CanvasController {
    private static final double VIEWPORT_INITIAL_X = -100;
    private static final double VIEWPORT_INITIAL_Y = -100;
    private static final double VIEWPORT_INITIAL_W = 200;
    private static final double VIEWPORT_INITIAL_H = 200;

    private TurtleManager turtleManager;
    private CanvasView canvasView;

    private Rectangle viewport;

    public CanvasController(TurtleManager turtleManager, CanvasView canvasView) {
        this.turtleManager = turtleManager;
        this.canvasView = canvasView;
        setupTurtleManager();
        setupViewport();
    }

    private void setupTurtleManager() {
        canvasView.addTurtle(
                ModelModule.INITIAL_TURTLE_ID,
                turtleManager.turtleModels().get(ModelModule.INITIAL_TURTLE_ID)
        );
        turtleManager.turtleModels().addListener((MapChangeListener<Integer, TurtleModel>) c -> {
            if(c.wasAdded()) canvasView.addTurtle(c.getKey(), c.getValueAdded());
        });
    }

    private void setupViewport() {
        viewport = new Rectangle(
                VIEWPORT_INITIAL_X,
                VIEWPORT_INITIAL_Y,
                VIEWPORT_INITIAL_W,
                VIEWPORT_INITIAL_H
        );
        canvasView.applyViewport(viewport);
    }

    public void setPenColor(Color c) { turtleManager.selected().forEach(idx -> canvasView.setPenColor(idx, c)); }
    public void setTurtleImage(Image img) { turtleManager.selected().forEach(idx -> canvasView.setImage(idx, img));}
    public void bindDuration(DoubleProperty model) { canvasView.durationProperty().bind(model); }
    public void setBackgroundColor(Color c) { canvasView.setBackgroundColor(c); }
}
