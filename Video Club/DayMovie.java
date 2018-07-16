
public class DayMovie extends Movie{

	private int daysRented;
	private double dayCost;
	
	public DayMovie(String title, int code,int daysRented, double dayCost) {
		super(title, code);
		this.daysRented = daysRented;
		this.dayCost = dayCost;
	}
	
	public double computeRentCost(){
		return daysRented * dayCost;
	}
}
