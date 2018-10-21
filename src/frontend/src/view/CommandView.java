package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import view.utils.Language;
import view.utils.PrettyUI;

import java.util.Set;

public class CommandView {
    private static final double COMMAND_WIDTH = TurtleView.TURTLE_VIEW_WIDTH;
    public static final String CARET = "<:";
    private static final String EMPTY = "";

    private static final String CONSTANT = "-?[0-9]+\\.?[0-9]*";
    private static final String VARIABLE = ":[a-zA-Z_]+";

    private ScrollPane root;
    private TextFlow display;

    private String model;
    private String lang;
    private Set<String> keywords, commands;

    CommandView() {
        display = new TextFlow();
        display.getStyleClass().add("command-view");
        display.setMinWidth(COMMAND_WIDTH);
        display.setMaxWidth(COMMAND_WIDTH);
        root = new ScrollPane();
        root.getStyleClass().add("command-view-bg");
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setPrefWidth(COMMAND_WIDTH-100);
        root.setContent(display);
        keywords = Language.keywords();
        commands = Language.nonKeywords();
        model = CARET;
        updateView();
    }

    public void setLang(String lang) { this.lang = lang; }
    public void clear() { this.model = CARET; updateView(); }
    public void insert(char newChar) {
        int caretPos = model.indexOf(CARET);
        model = model.replaceAll(CARET, EMPTY);
        model = new StringBuilder(model).insert(caretPos, newChar+CARET).toString();
        if(caretPos >= model.length()) root.setVvalue(1);
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
        for(var line : model.split("\\n+")) {
            if(line.startsWith("#")) {
                display.getChildren().add(PrettyUI.comment(line));
            } else {
                for(var token : line.split("\\s+")) {
                    var test = token.replace(CARET, EMPTY);
                    if(keywords.contains(test)) display.getChildren().add(PrettyUI.keyword(token));
                    else if(commands.contains(test)) display.getChildren().add(PrettyUI.command(token));
                    else if(test.matches(CONSTANT)) display.getChildren().add(PrettyUI.number(token));
                    else if(test.matches(VARIABLE)) display.getChildren().add(PrettyUI.variable(token));
                    else display.getChildren().add(PrettyUI.plain(token));
                } display.getChildren().add(new Text("\n"));
            }
        }
    }

    public ScrollPane view() { return root; }
}
