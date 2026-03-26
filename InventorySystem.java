import java.util.Scanner;

public class InventorySystem {
    
    // Parallel arrays for 20 products - Grocery Store Inventory
    static String[] productName = {
        "Apple", "Banana", "Orange", "Milk", "Bread", "Cheese", "Yogurt", "Chicken", "Beef", "Rice",
        "Pasta", "Tomato", "Potato", "Onion", "Carrot", "Cereal", "Juice", "Coffee", "Tea", "Sugar"
    };
    
    static double[] productPrice = {
        0.99, 0.59, 1.29, 3.49, 2.99, 4.99, 1.99, 8.99, 12.99, 2.49,
        1.79, 0.89, 0.69, 0.79, 0.99, 3.99, 2.49, 8.99, 4.99, 2.99
    };
    
    static int[] productQuantity = {
        150, 200, 120, 80, 60, 45, 90, 35, 25, 100,
        85, 140, 200, 180, 130, 55, 75, 40, 65, 110
    };
    
    // Categories: D=Dairy, M=Meat, P=Produce, B=Beverage, G=Grocery
    static char[] productCategory = {
        'P', 'P', 'P', 'D', 'G', 'D', 'D', 'M', 'M', 'G',
        'G', 'P', 'P', 'P', 'P', 'G', 'B', 'B', 'B', 'G'
    };
    
    static Scanner scanner = new Scanner(System.in);
           
       public static void main(String[] args) {
        int choice;
        
        do {
            System.out.println("\n========== INVENTORY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Display Full Report (Original Order)");
            System.out.println("2. Display Report by Category");
            System.out.println("3. Change Record (Quantity or Price)");
            System.out.println("4. Sort by Name and Display Report");
            System.out.println("5. Sort by Price and Display Report");
            System.out.println("6. Display Products Below Max Price");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    displayReport(productName, productPrice, productQuantity, productCategory);
                    break;
                case 2:
                    System.out.print("Enter category (F=Food, D=Dairy, M=Meat, P=Produce, B=Beverage, G=Grocery): ");
                    char cat = scanner.nextLine().toUpperCase().charAt(0);
                    categoryReport(cat);
                    break;
                case 3:
                    changeRecordMenu();
                    break;
                case 4:
                    sortByNameReport(productName, productPrice, productQuantity);
                    break;
                case 5:
                    sortByPriceReport();
                    break;
                case 6:
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();
                    priceReport(maxPrice);
                    break;
                case 0:
                    System.out.println("Thank you for using the Inventory System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }