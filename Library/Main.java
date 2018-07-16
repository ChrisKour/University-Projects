/* Author: Chris Kourounis */

public class Main {

	public static void main(String[] args) {
		
		Writer w1 = new Writer("Simon Singh",857485,0.02);
		Writer w2 = new Writer("Doxiadis Apostolos",635241,0.04);
		
		Book b1 = new Book(1234,"The code book",w1);
		Book b2 = new Book(4321,"Big Bang",w1);
		Book b3 = new Book(5678,"The Science Book",w1);
		Book b4 = new Book(4152,"Fermat's Enigma",w1);
		Book b5 = new Book(8463,"Logicomix",w2);
		Book b6 = new Book(2254,"Uncle Petros & Goldbach's Conjecture ",w2);
		Book b7 = new Book(3321,"Circles Disturbed",w2);

		Library library = new Library();
		
		library.addBook(b1);
		library.addBook(b2);
		library.addBook(b3);
		library.addBook(b4);
		library.addBook(b5);
		library.addBook(b6);
		library.addBook(b7);
		
		library.borrowBook(b1.getISBN());
		library.borrowBook(b2.getISBN());
		library.borrowBook(b3.getISBN());
		library.returnBook(b1.getISBN());
		library.borrowBook(b4.getISBN());
		library.borrowBook(b5.getISBN());
		library.returnBook(b2.getISBN());
		library.borrowBook(b6.getISBN());
		library.borrowBook(b7.getISBN());
		library.returnBook(b3.getISBN());
		library.borrowBook(b3.getISBN());
		
		System.out.println("------------------------------------------");
		System.out.println("The books that are currently borrowed are:");
		library.printBorrowedBooks();
		
		System.out.println("------------------------------------------");
		System.out.println("The writer profits are:");
		library.calculateWriterProfits(w1.getAfm());
		library.calculateWriterProfits(w2.getAfm());
	}
}
