public class Device {

	private int pmax;
	private double powerFactor;
	private int v;
	
	public Device(int pmax, double powerFactor) {
		this.pmax = pmax;
		this.powerFactor = powerFactor;
		v = 230;
	}
	
	public double computeMaxAmperePerDevice(){
		return pmax/(v*powerFactor);
	}
}
