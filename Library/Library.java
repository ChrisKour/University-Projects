public class Library {

	private Book[] books;
	private int counter;
	
	private final int MAX_SIZE = 30;

	public Library() {
		books = new Book[MAX_SIZE];
		counter = 0 ;
	}
	
	public void addBook(Book book) {
		if(counter < MAX_SIZE) {
			books[counter] = book;
			counter++;
		}
		else{
			System.out.println("The library is full");
		}
	}
	
	public Book checkIfBookExists(int isbn){
		for(int i=0;i<counter;i++){
			if(books[i].getISBN() == isbn)
				return books[i];
			}	
		return null;		
	}
	
	public void borrowBook(int isbn){
		if(checkIfBookExists(isbn) != null){
			for(int i=0;i<counter;i++){
				if(books[i].getISBN() == isbn && !books[i].isBorrowed()){
					books[i].setBorrowed(true); 
					books[i].setTimesRented(books[i].getTimesRented()+1);
					System.out.println("The book: '"+books[i].getTitle()+"' is now being rented");
				}
			}
		}
	}
	
	public void returnBook(int isbn){
		if(checkIfBookExists(isbn) != null){
			for(int i=0;i<counter;i++){
				if(books[i].getISBN() == isbn){
					books[i].setBorrowed(false);
					System.out.println("The book: '"+books[i].getTitle()+"' has been returned");
				}
			}
		}
	}
	
	public void calculateWriterProfits(int afm){
		double poso=0;
		int thesi=0;
		for(int i=0;i<counter;i++){
			if(books[i].getWriter().getAfm() == afm){
				poso = poso + books[i].getTimesRented() * books[i].getWriter().getKerdos();
				thesi = i;
			}
		}
		System.out.println("The writer " + books[thesi].getWriter().getName() + " must be paid " + poso+" euros");
	}
	
	public void printBorrowedBooks(){
		for(int i=0;i<counter;i++){
			if(books[i].isBorrowed()){
				System.out.println(books[i].getTitle()+", ("+books[i].getWriter().getName()+"), ISBN:"+books[i].getISBN());
			}
		}
	}
}
