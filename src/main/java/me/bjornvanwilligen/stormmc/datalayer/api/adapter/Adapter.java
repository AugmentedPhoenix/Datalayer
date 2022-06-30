package me.bjornvanwilligen.stormmc.datalayer.api.adapter;

import me.bjornvanwilligen.stormmc.datalayer.api.annotations.Default;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.NotNull;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.PrimaryKey;
import me.bjornvanwilligen.stormmc.datalayer.api.annotations.Unique;
import me.bjornvanwilligen.stormmc.datalayer.api.enums.TypeEnum;
import me.bjornvanwilligen.stormmc.datalayer.api.model.Model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
       /*
        HashMap<String, Field> columns = new HashMap<>();
        for (Field f : this.modelClass.getDeclaredFields()) {
            System.out.println(f);
            String[] fNameSplit = f.getName().toUpperCase().split("\\.");
            columns.put(fNameSplit[fNameSplit.length - 1], f);
        }
        */

        //generate and update table
        String checkTableQuery = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(checkTableQuery)) {
            statement.setString(1, tableName);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            //creat table
            Field[] fields = this.modelClass.getDeclaredFields();
            int result = resultSet.getInt("COUNT(*)");
            System.out.println("Checking if table exists: " + tableName + "  ||  Result: " + result);
            if (result < 1) {

                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("CREATE TABLE ").append(tableName).append("(\n");

                Field field = fields[0];
                String[] fNameSplit = field.getName().toUpperCase().split("\\.");
                String columnName = fNameSplit[fNameSplit.length - 1];

                System.out.println("Columnname: " + columnName + " | | Field: " + field );

                queryBuilder.append(columnName).append(" ").append(TypeEnum.HOOK.getSQLFromClass(field.getType())).append(" ");
                queryBuilder.append(field.getAnnotationsByType(Default.class).length >= 1? "DEFAULT " + field.getAnnotationsByType(Default.class)[0].defaultValue() : "");
                queryBuilder.append(field.getAnnotationsByType(NotNull.class).length >= 1? "NOT NULL" : "");

                if (field.getAnnotationsByType(PrimaryKey.class).length >= 1) {
                    queryBuilder.append(",\nCONSTRAINT ").append(tableName).append("_PK\n");
                    queryBuilder.append("PRIMARY KEY (").append(columnName).append(")");
                }

                queryBuilder.append(");");

                if (field.getAnnotationsByType(Unique.class).length >= 1) {
                    queryBuilder.append("CREATE UNIQUE INDEX ").append(tableName).append("_").append(columnName).append("_UINDEX\n");
                    queryBuilder.append("ON ").append(tableName).append("(").append(columnName).append(");");
                }

                try (PreparedStatement statement1 = this.connection.prepareStatement(queryBuilder.toString())) {
                    result = statement1.executeUpdate();
                    System.out.println("Creating table: " + tableName + "  ||  Result: " + result + "\n||Query||\n" + queryBuilder);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //Update table by scanning through the fields except the first field
            for (int i = 1; i < fields.length; i++) {
                StringBuilder queryBuilder = new StringBuilder();
                Field field = fields[i];
                String[] fNameSplit = field.getName().toUpperCase().split("\\.");
                String columnName = fNameSplit[fNameSplit.length - 1];

                String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?;";
                try (PreparedStatement statement1 = this.connection.prepareStatement(query)) {

                    statement1.setString(1, tableName);
                    statement1.setString(2, columnName);
                    ResultSet resultSet1 = statement1.executeQuery();
                    resultSet1.next();
                    result = resultSet1.getInt("COUNT(*)");

                    if(result < 1) {

                        System.out.println("Columnname: " + columnName + " | | Field: " + field );

                        queryBuilder.append("ALTER TABLE ").append(tableName).append("\n");
                        queryBuilder.append("ADD ").append(columnName).append(" ").append(TypeEnum.HOOK.getSQLFromClass(field.getType())).append(" ");
                        queryBuilder.append(field.getAnnotationsByType(Default.class).length >= 1? "DEFAULT " + field.getAnnotationsByType(Default.class)[0].defaultValue() + " " : "");
                        queryBuilder.append(field.getAnnotationsByType(NotNull.class).length >= 1? "NOT NULL" : "");

                        if (field.getAnnotationsByType(PrimaryKey.class).length >= 1) {
                            query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_TYPE = 'PRIMARY KEY' AND TABLE_NAME = ?;";

                            try (PreparedStatement statement2 = this.connection.prepareStatement(query)) {
                                statement1.setString(1, tableName);
                                resultSet = statement1.executeQuery();
                                resultSet.next();
                                result = resultSet.getInt("COUNT(*)");
                                if(result < 1) {
                                    queryBuilder.append(",\nCONSTRAINT ").append(tableName).append("_PK\n");
                                    queryBuilder.append("PRIMARY KEY (").append(columnName).append(")");
                                }
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }

                        queryBuilder.append(";");

                        if (field.getAnnotationsByType(Unique.class).length >= 1) {
                            queryBuilder.append("CREATE UNIQUE INDEX ").append(tableName).append("_").append(columnName).append("_UINDEX\n");
                            queryBuilder.append("ON ").append(tableName).append("(").append(columnName).append(");");
                        }

                        System.out.println("Creating column: " + tableName + "  ||  Result: " + result + "\n||Query||\n" + queryBuilder);
                        try (PreparedStatement statement2 = this.connection.prepareStatement(queryBuilder.toString())) {
                            result = statement2.executeUpdate();
                            System.out.println("Creating column: " + tableName + "  ||  Result: " + result + "\n||Query||\n" + queryBuilder);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
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
