package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import view.SidebarView;
import view.TurtleView;
import view.utils.ImageUtils;

import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

public class SidebarController {
    private static final int ICON_WIDTH = 100;
    private static final int ICON_HEIGHT = 80;

    private SidebarView sidebar;
    private TurtleView turtleView;
    private TurtleController turtleController;
    private EditorController editorController;

    private String lang;

    public SidebarController(
            String lang,
            SidebarView sidebar,
            TurtleView turtleView
    ) {
        this.lang = lang;
        this.sidebar = sidebar;
        this.turtleView = turtleView;
        setupHandlers();
    }

    public void registerControllers(
            TurtleController turtleController,
            EditorController editorController
    ) {
        this.turtleController = turtleController;
        this.editorController = editorController;
    }

    private void setupHandlers() {
        sidebar.backgroundColor().setOnAction(this::backgroundColorOnChange);
        sidebar.penColor().setOnAction(this::penColorOnChange);
        sidebar.turtleImageButton().setOnMouseClicked(this::turtleImageOnClick);
        sidebar.languageButton().setOnMouseClicked(this::languageOnClick);
        sidebar.helpButton().setOnMouseClicked(this::helpOnClick);
    }

    private void backgroundColorOnChange(ActionEvent e) {
        turtleView.setBackgroundColor(sidebar.backgroundColor().getValue());
    }

    private void penColorOnChange(ActionEvent e) {
        turtleView.setPenColor(sidebar.backgroundColor().getValue());
    }

    private void turtleImageOnClick(MouseEvent me) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Pick an image for the turtle");
        var file = fileChooser.showOpenDialog(null);

        try {
            var is = new FileInputStream(file);
            var image = ImageUtils.getImageFromAbsUrl(is, TurtleView.TURTLE_SIZE, TurtleView.TURTLE_SIZE);
            if(image.getException() != null)
                throw new RuntimeException("The chosen image file is either not an image or corrupted");
            turtleView.setTurtleImage(image);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Something went wrong ...");
            alert.setContentText("While loading the Image, this happened:\n"+e.toString());
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

        sidebar.appendLanguageTooltip("current: "+lang);
        editorController.setLang(lang);
    }

    private void helpOnClick(MouseEvent e) {

    }
}
