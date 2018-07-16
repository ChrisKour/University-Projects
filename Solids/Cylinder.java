
public class Cylinder extends Solid{
	
	private static int counter = 0;
	private int position;
	private double height;

	public Cylinder(double mass, double radius,double height) {
		super(mass, radius);
		this.height = height;
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
		return Math.PI * Math.pow(radius, 2) * height;
	}
	
	public double calculateSurfaceArea(){
		return 2 * Math.PI * radius * (radius + height);
	}
	
	public double calculateMomentOfInertia(){
		return (mass * Math.pow(radius, 2))/2.0;
	}

	public double getHeight() {
		return height;
	}
}
