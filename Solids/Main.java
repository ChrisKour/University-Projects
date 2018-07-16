/* Author: Chris Kourounis */

public class Main {

	public static void main(String[] args) {
		SolidObjectManager s1 = new SolidObjectManager();

		s1.userInteraction();
		s1.printResults();
		System.out.println("------------------------------------------------");
		System.out.println("Total Spheres made: "+Sphere.getCounter());
		System.out.println("Total Cones made: "+Cone.getCounter());
		System.out.println("Total Cylinders made: "+Cylinder.getCounter());
	}

}
