package view.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BackgroundUtils {
    public static Background coloredBackground(Color c) {
        return new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY));
    }
}
