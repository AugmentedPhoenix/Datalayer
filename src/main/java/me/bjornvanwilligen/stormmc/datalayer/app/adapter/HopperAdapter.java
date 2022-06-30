package me.bjornvanwilligen.stormmc.datalayer.app.adapter;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.app.model.HopperModel;

import java.sql.Connection;
import java.util.UUID;

public class HopperAdapter extends Adapter<UUID, HopperModel> {

    public HopperAdapter(Connection connection) {
        super(connection);
        this.tableName = "HOPPER";
    }

    @Override
    public HopperModel selectModel(UUID key) {
        return null;
    }

    @Override
    public HopperModel[] selectAllModels(UUID[] keys) {
        return new HopperModel[0];
    }

    @Override
    public HopperModel[] selectAllModels() {
        return new HopperModel[0];
    }

    @Override
    public void insertModel(HopperModel model) {

    }

    @Override
    public void updateModel(HopperModel model) {

    }

    @Override
    public void deleteModel(HopperModel model) {

    }
}
