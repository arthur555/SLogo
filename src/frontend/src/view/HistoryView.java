package view;

import engine.errors.CommandSyntaxException;
import engine.errors.RuntimeError;
import javafx.scene.control.TextArea;

public class HistoryView {
    public static final int HISTORY_VIEW_WIDTH =
            MainView.SCREEN_WIDTH - SidebarView.SIDEBAR_VIEW_WIDTH - TurtleView.TURTLE_VIEW_WIDTH;
    private TextArea root;

    HistoryView() {
        root = new TextArea();
        root.getStyleClass().add("command-line");
        root.setEditable(false);
        root.setWrapText(true);
    }

    public TextArea view() { return root; }

    public void displayError(CommandSyntaxException e) { }
    public void displayError(RuntimeError e) { }
    public void setLanguage(String lang) { }
}
