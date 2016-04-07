package game_engine.properties;

public class Price {

	private double myPrice;

	public Price(double price){
		this.myPrice = price;
	}

	public double getValue(){
		return myPrice;
	}

	public void setPrice(double price){
		this.myPrice = price;
	}
	
	public Price copyPrice() {
	    return new Price(this.getValue());
	}

}