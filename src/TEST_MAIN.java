import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
    Placeholder for the Javafx main file
    This File is only meant to test my end in the mean time,
    O
    Will scrap it once the gui is implemented
 */
public class TEST_MAIN extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        loadDriver();
        testDatabaseConnection();

        // Uncomment the following if there is no db file
//        createTable();


        // This should be uncommented when there is the gui
//        launch(args);

        // FOR TESTING PURPOSES
        System.exit(0);
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

    // TODO If there is a login, add statement for userlogin to create credential database
    // TODO UPDATE the table composition depending on GUI.
    /*
        CREATE TABLE INVENTORY (
            ID INTEGER PRIMARY KEY,
            NAME TEXT,
            NOTES TEXT,
            QUANTITY INTEGER,
            EXPIRATION TEXT,
            CATEGORY TEXT
        );
     */
    public static void createTable() {
        String createTableStatement = "CREATE TABLE "
                + DatabaseInfo.Tables.INVENTORY + " ("
                + "ID INTEGER PRIMARY KEY,"
                + " NAME TEXT,"
                + " NOTES TEXT,"
                + " QUANTITY INTEGER,"
                + " EXPIRATION TEXT,"
                + " CATEGORY TEXT"
                + ");";
        // TODO login statement may go here such as String createUserCredential table
        // Would have to then add enum to DatabaseInfo if that will go through.

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createTableStatement);
            System.out.println("The inventory table has been created.");
            Thread.sleep(1000);
        } catch (SQLException | InterruptedException e) {
            System.out.println("There was an error creating table.");
            System.out.println(e);
        }
    }
}
