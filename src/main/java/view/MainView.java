package view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import view.canvas.CanvasView;
import view.editor.EditorView;

public class MainView extends GridPane {
    public static final int DEFAULT_EDITOR_WIDTH = 400;
    public static final String ROOT_CSS_CLASS = "root";

    public MainView(CanvasView canvas, EditorView editor) {
        getStyleClass().add(ROOT_CSS_CLASS);
        getColumnConstraints().add(new ColumnConstraints(DEFAULT_EDITOR_WIDTH));

        add(canvas, 0, 0);
        add(editor, 1, 0);
    }
}
