import java.util.HashMap;
import java.util.Map;

public class Location {
    private String name;
    private String address;
    private Map<Product, Integer> inventory;

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
        this.inventory = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addInventory(Product product, int quantity) {
        inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
    }

    public void removeInventory(Product product, int quantity) {
        int currentQuantity = inventory.getOrDefault(product, 0);
        if (currentQuantity >= quantity) {
            inventory.put(product, currentQuantity - quantity);
        }
    }

    public int getInventoryQuantity(Product product) {
        return inventory.getOrDefault(product, 0);
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }
    // Implement more methods as needed
}
