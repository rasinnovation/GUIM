public class DatabaseInfo {
    public static final String DB_URL = "jdbc:sqlite:./src/INVENTORY_DATABASE.db";

    public enum Tables {
        INVENTORY ("INVENTORY"),
        USERS ("USERS"),
        CURRENT_USER ("CURRENT_USER");

        public final String label;

        Tables (String label) {
            this.label = label;
        }
    }
}
