package view;

import app.SLogoApp;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainView {
    public static final double split = 0.7f;
    private static final String ROOT_CSS_CLASS = "root";

    private GridPane root;
    private SplitPane center;
    private SplitPane right;

    MainView(
            SidebarView sidebarView,
            CanvasView canvasView,
            VariableView variableView,
            HistoryView historyView,
            CommandView commandView
    ) {
        setupRoot();
        assembleRight(variableView, historyView);
        assembleCenter(canvasView, commandView);

        root.add(sidebarView.view(), 0, 0);
        root.add(center, 1, 0);
        root.add(right, 2, 0);
    }

    private void setupRoot() {
        root = new GridPane();
        root.getColumnConstraints().add(new ColumnConstraints(SidebarView.SIDEBAR_VIEW_WIDTH));
        root.getColumnConstraints().add(new ColumnConstraints(CanvasView.TURTLE_VIEW_WIDTH));
        root.getStyleClass().add(ROOT_CSS_CLASS);
    }

    private void assembleCenter(CanvasView canvasView, CommandView commandView) {
        center = new SplitPane();
        center.setMinHeight(SLogoApp.APP_SCREEN_HEIGHT-SLogoApp.APP_TAB_HEIGHT);
        center.setMaxHeight(SLogoApp.APP_SCREEN_HEIGHT-SLogoApp.APP_TAB_HEIGHT);
        center.setMinWidth(SLogoApp.APP_SCREEN_WIDTH);
        center.setMaxWidth(SLogoApp.APP_SCREEN_WIDTH);
        center.setOrientation(Orientation.VERTICAL);
        center.setDividerPositions(split);

        center.getItems().addAll(canvasView.view(), commandView.view());
    }

    private void assembleRight(VariableView variableView, HistoryView historyView) {
        right = new SplitPane();
        right.setMinHeight(SLogoApp.APP_SCREEN_HEIGHT-SLogoApp.APP_TAB_HEIGHT);
        right.setMaxHeight(SLogoApp.APP_SCREEN_HEIGHT-SLogoApp.APP_TAB_HEIGHT);
        right.setMinWidth(HistoryView.HISTORY_VIEW_WIDTH);
        right.setMaxWidth(HistoryView.HISTORY_VIEW_WIDTH);
        right.setOrientation(Orientation.VERTICAL);
        right.setDividerPositions(0.5f);
        right.getStyleClass().add("split-pane");

        right.getItems().addAll(variableView.view(), historyView.view());
    }

    public GridPane view() { return root; }
}
