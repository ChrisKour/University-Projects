import java.util.ArrayList;


public class Customer {

	private String name;
	private int code;
	private ArrayList<Rental> rentals;
	
	public Customer(String name, int code) {
		this.name = name;
		this.code = code;
		rentals = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public double totalRentalCost(){
		double sum = 0;
		for(Rental r: rentals){
			sum += r.totalRentCost();
		}
		return sum;
	}

	public ArrayList<Rental> getRentals() {
		return rentals;
	}
	
	public int numberOfRentals(){
		return rentals.size();
	}
	
	public void addRental(Rental rental){
		rentals.add(rental);
	}
}
