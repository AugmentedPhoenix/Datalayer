package me.bjornvanwilligen.stormmc.datalayer.app.state;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.api.state.State;
import me.bjornvanwilligen.stormmc.datalayer.app.model.PlayerModel;

import java.util.UUID;

public class PlayerState extends State<UUID, PlayerModel> {

    public PlayerState(Class<PlayerModel> modelClass, Adapter<UUID, PlayerModel> adapter) {
        super(modelClass, adapter);
    }

    @Override
    public PlayerModel getModel(UUID key) {
        return null;
    }

    @Override
    public void setModel(UUID key, PlayerModel value) {

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
