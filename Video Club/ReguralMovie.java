
public class ReguralMovie extends Movie{

	private double rentCost;

	public ReguralMovie(String title, int code, double rentCost) {
		super(title, code);
		this.rentCost = rentCost;
	}
	
	public double computeRentCost(){
		return rentCost;
	}
}
