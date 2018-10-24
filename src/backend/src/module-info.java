module backend {
    requires javafx.base;
    exports model;
    exports engine.commands;
    exports engine.errors;
    exports engine.api;
    exports engine.compiler.storage;
}