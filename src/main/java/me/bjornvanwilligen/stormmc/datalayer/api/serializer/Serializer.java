package me.bjornvanwilligen.stormmc.datalayer.api.serializer;

public abstract class Serializer<T> {

    public abstract String serializeObject(Object object);

    public abstract String serializeObjects(Object[] objects);

    public abstract T deserializeObject(String string);

    public abstract T[] deserializeObjects(String[] strings);
}
