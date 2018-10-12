package view.editor;

import main.java.controller.editor.EditorController;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import view.ViewModule;

public class EditorView extends SplitPane {
    private static final String DEFAULT_LANGUAGE = "languages/English";

    private EditorController editorController;

    private TextArea commandHistory;
    private TextArea currentCommand;

    public EditorView() {
        super();

        setMinHeight(ViewModule.SCREEN_HEIGHT);
        setMaxHeight(ViewModule.SCREEN_HEIGHT);
        setMinWidth(ViewModule.SCREEN_WIDTH-ViewModule.SCREEN_HEIGHT);
        setMaxWidth(ViewModule.SCREEN_WIDTH-ViewModule.SCREEN_HEIGHT);
        setOrientation(Orientation.VERTICAL);
        setDividerPositions(0.8f);

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

        getItems().addAll(commandHistory, currentCommand);
    }

    public TextArea commandHistory() { return commandHistory; }
    public TextArea currentCommand() { return currentCommand; }
    public void registerController(EditorController controller) { editorController = controller; }
}
