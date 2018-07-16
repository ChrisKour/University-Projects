
public abstract class DEHInvoice {
	
	private int code;
	private long kw;
	
	public DEHInvoice(int code, long kw) {
		this.code = code;
		this.kw = kw;
	}

	public int getCode() {
		return code;
	}

	public long getKw() {
		return kw;
	}

	public double computeCost(){
		double sum;
		if(getKw() <=800)
			sum = getKw() * 0.05625;
		else if(getKw() <=1000)
			sum = 800*0.05625 + (getKw() - 800)*0.07850;
		else if(getKw() <=2000)
			sum = 800*0.05625 + 200*0.07850 + (getKw() - 1000) * 0.0815;
		else
			sum = 800*0.05625 + 200*0.07850 + 1000 * 0.0815 + (getKw() - 2000) * 0.09155;
		return sum;
	}
	
}
