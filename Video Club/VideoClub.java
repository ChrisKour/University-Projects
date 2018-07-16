import java.text.DecimalFormat;
import java.util.ArrayList;


public class VideoClub {

	private ArrayList<Customer> customers;
	
	public VideoClub() {
	customers = new ArrayList<>();
	}
	
	public void printCustomerMostRents(int x){
		Customer temp;
		for(int i = 0; i < customers.size(); i++){
			for(int j = 0; j < customers.size() - 1- i; j++){
				if(customers.get(j).numberOfRentals() < customers.get(j+1).numberOfRentals()){
					temp = customers.get(j);
					customers.set(j, customers.get(j+1));
					customers.set(j+1, temp);
				}
			}
		}
		System.out.println("Most rentals:");
		for(int i=0;i<x;i++){
			System.out.println("The customer: "+customers.get(i).getName()+" with "+customers.get(i).numberOfRentals()+" rentals");
		}
	}
	
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	
	DecimalFormat myFormat = new DecimalFormat("##.##");

	public void printMostMoneyCustomers(int x){
		Customer temp;
		for(int i = 0; i < customers.size(); i++){
			for(int j = 0; j < customers.size() - 1 - i; j++){
				if(customers.get(j).totalRentalCost() < customers.get(j+1).totalRentalCost()){
					temp = customers.get(j);
					customers.set(j, customers.get(j+1));
					customers.set(j+1, temp);
				}
			}
		}
		System.out.println("Most Money:");
		for(int i=0;i<x;i++){
			System.out.println("The customer: "+customers.get(i).getName()+" spent "+myFormat.format(customers.get(i).totalRentalCost())+" euros");
		}
	}
	
	public void printMostPopularMovies(int x){
		ArrayList<Movie> movies = new ArrayList<>();
		for(int i = 0; i < customers.size(); i++) {
			for(int j=0;j<customers.get(i).getRentals().size();j++){
				for(int k=0;k<customers.get(i).getRentals().get(j).getMovies().size();k++){
					if(!movies.contains(customers.get(i).getRentals().get(j).getMovies().get(k)))
						movies.add(customers.get(i).getRentals().get(j).getMovies().get(k));
				}
			}
		}

		Movie temp;
		for(int i = 0; i < movies.size(); i++){
			for(int j = 0; j < movies.size() - 1 - i; j++){
				if(movies.get(j).getCounter() < movies.get(j+1).getCounter()){
					temp = movies.get(j);
					movies.set(j, movies.get(j+1));
					movies.set(j+1, temp);
				}
			}
		}
		System.out.println("Most Rented Movies:");
		for(int i=0;i<x;i++){
			System.out.println("The movie: "+movies.get(i).getTitle()+" was rented "+movies.get(i).getCounter()+" times");
		}
	}
	
}