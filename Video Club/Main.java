/* Author: Chris Kourounis */

public class Main {

	public static void main(String[] args) {
		VideoClub v1 = new VideoClub();

		Movie m1 = new DayMovie("Matrix", 01,1, 0.5);
		Movie m2 = new DayMovie("The Matrix Realoaded", 02,3, 0.4);
		Movie m3 = new DayMovie("The Matrix Revolutions", 03,2, 0.3);
		Movie m4 = new DayMovie("Batman Begins", 04,1, 0.6);
		Movie m5 = new DayMovie("The Dark Knight", 05,5, 0.5);
		Movie m6 = new DayMovie("The Dark Knight Rises", 06,2, 0.7);
		Movie m7 = new DayMovie("Star Wars: Episode I", 07,3, 0.8);
		Movie m8 = new DayMovie("Star Wars: Episode II", 26,1, 1.5);
		Movie m9 = new DayMovie("Star Wars: Episode III", 27,3, 1.0);
		Movie m10 = new DayMovie("Star Wars: Episode IV", 10,2, 0.9);
		Movie m11 = new DayMovie("Star Wars: Episode V", 11,2, 2.5);
		Movie m12 = new DayMovie("Star Wars: Episode VI", 12,3, 0.5);
		Movie m13 = new ReguralMovie("Lord Of the Rings: The Fellowship of the Ring", 13, 2);
		Movie m14 = new ReguralMovie("Lord Of the Rings: The Two Towers", 14, 1);
		Movie m15 = new ReguralMovie("Lord Of the Rings: The Return of The King", 15, 2);
		Movie m16 = new ReguralMovie("Public Enemies", 16, 2.5);
		Movie m17 = new ReguralMovie("Taxi Driver", 17, 3);
		Movie m18 = new ReguralMovie("Once Upon a Time in America", 18, 1.5);
		Movie m19 = new ReguralMovie("Seraphine", 19, 1.2);
		Movie m20 = new ReguralMovie("The Girl with the Dragon Tattoo", 20, 1);
		Movie m21 = new ReguralMovie("The Godfather Part I", 21, 2.4);
		Movie m22 = new ReguralMovie("The Godfather Part II", 22, 4);
		Movie m23 = new ReguralMovie("The Godfather Part III", 23, 3);
		Movie m24 = new ReguralMovie("Road to Perdition", 24, 3.5);
		Movie m25 = new ReguralMovie("Cape Fear", 25, 4);

		Customer c1 = new Customer("Customer 1", 001);
		Customer c2 = new Customer("Customer 2", 002);
		Customer c3 = new Customer("Customer 3", 003);
		Customer c4 = new Customer("Customer 4", 004);
		Customer c5 = new Customer("Customer 5", 005);
		Customer c6 = new Customer("Customer 6", 006);
		Customer c7 = new Customer("Customer 7", 007);
		Customer c8 = new Customer("Customer 8", 010);
		Customer c9 = new Customer("Customer 9", 011);
		Customer c10 = new Customer("Customer 10", 012);
		
		Rental r1 = new Rental();
		Rental r2 = new Rental();
		Rental r3 = new Rental();
		Rental r4 = new Rental();
		Rental r5 = new Rental();
		Rental r6 = new Rental();
		Rental r7 = new Rental();
		Rental r8 = new Rental();
		Rental r9 = new Rental();
		Rental r10 = new Rental();
		
		r1.addMovie(m1);
		r1.addMovie(m2);
		r1.addMovie(m3);
		r1.addMovie(m25);
		r1.addMovie(m4);
		r2.addMovie(m5);
		r2.addMovie(m6);
		r2.addMovie(m7);
		r3.addMovie(m8);
		r4.addMovie(m9);
		r4.addMovie(m24);
		r4.addMovie(m25);
		r5.addMovie(m10);
		r5.addMovie(m11);
		r5.addMovie(m12);
		r6.addMovie(m13);
		r6.addMovie(m14);
		r6.addMovie(m1);
		r6.addMovie(m15);
		r7.addMovie(m16);
		r7.addMovie(m1);
		r7.addMovie(m12);
		r7.addMovie(m17);
		r8.addMovie(m18);
		r9.addMovie(m19);
		r9.addMovie(m20);
		r10.addMovie(m21);
		r10.addMovie(m22);
		r10.addMovie(m23);
		
		c1.addRental(r1);
		c2.addRental(r2);
		c3.addRental(r3);
		c4.addRental(r4);
		c5.addRental(r5);
		c6.addRental(r6);
		c7.addRental(r7);
		c8.addRental(r8);
		c9.addRental(r9);
		c10.addRental(r10);
		c1.addRental(r4);
		c4.addRental(r1);
		c1.addRental(r6);
		c3.addRental(r3);
		c1.addRental(r8);
		c4.addRental(r10);

		v1.addCustomer(c1);
		v1.addCustomer(c2);
		v1.addCustomer(c3);
		v1.addCustomer(c4);
		v1.addCustomer(c5);
		v1.addCustomer(c6);
		v1.addCustomer(c7);
		v1.addCustomer(c8);
		v1.addCustomer(c9);
		v1.addCustomer(c10);
		
		v1.printCustomerMostRents(3);
		v1.printMostMoneyCustomers(3);
		v1.printMostPopularMovies(3);
	}
}