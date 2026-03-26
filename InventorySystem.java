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