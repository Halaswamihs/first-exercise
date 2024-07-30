import java.util.LinkedHashMap;
import java.util.Scanner;

class Product {
    String name;
    double price;
    boolean availability;
    int count;

    public Product(String name, double price, boolean availability) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.count = 1;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}

public class eCommerse_problem {
    public static void main(String[] args) {
        LinkedHashMap<String, Product> pro = new LinkedHashMap<>();
        LinkedHashMap<String, Product> cart = new LinkedHashMap<>();

        Scanner sc = new Scanner(System.in);
    System.out.println("enter the products details in json format );  
 
        String items = sc.nextLine();

        String jsonInput = items;

        // Remove the outer square brackets and split the string into individual objects
        String[] itemStrings = jsonInput.substring(1, jsonInput.length() - 1).split("}, \\{");

        for (String itemString : itemStrings) {
            // Remove leading and trailing curly braces if they exist
            itemString = itemString.replaceAll("^[{]|[}]$", "").trim();

            // Split the string into key-value pairs
            String[] keyValuePairs = itemString.split(", ");

            String name = "";
            double price = 0;
            boolean available = false;
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(": ");
                String key = keyValue[0].trim();
                String value = keyValue[1].trim().replace("\"", "");

                switch (key) {
                    case "name":
                        name = value;
                        break;
                    case "price":
                        price = Double.parseDouble(value);
                        break;
                    case "available":
                        available = Boolean.parseBoolean(value);
                        break;
                }
            }
            Product p = new Product(name, price, available);
            pro.put(name, p);
        }

        for (int k = 0; k < 3; k++) {
System.out.println("enter the operation: details");
            String takeInput = sc.nextLine();
            String[] inputArray = takeInput.split(":");
            String operation = inputArray[0].trim();

            switch (operation) {
                case "Add to Cart":
                    String addingItem = inputArray[1].trim().replaceAll("\"","");

                    
                    Product addIt = pro.get(addingItem);
                    if (addIt != null) {
                        cart.put(addingItem, addIt);
                    } else {
                        System.out.println("Item not found in products");
                    }
                    break;
                case "Update Quantity":
                    String update = inputArray[1].trim().replaceAll("\"","");
                    String[] makeDivide = update.split(",");
                    String itemIs = makeDivide[0].trim();
                    int quantity = Integer.parseInt(makeDivide[1].trim());
                    Product updatingObject = cart.get(itemIs);
                    if (updatingObject != null) {
                        updatingObject.setCount(quantity);
                    } else {
                        System.out.println("Item not found in cart");
                    }
                    break;
                case "Remove from Cart":
                    String remove = inputArray[1].trim().replaceAll("\"","");
                    cart.remove(remove);
                    break;
            }
        }

        double sum = 0;
        Product ci;
        for (String entry : cart.keySet()) {
            ci = cart.get(entry);
            if (ci != null) {
                sum += ci.getPrice() * ci.getCount();
            }
        }

        System.out.print("You have: \"");
        for (String entry : cart.keySet()) {
            ci = cart.get(entry);
            if (ci != null) {
                System.out.print(ci.getCount() + " " + ci.getName() + " ");
            }
        }
        System.out.println("\" in your cart");
        System.out.println("Total price is: $ " + sum);
    }
}