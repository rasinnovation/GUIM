import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Notification {

    public static List<Inventory> getOutOfStockItems() {
        List<Inventory> outOfStockItems = new ArrayList<>();

        String SQL_loadAllItems = "SELECT * FROM "
                + DatabaseInfo.Tables.INVENTORY
                + " WHERE QUANTITY = 0;";

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(SQL_loadAllItems)
        ) {
            while (results.next()) {
                outOfStockItems.add(new Inventory(
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getInt(4),
                        results.getString(5),
                        results.getString(6)
                ));
            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving data from database");
        }

        return outOfStockItems;
    }
}
