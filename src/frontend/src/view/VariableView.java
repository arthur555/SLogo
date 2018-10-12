package view;

import javafx.collections.MapChangeListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VariableView<T> implements MapChangeListener<String, T> {
    private ScrollPane root;
    private HBox variableView;

    public VariableView() {
        root = new ScrollPane();
        variableView = new HBox();
    }

    public ScrollPane view() { return root; }

    @Override
    public void onChanged(Change<? extends String, ? extends T> change) {
        variableView.getChildren().clear(); // we can optimize this if we need to
        for(var e : change.getMap().entrySet()) {
            variableView.getChildren().add(keyValueText(e.getKey(), e.getValue()));
        }
    }

    private GridPane keyValueText(String key, T value) {
        var grid = new GridPane();
        var constraint = new ColumnConstraints();
        constraint.setPercentWidth(0.3);
        grid.getColumnConstraints().add(constraint);
        grid.add(new Text(key), 0, 0);
        grid.add(new Text(value.toString()), 1, 0);
        return grid;
    }
}
