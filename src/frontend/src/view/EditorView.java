package view;

import controller.EditorController;
import engine.errors.ParsingError;
import engine.errors.RuntimeError;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;

public class EditorView {
    private SplitPane root;

    private TextArea commandHistory;
    private TextArea currentCommand;

    public EditorView() {
        root = new SplitPane();

        root.setMinHeight(ViewModule.SCREEN_HEIGHT);
        root.setMaxHeight(ViewModule.SCREEN_HEIGHT);
        root.setMinWidth(ViewModule.SCREEN_WIDTH-ViewModule.SCREEN_HEIGHT);
        root.setMaxWidth(ViewModule.SCREEN_WIDTH-ViewModule.SCREEN_HEIGHT);
        root.setOrientation(Orientation.VERTICAL);
        root.setDividerPositions(0.8f);

        commandLineSetup();
    }

    private void commandLineSetup() {
        commandHistory = new TextArea();
        commandHistory.getStyleClass().add("command-line");
        commandHistory.setEditable(false);
        commandHistory.setWrapText(true);

        currentCommand = new TextArea();
        currentCommand.getStyleClass().add("command-line");
        currentCommand.setWrapText(true);
        currentCommand.setText(EditorController.COMMAND_PREFIX);
        currentCommand.positionCaret(EditorController.COMMAND_PREFIX.length());

        root.getItems().addAll(commandHistory, currentCommand);
    }

    public Node view() { return root; }
    public TextArea commandHistory() { return commandHistory; }
    public TextArea currentCommand() { return currentCommand; }

    public void displayError(ParsingError e) { }
    public void displayError(RuntimeError e) { }
    public void setLanguage(String lang) { }
}
