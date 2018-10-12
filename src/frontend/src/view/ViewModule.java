package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelModule;

import java.util.ResourceBundle;

public class ViewModule {
    private static final ResourceBundle APP_PROPERTIES = ResourceBundle.getBundle("slogo");
    private static final String STYLESHEET = "style.css";

    private MainView mainView;
    private SidebarView sidebarView;
    private TurtleView turtleView;
    private VariableView variableView;
    private HistoryView historyView;
    private CommandView commandView;

    public ViewModule(ModelModule modelModule) {
        sidebarView = new SidebarView();
        turtleView = new TurtleView(modelModule.turtleModel());
        variableView = new VariableView();
        historyView = new HistoryView();
        commandView = new CommandView();

        mainView = new MainView(sidebarView, turtleView, variableView, historyView, commandView);
    }

    public void show(Stage primaryStage) {
        primaryStage.setTitle(APP_PROPERTIES.getString("Title"));
        var scene = new Scene(mainView.view(), MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT);
        scene.getStylesheets().add(STYLESHEET);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public SidebarView sidebarView() { return sidebarView; }
    public TurtleView turtleView() { return turtleView; }
    public VariableView variableView() { return variableView; }
    public HistoryView historyView() { return historyView; }
    public CommandView commandView() { return commandView; }
}