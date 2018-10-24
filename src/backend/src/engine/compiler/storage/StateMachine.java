package engine.compiler.storage;

import engine.errors.InterpretationException;

/**
 * This interface handles the storage of variables and function predicates in Variable AST nodes.
 *
 * @author Haotian Wang
 */
public interface StateMachine {
    /**
     * Set a double value for a variable.
     *
     * @param key
     * @param value
     */
    public void setDouble(String key, double value);

    /**
     * Set an int value for a variable.
     *
     * @param key
     * @param value
     */
    public void setInteger(String key, int value);

    /**
     * Set a String value for a variable.
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value);

    /**
     * Get the type of the variable, either a double, an integer or a function.
     *
     * @param key
     * @return A String representation of the type of the variable.
     */
    String getVariableType(String key);

    /**
     * Remove the key entry from the map.
     *
     * @param key
     */
    public void removeVariable(String key) throws InterpretationException;

    /**
     * Clear all state variables in the state machine.
     */
    public void resetState();

    /**
     * Present the internal storage of the StateMachine in a list format, separated by newline.
     *
     * @return A String representation of the StateMachine.
     */
    public String listOfVariables();
}
