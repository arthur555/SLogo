package controller.dialog;

import javafx.scene.control.DialogPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;

class ReferenceDialogPane extends DialogPane {
    private static final double DIALOG_WIDTH = 700;
    private static final double DIALOG_HEIGHT = 500;

    private static final String REFERENCE_FILE = "slogo_ref";

    private GridPane root;

    public ReferenceDialogPane() {
        setMinWidth(DIALOG_WIDTH);
        setMinHeight(DIALOG_HEIGHT);

        root = new GridPane();

    }

    private void generateTree() {
        var rootItem = new TreeItem<>("Inbox");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<>("Message" + i);
            rootItem.getChildren().add(item);
        }
        var tree = new TreeView<>(rootItem);
    }
}

