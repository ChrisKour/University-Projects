
public class Sphere extends Solid{

	private static int counter = 0;
	private int position;

	public Sphere(double mass, double radius) {
		super(mass, radius);
		counter++;
		position = counter;
	}

	public static int getCounter() {
		return counter;
	}
	
	public int getPosition(){
		return position;
	}
	
	public double calculateVolume(){
		return (4.0/3.0) * Math.PI * Math.pow(radius, 3);
	}
	
	public double calculateSurfaceArea(){
		return 4 * Math.PI * Math.pow(radius, 2);
	}
	
	public double calculateMomentOfInertia(){
		return (2 * mass * Math.pow(radius, 2))/5.0;
	}

	//dummy
	public double getHeight() {
		return 1;
	}
}
