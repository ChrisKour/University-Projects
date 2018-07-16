
public class HouseInvoice extends DEHInvoice{

	private int squareMeters;

	public HouseInvoice(int code, long kw, int squareMeters) {
		super(code, kw);
		this.squareMeters = squareMeters;
	}

	public int getSquareMeters() {
		return squareMeters;
	}

	public double computeCost(){
		return super.computeCost() + (squareMeters * 2.5);
	}
	
}
