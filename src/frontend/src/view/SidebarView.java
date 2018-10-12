package view;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.utils.ImageUtils;

public class SidebarView {
    public static final int SIDEBAR_VIEW_WIDTH = 60;

    private static final String BACKGROUND_BUTTON_IMG = "background_button.png";
    private static final String TURTLE_IMAGE_BUTTON_IMG = "turtle_image_button.png";
    private static final String PEN_COLOR_BUTTON_IMG = "pen_color_button.png";
    private static final String LANGUAGE_BUTTON_IMG = "language_button.png";

    private static final String BACKGROUND_BUTTON_TOOLTIP = "Set background color";
    private static final String TURTLE_IMAGE_BUTTON_TOOLTIP = "Set turtle image";
    private static final String PEN_COLOR_BUTTON_TOOLTIP = "Set pen color";
    private static final String LANGUAGE_BUTTON_TOOLTIP = "Set language";

    private static final int BUTTON_SIZE = 50;
    private static final int BUTTON_MARGIN = 10;

    private VBox root;

    private ImageView backgroundButton;
    private ImageView turtleImageButton;
    private ImageView penColorButton;
    private ImageView languageButton;

    private Rectangle backgroundButtonBox;
    private Rectangle turtleImageButtonBox;
    private Rectangle penColorButtonBox;
    private Rectangle languageButtonBox;

    private StackPane backgroundButtonWrapper;
    private StackPane turtleImageButtonWrapper;
    private StackPane penColorButtonWrapper;
    private StackPane languageButtonWrapper;

    public SidebarView() {
        root = new VBox(BUTTON_MARGIN);
        root.getStyleClass().add("sidebar");

        loadImages();
        loadBoxes();
        loadWrappers();
        installTooltips();

        root.getChildren().addAll(
                backgroundButtonWrapper,
                turtleImageButtonWrapper,
                penColorButtonWrapper,
                languageButtonWrapper
        );
    }

    private void loadImages() {
        backgroundButton = new ImageView(
                ImageUtils.getImageFromUrl(BACKGROUND_BUTTON_IMG, BUTTON_SIZE, BUTTON_SIZE));
        turtleImageButton = new ImageView(
                ImageUtils.getImageFromUrl(TURTLE_IMAGE_BUTTON_IMG, BUTTON_SIZE, BUTTON_SIZE));
        penColorButton = new ImageView(
                ImageUtils.getImageFromUrl(PEN_COLOR_BUTTON_IMG, BUTTON_SIZE, BUTTON_SIZE));
        languageButton = new ImageView(
                ImageUtils.getImageFromUrl(LANGUAGE_BUTTON_IMG, BUTTON_SIZE, BUTTON_SIZE));
    }

    private void loadBoxes() {
        backgroundButtonBox = new Rectangle(BUTTON_SIZE, BUTTON_SIZE);
        turtleImageButtonBox = new Rectangle(BUTTON_SIZE, BUTTON_SIZE);
        penColorButtonBox = new Rectangle(BUTTON_SIZE, BUTTON_SIZE);
        languageButtonBox = new Rectangle(BUTTON_SIZE, BUTTON_SIZE);
        backgroundButtonBox.getStyleClass().add("sidebar-box");
        turtleImageButtonBox.getStyleClass().add("sidebar-box");
        penColorButtonBox.getStyleClass().add("sidebar-box");
        languageButtonBox.getStyleClass().add("sidebar-box");
    }

    private void loadWrappers() {
        backgroundButtonWrapper = new StackPane(backgroundButton, backgroundButtonBox);
        turtleImageButtonWrapper = new StackPane(turtleImageButton, turtleImageButtonBox);
        penColorButtonWrapper = new StackPane(penColorButton, penColorButtonBox);
        languageButtonWrapper = new StackPane(languageButton, languageButtonBox);
    }

    private void installTooltips() {
        var backgroundTooltip = new Tooltip(BACKGROUND_BUTTON_TOOLTIP);
        var turtleImageTooltip = new Tooltip(TURTLE_IMAGE_BUTTON_TOOLTIP);
        var penColorTooltip = new Tooltip(PEN_COLOR_BUTTON_TOOLTIP);
        var languageTooltip = new Tooltip(LANGUAGE_BUTTON_TOOLTIP);

        backgroundTooltip.setShowDelay(Duration.ZERO);
        backgroundTooltip.setHideDelay(Duration.ZERO);
        turtleImageTooltip.setShowDelay(Duration.ZERO);
        turtleImageTooltip.setHideDelay(Duration.ZERO);
        penColorTooltip.setShowDelay(Duration.ZERO);
        penColorTooltip.setHideDelay(Duration.ZERO);
        languageTooltip.setShowDelay(Duration.ZERO);
        languageTooltip.setHideDelay(Duration.ZERO);

        Tooltip.install(backgroundButtonBox, backgroundTooltip);
        Tooltip.install(turtleImageButtonBox, turtleImageTooltip);
        Tooltip.install(penColorButtonBox, penColorTooltip);
        Tooltip.install(languageButtonBox, languageTooltip);
    }

    public Node view() { return root; }

    public ImageView backgroundButton() { return backgroundButton; }
    public ImageView turtleImageButton() { return turtleImageButton; }
    public ImageView penColorButton() { return penColorButton; }
    public ImageView languageButton() { return languageButton; }
}
