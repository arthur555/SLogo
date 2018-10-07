package controller.editor;

import view.editor.EditorView;

public class EditorController {
    private EditorView editorView;

    public EditorController(EditorView editorView) {
        this.editorView = editorView;
        this.editorView.registerController(this);
    }
}
