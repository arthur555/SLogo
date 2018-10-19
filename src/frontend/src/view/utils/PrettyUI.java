package view.utils;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class PrettyUI {
    private static final String PURPLE_BG = "purple-bg";
    private static final String LIGHT_PURPLE_BG = "light-purple-bg";
    private static final String H1 = "text-header";
    private static final String P = "text-body";

    private static Text textWithStyle(String text, String styleClass) {
        var ret = new Text(text);
        ret.getStyleClass().add(styleClass);
        return ret;
    }

    public static Text h1(String text) { return textWithStyle(text, H1); }
    public static Text p(String text) { return textWithStyle(text, P); }
    public static Text p(String text, double wrapWidth) {
        var ret = p(text);
        ret.setWrappingWidth(wrapWidth);
        return ret;
    }

    public static void purplify(Node ugly) { ugly.getStyleClass().add(PURPLE_BG); }
    public static void lightPurplify(Node ugly) { ugly.getStyleClass().add(LIGHT_PURPLE_BG); }
    public static void alternatePurple(int idx, Node ugly) {
        ugly.getStyleClass().add(idx % 2 == 0 ? LIGHT_PURPLE_BG : PURPLE_BG);
    }
}
