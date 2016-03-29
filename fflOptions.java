import java.io.*;
import java.util.*;
import java.lang.*;

public class fflOptions{
	public static ArrayList<Product> getProducts (ArrayList<Product> products) throws FileNotFoundException {
        Scanner fileInput= fflIO.openFile("stock.txt");
        while (fileInput.hasNextLine()){
            String line = fileInput.nextLine();
            String[] segments = line.split(" ");
            try {
                products.add(new Product (segments[0], segments [1], Integer.valueOf(segments[2]), Double.valueOf(segments[3])));
            } catch (IndexOutOfBoundsException idex){
                throw new RuntimeException(String.format("Incomplete data detected! Please check inventory file."));
            }
        }
        return products;
    }

    /**
    * Displays a greeting message, identifying the company
    */ 
    public static void greeting(){
        ArrayList<String> greetings1= new ArrayList<String>(Arrays.asList("%50s%n", "Welcome to Foods for Less Grocery.", "%56s%n%n", "Proudly serving Point Fortin for over 40 years."));
        fflIO.printOutput(2,greetings1);    
    }

    /**
    * Presents the user with a listing of the program's features. Subsequent to this, the user is allowed to specify their desired option.
    */ 
    public static void giveOptions(){
        ArrayList<String> prompt= new ArrayList<String>(Arrays.asList("%nKindly select an option from the list below:%n", "1. Display current stock levels and values%n", "2. Display all out of stock items%n", "3. Display total value of current stock%n", "4. Identify most expensive food item%n", "5. Create an order from current inventory%n"));
        fflIO.printOutput(1, prompt);
    }

    public static void pickOption(ArrayList<Product> products, ArrayList<Product> sales, boolean changes) throws InputMismatchException, IOException, NumberFormatException{
        int option = 0;
        giveOptions();        
        while (option<1 || option>6){
            String response= fflIO.getUserInput("Which would you like to do?: ");
            try{
                option = Integer.valueOf(response);
            }catch (InputMismatchException|NumberFormatException e){
                ArrayList<String> badInput= new ArrayList<String>(Arrays.asList("That's definitely not an option on the list.%n"));
                fflIO.printOutput(1, badInput);
                pickOption(products, sales, changes);
            }            
        }
        switch(option){
            case 1: giveStock(1, products, sales, changes);
                break;
            case 2: getWhatsOut(products, sales, changes);
                break;
            case 3: getTotalValue(products, sales, changes);
                break;
            case 4: getMostExpensive(products, sales, changes);
                break;
            /*case 5: getOrderSize(productCodeArray, descriptionArray, stockArray, priceArray, size, ordProdCode, ordQuant, ordValue, changes, test_mode);
                break;*/
            default: pickOption(products, sales, changes);
                break;
        }                      
    }

    public static void getAnother(ArrayList<Product> products, ArrayList<Product> sales, boolean changes) throws IOException{
        String response = "";
        response = fflIO.getUserInput("%nWould you like to perform another operation? [Yes/No]: ");
        switch (response.toLowerCase()){
            case "y": case "yes": pickOption(products, sales, changes);
                break;
            case "n": case "no": ArrayList<String> farewell= new ArrayList<String>(Arrays.asList("Goodbye!\n"));
                fflIO.printOutput(1, farewell);
                if (changes){
                    fflIO.updateStockFile(products, "stock3.txt");
                }
                break;
            default: ArrayList<String> valid= new ArrayList<String>(Arrays.asList("Please enter a valid response.\n"));
                fflIO.printOutput(1, valid);
                getAnother(products, sales, changes);
        }
    }

    public static void giveStock(int option, ArrayList<Product> products, ArrayList<Product> sales, boolean changes) throws IOException{
        if (option==1){ 
            ArrayList<String> header= new ArrayList<String>(Arrays.asList("%55s\n", "INVENTORY AND CURRENT STOCK LEVELS:", "%-16s", "Item", "%-20s", "Description", "%-12s", "Quantity", "%-16s", "Unit Price", "%-15s%n", "Stock Total"));
            fflIO.printOutput(2, header);
            for (int i=0; i<products.size(); i++){
                String productCode= products.get(i).giveProdCode();
                String description = products.get(i).giveDescription();
                int stock = products.get(i).giveStock();
                double price= products.get(i).givePrice();
                double totals= products.get(i).giveTotalValue();
                ArrayList<String> listing = new ArrayList<String>(Arrays.asList("%-16s", productCode, "%-20s", description, "%12s", Integer.toString(stock), "%10s", String.format("%10.2f", price), "%17s%n", String.format("%13.2f", totals)));
                fflIO.printOutput(2, listing);
            }
            getAnother(products, sales, changes);
        }/*else{
            ArrayList<String> header2= new ArrayList<String>(Arrays.asList("%35s\n", "YOUR ORDER:","%-20s", "Description", "%-12s", "Quantity", "%-16s", "Unit Price", "%-15s%n", "Item Total"));
            fflIO.printOutput(2, header2);
            for (int i=0; i<sales.size(); i++){
                String description= sales.get(i).giveDescription();
                int quant = sales.get(i).giveStock();
                double price= sales.get(i).givePrice();
                double itemTotal= sales.get(i).giveTotalValue();
                ArrayList<String> invoice = new ArrayList<String>(Arrays.asList("%-15s", description, "%8s", Integer.toString(quant), "%7s", "", "%12s", String.format("%10.2f", price),"%16s%n", String.format("%13.2f", itemTotal)));
                fflIO.printOutput(2, invoice);
            }
            ArrayList<String> ordTotal= new ArrayList<String>(Arrays.asList("%n%42s", "Total","%4s", "", "%14s%n", String.format("%10.2f%n", ordValue)));
            fflIO.printOutput(2, ordTotal);
            sales.clear();
            getAnother(products, sales, changes);
        }*/
    }

    public static void getWhatsOut(ArrayList<Product> products, ArrayList<Product> sales, boolean changes)throws IOException, IndexOutOfBoundsException{
        ArrayList<String> header= new ArrayList<String>(Arrays.asList("%n***********OUT OF STOCK ITEMS***********%n"));
        fflIO.printOutput(1, header);
        try{
            for (int i=0; i<products.size(); i++){
                if (products.get(i).giveStock()==0){
                    String item = products.get(i).giveDescription();
                    ArrayList<String> out = new ArrayList<String>(Arrays.asList("%s%n", item));
                    fflIO.printOutput(2, out);
                }
            }
            getAnother(products, sales, changes);            
        } catch (IndexOutOfBoundsException iooex){
            ArrayList<String> notThere= new ArrayList<String>(Arrays.asList("%n%s%n", "Something went wrong. Please check the input."));
            fflIO.printOutput(2, notThere);
        }
    }

    public static void getTotalValue(ArrayList<Product> products, ArrayList<Product> sales, boolean changes) throws IOException{
        try{
            double count = 0;
            for (int i=0; i<products.size(); i++){
                count+=products.get(i).giveTotalValue();
            }
            ArrayList<String> total= new ArrayList<String>(Arrays.asList("%nThe total value of the current stock is $%s%n", String.format("%4.2f", count)));
            fflIO.printOutput(2, total);
            getAnother(products, sales, changes);
        } catch (IndexOutOfBoundsException iooex){
            ArrayList<String> notThere= new ArrayList<String>(Arrays.asList("%n%s%n", "Something went wrong. Please check the input."));
            fflIO.printOutput(2, notThere);
        }
    }

    public static void getMostExpensive(ArrayList<Product> products, ArrayList<Product> sales, boolean changes) throws IOException{
        double big = products.get(0).givePrice();
        try {
            ArrayList<Product> upper= new ArrayList<Product>();
            for (int i=1; i<products.size(); i++){
                if (products.get(i).givePrice()>big){
                    upper.clear();
                    upper.add(products.get(i));
                    big=products.get(i).givePrice();
                }
                else if (products.get(i).givePrice()==big){
                    upper.add(products.get(i));
                }
            }
            ArrayList<String> header = new ArrayList<String>(Arrays.asList("%n%42s%n", "***********MOST EXPENSIVE ITEMS***********", "%-20s", "Description", "%10s%n", "Price"));
            fflIO.printOutput(2, header);
            for (int j=0; j<upper.size(); j++){
                String desc= upper.get(j).giveDescription();
                double price= upper.get(j).givePrice();
                ArrayList<String> pricey= new ArrayList<String>(Arrays.asList("%-20s", desc, "%10s%n", String.format("%4.2f", price)));
                fflIO.printOutput(2, pricey);
            }
            getAnother(products, sales, changes);
        } catch (IndexOutOfBoundsException iooex){
            ArrayList<String> notThere= new ArrayList<String>(Arrays.asList("%n%s%n", "Something went wrong. Please check the input."));
            fflIO.printOutput(2, notThere);
        }
    }
}