package me.bjornvanwilligen.stormmc.datalayer.app.model;

import me.bjornvanwilligen.stormmc.datalayer.api.annotations.Default;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.NotNull;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.PrimaryKey;
import me.bjornvanwilligen.stormmc.datalayer.api.model.Model;

import java.util.UUID;

public class PlayerModel implements Model {

    @PrimaryKey
    @NotNull
    private UUID ID;
    @NotNull
    @Default(defaultValue = 0)
    private int kills;
    @NotNull
    @Default(defaultValue = 0)
    private int deaths;
    @NotNull
    @Default(defaultValue = 0)
    private int sem;
    /*
    @Serialize(serializer = ItemStackSerializer.class)
    @NotNull
    private ArrayList<ItemStack> content;
     */
}
