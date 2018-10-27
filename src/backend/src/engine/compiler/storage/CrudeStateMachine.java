package engine.compiler.storage;

import engine.compiler.slogoast.Expression;
import engine.errors.InterpretationException;

import java.util.*;

/**
 * This StateMachine records variables that the parser can use. For example, it records "x = 3".
 *
 * @author Haotian Wang
 */
public class CrudeStateMachine implements StateMachine {
    private Map<String, String> typeMap;
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
    public boolean containsVariable(String key) {
        return aggregateMap.containsKey(key);
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
            String type = typeMap.get(key);
            if (type.equals("Integer")) {
                integerMap.remove(key);
            } else if (type.equals("String")) {
                stringMap.remove(key);
            } else if (type.equals("Function")) {
                functionMap.remove(key);
            }
        }
        doubleMap.put(key, value);
        aggregateMap.put(key, value);
        typeMap.put(key, "Double");
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
            String type = typeMap.get(key);
            if (type.equals("Double")) {
                doubleMap.remove(key);
            } else if (type.equals("String")) {
                stringMap.remove(key);
            } else if (type.equals("Function")) {
                functionMap.remove(key);
            }
        }
        integerMap.put(key, value);
        aggregateMap.put(key, value);
        typeMap.put(key, "Integer");
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
            String type = typeMap.get(key);
            if (type.equals("Double")) {
                doubleMap.remove(key);
            } else if (type.equals("Integer")) {
                integerMap.remove(key);
            } else if (type.equals("Function")) {
                functionMap.remove(key);
            }
        }
        stringMap.put(key, value);
        aggregateMap.put(key, value);
        typeMap.put(key, "Integer");
        pushAlarm();
    }

    /**
     * Set an Expression value for a variable.
     *
     * @param key
     * @param function
     */
    @Override
    public void setFunction(String key, Expression function) {
        if (typeMap.containsKey(key)) {
            String type = typeMap.get(key);
            if (type.equals("Double")) {
                doubleMap.remove(key);
            } else if (type.equals("Integer")) {
                integerMap.remove(key);
            } else if (type.equals("String")) {
                stringMap.remove(key);
            }
        }
        functionMap.put(key, function);
        aggregateMap.put(key, function);
        typeMap.put(key, "Integer");
        pushAlarm();
    }

    /**
     * Set the value of a variable in the StateMachine by taking in three parameters, identifying automatically what type the variable is.
     *
     * @param key   : The String name of the variable.
     * @param value : The value of the variable to be stored in the Object format.
     * @param type  : The String name of the type of the variable to be stored.
     */
    @Override
    public void setValue(String key, Object value, String type) throws InterpretationException {
        if (containsVariable(key)) {
            removeVariable(key);
        }
    }

    /**
     * Get the type of the variable, either a double, an integer or a function.
     *
     * @param key
     * @return A String representation of the type of the variable.
     */
    @Override
    public String getVariableType(String key) throws InterpretationException {
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
    public Object getValueleInGeneralForm(String key) throws InterpretationException {
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
        String type = typeMap.get(key);
        if (type.equals("Double")) {
            doubleMap.remove(key);
        } else if (type.equals("Integer")) {
            integerMap.remove(key);
        } else if (type.equals("Function")) {
            functionMap.remove(key);
        } else if (type.equals("String")) {
            stringMap.remove(key);
        } aggregateMap.remove(key);
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
    public Map<String, Object> listOfVariables() { return Collections.unmodifiableMap(aggregateMap); }

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
}
