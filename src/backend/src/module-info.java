module backend {
    requires javafx.base;
    requires javafx.graphics; // just for Color

    exports model;
    exports engine.commands;
    exports engine.errors;
    exports engine.api;
    exports engine.compiler.storage;
}