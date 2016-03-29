import java.util.*;
import java.io.*;

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
	public static void main (String[] args) throws FileNotFoundException, IOException{
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Product> sales = new ArrayList<Product>();
        boolean changes = false;
		fflOptions.getProducts(products);
        fflOptions.greeting();
        fflOptions.pickOption(products, sales, changes);
	}
}