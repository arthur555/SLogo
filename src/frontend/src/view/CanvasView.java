package view;

import app.SLogoApp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.TurtleManager;
import view.utils.BackgroundUtils;

public class CanvasView {
    public static final int TURTLE_VIEW_WIDTH = SLogoApp.APP_SCREEN_HEIGHT;
    private Pane root;
    private SimpleDoubleProperty duration;
    public CanvasView(TurtleManager turtleManager) {
        root = new Pane();
        root.getStyleClass().add("canvas");
        duration = new SimpleDoubleProperty(100);
    }

    public DoubleProperty durationModel() { return duration; }

    public void setBackgroundColor(Color c) {
        root.setBackground(BackgroundUtils.coloredBackground(c));
    }

    public Pane view() { return root; }
}
