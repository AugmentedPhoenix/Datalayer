package me.bjornvanwilligen.stormmc.datalayer.api.adapter;

import me.bjornvanwilligen.stormmc.datalayer.api.enums.TypeEnum;
import me.bjornvanwilligen.stormmc.datalayer.api.model.Model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Adapter<K, M extends Model> {

    private Class<M> modelClass;
    private Connection connection;

    public String tableName = null;

    public Adapter(Connection connection){
        this.connection = connection;
    }

    private void checkTable() {
        if (tableName == null) {
            String[] nameArray = modelClass.getName().toUpperCase().split("\\.");
            tableName = nameArray[nameArray.length - 1];
        }

        //Generate list of all the columns that should be in the table String = ColumnName, String = SQLtype
        HashMap<String, String> columns = new HashMap<>();
        for (Field f : this.modelClass.getDeclaredFields()) {
            String[] fNameSplit = f.getName().toUpperCase().split("\\.");
            columns.put(fNameSplit[fNameSplit.length - 1], TypeEnum.HOOK.getSQLFromClass(f.getType()));
        }

        //generate and update table
        String checkTableQuery = "SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA LIKE ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(checkTableQuery)) {
            statement.setString(1, tableName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            //creat table
            if (resultSet.getInt("COUNT(TABLE_NAME)") == 0) {

                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("CREATE TABLE " + tableName + "(\n");
                columns.forEach((columnName, sql) -> {
                    queryBuilder.append(columnName + " ")
                });
                queryBuilder.append(");");

                try (PreparedStatement statement1 = this.connection.prepareStatement(queryBuilder.toString())) {
                    int result = statement1.executeUpdate();
                    System.out.println("Creating table: " + tableName + " || Result: " + result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            //Update table
            } else {

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void init() {
        this.checkTable();
    }

    public void registerModelClass(Class<M> modelClass) {
        this.modelClass = modelClass;
    }
    public abstract M selectModel(K key);

    public abstract M[] selectAllModels(K[] keys);

    public abstract M[] selectAllModels();

    public abstract void insertModel(M model);

    public abstract void updateModel(M model);

    public abstract void deleteModel(M model);
}
