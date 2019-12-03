package ParkingProj;

public class Util {
	public static final double R = 3958.8; //approx radius of the earth in miles
	
	//Haversine implementation written by Rosetta Code
	//https://rosettacode.org/wiki/Haversine_formula#Java
	public static double haversineMiles(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
	
	public static double haversineFeet(double lat1, double lon1, double lat2, double lon2) {
		return milesToFeet(haversineMiles(lat1,lon1,lat2,lon2));
	}
	
	public static double milesToFeet(double miles) {
		return miles*5280;
	}
}
