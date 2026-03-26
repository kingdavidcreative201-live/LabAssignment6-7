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
        // Requirement 1: Display full report with headers and footers
    public static void displayReport(String[] pn, double[] pp, int[] pq, char[] pc) {
        productHeader();
        
        double totalSales = 0;
        
        for (int i = 0; i < pn.length; i++) {
            double extPrice = pp[i] * pq[i];
            totalSales += extPrice;
            productDetailLine(pn[i], pp[i], pq[i], pc[i], extPrice);
        }
        
        productFooter(totalSales);
    }
    
    // Requirement 4: Product detail line with formatted output
    public static void productDetailLine(String name, double price, int qty, char category, double extPrice) {
        System.out.printf("%-20s %10.2f %10d %10c %14.2f%n", 
            name, price, qty, category, extPrice);
    }
    
    // Requirement 4: Product header
    public static void productHeader() {
        System.out.println("\n-------------------- INVENTORY REPORT --------------------");
        System.out.printf("%-20s %10s %10s %10s %14s%n", 
            "Product Name", "Price", "Qty", "Category", "Ext. Price");
        System.out.println("----------------------------------------------------------");
    }
    
    // Requirement 4: Product footer with totals
    public static void productFooter(double totalSales) {
        System.out.println("----------------------------------------------------------");
        double avgSales = findAverageSales(productPrice, productQuantity);
        System.out.printf("Average Sales: %42.2f%n", avgSales);
        System.out.printf("Total Sales:   %42.2f%n", totalSales);
        System.out.println("----------------------------------------------------------");
    }
        // Requirement 2: Calculate total sales
    public static double findTotalSales(double[] pp, int[] pq) {
        double total = 0;
        for (int i = 0; i < pp.length; i++) {
            total += pp[i] * pq[i];
        }
        return total;
    }
    
    // Requirement 3: Calculate average sales per product
    public static double findAverageSales(double[] pp, int[] pq) {
        double total = findTotalSales(pp, pq);
        return total / pp.length;
    }
        // Sub-menu for changing records
    public static void changeRecordMenu() {
        System.out.println("\n--- Change Record ---");
        System.out.println("1. Change Quantity");
        System.out.println("2. Change Price");
        System.out.print("Enter choice: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (subChoice == 1) {
            changeQuantity();
        } else if (subChoice == 2) {
            changePrice();
        } else {
            System.out.println("Invalid choice.");
        }
    }
    
    // Requirement 5: Change quantity function with linear search
    public static void changeQuantity() {
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine();
        
        int foundIndex = -1;
        for (int i = 0; i < productName.length; i++) {
            if (productName[i].equalsIgnoreCase(searchName)) {
                foundIndex = i;
                break;
            }
        }
        
        if (foundIndex != -1) {
            System.out.println("Found: " + productName[foundIndex] + 
                             " | Current Quantity: " + productQuantity[foundIndex]);
            System.out.print("Enter new quantity: ");
            int newQty = scanner.nextInt();
            scanner.nextLine();
            
            productQuantity[foundIndex] = newQty;
            System.out.println("Quantity updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }
    
    // Requirement 6: Change price function with linear search
    public static void changePrice() {
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine();
        
        int foundIndex = -1;
        for (int i = 0; i < productName.length; i++) {
            if (productName[i].equalsIgnoreCase(searchName)) {
                foundIndex = i;
                break;
            }
        }
        
        if (foundIndex != -1) {
            System.out.println("Found: " + productName[foundName] + 
                             " | Current Price: $" + productPrice[foundIndex]);
            System.out.print("Enter new price: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            
            productPrice[foundIndex] = newPrice;
            System.out.println("Price updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }
        // Requirement 7: Category report - filters by category
    public static void categoryReport(char c) {
        System.out.println("\n---------- CATEGORY REPORT: " + c + " ----------");
        productHeader();
        
        double catTotal = 0;
        int count = 0;
        
        for (int i = 0; i < productCategory.length; i++) {
            if (productCategory[i] == c) {
                double extPrice = productPrice[i] * productQuantity[i];
                catTotal += extPrice;
                count++;
                productDetailLine(productName[i], productPrice[i], 
                                productQuantity[i], productCategory[i], extPrice);
            }
        }
        
        if (count == 0) {
            System.out.println("No products found in category " + c);
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.printf("Products in category %c: %d%n", c, count);
            System.out.printf("Category Total Sales: %29.2f%n", catTotal);
        }
    }
    
    // Requirement 8: Price report - products below max price
    public static void priceReport(double p) {
        System.out.printf("%n---------- PRODUCTS BELOW $%.2f ----------%n", p);
        productHeader();
        
        double subTotal = 0;
        int count = 0;
        
        for (int i = 0; i < productPrice.length; i++) {
            if (productPrice[i] < p) {
                double extPrice = productPrice[i] * productQuantity[i];
                subTotal += extPrice;
                count++;
                productDetailLine(productName[i], productPrice[i], 
                                productQuantity[i], productCategory[i], extPrice);
            }
        }
        
        if (count == 0) {
            System.out.println("No products found below $" + p);
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.printf("Products below $%.2f: %d%n", p, count);
            System.out.printf("Subtotal Sales: %35.2f%n", subTotal);
        }
    }
        // Requirement 9: Sort by name using bubble sort with index array
    public static void sortByNameReport(String[] pn, double[] pp, int[] pq) {
        // Create index array
        int[] index = new int[pn.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        
        // Bubble sort on index array based on product names
        for (int i = 0; i < index.length - 1; i++) {
            for (int j = 0; j < index.length - 1 - i; j++) {
                // Compare names using index
                if (pn[index[j]].compareToIgnoreCase(pn[index[j + 1]]) > 0) {
                    // Swap indices only, not the actual data
                    int temp = index[j];
                    index[j] = index[j + 1];
                    index[j + 1] = temp;
                }
            }
        }
        
        // Display report using sorted indices
        System.out.println("\n---------- SORTED BY NAME ----------");
        productHeader();
        
        double totalSales = 0;
        for (int i = 0; i < index.length; i++) {
            int idx = index[i];
            double extPrice = pp[idx] * pq[idx];
            totalSales += extPrice;
            productDetailLine(pn[idx], pp[idx], pq[idx], productCategory[idx], extPrice);
        }
        
        productFooter(totalSales);
    }
    
    // Requirement 10: Sort by price using bubble sort with index array
    public static void sortByPriceReport() {
        // Create index array
        int[] index = new int[productPrice.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        
        // Bubble sort on index array based on prices
        for (int i = 0; i < index.length - 1; i++) {
            for (int j = 0; j < index.length - 1 - i; j++) {
                if (productPrice[index[j]] > productPrice[index[j + 1]]) {
                    // Swap indices only, not the actual data
                    int temp = index[j];
                    index[j] = index[j + 1];
                    index[j + 1] = temp;
                }
            }
        }
        
        // Display report using sorted indices
        System.out.println("\n---------- SORTED BY PRICE ----------");
        productHeader();
        
        double totalSales = 0;
        for (int i = 0; i < index.length; i++) {
            int idx = index[i];
            double extPrice = productPrice[idx] * productQuantity[idx];
            totalSales += extPrice;
            productDetailLine(productName[idx], productPrice[idx], 
                            productQuantity[idx], productCategory[idx], extPrice);
        }
        
        productFooter(totalSales);
    }
}