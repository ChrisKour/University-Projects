/* Author: Chris Kourounis */

public class Main {

	public static void main(String[] args) {

		ElectricBoard b1 = new ElectricBoard();
		
		ElectricLine l1 = new ElectricLine();
		ElectricLine l2 = new ElectricLine();
		ElectricLine l3 = new ElectricLine();
		
		Device d1 = new Device(3500,0.9);
		Device d2 = new Device(10000,1);
		Device d3 = new Device(1000,1);
		Device d4 = new Device(500,0.8);
		Device d5 = new Device(35000,1);
		Device d6 = new Device(4000,0.7);
		Device d7 = new Device(100,0.2);
		Device d8 = new Device(8500,0.6);
		Device d9 = new Device(3500,0.4);
		
		b1.addElectricLine(l1);
		b1.addElectricLine(l2);
		b1.addElectricLine(l3);
		
		l1.addDevice(d1);
		l1.addDevice(d2);
		l1.addDevice(d3);
		l2.addDevice(d4);
		l2.addDevice(d5);
		l2.addDevice(d6);
		l3.addDevice(d7);
		l3.addDevice(d8);
		l3.addDevice(d9);
		
		System.out.println("1h asfaleia:");
		l1.computeSecureAmpere();
		System.out.println("---------------------");
		System.out.println("2h asfaleia:");
		l2.computeSecureAmpere();
		System.out.println("---------------------");
		System.out.println("3h asfaleia:");
		l3.computeSecureAmpere();
		System.out.println("---------------------\n");
		
		System.out.println("1h asfaleia:");
		l1.computeWire();
		System.out.println("---------------------");
		System.out.println("2h asfaleia:");
		l2.computeWire();
		System.out.println("---------------------");
		System.out.println("3h asfaleia:");
		l3.computeWire();
		System.out.println("---------------------\n");
		
		b1.computeGeneral();	
	}
}
