package me.bjornvanwilligen.stormmc.datalayer.api.annotations;

import me.bjornvanwilligen.stormmc.datalayer.api.serializer.Serializer;

public @interface Serialize {
    Class<? extends Serializer<?>> serializer();
}
