import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;


public class InventoryManager {
    private List<Product> products;
    private List<Location> locations;
    private Scanner scanner;

    public InventoryManager() {
        products = new ArrayList<>();
        locations = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addItemsToInventory() {
        System.out.print("Enter product barcode: ");
        String barcode = scanner.next();
        Product product = findProductByBarcode(barcode);

        if (product == null) {
            System.out.println("Product not in system.");
            return;
        }

        System.out.println("Available Locations:");
        for (Location location : locations) {
            System.out.println(location.getName());
        }

        System.out.print("Enter location name: ");
        String locationName = scanner.next();
        Location location = findLocationByName(locationName);

        if (location == null) {
            System.out.println("Location not found.");
            return;
        }

        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();

        // Update inventory
        location.addInventory(product, quantity);
    }


    public void removeItemsFromInventory() {
        System.out.print("Enter product barcode: ");
        String barcode = scanner.next();
        Product product = findProductByBarcode(barcode);

        if (product == null) {
            System.out.println("Product not in system.");
            return;
        }

        System.out.println("Available Locations:");
        for (Location location : locations) {
            System.out.println(location.getName());
        }

        System.out.print("Enter location name: ");
        String locationName = scanner.next();
        Location location = findLocationByName(locationName);

        if (location == null) {
            System.out.println("Location not found.");
            return;
        }

        System.out.print("Enter quantity to remove: ");
        int quantity = scanner.nextInt();

        int currentQuantity = location.getInventoryQuantity(product);

        if (currentQuantity < quantity) {
            System.out.println("Insufficient quantity in inventory.");
            return;
        }

        // Update inventory
        location.removeInventory(product, quantity);
        System.out.println("Items removed from inventory.");
    }


    public void moveStockLocation() {
        System.out.println("Available Locations:");
        for (Location location : locations) {
            System.out.println(location.getName());
        }

        System.out.print("Enter source location name: ");
        String sourceLocationName = scanner.next();
        Location sourceLocation = findLocationByName(sourceLocationName);

        if (sourceLocation == null) {
            System.out.println("Source location not found.");
            return;
        }

        System.out.print("Enter destination location name: ");
        String destinationLocationName = scanner.next();
        Location destinationLocation = findLocationByName(destinationLocationName);

        if (destinationLocation == null) {
            System.out.println("Destination location not found.");
            return;
        }

        System.out.println("Scan product barcodes (type 'stop' to finish):");
        while (true) {
            System.out.print("Enter barcode: ");
            String barcode = scanner.next();

            if (barcode.equalsIgnoreCase("stop")) {
                break;
            }

            Product product = findProductByBarcode(barcode);

            if (product == null) {
                System.out.println("Product not in system.");
                continue;
            }

            int quantity = sourceLocation.getInventoryQuantity(product);

            if (quantity > 0) {
                sourceLocation.removeInventory(product, 1);
                destinationLocation.addInventory(product, 1);
                System.out.println("Stock moved successfully.");
            } else {
                System.out.println("Product doesn't exist in source inventory.");
            }
        }
    }


    public void configure() {
        System.out.println("Select a config option:");
        System.out.println("1. Add new Products");
        System.out.println("2. Create new Location");
        int configOption = scanner.nextInt();

        switch (configOption) {
            case 1:
                addNewProduct();
                break;
            case 2:
                createNewLocation();
                break;
            default:
                System.out.println("Invalid config option.");
        }
    }

    private Product findProductByBarcode(String barcode) {
        for (Product product : products) {
            if (product.getBarcode().equals(barcode)) {
                return product;
            }
        }
        return null;
    }

    private Location findLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    private void addNewProduct() {
        System.out.print("Enter product barcode: ");
        String barcode = scanner.next();
        System.out.print("Enter product name: ");
        String name = scanner.next();

        Product newProduct = new Product(barcode, name);
        products.add(newProduct);

        System.out.println("Product added successfully.");
    }


    private void createNewLocation() {
        System.out.print("Enter location name: ");
        String name = scanner.next();
        System.out.print("Enter location address: ");
        String address = scanner.next();

        Location newLocation = new Location(name, address);
        locations.add(newLocation);

        System.out.println("Location created successfully.");
    }

    public void listInventory() {
        System.out.println("Available Locations:");
        for (Location location : locations) {
            System.out.println(location.getName());
        }

        System.out.print("Enter location name: ");
        String locationName = scanner.next();
        Location location = findLocationByName(locationName);

        if (location == null) {
            System.out.println("Location not found.");
            return;
        }

        System.out.println("Inventory in " + location.getName() + ":");
        Map<Product, Integer> inventory = location.getInventory();
        for (Entry<Product, Integer> entry : inventory.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getBarcode() + ", " + product.getName() + ", " + quantity);
        }
    }


}


