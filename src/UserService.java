import java.sql.*;
import java.util.UUID;

public class UserService {
    public static boolean verifyLogin(String username, String password) {
        boolean isUserValid = false;
        User user = getUserByUsername(username);

        if (user == null) {
            return false;
        }

        if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
            isUserValid = true;
        }

        return  isUserValid;
    }
    public static void createUser(User user, String table) {

        String SQL_insertUser = "INSERT INTO "
                + table + " VALUES (\""
                + user.getIdString()
                + "\", \""
                + user.getUsername()
                + "\", \"" +
                user.getPassword() +
                "\");";

        System.out.println(SQL_insertUser);

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(SQL_insertUser);
        } catch (SQLException e) {
            System.out.println("Error occurred when saving account to the database");
        }
    }

    public static User getUserByUsername(String username) {
        String SQL_userByUsername = "SELECT * FROM "
                + DatabaseInfo.Tables.USERS
                + " WHERE USERNAME=\"" + username + "\";";

        User user = null;

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_userByUsername)
        ) {
            if (resultSet.next()) {
                user = new User(UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong finding the user in the database");
        }

        return user;
    }

    public static User getUserByID(String id) {
        String SQL_userById = "SELECT * FROM "
                + DatabaseInfo.Tables.USERS
                + " WHERE ID=\""
                + id + "\";";

        User user = null;

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_userById)
        ) {
            if (resultSet.next()) {
                user = new User(UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong finding the user in the database.");
        }

        return user;
    }

    public static User getLoggedInUser() {
        String SQL_loadFromCurrentUserTable = "SELECT * FROM "
                + DatabaseInfo.Tables.CURRENT_USER + ";";

        User user = null;

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_loadFromCurrentUserTable)
        ) {
            if (resultSet.next()) {
                user = new User(UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3));

                int size = 1;

                while (resultSet.next()) {
                    size++;
                }
                if (size > 1) {
                    user = null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong loading the logged in user.");
        }

        return user;
    }

    public static void deleteUser(User user) {
        String SQL_deleteUsers = "DELETE FROM "
                + DatabaseInfo.Tables.USERS
                +  " WHERE ID = \""
                + user.getIdString() + "\";";

        try (
                Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(SQL_deleteUsers);
        } catch (SQLException e) {
            System.out.println("Something went wrong deleting the current " +
                    "user's profile");
        }
    }
}
