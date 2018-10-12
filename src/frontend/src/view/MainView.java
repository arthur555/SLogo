package view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class MainView extends GridPane {
    private static final String ROOT_CSS_CLASS = "root";

    public MainView(TurtleView turtleView, EditorView editorView, SidebarView sidebarView) {
        getStyleClass().add(ROOT_CSS_CLASS);
        getColumnConstraints().add(new ColumnConstraints(SidebarView.SIDEBAR_VIEW_WIDTH));
        getColumnConstraints().add(new ColumnConstraints(TurtleView.TURTLE_VIEW_WIDTH));

        add(sidebarView.view(), 0, 0);
        add(turtleView.view(), 1, 0);
        add(editorView.view(), 2, 0);
    }
}
