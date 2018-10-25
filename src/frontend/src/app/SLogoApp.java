package app;

import controller.ControllerModule;
import engine.api.ASTEngineAPI;
import engine.api.EngineAPI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.ModelModule;
import view.ViewModule;

import java.util.ResourceBundle;

public class SLogoApp extends Application implements TabbedApp {
    public static final int APP_SCREEN_WIDTH = 1200;
    public static final int APP_SCREEN_HEIGHT = 800;
    public static final int APP_TAB_HEIGHT = 24;

    private static final ResourceBundle APP_PROPERTIES = ResourceBundle.getBundle("slogo");
    private static final String TAB_STYLE = "main-tab";
    private static final String STYLESHEET = "style.css";

    private TabPane tabPane;
    private Scene scene;
    private int tabIndex;

    @Override
    public void start(Stage primaryStage) {
        tabIndex = 1;
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        tabPane.getStyleClass().add(TAB_STYLE);
        primaryStage.setResizable(false);
        primaryStage.setTitle(APP_PROPERTIES.getString("Title"));
        scene = new Scene(tabPane, APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        primaryStage.setScene(scene);
        primaryStage.show();
        newInstance();
    }

    @Override
    public void newInstance() {
        ModelModule modelModule = new ModelModule();
        EngineAPI engineApi = new ASTEngineAPI(modelModule.turtleManager());
        ViewModule viewModule = new ViewModule(engineApi);
        new ControllerModule(this, modelModule, engineApi, viewModule);

        var tab = new Tab("Untitled " + tabIndex);
        if(tabIndex == 1) tab.setClosable(false);
        tabIndex ++;
        tab.setContent(viewModule.mainView().view());
        tabPane.getTabs().add(tab);
    }

    public static void main(String[] args) { launch(args); }
}
