import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    public static void checkStock() {
        List<Inventory> inventories = getOutOfStockItems();
        if (!getOutOfStockItems().isEmpty()) {
            displayOutofStockAlert(inventories);
        }
    }

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

    public static void displayOutofStockAlert(List<Inventory> items) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Out of Stock Alert!");
        window.setMinWidth(250);

        Label label = null;

        for (Inventory inventory : items) {
            label = new Label();
            label.setText("ID: " + inventory.getITEM_ID()
                    + " " + inventory.getName()
                    + " is out of stock"
            );
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
