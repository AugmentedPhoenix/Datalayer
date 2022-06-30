package me.bjornvanwilligen.stormmc.datalayer.app.adapter;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.app.model.PlayerModel;

import java.sql.Connection;
import java.util.UUID;

public class PlayerAdapter extends Adapter<UUID, PlayerModel> {

    public PlayerAdapter(Connection connection) {
        super(connection);
        this.tableName = "PLAYER";
    }

    @Override
    public PlayerModel selectModel(UUID key) {
        return null;
    }

    @Override
    public PlayerModel[] selectAllModels(UUID[] keys) {
        return new PlayerModel[0];
    }

    @Override
    public PlayerModel[] selectAllModels() {
        return new PlayerModel[0];
    }

    @Override
    public void insertModel(PlayerModel model) {

    }

    @Override
    public void updateModel(PlayerModel model) {

    }

    @Override
    public void deleteModel(PlayerModel model) {

    }
}
