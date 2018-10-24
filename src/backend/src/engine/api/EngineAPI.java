package engine.api;

import engine.errors.CommandSyntaxException;
import engine.errors.InterpretationException;
import engine.errors.UndefinedKeywordException;

public interface EngineAPI {
    void processString(String str) throws ClassNotFoundException, UndefinedKeywordException, CommandSyntaxException, InterpretationException;
}
