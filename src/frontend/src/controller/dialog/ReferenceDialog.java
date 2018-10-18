package controller.dialog;

import javafx.scene.control.Dialog;

public class ReferenceDialog extends Dialog<Void> {
    private ReferenceDialogPane dialogPane;

    public ReferenceDialog() {
        dialogPane = new ReferenceDialogPane();
        setDialogPane(dialogPane);
        setResultConverter(e -> null);
    }
}

