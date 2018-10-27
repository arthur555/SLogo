package engine.compiler.storage;

import engine.compiler.slogoast.Expression;
import engine.compiler.slogoast.Variable;
import engine.errors.InterpretationException;

import java.util.*;

/**
 * This StateMachine records variables that the parser can use. For example, it records "x = 3".
 *
 * @author Haotian Wang
 */
public class CrudeStateMachine implements StateMachine {
    private Map<String, VariableType> typeMap;
    private Map<String, Double> doubleMap;
    private Map<String, Integer> integerMap;
    private Map<String, String> stringMap;
    private Map<String, Expression> functionMap;

    private Map<String, Object> aggregateMap;

    private List<StateMachineObserver> observers;

    public CrudeStateMachine() {
        typeMap = new HashMap<>();
        doubleMap = new HashMap<>();
        integerMap = new HashMap<>();
        stringMap = new HashMap<>();
        functionMap = new HashMap<>();
        aggregateMap = new HashMap<>();

        observers = new ArrayList<>();
    }

    public void register(StateMachineObserver observer) { observers.add(observer); }

    /**
     * Returns true if the variable is already defined in the StateMachine and false otherwise.
     *
     * @param key : The String name of the variable to be queried.
     * @return A boolean value indicating whether the variable key is defined in the StateMachine.
     */
    @Override
    public boolean containsGlobalVariable(String key) {
        return aggregateMap.containsKey(key);
    }

    /**
     * Returns true if the variable is defined in the local StateMachine and false otherwise.
     *
     * @param key : The String name of the local variable to be queried.
     * @return A boolean value indicating whether the variable key is defined in the local scope.
     */
    @Override
    public boolean containsLocalVariable(String key) {
        return false;
    }

    public void pushAlarm() { observers.forEach(StateMachineObserver::notifyListener);}

    /**
     * Set a double value for a variable.
     *
     * @param key
     * @param value
     */
    public void setDouble(String key, double value) {
        if (typeMap.containsKey(key)) {
            VariableType type = typeMap.get(key);
            if (type == VariableType.INTEGER) {
                integerMap.remove(key);
            } else if (type == VariableType.STRING) {
                stringMap.remove(key);
            } else if (type == VariableType.EXPRESSION) {
                functionMap.remove(key);
            }
        }
        doubleMap.put(key, value);
        aggregateMap.put(key, value);
        typeMap.put(key, VariableType.DOUBLE);
        pushAlarm();
    }

    /**
     * Set an int value for a variable.
     *
     * @param key
     * @param value
     */
    public void setInteger(String key, int value) {
        if (typeMap.containsKey(key)) {
            VariableType type = typeMap.get(key);
            if (type == VariableType.DOUBLE) {
                doubleMap.remove(key);
            } else if (type == VariableType.STRING) {
                stringMap.remove(key);
            } else if (type == VariableType.EXPRESSION) {
                functionMap.remove(key);
            }
        }
        integerMap.put(key, value);
        aggregateMap.put(key, value);
        typeMap.put(key, VariableType.INTEGER);
        pushAlarm();
    }

    /**
     * Set a String value for a variable.
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        if (typeMap.containsKey(key)) {
            VariableType type = typeMap.get(key);
            if (type == VariableType.DOUBLE) {
                doubleMap.remove(key);
            } else if (type == VariableType.INTEGER) {
                integerMap.remove(key);
            } else if (type == VariableType.EXPRESSION) {
                functionMap.remove(key);
            }
        }
        stringMap.put(key, value);
        aggregateMap.put(key, value);
        typeMap.put(key, VariableType.INTEGER);
        pushAlarm();
    }

    /**
     * Set an Expression value for a variable.
     *
     * @param key
     * @param function
     */
    @Override
    public void setExpression(String key, Expression function) {
        if (typeMap.containsKey(key)) {
            VariableType type = typeMap.get(key);
            if (type == VariableType.DOUBLE) {
                doubleMap.remove(key);
            } else if (type == VariableType.INTEGER) {
                integerMap.remove(key);
            } else if (type == VariableType.STRING) {
                stringMap.remove(key);
            }
        }
        functionMap.put(key, function);
        aggregateMap.put(key, function);
        typeMap.put(key, VariableType.EXPRESSION);
        pushAlarm();
    }

    /**
     * Set a local integer value for a key.
     *
     * @param key
     * @param value
     */
    @Override
    public void setLocalInteger(String key, int value) {

    }

    /**
     * Set a local double value for a key.
     *
     * @param key
     * @param value
     */
    @Override
    public void setLocalDouble(String key, double value) {

    }

    /**
     * Set a local String value for a key.
     *
     * @param key
     * @param value
     */
    @Override
    public void setLocalString(String key, String value) {

    }

    /**
     * Set an Expression value for a key.
     *
     * @param key
     * @param value
     */
    @Override
    public void setLocalExpression(String key, Expression value) {

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

    }

    /**
     * Remove the local variable, either deleting the local variable if it is not set in the global dictionary, or restore its value to the global value.
     *
     * @param key : A String representation of the name of the variable to be removed.
     */
    @Override
    public void removeLocalVariable(String key) {

    }

    /**
     * Get the variable type of the local variable.
     *
     * @param key : The String name of the variable to query.
     */
    @Override
    public VariableType getLocalVariableType(String key) {
        return null;
    }

    /**
     * Get the value of the local variable in the most general form.
     *
     * @param key : The String name of the variable to query.
     */
    @Override
    public Object getLocalValueInGeneralForm(String key) {
        return null;
    }

    /**
     * Set the value of a variable in the StateMachine by taking in three parameters, identifying automatically what type the variable is.
     *
     * @param key   : The String name of the variable.
     * @param value : The value of the variable to be stored in the Object format.
     * @param type  : The type of the variable to be stored.
     */
    @Override
    public void setGlobalVariable(String key, Object value, VariableType type) {
        if (type == VariableType.INTEGER) {
            setInteger(key, (int) value);
        } else if (type == VariableType.DOUBLE) {
            setDouble(key, (double) value);
        } else if (type == VariableType.STRING) {
            setString(key, (String) value);
        } else if (type == VariableType.EXPRESSION) {
            setExpression(key, (Expression) value);
        }
    }

    /**
     * Get the type of the variable, either a double, an integer or a function.
     *
     * @param key
     * @return A String representation of the type of the variable.
     */
    @Override
    public VariableType getGlobalVariableType(String key) throws InterpretationException {
        if (!typeMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable %s is not defined, therefore its type cannot be determined", key));
        }
        return typeMap.get(key);
    }

    /**
     * Get the value of the variable as an Object from the aggregate map.
     *
     * @param key
     * @return An Object representation of the value of the variable.
     */
    @Override
    public Object getGlobalValueInGeneralForm(String key) throws InterpretationException {
        if (!aggregateMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable %s is not defined, therefore its value cannot be returned", key));
        }
        return aggregateMap.get(key);
    }

    /**
     * Remove the key entry from the map.
     *
     * @param key
     */
    public void removeVariable(String key) throws InterpretationException {
        if (!typeMap.containsKey(key)) {
            throw new InterpretationException(String.format("The variable %s is not defined, therefore cannot be removed", key));
        }
        VariableType type = typeMap.get(key);
        if (type == VariableType.DOUBLE) {
            doubleMap.remove(key);
        } else if (type == VariableType.INTEGER) {
            integerMap.remove(key);
        } else if (type == VariableType.EXPRESSION) {
            functionMap.remove(key);
        } else if (type == VariableType.STRING) {
            stringMap.remove(key);
        }
        aggregateMap.remove(key);
        pushAlarm();
    }

    /**
     * Clear all state variables in the state machine.
     */
    public void resetState() {
        typeMap.clear();
        integerMap.clear();
        doubleMap.clear();
        functionMap.clear();
        stringMap.clear();
        aggregateMap.clear();
        pushAlarm();
    }

    @Override
    public Map<String, Object> listOfGlobalVariables() { return Collections.unmodifiableMap(aggregateMap); }

    /**
     * Present the internal storage of the StateMachine in a list format, separated by newline.
     *
     * @return A String representation of the StateMachine.
     */
    @Override
    public String toString() {
        String ans = "";
        for (Map.Entry entry : integerMap.entrySet()) {
            ans += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        for (Map.Entry entry : doubleMap.entrySet()) {
            ans += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        for (Map.Entry entry : stringMap.entrySet()) {
            ans += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        for (Map.Entry entry : functionMap.entrySet()) {
            ans += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        return ans;
    }

    /**
     * Look at the local variable and then the global variables for the queried variable.
     *
     * @return The value of the variable.
     * @param key
     */
    @Override
    public Object getValue(String key) {
        return null;
    }

    /**
     * Look at the local variable and then the global variables for the queried variable.
     *
     * @return The type of the variable.
     * @param key
     */
    @Override
    public VariableType getVariableType(String key) {
        return null;
    }
}
