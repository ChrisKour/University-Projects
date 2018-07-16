
public abstract class Solid {

	protected double mass;
	protected double radius;
	
	public Solid(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getRadius() {
		return radius;
	}

	public abstract int getPosition();
	
	public abstract double calculateVolume();
	
	public abstract double calculateSurfaceArea();
	
	public abstract double calculateMomentOfInertia();
	
	public abstract double getHeight();
	
}
