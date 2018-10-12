package view;

import engine.errors.ParsingError;
import engine.errors.RuntimeError;
import javafx.scene.control.TextArea;

public class HistoryView {
    private TextArea root;

    public HistoryView() {
        root = new TextArea();
        root.getStyleClass().add("command-line");
        root.setEditable(false);
        root.setWrapText(true);
    }

    public TextArea view() { return root; }

    public void displayError(ParsingError e) { }
    public void displayError(RuntimeError e) { }
    public void setLanguage(String lang) { }
}
