public class Product {
	// the fields of the Product class
	private String productCode;
	private String description;
	private int stock;
	private double price;

	// the constructor
	public Product(String code, String desc, int quant, double cost) {
		this.productCode= code;
		this.description= desc;
		this.stock= quant;
		this.price= cost;
	}

	// class methods
	/**
	 * returns the productCode to the caller
	 * @return productCode
	 */
	public String giveProdCode() {
		return productCode;
	}

	/**
	 * returns the Product's description to the caller
	 * @return description
	 */
	public String giveDescription() {
		return description;
	}

	/**
	 * returns the current stock level to the caller
	 * @return stock
	 */
	public int giveStock() {
		return stock;
	}

	/**
	 * returns the Product's price to the caller
	 * @return price
	 */
	public double givePrice() {
		return price;
	}

	/**
	 * returns the Product's total value to the caller
	 * @return stock*price
	 */
	public double giveTotalValue() {
		return stock*price;
	}

	/**
	 * returns either the price or stock of the product as a String
	 * @param  attribute specifies whether the price or the stock value is to be returned
	 * @return           either the stock value or price as a string, or an error message
	 */
	public String giveAsString(String attribute) {
		String conversion= "";
		if (attribute == "price") {
			conversion= String.format("%3.2f", price);
		} else {
			conversion= String.format("%3f", stock);
		}
		return conversion;
	}

	/**
	 * increases the Product's stock level
	 * @param  increase quantity of stock to be added
	 */
	public void addStock(int increase){
		stock+= increase;
	}

	/**
	 * decreases the Product's stock level by a specified quantity
	 * @param  sale the quantity stock is to be decreased by
	 */
	public void sellStock(int sale){
		stock-= sale;
	}
}