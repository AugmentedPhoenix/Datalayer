import me.bjornvanwilligen.stormmc.datalayer.app.connector.DatabaseConnector;
import me.bjornvanwilligen.stormmc.datalayer.app.adapter.*;
import me.bjornvanwilligen.stormmc.datalayer.app.model.*;
import me.bjornvanwilligen.stormmc.datalayer.app.state.*;

public class TestMainClass {

    public static void main(String[] args){
        PlayerAdapter playerAdapter = new PlayerAdapter(DatabaseConnector.getInstance().getConnection());
        PlayerState playerState = new PlayerState(PlayerModel.class, playerAdapter);

        BaseTotemAdapter baseTotemAdapter = new BaseTotemAdapter(DatabaseConnector.getInstance().getConnection());
        BaseTotemState baseTotemState = new BaseTotemState(BaseTotemModel.class, baseTotemAdapter);

        HopperAdapter hopperAdapter = new HopperAdapter(DatabaseConnector.getInstance().getConnection());
        HopperState hopperState = new HopperState(HopperModel.class, hopperAdapter);

        BarrelAdapter barrelAdapter = new BarrelAdapter(DatabaseConnector.getInstance().getConnection());
        BarrelState barrelState = new BarrelState(BarrelModel.class, barrelAdapter);
    }
}
