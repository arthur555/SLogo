package engine.Lexer;

import engine.errors.CommandSyntaxException;
import engine.parser.CrudeParser;
import engine.translator.LanguageTranslator;
import engine.translator.TypeTranslator;

import java.util.LinkedList;
import java.util.Queue;

public class CrudeLexer implements Lexer{
    private static final String PREFIX = "engine/translator/languages/";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String SYNTAX = "Syntax";

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
                    type = cateogrize(chunk);
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
     * This method categorize the input command into its grammar types. Details on grammar types are found in the EBNF reference file.
     *
     * @param chunk: An input String type such as Forward or Left.
     * @return A String indicating the type of the command such as Unary and Binary.
     */
    private String cateogrize(String chunk) {

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
