
public class HouseNightInvoice extends HouseInvoice{

	private int nightKW;

	public HouseNightInvoice(int code, long kw, int squareMeters, int nightKW) {
		super(code, kw, squareMeters);
		this.nightKW = nightKW;
	}
	
	public double computeCost(){
		return super.computeCost() + nightKW * 0.054;
	}
}
