import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.jfr.Category;

import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {


    @javafx.fxml.FXML
    private MenuItem MI_FileSA;
    @javafx.fxml.FXML
    private MenuItem MI_FileN;
    @javafx.fxml.FXML
    private MenuButton AddButton;
    @javafx.fxml.FXML
    private MenuItem MI_AddItem;
    @javafx.fxml.FXML
    private MenuItem MI_FileS;
    @javafx.fxml.FXML
    private MenuItem MI_RemoveItem;
    @javafx.fxml.FXML
    private MenuButton FileButton;
    @javafx.fxml.FXML
    private MenuItem MI_AddNotify;

    private UserService userService;
    private InventoryService inventoryService;
    @javafx.fxml.FXML
    private MenuButton UpdateButton;
    @javafx.fxml.FXML
    private MenuItem MI_UpdateItem;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> notes;
    @javafx.fxml.FXML
    private TableColumn<Inventory, Integer> quantity;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> name;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> expiration;
    @javafx.fxml.FXML
    private TableColumn<Inventory, Integer> id;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> category;
    @javafx.fxml.FXML
    private TableView<Inventory> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Load current database from local memory when opening menu
        userService = new UserService();
        inventoryService = new InventoryService();

        id.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("ITEM_ID"));
        name.setCellValueFactory(new PropertyValueFactory<Inventory, String>("name"));
        notes.setCellValueFactory(new PropertyValueFactory<Inventory, String>("notes"));
        quantity.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("quantity"));
        expiration.setCellValueFactory(new PropertyValueFactory<Inventory, String>("expiration"));
        category.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));

        displayTableView();

    }

    public void displayTableView() {
        tableView.getItems().clear();
        tableView.setItems(getInventory());


    }

    public ObservableList<Inventory> getInventory() {
        ObservableList<Inventory> observableList = FXCollections.observableArrayList();

        List<Inventory> retrieveInventory = inventoryService.getAllItems();

        observableList.addAll(retrieveInventory);

        return observableList;
    }

    public void checkForNotifications() {
        List<Inventory> outOfStockItems = Notification.getOutOfStockItems();

        if (!outOfStockItems.isEmpty()) {

        }
    }
    @javafx.fxml.FXML
    public void fileSave(ActionEvent actionEvent) {
        //call to save database
    }

    @javafx.fxml.FXML
    public void fileSaveAs(ActionEvent actionEvent) {
        //call to save database under new local name
    }

    String getText(TextField field){
        if(field.getText().isEmpty()) {
            return "";
        } else {
            return field.getText();
        }
    }

    //attempts to parse string
    int parsetoInt(String textvalue){
        System.out.println(textvalue);
        try{
            return Integer.parseInt(textvalue);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Data");
        }
        //returning number impossible for random to generate if parse fails
        return -1;
    }

    @javafx.fxml.FXML
    public void removeItemMenu(ActionEvent actionEvent) {
        //create item menu meant for removing items from database
        //create stage
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Remove Items");

        //create GUI elements
        Label catL = new Label();
        catL.setText("ID");

        TextField CatTF = new TextField();
        CatTF.setPromptText("Enter ID: ");

        Button acceptButton = new Button("Remove");
        acceptButton.setOnAction(
                e -> removeItems(getText(CatTF), dialog)
        );

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> dialog.close());

        //create layout structure
        VBox layout = new VBox(10);
        layout.getChildren().addAll(catL, CatTF, acceptButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        //set scene
        Scene scene = new Scene(layout, 350, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    @javafx.fxml.FXML
    public void updateItemMenu(ActionEvent actionEvent) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Update Item");

        //create GUI elements
        Label catL = new Label();
        catL.setText("ID");

        TextField CatTF = new TextField();
        CatTF.setPromptText("Enter ID");

        Label itemL = new Label();
        itemL.setText("Update Quantity");

        TextField ItemTF = new TextField();
        ItemTF.setPromptText("Enter new quantity");

        Button acceptButton = new Button("Update");
        acceptButton.setOnAction(
                e -> updateItems(getText(CatTF), getText(ItemTF), dialog)
        );

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> dialog.close());

        //create layout structure
        VBox layout = new VBox(10);
        layout.getChildren().addAll(catL, CatTF, itemL, ItemTF, acceptButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        //set scene
        Scene scene = new Scene(layout, 350, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    public void updateItems(String stringID, String quantity, Stage dialog){
        //calls from removeItemMenu
        dialog.close();

        //use data to remove items, if no items is to be removed open dialog box stating error
        int ID = parsetoInt(stringID);
        int amm = parsetoInt(quantity);


        Inventory inventory = inventoryService.getItemByID(ID);

        inventoryService.updateInventoryItem(inventory, amm);

        displayTableView();
    }


    public void removeItems(String stringID,  Stage dialog){
        //calls from removeItemMenu
        dialog.close();

        //use data to remove items, if no items is to be removed open dialog box stating error
        int ID = parsetoInt(stringID);

        Inventory inventory = inventoryService.getItemByID(ID);

        // DEBUG
        if (inventory == null) {
            System.out.println("Inventory is null for removing");
        }

        if (inventory != null) {
            inventoryService.removeInventoryItem(inventory);
            System.out.println("Inventory not null");
            displayTableView();
            System.out.println(inventory.getITEM_ID() + " " + inventory.getQuantity());
        } else {
            System.out.println("Inventory is null");
        }
    }

    public void createErrorBox(String ErrorMessage){
        //create stage
        Stage errdialog = new Stage();
        errdialog.initModality(Modality.APPLICATION_MODAL);
        errdialog.setTitle("Error");

        //create GUI elements
        Label label = new Label();
        label.setText(ErrorMessage);

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> errdialog.close());

        //create layout structure
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //set scene
        Scene scene = new Scene(layout, 250, 150);
        errdialog.setScene(scene);
        errdialog.show();
    }


    @javafx.fxml.FXML
    public void addNotifyMenu(ActionEvent actionEvent) {
        //create item menu meant for adding items from database
        //create stage
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Notification");

        //create GUI elements
        Label NRL = new Label();
        NRL.setText("Notification Reason");

        TextField RTF = new TextField();
        RTF.setPromptText("Reason: ");

        Label dateL = new Label();
        dateL.setText("Date of Notification");

        TextField DateTF = new TextField();
        DateTF.setPromptText("9/25/2024, 4pm");

        Button acceptButton = new Button("Create");
        acceptButton.setOnAction(
                e -> addNotification(getText(RTF), getText(DateTF), dialog)
        );

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> dialog.close());

        //create layout structure
        VBox layout = new VBox(10);
        layout.getChildren().addAll(NRL, RTF, dateL, DateTF, acceptButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        //set scene
        Scene scene = new Scene(layout, 350, 300);
        dialog.setScene(scene);
        dialog.show();
    }
    public void addNotification(String Reason, String date, Stage dialog){
        dialog.close();
        Date notificationDate = parseDate(date);

    }
    Date parseDate(String dateText){
        Date date = new Date();
        return date;
    }

    public void displayNotification(List<Inventory> inventoryNotification) {

    }

    //
    @javafx.fxml.FXML
    public void addItemMenu(ActionEvent actionEvent) {
        //create item menu meant for adding items from database
        //create stage
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Items");

        //create GUI elements
        Label catL = new Label();
        catL.setText("Category");

        TextField CatTF = new TextField();
        CatTF.setPromptText("Category: ");

        Label nameL = new Label();
        nameL.setText("Item Name");

        TextField NameTF = new TextField();
        NameTF.setPromptText("Name of Item");

        Label itemL = new Label();
        itemL.setText("Quantity");

        TextField ItemTF = new TextField();
        ItemTF.setPromptText("Quantity");

        Button acceptButton = new Button("Add");
        acceptButton.setOnAction(
                e -> addItems(getText(CatTF), getText(ItemTF), getText(NameTF), dialog)
        );

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> dialog.close());

        //create layout structure
        VBox layout = new VBox(10);
        layout.getChildren().addAll(catL, CatTF, nameL, NameTF, itemL, ItemTF, acceptButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        //set scene
        Scene scene = new Scene(layout, 350, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    //
    public void addItems(String category, String stringQuantity, String name, Stage dialog){
        dialog.close();

        int quantity = parsetoInt(stringQuantity);

        //add items to the ui
        if (quantity != -1) {
            Inventory inventory = new Inventory(name, quantity, category);
            inventoryService.addInventoryItem(inventory);
            displayTableView();
        } else {
            createErrorBox("Error Adding Items");
        }
    }

    @javafx.fxml.FXML
    public void fileNew(ActionEvent actionEvent) {
    }
}
