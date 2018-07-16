import java.text.DecimalFormat;
import java.util.ArrayList;

public class ElectricBoard {

	private ArrayList<ElectricLine> electricLines;
	
	public ElectricBoard(){
		electricLines =  new ArrayList<>();
	}
	
	DecimalFormat myFormat = new DecimalFormat("##.##");
	
	public void computeGeneral(){
		double maxAmpere = 0;
		double temp;
		for(ElectricLine aLine : electricLines) {
			temp = aLine.computeMaxAmpere();
			if(temp > maxAmpere)
				maxAmpere = temp;
		}
		System.out.println("Entash genikou diakopth: "+myFormat.format(maxAmpere));
	}
	
	public void addElectricLine(ElectricLine eLine){
		electricLines.add(eLine);
	}
}
