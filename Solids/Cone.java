
public class Cone extends Solid{
	
	private static int counter = 0;
	private int position;
	private double height;

	public Cone(double mass, double radius,double height) {
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
		return (1.0/3.0) * Math.PI * Math.pow(radius, 2) * height;
	}
	
	public double calculateSurfaceArea(){
		return Math.PI * radius * (radius + height);
	}
	
	public double calculateMomentOfInertia(){
		return mass * Math.pow(radius, 2) * (3.0/10.0);
	}
	
	public double getHeight() {
		return height;
	}
}
