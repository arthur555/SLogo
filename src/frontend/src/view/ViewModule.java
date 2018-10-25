package view;

import engine.api.EngineAPI;
import model.ModelModule;

public class ViewModule {
    private MainView mainView;
    private SidebarView sidebarView;
    private CanvasView canvasView;
    private VariableView variableView;
    private HistoryView historyView;
    private CommandView commandView;

    public ViewModule(EngineAPI engineAPI) {
        sidebarView = new SidebarView();
        canvasView = new CanvasView();
        variableView = new VariableView(engineAPI.stateMachine());
        historyView = new HistoryView();
        commandView = new CommandView();
        mainView = new MainView(sidebarView, canvasView, variableView, historyView, commandView);
    }

    public MainView mainView() { return mainView; }
    public SidebarView sidebarView() { return sidebarView; }
    public CanvasView canvasView() { return canvasView; }
    public VariableView variableView() { return variableView; }
    public HistoryView historyView() { return historyView; }
    public CommandView commandView() { return commandView; }
}
