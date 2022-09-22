import java.sql.*;

public class InventoryService {

    public void addInventoryItem(Inventory item) {
        String SQL_addItem = "INSERT INTO "
                + DatabaseInfo.Tables.INVENTORY + " VALUES ("
                + "\"" + item.getITEM_ID() + "\", "
                + "\"" + item.getName() + "\", "
                + "\"" + item.getNotes() + "\", "
                + "\"" + item.getQuantity() + "\", "
                + "\"" + item.getExpiration() + "\", "
                + item.getCategory() + ");";


        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                // By convention, the last statement within a try-with-resources block
                // has no semicolon. So the following statement is intended.
                Statement statement = connection.createStatement()
        ) {
            // This statement will add item to database
            statement.executeUpdate(SQL_addItem);
        } catch (SQLException e) {
            System.out.println("There was an error saving the inventory item " +
                    "to the database");
        }
    }

    // TODO update inventory
    /*
        statement to update
        UPDATE table_name
        SET column1 = value1, column2 = value2, ...
        WHERE condition;      (Example WHERE ID = 1;)
     */
    public void updateInventoryItem(Inventory item) {
        String SQL_updateItem = "UPDATE "
                + DatabaseInfo.Tables.INVENTORY + " SET "
                + "ID = " + item.getITEM_ID() + ", "
                + "TEXT = " + item.getName() + ", "
                + "NOTES = " + item.getNotes() + ", "
                + "QUANTITY = " + item.getQuantity() + ", "
                + "EXPIRATION = " + item.getExpiration() + ", "
                + "CATEGORY = " + item.getCategory() + " "
                + "WHERE ID = " + item.getITEM_ID() + ";";

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement()
        ) {
            // This statement will update item in database
            statement.executeUpdate(SQL_updateItem);
        } catch (SQLException e) {
            System.out.println("There was an error updating the inventory item " +
                    "to the database");
        }
    }

    // TODO remove inventory
    /*
        DELETE FROM table_name
        WHERE condition;  (Example WHERE ID = 1;)
     */
    public void removeInventoryItem(Inventory item) {
        String SQL_removeItem = "DELETE FROM "
                + DatabaseInfo.Tables.INVENTORY
                + " WHERE ID = " + item.getITEM_ID() + ";";

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement()
        ) {
            // This statement will remove item in database
            statement.executeUpdate(SQL_removeItem);
        } catch (SQLException e) {
            System.out.println("There was an error removing the inventory item " +
                    "from the database");
        }
    }

    // On this return check to see if the result is NULL
    // If null, the item was never found.
    public Inventory getItemByName(String name) {
        String SQL_getItemByName = "SELECT * FROM "
                + DatabaseInfo.Tables.INVENTORY + " "
                + "WHERE NAME = \"" + name + "\";";

        Inventory inventory = null;

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(SQL_getItemByName)
        ) {
            if (results.next()) {
                inventory = new Inventory(
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getInt(4),
                        results.getString(5),
                        results.getString(6)
                );
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong finding the inventory in " +
                    "the database.");
        }

        return inventory;
    }
}
