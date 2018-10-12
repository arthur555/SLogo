module frontend {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;

    requires backend;

    exports view.api;
}