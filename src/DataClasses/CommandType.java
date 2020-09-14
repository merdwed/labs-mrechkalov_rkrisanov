package DataClasses;

import java.io.Serializable;

public enum CommandType implements Serializable {
    ADD,
    ADD_IF_MAX,
    ADD_IF_MIN,
    SHOW,
    INFO,
    HELP,
    CLEAR,
    REMOVE_BY_ID,
    REMOVE_LOWER,
    UPDATE,
    EXECUTE_SCRIPT,
    EXIT;
    private static final long serialVersionUID = 12L;
}
