
public abstract class Movie {

	protected String title;
	protected int code;
	protected int counter;
	
	public Movie(String title, int code) {
		this.title = title;
		this.code = code;
		counter = 0;
	}

	public String getTitle() {
		return title;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public abstract double computeRentCost();
}
