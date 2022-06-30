package me.bjornvanwilligen.stormmc.datalayer.app.adapter;

import me.bjornvanwilligen.stormmc.datalayer.api.adapter.Adapter;
import me.bjornvanwilligen.stormmc.datalayer.app.model.BaseTotemModel;

import java.sql.Connection;
import java.util.UUID;

public class BaseTotemAdapter extends Adapter<UUID, BaseTotemModel> {

    public BaseTotemAdapter(Connection connection) {
        super(connection);
        this.tableName = "BASE_TOTEM";
    }

    @Override
    public BaseTotemModel selectModel(UUID key) {
        return null;
    }

    @Override
    public BaseTotemModel[] selectAllModels(UUID[] keys) {
        return new BaseTotemModel[0];
    }

    @Override
    public BaseTotemModel[] selectAllModels() {
        return new BaseTotemModel[0];
    }

    @Override
    public void insertModel(BaseTotemModel model) {

    }

    @Override
    public void updateModel(BaseTotemModel model) {

    }

    @Override
    public void deleteModel(BaseTotemModel model) {

    }
}
