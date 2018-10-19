package view.reference;

import javafx.scene.control.Dialog;
import javafx.stage.Modality;

public class ReferenceDialog extends Dialog<Void> {
    private static final String TITLE = "Reference Sheet";
    private static final String STYLESHEET = "style.css";

    public ReferenceDialog() {
        setTitle(TITLE);
        var pane = new ReferenceDialogPane();
        setDialogPane(pane);
        pane.getStylesheets().add(STYLESHEET);
        initModality(Modality.WINDOW_MODAL);
    }
}

