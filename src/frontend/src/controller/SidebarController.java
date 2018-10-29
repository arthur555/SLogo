package controller;

import app.TabbedApp;
import engine.errors.IllegalParameterException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import view.CanvasView;
import view.SidebarView;
import view.TurtleView;
import view.reference.ReferenceDialog;
import view.utils.ImageUtils;

import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import model.ModelModule;
import model.TurtleManager;
import model.TurtleModel;


public class SidebarController {
    private static final int ICON_WIDTH = 100;
    private static final int ICON_HEIGHT = 80;

    private TurtleManager turtleManager;

    private TabbedApp app;

    private SidebarView sidebar;

    private EditorController editorController;
    private CanvasController canvasController;

    private String lang;

    private Consumer<String> setEngineLanguage;

    public SidebarController(
            String lang,
            TabbedApp app,
            SidebarView sidebar,
            TurtleManager turtleManager,
            Consumer<String> setEngineLanguage
    ) {
        this.app = app;
        this.lang = lang;
        this.sidebar = sidebar;
        this.turtleManager = turtleManager;
        this.setEngineLanguage = setEngineLanguage;
        setupHandlers();
    }

    public void registerControllers(
            EditorController editorController,
            CanvasController canvasController
    ) {
        this.editorController = editorController;
        this.canvasController = canvasController;
        canvasController.bindDuration(sidebar.speedSlider().valueProperty());
        canvasController.bindStroke(sidebar.strokeSlider().valueProperty());
    }

    private void setupHandlers() {
        sidebar.newButton().setOnMouseClicked(this::newInstanceOnClick);
        sidebar.backgroundColor().setOnAction(this::backgroundColorOnChange);
        sidebar.penColor().setOnAction(this::penColorOnChange);
        sidebar.turtleImageButton().setOnMouseClicked(this::turtleImageOnClick);
        sidebar.languageButton().setOnMouseClicked(this::languageOnClick);
        sidebar.helpButton().setOnMouseClicked(this::helpOnClick);
        sidebar.multiTurtle().setOnMouseClicked(this::multiOnClick);
    }

    private void newInstanceOnClick(MouseEvent e) {
        app.newInstance();
    }

    private void backgroundColorOnChange(ActionEvent e) {
        canvasController.setBackgroundColor(sidebar.backgroundColor().getValue());
    }

    private void penColorOnChange(ActionEvent e) {
        canvasController.setPenColor(sidebar.penColor().getValue());
    }

    private void turtleImageOnClick(MouseEvent me) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Pick an image for the turtle");
        var file = fileChooser.showOpenDialog(null);

        try {
            var is = new FileInputStream(file);
            var image = ImageUtils.getImageFromAbsUrl(is, TurtleView.TURTLE_SIZE, TurtleView.TURTLE_SIZE);
            if (image.getException() != null)
                throw new RuntimeException("The chosen image file is either not an image or corrupted");
            canvasController.setTurtleImage(image);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Something went wrong ...");
            alert.setContentText("While loading the Image, this happened:\n" + e.toString());
            alert.showAndWait();
        }
    }

    private void languageOnClick(MouseEvent e) {
        List<String> choices = ControllerModule.LANGUAGES;
        ChoiceDialog<String> dialog = new ChoiceDialog<>(lang, choices);
        dialog.setTitle("Language settings");
        dialog.setHeaderText("Choose a Language");
        dialog.setGraphic(new Pane(new ImageView(ImageUtils.getImageFromUrl("languages.png", ICON_WIDTH, ICON_HEIGHT))));
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(chosenLanguage -> lang = chosenLanguage);

        sidebar.appendLanguageTooltip("current: " + lang);
        setEngineLanguage.accept(lang);
    }

    private void helpOnClick(MouseEvent e) {
        var dialog = new ReferenceDialog();
        dialog.show();
    }

    private void multiOnClick(MouseEvent e) {
        try {
            turtleManager.addTurtle(turtleManager.id() + 1);
        } catch (IllegalParameterException error) {
            System.out.println("");
        }
    }
}
