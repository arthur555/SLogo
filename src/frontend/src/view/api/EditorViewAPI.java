package view.api;

import engine.errors.ParsingError;
import engine.errors.RuntimeError;

public interface EditorViewAPI {
    void displayError(ParsingError e);
    void displayError(RuntimeError e);
}
