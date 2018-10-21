package controller;

import engine.api.EngineAPI;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.CommandView;
import view.HistoryView;

public class EditorController {
    private static final char NEWLINE = '\n';
    private static final String BLANK = "";

    private EngineAPI engineApi;
    private CommandView commandView;
    private HistoryView historyView;
    private String lang;

    public EditorController(String lang, CommandView commandView, HistoryView historyView, EngineAPI engineApi) {
        this.lang = lang;
        this.commandView = commandView;
        this.historyView = historyView;
        this.commandView.setLang(lang);
        this.engineApi = engineApi;
    }

    public void setLang(String lang) {
        this.lang = lang;
        this.commandView.setLang(lang);
    }

    public void handleKeyPressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ESCAPE) commandView.clear();
        else if(e.getCode() == KeyCode.ENTER) {
            if(e.isShiftDown()) commandView.insert(NEWLINE);
            else submitCommand();
        } else if(e.getCode() == KeyCode.BACK_SPACE) commandView.delete();
        else if(e.getCode() == KeyCode.LEFT) commandView.left();
        else if(e.getCode() == KeyCode.RIGHT) commandView.right();
        else if(e.getCode() == KeyCode.QUOTE) commandView.insert(e.isShiftDown() ? '\"' : '\'');
        else if (e.getCode() != KeyCode.COMMAND) {
            char code = (char) e.getCode().getCode();
            if(Character.isAlphabetic(code)) code = (char) (e.isShiftDown() ? code : code+'a'-'A');
            else if(e.isShiftDown()) code = convertShift(code);
            if(code != KeyCode.SHIFT.getCode()) commandView.insert(code);
        }
    }

    private char convertShift(int code) {
        if(code == ';') code = ':';
        if(code == '`') code = '~';
        if(code == '1') code = '!';
        if(code == '2') code = '@';
        if(code == '3') code = '#';
        if(code == '4') code = '$';
        if(code == '5') code = '%';
        if(code == '6') code = '^';
        if(code == '7') code = '&';
        if(code == '8') code = '*';
        if(code == '9') code = '(';
        if(code == '0') code = ')';
        if(code == '-') code = '_';
        if(code == '=') code = '+';
        if(code == '/') code = '?';
        if(code == '.') code = '>';
        if(code == ',') code = '<';
        if(code == '[') code = '{';
        if(code == ']') code = '}';
        if(code == '\\') code = '|';
        return (char) code;
    }

    private void submitCommand() {
        String cmd = commandView.model().replace(CommandView.CARET, BLANK).trim();
        historyView.view().appendText(cmd+NEWLINE);
        commandView.clear();
        engineApi.processString(cmd);
    }
}
