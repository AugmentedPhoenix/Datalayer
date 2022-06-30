import me.bjornvanwilligen.stormmc.datalayer.app.adapter.PlayerAdapter;
import me.bjornvanwilligen.stormmc.datalayer.app.connector.DatabaseConnector;
import me.bjornvanwilligen.stormmc.datalayer.app.model.PlayerModel;
import me.bjornvanwilligen.stormmc.datalayer.app.state.PlayerState;

public class TestMainClass {

    public static void main(String[] args){
        PlayerAdapter playerAdapter = new PlayerAdapter(DatabaseConnector.getInstance().getConnection());
        PlayerState playerState = new PlayerState(PlayerModel.class, playerAdapter);
    }
}
