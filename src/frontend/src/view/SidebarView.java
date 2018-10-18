package view;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SidebarView {
    public static final int SIDEBAR_VIEW_WIDTH = 75;

    private static final int BUTTON_MARGIN = 10;
    private static final int BUTTON_SIZE = 50;

    private static final int BACKGROUND_BUTTON = 0;
    private static final int TURTLE_IMAGE_BUTTON = 1;
    private static final int PEN_COLOR_BUTTON = 2;
    private static final int LANGUAGE_BUTTON = 3;
    private static final int HELP_BUTTON = 4;

    private static final List<Image> ICONS = Collections.unmodifiableList(List.of(
            ImageUtils.getImageFromUrl("background_button.png", BUTTON_SIZE, BUTTON_SIZE),
            ImageUtils.getImageFromUrl("turtle_image_button.png", BUTTON_SIZE, BUTTON_SIZE),
            ImageUtils.getImageFromUrl("pen_color_button.png", BUTTON_SIZE, BUTTON_SIZE),
            ImageUtils.getImageFromUrl("language_button.png", BUTTON_SIZE, BUTTON_SIZE),
            ImageUtils.getImageFromUrl("help_button.png", BUTTON_SIZE, BUTTON_SIZE)
    ));

    private static final List<String> TOOLTIPS = Collections.unmodifiableList(List.of(
            "Set background color",
            "Set turtle image",
            "Set pen color",
            "Set language",
            "Open documentation"
    ));

    private Pane root;
    private VBox icons;
    private List<StackPane> buttons;
    private ColorPicker backgroundColor, penColor;

    SidebarView() {
        root = new Pane();
        root.getStyleClass().add("sidebar");

        icons = new VBox(BUTTON_MARGIN);
        icons.getStyleClass().add("sidebar");

        backgroundColor = new ColorPicker();
        backgroundColor.getStyleClass().add("background-button");
        penColor = new ColorPicker();
        penColor.getStyleClass().add("pen-color-button");
        setupButtons();

        icons.getChildren().addAll(buttons);
        root.getChildren().add(icons);
    }

    private void setupButtons() {
        buttons = new ArrayList<>();
        for(int i = 0 ; i < ICONS.size() ; i ++) {
            StackPane button;
            Node image;
            if(i == BACKGROUND_BUTTON || i == PEN_COLOR_BUTTON) {
                image = i == BACKGROUND_BUTTON ? backgroundColor : penColor;
            } else image = new ImageView(ICONS.get(i));
            button = new StackPane(image);
            button.getStyleClass().add("sidebar-box");

            var tooltip = new Tooltip(TOOLTIPS.get(i));
            tooltip.setShowDelay(Duration.ZERO);
            tooltip.setHideDelay(Duration.ZERO);
            Tooltip.install(button, tooltip);
            buttons.add(button);
        }
        buttons = Collections.unmodifiableList(buttons);
    }

    public Pane view() { return root; }

    public StackPane backgroundButton() { return buttons.get(BACKGROUND_BUTTON); }
    public StackPane turtleImageButton() { return buttons.get(TURTLE_IMAGE_BUTTON); }
    public StackPane penColorButton() { return buttons.get(PEN_COLOR_BUTTON); }
    public StackPane languageButton() { return buttons.get(LANGUAGE_BUTTON); }
    public StackPane helpButton() { return buttons.get(HELP_BUTTON); }
    public ColorPicker backgroundColor() { return backgroundColor; }
    public ColorPicker penColor() { return penColor; }
}
