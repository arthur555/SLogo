package view.editor;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

public class EditorView extends VBox {
    private static final String DEFAULT_LANGUAGE = "languages/English";

    private ResourceBundle langPack;

    public EditorView() {
        super(10);
        langPack = ResourceBundle.getBundle(DEFAULT_LANGUAGE);
        getChildren().add(new Text("Editor"));
    }
}
