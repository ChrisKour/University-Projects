
public class IndustrialInvoice extends DEHInvoice{

	public IndustrialInvoice(int code, long kw) {
		super(code, kw);
	}

	public double computeCost(){
		return super.getKw() * 0.093;
	}
}
