package todoapp.utils;

import java.util.HashMap;
import java.util.Map;
import todoapp.dto.User;

/**
 *
 * @author jkawczynski
 */
public class Session {

    private Map<String, Object> sessionValues;

    private static Session INSTANCE;

    public Session() {
        this.sessionValues = new HashMap<>();
    }

    public static synchronized Session getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Session();
        }
        return INSTANCE;
    }

    public void putValue(String key, Object val) {
        sessionValues.put(key, val);
    }

    public Object getValue(String key) {
        return sessionValues.get(key);
    }

    public void clearValue(String key) {
        sessionValues.remove(key);
    }

    public <T> Object getValue(String key, Class<T> clazz) {
        Object value = getValue(key);
        if (value != null) {
            return (T) value;
        }
        return value;
    }

    public Object getUser() {
        return getValue("user", User.class);
    }

    public void clearSession() {
        sessionValues = new HashMap<>();
    }

}
