package me.bjornvanwilligen.stormmc.datalayer.app.state;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.api.state.State;
import me.bjornvanwilligen.stormmc.datalayer.app.model.BaseTotemModel;

import java.util.UUID;

public class BaseTotemState extends State<UUID, BaseTotemModel> {

    public BaseTotemState(Class<BaseTotemModel> modelClass, Adapter<UUID, BaseTotemModel> adapter) {
        super(modelClass, adapter);
    }

    @Override
    public BaseTotemModel getModel(UUID key) {
        return null;
    }

    @Override
    public void setModel(UUID key, BaseTotemModel value) {

    }

    @Override
    public void removeModel(UUID key) {

    }

    @Override
    public void removeModel(UUID[] keys) {

    }

    @Override
    public void loadModel(UUID key) {

    }

    @Override
    public void loadModels(UUID[] key) {

    }

    @Override
    public void loadModels() {

    }
}
