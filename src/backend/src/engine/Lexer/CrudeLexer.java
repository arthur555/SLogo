package engine.Lexer;

import engine.commands.Forward;
import engine.errors.CommandSyntaxException;
import engine.parser.CrudeParser;
import engine.translator.LanguageTranslator;
import engine.translator.TypeTranslator;

import java.util.*;

/**
 * This CrudeLexer reads in a String input and output a queue of Tokens representing the basic syntactic building blocks in the SLogo language.
 *
 * @author Haotian Wang
 */
public class CrudeLexer implements Lexer{
    private static final String PREFIX = "engine/translator/languages/";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String SYNTAX = "Syntax";
    private static final Map<String, String> myGrammerMap;
    static {
        Map<String, String> grammarMap = new HashMap<>();
        grammarMap.put("Forward", "unary");
        grammarMap.put("Backward", "unary");
        grammarMap.put("Left", "unary");
        grammarMap.put("Right", "unary");
        grammarMap.put("SetHeading", "unary");
        grammarMap.put("SetTowards", "unary");
        grammarMap.put("SetPosition", "unary");
        grammarMap.put("Minus", "unary");
        grammarMap.put("Random", "unary");
        grammarMap.put("Sine", "unary");
        grammarMap.put("Cosine", "unary");
        grammarMap.put("Tangent", "unary");
        grammarMap.put("ArcTangent", "unary");
        grammarMap.put("NaturalLog", "unary");
        grammarMap.put("Not", "unary");

        grammarMap.put("PenUp", "direct");
        grammarMap.put("PenDown", "direct");
        grammarMap.put("ShowTurtle", "direct");
        grammarMap.put("HideTurtle", "direct");
        grammarMap.put("Home", "direct");
        grammarMap.put("ClearScreen", "direct");
        grammarMap.put("XCoordinate", "direct");
        grammarMap.put("YCoordinate", "direct");
        grammarMap.put("Heading", "direct");
        grammarMap.put("IsPenDown", "direct");
        grammarMap.put("IsShowing", "direct");
        grammarMap.put("Pi", "direct");

        grammarMap.put("Sum", "binary");
        grammarMap.put("Difference", "binary");
        grammarMap.put("Quotient", "binary");
        grammarMap.put("Remainder", "binary");
        grammarMap.put("Power", "binary");
        grammarMap.put("LessThan", "binary");
        grammarMap.put("GreaterThan", "binary");
        grammarMap.put("Equal", "binary");
        grammarMap.put("NotEqual", "binary");
        grammarMap.put("And", "binary");
        grammarMap.put("Or", "binary");

        grammarMap.put("MakeVariable", "assign");

        grammarMap.put("Repeat", "condition");
        grammarMap.put("If", "condition");

        grammarMap.put("DoTimes", "dotimes");

        grammarMap.put("For", "for");

        grammarMap.put("IfElse", "ifelse");

        grammarMap.put("MakeUserInstruction", "create");

        myGrammerMap = Collections.unmodifiableMap(grammarMap);
    }

    private TypeTranslator myType;
    private LanguageTranslator myLanguage;
    private Queue<Token> myTokens;

    /**
     * Constructs a CrudeParser with the user-defined language as the starting recognized language.
     *
     * @param language: A String such as "English", "Chinese" or "French".
     */
    public CrudeLexer(String language) {
        myType = new TypeTranslator(PREFIX + SYNTAX);
        myLanguage = new LanguageTranslator(PREFIX + language);
        myTokens = new LinkedList<>();
    }

    /**
     * The default constructor will choose English.properties as its starting recognized languages and Syntax.properties as its starting recognized syntax.
     */
    public CrudeLexer() {
        this(DEFAULT_LANGUAGE);
    }

    /**
     * Reset the language dictionary to use the default language only, which is English.
     */
    public void resetLanguage() {
        myLanguage.setPatterns(PREFIX + DEFAULT_LANGUAGE);
    }

    /**
     * This processes the user input raw String and tokenizes the input String.
     *
     * @param input : A user input raw String.
     */
    @Override
    public void readString(String input) throws CommandSyntaxException {
        myTokens.clear();
        if (input == null || input.isEmpty()) {
            return;
        }
        int start = 0;
        int end = 0;
        while (start < input.length() && end < input.length()) {
            try {
                myType.getSymbol(input.substring(start, end + 1));
                while (end < input.length() && myType.containsString(input.substring(start, end + 1))) {
                    end++;
                }
                end--;
                String chunk = input.substring(start, end + 1);
                String type = myType.getSymbol(chunk);
                if (type.equals("Comment") || type.equals("Whitespace")) {
                    start = end + 1;
                    end++;
                    continue;
                }
                if (type.equals("Command")) {
                    chunk = myLanguage.getSymbol(chunk);
                    start = end + 1;
                    end++;
                    type = myGrammerMap.get(type);
                }
                myTokens.offer(new Token(chunk, type));
            } catch (CommandSyntaxException e) {
                end++;
            }
        }
        if (start != input.length() || end != input.length()) {
            throw new CommandSyntaxException("The input String contains tokens that are not properly defined in the properties files.");
        }
    }

    /**
     * Return a list of Token from the input String, after translation by two translators.
     *
     * @return A list of Token from the input String.
     */
    @Override
    public Queue<Token> getTokens() {
        return myTokens;
    }
}
