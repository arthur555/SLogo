package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelModule;
import view.canvas.CanvasView;
import view.editor.EditorView;

import java.util.ResourceBundle;

public class ViewModule {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    private static final ResourceBundle APP_PROPERTIES = ResourceBundle.getBundle("slogo");
    private static final String STYLESHEET = "style.css";

    private MainView mainView;
    private EditorView editorView;
    private CanvasView canvasView;

    public ViewModule(ModelModule modelModule) {
        canvasView = new CanvasView(modelModule.turtleModel());
        editorView = new EditorView();

        mainView = new MainView(canvasView, editorView);
    }

    public void show(Stage primaryStage) {
        primaryStage.setTitle(APP_PROPERTIES.getString("Title"));
        var scene = new Scene(mainView, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public EditorView editorView() { return editorView; }
    public CanvasView canvasView() { return canvasView; }
}
