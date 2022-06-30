package me.bjornvanwilligen.stormmc.datalayer.app.model;

import me.bjornvanwilligen.stormmc.datalayer.api.annotations.NotNull;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.PrimaryKey;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.Serialize;
import me.bjornvanwilligen.stormmc.datalayer.api.model.Model;

import java.util.UUID;

public class HopperModel implements Model {
    @PrimaryKey
    @NotNull
    private UUID ID;
    @NotNull
    private int X;
    @NotNull
    private int Y;
    @NotNull
    private int Z;
    @NotNull
    private UUID worldID;
    @NotNull
    private UUID ownerID;
    @NotNull
    private UUID baseTotemID;
}
