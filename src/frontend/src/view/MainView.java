package view;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class MainView {
    private static final String ROOT_CSS_CLASS = "root";

    private GridPane root;
    private SplitPane center;
    private SplitPane right;

    public MainView(
            SidebarView sidebarView,
            TurtleView turtleView,
            VariableView variableView,
            HistoryView historyView,
            CommandView commandView
    ) {
        setupRoot();
        assembleRight(variableView, historyView);
        assembleCenter(turtleView, commandView);

        root.add(sidebarView.view(), 0, 0);
        root.add(center, 1, 0);
        root.add(right, 2, 0);
    }

    private void setupRoot() {
        root = new GridPane();
        root.getColumnConstraints().add(new ColumnConstraints(SidebarView.SIDEBAR_VIEW_WIDTH));
        root.getColumnConstraints().add(new ColumnConstraints(TurtleView.TURTLE_VIEW_WIDTH));
        root.getStyleClass().add(ROOT_CSS_CLASS);
    }

    private void assembleCenter(TurtleView turtleView, CommandView commandView) {
        center = new SplitPane();
        center.setMinHeight(ViewModule.SCREEN_HEIGHT);
        center.setMaxHeight(ViewModule.SCREEN_HEIGHT);
        center.setMinWidth(ViewModule.SCREEN_WIDTH);
        center.setMaxWidth(ViewModule.SCREEN_WIDTH);
        center.setOrientation(Orientation.VERTICAL);
        center.setDividerPositions(0.7f);

        center.getItems().addAll(turtleView.view(), commandView.view());
    }

    private void assembleRight(VariableView variableView, HistoryView historyView) {
        right = new SplitPane();
        right.setMinHeight(ViewModule.SCREEN_HEIGHT);
        right.setMaxHeight(ViewModule.SCREEN_HEIGHT);
        right.setMinWidth(ViewModule.SCREEN_WIDTH-ViewModule.SCREEN_HEIGHT);
        right.setMaxWidth(ViewModule.SCREEN_WIDTH-ViewModule.SCREEN_HEIGHT);
        right.setOrientation(Orientation.VERTICAL);
        right.setDividerPositions(0.5f);

        right.getItems().addAll(variableView.view(), historyView.view());
    }

    public Parent view() { return root; }
}
