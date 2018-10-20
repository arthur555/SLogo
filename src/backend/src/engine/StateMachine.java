package engine;

import java.util.HashMap;
import java.util.Map;

/**
 * This StateMachine records variables that the parser can use. For example, it records "x = 3".
 *
 * @author Haotian Wang
 */
public class StateMachine {
    private Map<String, Object> myMap;

    public StateMachine() {
        myMap = new HashMap<>();
    }

    /**
     * Set a double value for a variable.
     *
     * @param key
     * @param value
     */
    public void setDouble(String key, double value) {
        myMap.put(key, value);
    }

    /**
     * Set an int value for a variable.
     *
     * @param key
     * @param value
     */
    public void setInteger(String key, int value) {
        myMap.put(key, value);
    }

    /**
     * Set a String value for a variable.
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        myMap.put(key, value);
    }

    /**
     * Remove the key entry from the map.
     *
     * @param key
     */
    public void removeKey(String key) {
        myMap.remove(key);
    }

    /**
     * Clear all state variables in the state machine.
     */
    public void resetState() {
        myMap.clear();
    }
}
