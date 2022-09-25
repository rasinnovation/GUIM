public class Inventory {
    private static int ID_GENERATOR = InventoryService.getMaxID();
    private final int ITEM_ID;
    private String name;
    private String notes;
    private int quantity;
    private String expiration;
    private String category;

    // TODO Notifications?

    public Inventory(int ID, String name, String notes, int quantity,
                     String expiration, String category) {
        ID_GENERATOR++;
        this.ITEM_ID = ID; // TODO This one may need an unique ID. Handle if using.
        this.name = name;
        this.notes = notes;
        this.quantity = quantity;
        this.expiration = expiration;
        this.category = category;
    }

    public Inventory(String name, int quantity, String category) {
        ID_GENERATOR++;
        this.ITEM_ID = ID_GENERATOR;
        System.out.println("Debug " + ITEM_ID);
        this.name = name;
        this.notes = "None";
        this.quantity = quantity;
        this.expiration = "None";
        this.category = category;
    }

    /*
        Getters and Setters
    */
    // TODO Just did the getters for now

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getCategory() {
        return category;
    }
}
