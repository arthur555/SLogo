package view;

import engine.compiler.storage.StateMachine;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import view.utils.PrettyUI;
import engine.compiler.storage.StateMachineObserver;

public class VariableView implements StateMachineObserver {
    private static final int VARIABLE_VIEW_WIDTH = HistoryView.HISTORY_VIEW_WIDTH - 20;
    private static final int KEY_VALUE_MARGIN = 150;
    private static final int VALUE_WIDTH = 150;
    private static final int MAX_LENGTH = 15;
    private static final String DOTDOTDOT = "...";

    private ScrollPane root;
    private VBox variableView;
    private StateMachine stateMachine;

    VariableView(StateMachine stateMachine) {
        variableView = new VBox();
        variableView.setPrefWidth(VARIABLE_VIEW_WIDTH);
        variableView.getStyleClass().add("variable-view");

        this.stateMachine = stateMachine;
        this.stateMachine.register(this);

        root = new ScrollPane();
        root.getStyleClass().add("variable-view-bg");
        root.setContent(variableView);
    }

    public ScrollPane view() { return root; }

    @Override
    public void notifyListener() {
        variableView.getChildren().clear(); // we can optimize this if we need to
        stateMachine.listOfVariables().forEach( (k, v) ->
                variableView.getChildren().add(keyValueText(variableView.getChildren().size(), k, v))
        );
    }

    private GridPane keyValueText(int idx, String key, Object value) {
        var grid = new GridPane();
        grid.setMaxWidth(KEY_VALUE_MARGIN+VALUE_WIDTH);
        var constraint = new ColumnConstraints(KEY_VALUE_MARGIN);
        grid.getColumnConstraints().add(constraint);
        var keyPane = new StackPane(new Text(key));
        keyPane.setMaxWidth(KEY_VALUE_MARGIN);
        keyPane.setMinWidth(KEY_VALUE_MARGIN);
        keyPane.setAlignment(Pos.CENTER_LEFT);
        PrettyUI.alternateBgTheme(idx, keyPane);
        var valuePane = new StackPane(new Text(trim(value.toString())));
        valuePane.setMaxWidth(VALUE_WIDTH);
        valuePane.setMinWidth(VALUE_WIDTH);
        PrettyUI.alternateBgTheme(idx+1, valuePane);
        valuePane.setAlignment(Pos.CENTER_LEFT);
        grid.add(keyPane, 0, 0);
        grid.add(valuePane, 1, 0);
        return grid;
    }


    private String trim(String in) {
        if(in.length() > MAX_LENGTH) return in.substring(0, MAX_LENGTH) + DOTDOTDOT;
        else return in;
    }

}
