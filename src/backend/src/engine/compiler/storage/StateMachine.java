package engine.compiler.storage;

import engine.compiler.slogoast.Expression;
import engine.compiler.slogoast.Variable;
import engine.errors.InterpretationException;

import java.util.Map;

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
    void setDouble(String key, double value);

    /**
     * Set an int value for a variable.
     *
     * @param key
     * @param value
     */
    void setInteger(String key, int value);

    /**
     * Set a String value for a variable.
     *
     * @param key
     * @param value
     */
    void setString(String key, String value);

    /**
     * Set an Expression value for a variable.
     *
     * @param key
     * @param function
     */
    void setExpression(String key, Expression function);

    /**
     * Set a local integer value for a key.
     *
     * @param key
     * @param value
     */
    void setLocalInteger(String key, int value);

    /**
     * Set a local double value for a key.
     *
     * @param key
     * @param value
     */
    void setLocalDouble(String key, double value);

    /**
     * Set a local String value for a key.
     *
     * @param key
     * @param value
     */
    void setLocalString(String key, String value);

    /**
     * Set an Expression value for a key.
     *
     * @param key
     * @param value
     */
    void setLocalExpression(String key, Expression value);

    /**
     * Set a "local" variable that overwrites the global variable with the same name for the duration before the local variable is deleted.
     *
     * @param key: A String representation of the name of the variable.
     * @param value: The value of the local variable to be stored, in the most general form of Object.
     * @param type: The type of object to be stored.
     */
    void setLocalVariable(String key, Object value, VariableType type);

    /**
     * Remove the local variable, either deleting the local variable if it is not set in the global dictionary, or restore its value to the global value.
     *
     * @param key: A String representation of the name of the variable to be removed.
     */
    void removeLocalVariable(String key) throws InterpretationException;

    /**
     * Get the variable type of the local variable.
     *
     * @param key: The String name of the variable to query.
     */
    VariableType getLocalVariableType(String key) throws InterpretationException;

    /**
     * Get the value of the local variable in the most general form.
     *
     * @param key: The String name of the variable to query.
     */
    Object getLocalValueInGeneralForm(String key) throws InterpretationException;

    /**
     * Set the value of a variable in the StateMachine by taking in three parameters, identifying automatically what type the variable is.
     *
     * @param key: The String name of the variable.
     * @param value: The value of the variable to be stored in the Object format.
     * @param type: The type of the variable to be stored.
     */
    void setGlobalVariable(String key, Object value, VariableType type);

    /**
     * Get the type of the variable, either a double, an integer or a function.
     *
     * @param key
     * @return The type of the variable.
     */
    VariableType getGlobalVariableType(String key) throws InterpretationException;

    /**
     * Get the value of the variable as an Object from the aggregate map.
     *
     * @param key
     * @return An Object representation of the value of the variable.
     */
    Object getGlobalValueInGeneralForm(String key) throws InterpretationException;

    /**
     * Remove the key entry from the map.
     *
     * @param key
     */
    void removeVariable(String key) throws InterpretationException;

    /**
     * Clear all state variables in the state machine.
     */
    void resetState();

    /**
     * Present the internal storage of the StateMachine in a map format.
     * It is strongly suggested to make the returned map unmodifiable.
     *
     * @return A Map representation of the StateMachine.
     */
    Map<String, Object> listOfGlobalVariables();

    /**
     *  Allow any observers to register as an observer to this StateMachine.
     */
    void register(StateMachineObserver observer);

    /**
     * Returns true if the variable is already defined in the StateMachine and false otherwise.
     *
     * @param key: The String name of the variable to be queried.
     * @return A boolean value indicating whether the variable key is defined in the StateMachine.
     */
    boolean containsGlobalVariable(String key);

    /**
     * Returns true if the variable is defined in the local StateMachine and false otherwise.
     *
     * @param key: The String name of the local variable to be queried.
     * @return A boolean value indicating whether the variable key is defined in the local scope.
     */
    boolean containsLocalVariable(String key);

    /**
     *  Push notifications to observers whenever there's change within the StateMachine.
     */
    void pushAlarm();

    /**
     * Present the internal storage of the StateMachine in a list format, separated by newline.
     *
     * @return A String representation of the StateMachine.
     */
    String toString();

    /**
     * Look at the local variable and then the global variables for the queried variable.
     *
     * @return The value of the variable.
     * @param key
     */
    Object getValue(String key) throws InterpretationException;

    /**
     * Look at the local variable and then the global variables for the queried variable.
     *
     * @return The type of the variable.
     * @param key
     */
    VariableType getVariableType(String key) throws InterpretationException;
}
