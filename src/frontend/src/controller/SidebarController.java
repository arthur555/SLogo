package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import view.SidebarView;
import view.TurtleView;
import view.utils.ImageUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SidebarController {
    private SidebarView sidebar;
    private TurtleView turtleView;
    private TurtleController turtleController;
    private EditorController editorController;

    public SidebarController(
            SidebarView sidebar,
            TurtleView turtleView
    ) {
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
        var filters = new FileChooser.ExtensionFilter("Image files", ".jpg", ".png", "gif", "bmp");
        fileChooser.setSelectedExtensionFilter(filters);
        var file = fileChooser.showOpenDialog(null);

        try {
            var is = new FileInputStream(file);
            turtleView.setTurtleImage(
                    TurtleView.TURTLE_VIEW_WIDTH/2,
                    TurtleView.TURTLE_VIEW_WIDTH/2,
                    ImageUtils.getImageFromAbsUrl(is, TurtleView.TURTLE_SIZE, TurtleView.TURTLE_SIZE)
            );
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Something went wrong ...");
            alert.setContentText("While loading the Image, this happened:\n"+e.toString());
            alert.showAndWait();
        }
    }

    private void languageOnClick(MouseEvent e) {
    }

    private void helpOnClick(MouseEvent e) {
    }
}
