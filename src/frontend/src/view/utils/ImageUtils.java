package view.utils;

import javafx.scene.image.Image;

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
}
