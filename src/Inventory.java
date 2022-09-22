public class Inventory {
    private final int ITEM_ID;
    private String name;
    private String notes;
    private int quantity;
    private String expiration;
    private String category;
    // TODO Notifications?

    public Inventory(int ITEM_ID, String name, String notes, int quantity,
                     String expiration, String category) {
        this.ITEM_ID = ITEM_ID; // TODO This one may need an unique ID. Handle if using.
        this.name = name;
        this.notes = notes;
        this.quantity = quantity;
        this.expiration = expiration;
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
