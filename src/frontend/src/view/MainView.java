package view;

import view.api.ViewModule;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class MainView extends GridPane {
    private static final String ROOT_CSS_CLASS = "root";

    public MainView(TurtleViewView turtleView, EditorView editorView) {
        getStyleClass().add(ROOT_CSS_CLASS);
        getColumnConstraints().add(new ColumnConstraints(ViewModule.SCREEN_HEIGHT));

        add(turtleView.getView(), 0, 0);
        add(editorView.getView(), 1, 0);
    }
}
