import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void createAccountButtonPressed(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            User user = new User(username, password);
            UserService.createUser(user, DatabaseInfo.Tables.USERS.label);
        }
    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException {
        // Test code
        String userName = usernameTextField.getText();
        String password = passwordField.getText();

        if (UserService.verifyLogin(userName, password)) {
            User user = new User(userName, password);
            AuthService.login(user);
            loginUserIn(event);
        }

    }

    private void loginUserIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PrimaryFX.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}