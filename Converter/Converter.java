/*Author: Chris Kourounis */

import java.util.Scanner;

public class Converter {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("This program does 3 simple conversions.");
        System.out.print("Miles to Km: ");
        double miles = in.nextDouble();
        System.out.printf(miles + " miles = %.2f Km\n", miles*1.609344);

        System.out.print("Feet to meters: ");
        double feet = in.nextDouble();
        System.out.printf(feet + " feet = %.2f meters\n", feet*0.3048);

        System.out.print("Gallons to liters: ");
        double gallons = in.nextDouble();
        System.out.printf(gallons + " gallons = %.2f liters\n", gallons*3.78541178);
    }
}
