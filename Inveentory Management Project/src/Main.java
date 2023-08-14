import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventoryManager = new InventoryManager();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("0. List Inventory");
            System.out.println("1. Add items to inventory");
            System.out.println("2. Remove items from inventory");
            System.out.println("3. Move stock location");
            System.out.println("4. Check for stock updates");
            System.out.println("5. Config");
            int option = scanner.nextInt();

            switch (option) {
                case 0:
                    inventoryManager.listInventory();
                    break;
                case 1:
                    inventoryManager.addItemsToInventory();
                    break;
                case 2:
                    inventoryManager.removeItemsFromInventory();
                    break;
                case 3:
                    inventoryManager.moveStockLocation();
                    break;
                case 4:
                    // Implement stock update checking
                    break;
                case 5:
                    inventoryManager.configure();
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }
}




