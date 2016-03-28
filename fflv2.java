import java.io.*;
import java.util.*;
import java.lang.*;

/** 
*<h1>FoodforLess Grocery </h1>
*<p>
*Allows the user to perform several functions, based on the current inventory of Food for Less Grocery--which is read in from the stock.txt file--, including:
*<p> 
*<ol><li>listing the current inventory</li> <li>what is not in stock</li> <li>the most expensive item in stock</li> <li>the total value of the current inventory</li> <li>allowing the user to create a custom order</li></ol>
*@author Berhane-Hiwet N. La Rose
*@since 27-Mar-16
*@version 2.0
*/
public class fflv2 {
	public static void main (String[] args) throws FileNotFoundException {
        ArrayList<Product> products = new ArrayList<Product>();
		/*Product cheese = new Product ("Ch33Z", "Cheese", 20, 12.50);
        products.add(cheese);
        Product milk = new Product ("M101", "Milk", 50, 14.00);
        products.add(milk);
        for (int i=0; i<products.size(); i++){
            String name = products.get(i).giveDescription();
            String price = products.get(i).giveAsString("price");
            System.out.printf("We have %s for $%s%n", name, price);
        }*/
        getProducts(products);
        /*for (int i=0; i<products.size(); i++){
            System.out.printf(products.get(i).giveDescription()+"%n");
        }*/
        greeting();
	}

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

}