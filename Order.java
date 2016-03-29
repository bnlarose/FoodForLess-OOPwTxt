import java.util.*;

public class Order{
	// the single field of this class, which contains all the products that constitute the order
	private ArrayList<Product> items = new ArrayList<Product>();

	// constructor for Order, which requires no arguments
	public Order() {

	}

	// getters and setters
	public void addToOrder (Product product){
		items.add(product);
	}

	public ArrayList<Product> getOrderItems(){
		return items;
	}

	public double getTotalValue(){
		double total= 0.0;
		for (int i=0; i<items.size(); i++){
			total+=items.get(i).giveTotalValue();
		}
		return total;
	}
}