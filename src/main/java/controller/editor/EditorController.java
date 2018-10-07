package controller.editor;

import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.editor.EditorView;

public class EditorController {
    public static final String COMMAND_PREFIX = ">> ";

    private EditorView editorView;

    public EditorController(EditorView editorView) {
        this.editorView = editorView;
        this.editorView.registerController(this);
        setupHandlers();
    }

    private void setupHandlers() {
        editorView.currentCommand().setOnKeyPressed(this::handleKeyPressed);
        editorView.currentCommand().caretPositionProperty().addListener(this::handleCaretPositionChanged);
        editorView.currentCommand().textProperty().addListener(this::handleTextChanged);
        editorView.commandHistory().setOnKeyPressed(e -> editorView.currentCommand().requestFocus());
    }

    private void handleKeyPressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ESCAPE) editorView.currentCommand().clear();
        else if(e.getCode() == KeyCode.ENTER) {
            if(e.isShiftDown()) {
                editorView.currentCommand().insertText(editorView.currentCommand().getCaretPosition(), "\n");
            } else submitCommand();
        }
    }

    private void handleCaretPositionChanged(ObservableValue<? extends Number> ob, Number ov, Number nv) {
        if(nv.intValue() < COMMAND_PREFIX.length()) {
            editorView.currentCommand().positionCaret(COMMAND_PREFIX.length());
        }
    }

    private void handleTextChanged(ObservableValue<? extends String> ob, String ov, String nv) {
        if(!nv.startsWith(COMMAND_PREFIX)) {
            editorView.currentCommand().setText(COMMAND_PREFIX + nv.substring(findCorruptPrefix(nv)));
            editorView.currentCommand().positionCaret(COMMAND_PREFIX.length());
        }
    }

    private int findCorruptPrefix(String s) {
        for(int i = 0 ; i < Math.min(COMMAND_PREFIX.length(), s.length()) ; i ++) {
           if(!COMMAND_PREFIX.contains(s.subSequence(i, i+1))) return i;
        } return Math.min(COMMAND_PREFIX.length(), s.length());
    }

    private void submitCommand() {
        editorView.commandHistory().appendText(editorView.currentCommand().getText().trim()+"\n");
        editorView.currentCommand().clear();
    }
}
