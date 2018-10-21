package engine.Lexer;

import engine.commands.Command;
import engine.commands.Forward;
import engine.errors.CommandSyntaxException;
import engine.parser.CrudeParser;
import engine.translator.LanguageTranslator;
import engine.translator.TypeTranslator;

import java.lang.reflect.InvocationTargetException;
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
        grammarMap.put("Forward", "Unary");
        grammarMap.put("Backward", "Unary");
        grammarMap.put("Left", "Unary");
        grammarMap.put("Right", "Unary");
        grammarMap.put("SetHeading", "Unary");
        grammarMap.put("SetTowards", "Unary");
        grammarMap.put("SetPosition", "Unary");
        grammarMap.put("Minus", "Unary");
        grammarMap.put("Random", "Unary");
        grammarMap.put("Sine", "Unary");
        grammarMap.put("Cosine", "Unary");
        grammarMap.put("Tangent", "Unary");
        grammarMap.put("ArcTangent", "Unary");
        grammarMap.put("NaturalLog", "Unary");
        grammarMap.put("Not", "Unary");

        grammarMap.put("PenUp", "Direct");
        grammarMap.put("PenDown", "Direct");
        grammarMap.put("ShowTurtle", "Direct");
        grammarMap.put("HideTurtle", "Direct");
        grammarMap.put("Home", "Direct");
        grammarMap.put("ClearScreen", "Direct");
        grammarMap.put("XCoordinate", "Direct");
        grammarMap.put("YCoordinate", "Direct");
        grammarMap.put("Heading", "Direct");
        grammarMap.put("IsPenDown", "Direct");
        grammarMap.put("IsShowing", "Direct");
        grammarMap.put("Pi", "Direct");

        grammarMap.put("Sum", "Binary");
        grammarMap.put("Difference", "Binary");
        grammarMap.put("Quotient", "Binary");
        grammarMap.put("Remainder", "Binary");
        grammarMap.put("Power", "Binary");
        grammarMap.put("LessThan", "Binary");
        grammarMap.put("GreaterThan", "Binary");
        grammarMap.put("Equal", "Binary");
        grammarMap.put("NotEqual", "Binary");
        grammarMap.put("And", "Binary");
        grammarMap.put("Or", "Binary");

        grammarMap.put("MakeVariable", "MakeVariable");

        grammarMap.put("Repeat", "Condition");
        grammarMap.put("If", "Condition");

        grammarMap.put("DoTimes", "DoTimes");

        grammarMap.put("For", "For");

        grammarMap.put("IfElse", "IfElse");

        grammarMap.put("MakeUserInstruction", "MakeUserInstruction");

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
     * Set the language dictionary to use the designated languages.
     *
     * @param languages : A String array of languages
     * @throws MissingResourceException
     */
    @Override
    public void setLanguage(String... languages) throws MissingResourceException {
        if (languages.length == 0 || languages == null) {
            return;
        }
        myLanguage.setPatterns(null);
        for (String language : languages) {
            myLanguage.addPatterns(PREFIX + language);
        }
    }

    /**
     * Add more languages to the internal dictionary.
     *
     * @param languages : A String array of languages.
     * @throws MissingResourceException
     */
    @Override
    public void addLanguage(String... languages) throws MissingResourceException {

    }

    public void addLanguage(String language) {
        myLanguage.addPatterns(PREFIX + language);
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
                } else if (type.equals("Command")) {
                    chunk = myLanguage.getSymbol(chunk);
                    type = myGrammerMap.get(chunk);
                    start = end + 1;
                    end++;
                } else {
                    start = end + 1;
                    end++;
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

    /**
     * This main function tests the functionality of the Lexer.
     *
     * @param args
     */
    public static void main(String[] args) {
        CrudeLexer lexer = new CrudeLexer();
        String input = "fd(Forward #thisIsAComment :aVariable) - back (([+ 3 3.33 4]))";
        String input2 = "fd 2";
        try {
            lexer.readString(input);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        Queue<Token> tokens = lexer.getTokens();
        System.out.println("The input String is\n\n" + input + "\n");
        System.out.print("The queue of tokens is:\n\n");
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }
}
