package controller;

import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.CommandView;
import view.HistoryView;

public class EditorController {
    public static final String COMMAND_PREFIX = ">> ";

    private CommandView commandView;
    private HistoryView historyView;

    public EditorController(CommandView commandView, HistoryView historyView) {
        this.commandView = commandView;
        this.historyView = historyView;
        setupHandlers();
    }

    private void setupHandlers() {
        commandView.view().setOnKeyPressed(this::handleKeyPressed);
        commandView.view().caretPositionProperty().addListener(this::handleCaretPositionChanged);
        commandView.view().textProperty().addListener(this::handleTextChanged);
        historyView.view().setOnKeyPressed(e -> {
            if(!e.isMetaDown()) commandView.view().requestFocus();
        });
    }

    private void handleKeyPressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ESCAPE) commandView.view().clear();
        else if(e.getCode() == KeyCode.ENTER) {
            if(e.isShiftDown()) {
                commandView.view().insertText(commandView.view().getCaretPosition(), "\n");
            } else submitCommand();
        }
    }

    private void handleCaretPositionChanged(ObservableValue<? extends Number> ob, Number ov, Number nv) {
        if(nv.intValue() < COMMAND_PREFIX.length()) {
            commandView.view().positionCaret(COMMAND_PREFIX.length());
        }
    }

    private void handleTextChanged(ObservableValue<? extends String> ob, String ov, String nv) {
        if(!nv.startsWith(COMMAND_PREFIX)) {
            commandView.view().setText(COMMAND_PREFIX + nv.substring(findCorruptPrefix(nv)));
            commandView.view().positionCaret(COMMAND_PREFIX.length());
        }
    }

    private int findCorruptPrefix(String s) {
        for(int i = 0 ; i < Math.min(COMMAND_PREFIX.length(), s.length()) ; i ++) {
            if(!COMMAND_PREFIX.contains(s.subSequence(i, i+1))) return i;
        } return Math.min(COMMAND_PREFIX.length(), s.length());
    }

    private void submitCommand() {
        historyView.view().appendText(historyView.view().getText().trim()+"\n");
        commandView.view().clear();
    }

}
