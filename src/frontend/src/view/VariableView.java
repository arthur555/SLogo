package view;

import javafx.collections.MapChangeListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariableView implements MapChangeListener<String, Object> {
    private static final int VARIABLE_VIEW_WIDTH = HistoryView.HISTORY_VIEW_WIDTH;
    private static final int KEY_VALUE_MARGIN = 150;

    private ScrollPane root;
    private VBox variableView;

    VariableView() {
        variableView = new VBox();
        variableView.setPrefWidth(VARIABLE_VIEW_WIDTH);
        variableView.getStyleClass().add("variable-view");

        for(int i = 0 ; i < 100 ; i ++) {
            variableView.getChildren().add(keyValueText("test"+i, i));
        }

        root = new ScrollPane();
        root.setContent(variableView);
    }

    public ScrollPane view() { return root; }

    @Override
    public void onChanged(Change<? extends String, ? extends Object> change) {
        variableView.getChildren().clear(); // we can optimize this if we need to
        for(var e : change.getMap().entrySet()) {
            variableView.getChildren().add(keyValueText(e.getKey(), e.getValue()));
        }
    }

    private GridPane keyValueText(String key, Object value) {
        var grid = new GridPane();
        var constraint = new ColumnConstraints(KEY_VALUE_MARGIN);
        constraint.setFillWidth(true);
        grid.getColumnConstraints().add(constraint);
        grid.add(new Text(key), 0, 0);
        grid.add(new Text(value.toString()), 1, 0);
        return grid;
    }
}
