package view.utils;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class ImageUtils {
    public static Image getImageFromUrl(String url, double width, double height) {
        return new Image(
                ImageUtils
                        .class
                        .getClassLoader()
                        .getResourceAsStream(url),
                width, height,
                false, true
        );
    }

    public static Image getImageFromAbsUrl(FileInputStream is, double width, double height) {
        return new Image(
                is,
                width, height,
                false, true
        );
    }
}
