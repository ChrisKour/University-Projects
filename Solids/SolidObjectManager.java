import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class SolidObjectManager {

	private ArrayList<Solid> solids;
	
	public SolidObjectManager(){
		solids = new ArrayList<>();
	}
	
	public void addSolid(Solid solid){
		solids.add(solid);
	}
	
	DecimalFormat myFormat = new DecimalFormat("##.###");
	
	public void printResults(){
		for(Solid s: solids){
			if(s.getClass().getName() == "Sphere")
				System.out.println(s.getClass().getName()+" no "+s.getPosition()+ " mass = "+ s.getMass()+" kg and radius = "+s.getRadius()+" m");
			else
				System.out.println(s.getClass().getName()+" no "+s.getPosition()+ " mass = "+ s.getMass()+" kg, base radius = "+s.getRadius()+" m, height = "+s.getHeight()	+" m");
			System.out.println("- Volume: " +myFormat.format(s.calculateVolume())+" m^3");
			System.out.println("- Surface area: " +myFormat.format(s.calculateSurfaceArea())+" m^2");
			System.out.println("- Moment of Inertia: "+myFormat.format(s.calculateMomentOfInertia())+" kg.m^2");
		}
	}
	
	public void userInteraction(){
		Scanner in = new Scanner(System.in);
		System.out.println("How many solids do you want to make?");
		int numObjects = in.nextInt();
		
		for(int i=0; i<numObjects; i++){
			System.out.println("What sort of solid do you want to create? 1 = Cylinder, 2 = Cone, 3 = Sphere");
			int kindofsolid = in.nextInt();
			System.out.println("Give desired mass");
			double mass = in.nextDouble();
			System.out.println("Give desired radius");
			double radius = in.nextDouble();

			if(kindofsolid == 3){
				Solid s = new Sphere(mass,radius);
				addSolid(s);
			}
			else{
				System.out.println("Give desired height");
				double height = in.nextDouble();
				if(kindofsolid == 1){
					Solid s = new Cylinder(mass,radius,height);
					addSolid(s);
				}
				else{
					Solid s = new Cone(mass,radius,height);
					addSolid(s);
				}
			}
		}
	}
}
