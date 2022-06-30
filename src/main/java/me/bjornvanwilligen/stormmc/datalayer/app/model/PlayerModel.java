package me.bjornvanwilligen.stormmc.datalayer.app.model;

import me.bjornvanwilligen.stormmc.datalayer.api.model.Model;

import java.util.UUID;

public class PlayerModel implements Model {

    private UUID ID;
    private int kills;
    private int deaths;
    private int sem;

}
