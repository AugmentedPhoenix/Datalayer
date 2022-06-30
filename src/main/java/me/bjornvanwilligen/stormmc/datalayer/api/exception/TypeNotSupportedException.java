package me.bjornvanwilligen.stormmc.datalayer.api.exception;

import me.bjornvanwilligen.stormmc.datalayer.api.enums.TypeEnum;

public class TypeNotSupportedException extends RuntimeException {
    public TypeNotSupportedException(Class<?> type){
        super("Variable with the type: " + type.toString() + " cant be found in " + TypeEnum.class);
    }

    public TypeNotSupportedException(String sqlType) {
        super("SQL with the type: " + sqlType + " cant be found in " + TypeEnum.class);
    }
}
