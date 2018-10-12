package view.api;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelModule;
import view.EditorView;
import view.MainView;
import view.TurtleViewView;

import java.util.ResourceBundle;

public class ViewModule {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    private static final ResourceBundle APP_PROPERTIES = ResourceBundle.getBundle("slogo");
    private static final String STYLESHEET = "style.css";

    private MainView mainView;
    private EditorView editorView;
    private TurtleViewView turtleView;

    public ViewModule(ModelModule modelModule) {
        turtleView = new TurtleViewView(modelModule.turtleModel());
        editorView = new EditorView();

        mainView = new MainView(turtleView, editorView);
    }

    public void show(Stage primaryStage) {
        primaryStage.setTitle(APP_PROPERTIES.getString("Title"));
        var scene = new Scene(mainView, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public EditorViewAPI editorView() { return editorView; }
    public TurtleViewAPI turtleView() { return turtleView; }
}