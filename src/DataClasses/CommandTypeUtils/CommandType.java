package DataClasses.CommandTypeUtils;

import java.io.Serializable;
/**
 * all command which can execute on server. If you add new command, please add string name and full parameters name of this commandType to CommandTypeInformation
 */
public enum CommandType implements Serializable {
    ADD,
    ADD_IF_MAX,
    ADD_IF_MIN,
    SHOW,
    INFO,
    CLEAR,
    REMOVE_BY_ID,
    REMOVE_LOWER,
    FILTER_BY_PRICE,
    FILTER_LESS_BY_TYPE,
    PRINT_ASCENDING,
    UPDATE,
    EXIT;
    private static final long serialVersionUID = 12L;
}
