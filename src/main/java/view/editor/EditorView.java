package view.editor;

import controller.editor.EditorController;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class EditorView extends GridPane {
    private static final String DEFAULT_LANGUAGE = "languages/English";

    private EditorController editorController;

    public EditorView() { this(DEFAULT_LANGUAGE); }

    public EditorView(String language) {
        super();
        getChildren().add(new Text("Editor"));
    }

    public void registerController(EditorController controller) {
        editorController = controller;
    }
}
