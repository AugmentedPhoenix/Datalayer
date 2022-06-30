package me.bjornvanwilligen.stormmc.datalayer.app.state;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.api.state.State;
import me.bjornvanwilligen.stormmc.datalayer.app.model.HopperModel;

import java.util.UUID;

public class HopperState extends State<UUID, HopperModel> {

    public HopperState(Class<HopperModel> modelClass, Adapter<UUID, HopperModel> adapter) {
        super(modelClass, adapter);
    }

    @Override
    public HopperModel getModel(UUID key) {
        return null;
    }

    @Override
    public void setModel(UUID key, HopperModel value) {

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
