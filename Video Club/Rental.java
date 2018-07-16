import java.util.ArrayList;


public class Rental {

	private ArrayList<Movie> movies;
	
	public Rental(){
		movies = new ArrayList<>();
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public double totalRentCost(){
		double sum = 0;
		for(Movie m: movies){
			sum += m.computeRentCost();
		}
		return sum;
	}
	
	public void addMovie(Movie movie){
		movies.add(movie);
		movie.setCounter(movie.getCounter()+1);
	}
	
}
