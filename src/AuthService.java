import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthService {
    public static void login(User user) {
        AuthService.logout();
        UserService.createUser(user, DatabaseInfo.Tables.CURRENT_USER.label);
    }

    public static User loadFromCurrentUserTable() {
        return UserService.getLoggedInUser();
    }

    public static User logout() {
        String SQL_clearCurrentUserTable = "DELETE FROM "
                + DatabaseInfo.Tables.CURRENT_USER + ";";

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(SQL_clearCurrentUserTable);
        } catch (SQLException e) {
            System.out.println("Something went wrong clearing the current " +
                    "user information");
        }

        return null;
    }
}
