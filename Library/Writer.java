public class Writer {

	private int afm;
	private String name;
	private double kerdos;
	
	public Writer(String name, int afm, double kerdos) {
		
		this.name = name;
		this.afm = afm;
		this.kerdos = kerdos;
	}

	public double getKerdos() {
		return kerdos;
	}

	public int getAfm() {
		return afm;
	}

	public String getName() {
		return name;
	}
}
