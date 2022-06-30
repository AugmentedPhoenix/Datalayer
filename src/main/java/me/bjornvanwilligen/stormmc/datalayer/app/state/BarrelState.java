package me.bjornvanwilligen.stormmc.datalayer.app.state;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.api.state.State;
import me.bjornvanwilligen.stormmc.datalayer.app.model.BarrelModel;

import java.util.UUID;

public class BarrelState extends State<UUID, BarrelModel> {

    public BarrelState(Class<BarrelModel> modelClass, Adapter<UUID, BarrelModel> adapter) {
        super(modelClass, adapter);
    }

    @Override
    public BarrelModel getModel(UUID key) {
        return null;
    }

    @Override
    public void setModel(UUID key, BarrelModel value) {

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
