package engine.parser.translator;

import engine.errors.CommandSyntaxException;

import java.util.MissingResourceException;

public class LanguageTranslator implements Translator {

    @Override
    public void addPatterns(String file) throws MissingResourceException {

    }

    @Override
    public void setPatterns(String file) throws MissingResourceException {

    }

    @Override
    public String getSymbol(String text) throws CommandSyntaxException {
        return null;
    }
}
