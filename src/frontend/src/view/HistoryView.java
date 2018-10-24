package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import view.utils.PrettyUI;

public class HistoryView {
    static final int HISTORY_VIEW_WIDTH =
            MainView.SCREEN_WIDTH - SidebarView.SIDEBAR_VIEW_WIDTH - TurtleView.TURTLE_VIEW_WIDTH;
    private final int FONT_SIZE = 12;


    private ScrollPane root;
    private VBox display;

    HistoryView() {
        display = new VBox(10);
        display.getStyleClass().add("history-view");
        display.setMinWidth(HISTORY_VIEW_WIDTH);
        display.setMaxWidth(HISTORY_VIEW_WIDTH);
        display.heightProperty().addListener(ob -> root.setVvalue(root.getVmax()));

        root = new ScrollPane();
        root.getStyleClass().add("history-view-bg");
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setContent(display);
    }

    public ScrollPane view() { return root; }

    public void addText(String text) {
        var flo = new TextFlow();
        PrettyUI.purplify(flo);
        flo.getChildren().addAll(PrettyUI.highlight(text.trim(), FONT_SIZE));
        display.getChildren().add(flo);
    }

    public void displayError(Exception e) {
        var flo = new TextFlow();
        PrettyUI.purplify(flo);
        flo.getChildren().addAll(PrettyUI.error(e.getMessage().trim(), "", FONT_SIZE));
        display.getChildren().add(flo);
    }

    public void setLanguage(String lang) { }
}
