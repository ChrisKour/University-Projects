public class Book {
	
	private int ISBN;
	private String title;
	private Writer writer;
	private int timesRented;
	private boolean borrowed;
	
	public Book(int isbn, String title, Writer writer) {
		ISBN = isbn;
		this.title = title;
		this.writer = writer;
		timesRented = 0;
		borrowed = false;
	}

	public int getTimesRented() {
		return timesRented;
	}

	public void setTimesRented(int timesRented) {
		this.timesRented = timesRented;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public int getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public Writer getWriter() {
		return writer;
	}
}
