package me.bjornvanwilligen.stormmc.datalayer.api.state;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.api.model.Model;

import java.util.HashMap;

public abstract class State<K, M extends Model> {

    public Adapter<K, M> adapter;

    public HashMap<K, M> state = new HashMap<>();

    public State(Class<M> modelClass, Adapter<K, M> adapter) {
        this.adapter = adapter;
        this.adapter.registerModelClass(modelClass);
        adapter.init();
    }

    public abstract M getModel(K key);

    public abstract void setModel(K key, M value);

    public abstract void removeModel(K key);

    public abstract void removeModel(K[] keys);

    public abstract void loadModel(K key);

    public abstract void loadModels(K[] key);

    public abstract void loadModels();
}
