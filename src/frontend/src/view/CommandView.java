package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextFlow;
import view.utils.PrettyUI;

public class CommandView {
    private static final double COMMAND_WIDTH = CanvasView.TURTLE_VIEW_WIDTH;
    public static final String CARET = "<:";
    private static final String EMPTY = "";
    private static final int FONT_SIZE = 16;

    private ScrollPane root;
    private TextFlow display;

    private String model;

    CommandView() {
        display = new TextFlow();
        display.getStyleClass().add("command-view");
        display.setMinWidth(COMMAND_WIDTH);
        display.setMaxWidth(COMMAND_WIDTH);
        root = new ScrollPane();
        root.getStyleClass().add("command-view-bg");
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setContent(display);
        model = CARET;
        updateView();
    }

    public void clear() { this.model = CARET; updateView(); }
    public void insert(char newChar) {
        int caretPos = model.indexOf(CARET);
        model = model.replaceAll(CARET, EMPTY);
        model = new StringBuilder(model).insert(caretPos, newChar+CARET).toString();
        if(caretPos >= model.length()-CARET.length()-1) root.setVvalue(1);
        updateView();
    }
    public void delete() {
        int caretPos = model.indexOf(CARET);
        if(caretPos > 0) {
            model = model.replaceAll(CARET, EMPTY);
            model = new StringBuilder(model)
                    .deleteCharAt(caretPos - 1)
                    .insert(caretPos-1, CARET).toString();
        } updateView();
    }

    public void left() {
        int caretPos = model.indexOf(CARET);
        if(caretPos > 0) {
            model = model.replaceAll(CARET, EMPTY);
            model = new StringBuilder(model)
                    .insert(caretPos - 1, CARET).toString();
        } updateView();
    }

    public void right() {
        int caretPos = model.indexOf(CARET);
        if (caretPos < model.length() - CARET.length()) {
            model = model.replaceAll(CARET, EMPTY);
            model = new StringBuilder(model)
                    .insert(caretPos + 1, CARET).toString();
        } updateView();
    }

    public String model() { return model; }

    private void updateView() {
        display.getChildren().clear();
        display.getChildren().addAll(PrettyUI.highlight(model, FONT_SIZE));
    }

    public ScrollPane view() { return root; }
}
