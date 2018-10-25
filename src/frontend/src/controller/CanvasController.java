package controller;

import javafx.beans.property.DoubleProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.ModelModule;
import model.TurtleManager;
import model.TurtleModel;
import view.CanvasView;

public class CanvasController {
    private TurtleManager turtleManager;
    private CanvasView canvasView;

    public CanvasController(TurtleManager turtleManager, CanvasView canvasView) {
        this.turtleManager = turtleManager;
        this.canvasView = canvasView;
        setupTurtleManager();
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

    public void setPenColor(Color c) { turtleManager.selected().forEach(idx -> canvasView.setPenColor(idx, c)); }
    public void setTurtleImage(Image img) { turtleManager.selected().forEach(idx -> canvasView.setImage(idx, img));}
    public void bindDuration(DoubleProperty model) { canvasView.durationProperty().bind(model); }
    public void setBackgroundColor(Color c) { canvasView.setBackgroundColor(c); }
}
