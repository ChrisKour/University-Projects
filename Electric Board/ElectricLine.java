import java.text.DecimalFormat;
import java.util.ArrayList;

public class ElectricLine {

	private ArrayList<Device> devices;

	public ElectricLine() {
		devices =  new ArrayList<>();
	}
	
	public double computeMaxAmpere(){
		double iMax=0;
		for(Device aDevice : devices) {
			iMax += aDevice.computeMaxAmperePerDevice();
		}
		return iMax;
	}
	
	DecimalFormat myFormat = new DecimalFormat("##.##");
	
	public void computeSecureAmpere(){
		double iMax = computeMaxAmpere();
		double iSecure = iMax - 5;
		System.out.println("Timh entashs ths asfaleias: "+myFormat.format(iSecure));
	}
	
	public void computeWire(){
		double iMax = computeMaxAmpere();
		double d = 0.021*(Math.pow(iMax, 1.61));
		System.out.println("Diatomh kalwdiou: "+myFormat.format(d));
	}
	
	public void addDevice(Device device){
		devices.add(device);
	}
}
