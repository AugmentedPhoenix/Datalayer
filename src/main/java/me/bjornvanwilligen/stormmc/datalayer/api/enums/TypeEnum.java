package me.bjornvanwilligen.stormmc.datalayer.api.enums;

import me.bjornvanwilligen.stormmc.datalayer.api.exception.TypeNotSupportedException;

import java.util.UUID;

public enum TypeEnum {
    STRING(String.class, "LONGTEXT"),
    UUID(java.util.UUID.class, "VARCHAR(128)"),
    INT(int.class, "INT"),
    HOOK(null, null);

    private final Class<?> clazz;
    private final String sqlReplacement;

    TypeEnum(Class<?> clazz, String sqlReplacement) {
        this.clazz = clazz;
        this.sqlReplacement = sqlReplacement;
    }

    public String getSQLFromClass(Class<?> clazz){
        for(TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.clazz == clazz){
                return typeEnum.sqlReplacement;
            }
        }
        throw new TypeNotSupportedException(clazz);
    }

    public Class<?> getClassFromSQL(String sqlReplacement){
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.sqlReplacement == sqlReplacement) {
                return clazz;
            }
        }
        throw new TypeNotSupportedException(sqlReplacement);
    }
}
