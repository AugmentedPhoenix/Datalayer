package me.bjornvanwilligen.stormmc.datalayer.app.adapter;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.app.model.BarrelModel;

import java.sql.Connection;
import java.util.UUID;

public class BarrelAdapter extends Adapter<UUID, BarrelModel> {

    public BarrelAdapter(Connection connection) {
        super(connection);
        this.tableName = "BARREL";
    }

    @Override
    public BarrelModel selectModel(UUID key) {
        return null;
    }

    @Override
    public BarrelModel[] selectAllModels(UUID[] keys) {
        return new BarrelModel[0];
    }

    @Override
    public BarrelModel[] selectAllModels() {
        return new BarrelModel[0];
    }

    @Override
    public void insertModel(BarrelModel model) {

    }

    @Override
    public void updateModel(BarrelModel model) {

    }

    @Override
    public void deleteModel(BarrelModel model) {

    }
}
