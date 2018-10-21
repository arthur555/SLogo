package view.utils;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PrettyUI {
    private static final String DEFAULT_FONT = "consolas";
    private static final int COMMAND_SIZE = 16;
    private static final String SPACE = " ";

    private static final String PURPLE_BG = "purple-bg";
    private static final String LIGHT_PURPLE_BG = "light-purple-bg";
    private static final String H1 = "text-header";
    private static final String P = "text-body";

    private static final Color SYNTAX_PLAIN = Color.BLACK;
    private static final Color SYNTAX_KEYWORD = Color.valueOf("#125bd1");
    private static final Color SYNTAX_VARIABLE = Color.valueOf("#01a362");
    private static final Color SYNTAX_NUMBER = Color.valueOf("#800020");
    private static final Color SYNTAX_COMMAND = Color.valueOf("#661bad");
    private static final Color SYNTAX_COMMENT = Color.valueOf("#afafaf");

    private static Text textWithStyle(String text, String styleClass) {
        var ret = new Text(text);
        ret.getStyleClass().add(styleClass);
        return ret;
    }

    private static Text textWithColor(String text, Color color, int size) {
        var ret = new Text(text);
        ret.setFill(color);
        ret.setFont(new Font(DEFAULT_FONT, size));
        return ret;
    }

    public static Text plain(String text) { return textWithColor(text+SPACE, SYNTAX_PLAIN, COMMAND_SIZE); }
    public static Text keyword(String text) { return textWithColor(text+SPACE, SYNTAX_KEYWORD, COMMAND_SIZE); }
    public static Text variable(String text) { return textWithColor(text+SPACE, SYNTAX_VARIABLE, COMMAND_SIZE); }
    public static Text number(String text) { return textWithColor(text+SPACE, SYNTAX_NUMBER, COMMAND_SIZE); }
    public static Text command(String text) { return textWithColor(text+SPACE, SYNTAX_COMMAND, COMMAND_SIZE); }
    public static Text comment(String text) { return textWithColor(text+SPACE, SYNTAX_COMMENT, COMMAND_SIZE); }

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
