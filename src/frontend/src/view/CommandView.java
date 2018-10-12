package view;

import controller.EditorController;
import javafx.scene.control.TextArea;

public class CommandView {
    private TextArea root;

    CommandView() {
        root = new TextArea();
        root.getStyleClass().add("command-line");
        root.setWrapText(true);
        root.setText(EditorController.COMMAND_PREFIX);
        root.positionCaret(EditorController.COMMAND_PREFIX.length());
    }

    public TextArea view() { return root; }
}
