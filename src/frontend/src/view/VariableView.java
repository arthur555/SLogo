package view;

import javafx.collections.MapChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.utils.PrettyUI;

public class VariableView implements MapChangeListener<String, Object> {
    private static final int VARIABLE_VIEW_WIDTH = HistoryView.HISTORY_VIEW_WIDTH - 20;
    private static final int KEY_VALUE_MARGIN = 150;
    private static final int VALUE_WIDTH = 150;

    private ScrollPane root;
    private VBox variableView;

    VariableView() {
        variableView = new VBox();
        variableView.setPrefWidth(VARIABLE_VIEW_WIDTH);
        variableView.getStyleClass().add("variable-view");

        for(int i = 0 ; i < 100 ; i ++) {
            variableView.getChildren().add(keyValueText(variableView.getChildren().size(), "test"+i, i));
        }

        root = new ScrollPane();
        root.setContent(variableView);
    }

    public ScrollPane view() { return root; }

    @Override
    public void onChanged(Change<? extends String, ? extends Object> change) {
        variableView.getChildren().clear(); // we can optimize this if we need to
        for(var e : change.getMap().entrySet()) {
            variableView.getChildren().add(
                    keyValueText(variableView.getChildren().size(), e.getKey(), e.getValue())
            );
        }
    }

    private GridPane keyValueText(int idx, String key, Object value) {
        var grid = new GridPane();
        var constraint = new ColumnConstraints(KEY_VALUE_MARGIN);
        grid.getColumnConstraints().add(constraint);
        var keyPane = new StackPane(new Text(key));
        keyPane.setPrefWidth(KEY_VALUE_MARGIN);
        keyPane.setAlignment(Pos.CENTER_LEFT);
        PrettyUI.alternatePurple(idx, keyPane);
        var valuePane = new StackPane(new Text(value.toString()));
        valuePane.setPrefWidth(VALUE_WIDTH);
        PrettyUI.alternatePurple(idx+1, valuePane);
        valuePane.setAlignment(Pos.CENTER_LEFT);
        grid.add(keyPane, 0, 0);
        grid.add(valuePane, 1, 0);
        return grid;
    }
}
