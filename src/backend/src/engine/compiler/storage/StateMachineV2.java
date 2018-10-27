package engine.compiler.storage;

import engine.compiler.slogoast.Expression;
import engine.errors.InterpretationException;

import java.util.*;

/**
 * This is the version 2 of the StateMachine implementation. It does not build a different map for a different type of variable. Instead it stores everything in two maps, the global and the local.
 *
 * @author Haotian Wang
 */
public class StateMachineV2 implements StateMachine{
    private Map<String, VariableType> globalTypeMap;
    private Map<String, Object> globalMap;
    private Map<String, VariableType> localTypeMap;
    private Map<String, Object> localMap;
    private List<StateMachineObserver> observers;

    public StateMachineV2() {
        globalTypeMap = new HashMap<>();
        globalMap = new HashMap<>();
        localTypeMap = new HashMap<>();
        localMap = new HashMap<>();
        observers = new ArrayList<>();
    }

    /**
     * Set a double value for a variable.
     *
     * @param key
     * @param value
     */
    @Override
    public void setDouble(String key, double value) {
        setVariable(key, new Double(value), VariableType.DOUBLE);
    }

    /**
     * Set an int value for a variable.
     *
     * @param key
     * @param value
     */
    @Override
    public void setInteger(String key, int value) {
        setVariable(key, new Integer(value), VariableType.INTEGER);
    }

    /**
     * Set a String value for a variable.
     *
     * @param key
     * @param value
     */
    @Override
    public void setString(String key, String value) {
        setVariable(key, value, VariableType.STRING);
    }

    /**
     * Set an Expression value for a variable.
     *
     * @param key
     * @param function
     */
    @Override
    public void setExpression(String key, Expression function) {
        setVariable(key, function, VariableType.EXPRESSION);
    }

    /**
     * Set a "local" variable that overwrites the global variable with the same name for the duration before the local variable is deleted.
     *
     * @param key   : A String representation of the name of the variable.
     * @param value : The value of the local variable to be stored, in the most general form of Object.
     * @param type  : The type of object to be stored.
     */
    @Override
    public void setLocalVariable(String key, Object value, VariableType type) {
        localMap.put(key, value);
        localTypeMap.put(key, type);
        pushAlarm();
    }

    /**
     * Remove the local variable, either deleting the local variable if it is not set in the global dictionary, or restore its value to the global value.
     *
     * @param key : A String representation of the name of the variable to be removed.
     */
    @Override
    public void removeLocalVariable(String key) throws InterpretationException {
        if (!localMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable \"%s\" is not defined in the local scope, therefore it cannot be removed", key));
        }
        localMap.remove(key);
        localTypeMap.remove(key);
        pushAlarm();
    }

    /**
     * Get the variable type of the local variable.
     *
     * @param key : The String name of the variable to query.
     */
    @Override
    public VariableType getLocalVariableType(String key) throws InterpretationException {
        if (!localTypeMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable \"%s\" is not defined in the local scope, therefore its type cannot be determined", key));
        }
        return localTypeMap.get(key);
    }

    /**
     * Get the value of the local variable in the most general form.
     *
     * @param key : The String name of the variable to query.
     */
    @Override
    public Object getLocalValueInGeneralForm(String key) throws InterpretationException {
        if (!localMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable \"%s\" is not defined in the local scope, therefore its value cannot be determined", key));
        }
        return localMap.get(key);
    }

    /**
     * Set the value of a variable in the StateMachine by taking in three parameters, identifying automatically what type the variable is.
     *
     * @param key   : The String name of the variable.
     * @param value : The value of the variable to be stored in the Object format.
     * @param type  : The type of the variable to be stored.
     */
    @Override
    public void setVariable(String key, Object value, VariableType type) {
        globalMap.put(key, value);
        globalTypeMap.put(key, type);
        pushAlarm();
    }

    /**
     * Get the type of the variable, either a double, an integer or a function.
     *
     * @param key
     * @return The type of the variable.
     */
    @Override
    public VariableType getVariableType(String key) throws InterpretationException {
        if (!globalTypeMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable \"%s\" is not defined in the global scope, therefore its type cannot be determined", key));
        }
        return globalTypeMap.get(key);
    }

    /**
     * Get the value of the variable as an Object from the aggregate map.
     *
     * @param key
     * @return An Object representation of the value of the variable.
     */
    @Override
    public Object getValueInGeneralForm(String key) throws InterpretationException {
        if (!globalMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable \"%s\" is not defined in the global scope, therefore its value cannot be determined", key));
        }
        return globalMap.get(key);
    }

    /**
     * Remove the key entry from the map.
     *
     * @param key
     */
    @Override
    public void removeVariable(String key) throws InterpretationException {
        if (!globalMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable \"%s\" is not defined in the global scope, therefore it cannot be removed",key));
        }
        globalMap.remove(key);
        globalTypeMap.remove(key);
        pushAlarm();
    }

    /**
     * Clear all state variables in the state machine.
     */
    @Override
    public void resetState() {
        globalMap.clear();
        globalTypeMap.clear();
        localMap.clear();
        localTypeMap.clear();
        pushAlarm();
    }

    /**
     * Present the internal storage of the StateMachine in a map format.
     * It is strongly suggested to make the returned map unmodifiable.
     *
     * @return A Map representation of the StateMachine.
     */
    @Override
    public Map<String, Object> listOfVariables() {
        return Collections.unmodifiableMap(globalMap);
    }

    /**
     * Allow any observers to register as an observer to this StateMachine.
     *
     * @param observer
     */
    @Override
    public void register(StateMachineObserver observer) {
        observers.add(observer);
    }

    /**
     * Returns true if the variable is already defined in the StateMachine and false otherwise.
     *
     * @param key : The String name of the variable to be queried.
     * @return A boolean value indicating whether the variable key is defined in the StateMachine.
     */
    @Override
    public boolean containsVariable(String key) {
        return globalMap.containsKey(key);
    }

    /**
     * Returns true if the variable is defined in the local StateMachine and false otherwise.
     *
     * @param key : The String name of the local variable to be queried.
     * @return A boolean value indicating whether the variable key is defined in the local scope.
     */
    @Override
    public boolean containsLocalVariable(String key) {
        return localMap.containsKey(key);
    }

    /**
     * Push notifications to observers whenever there's change within the StateMachine.
     */
    @Override
    public void pushAlarm() {
        observers.forEach(StateMachineObserver::notifyListener);
    }

    /**
     * Present the internal storage of the StateMachine in a list format, separated by newline.
     *
     * @return A String representation of the StateMachine.
     */
    @Override
    public String toString() {
        String ans = "The global variables are\n";
        for (Map.Entry entry : globalMap.entrySet()) {
            ans += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        ans += "The local variables are\n";
        for (Map.Entry entry : localMap.entrySet()) {
            ans += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        return ans;
    }
}
