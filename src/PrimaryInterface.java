import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PrimaryInterface extends Application {

    public static void main(String[] args) {
        loadDriver();
        testDatabaseConnection();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrimaryFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setTitle("General Use Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("The driver was successfully loaded");
            Thread.sleep(1000);
        } catch (ClassNotFoundException | InterruptedException e) {
            System.out.println("The driver class was not found.");
            System.out.println(e);
            System.exit(1);
        }
    }

    public static void testDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL)) {
            System.out.println("The connection to the SQLite Database was successful.");
            Thread.sleep(1000);
        } catch (SQLException | InterruptedException e) {
            System.out.println("The connection to the SQLite Database was unsuccessful.");
            System.out.println(e);
        }
    }
}

