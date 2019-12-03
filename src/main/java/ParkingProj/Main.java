package ParkingProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;

import maps.Route;
import maps.SortRoutesByTime;

public class Main {
	public static GeoApiContext context;

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		//System.out.println(Util.haversineFeet(36.12, -86.67, 33.94, -118.40)); //9470067.658832366 ft
		//https://maps.googleapis.com/maps/api/staticmap?center=George+Mason+Statue,Fairfax,VA&zoom=15&size=600x300&maptype=roadmap&markers=color:blue|label:S|40.702147,-74.015794&markers=color:green|label:G|40.711614,-74.012318&markers=color:red|label:C|40.718217,-73.998284&key=AIzaSyAh3NSShZO_ErpmjyHRKtjtOFrwfk0t2FQ
		//Read in API key
		Scanner sc = new Scanner(new File("key.txt"));
		String key = "";
		if (sc.hasNextLine()) {
			key = sc.nextLine();
		}
		sc.close();

		//TODO read parking locations from config
		readConfig();
		ArrayList<String> locations = new ArrayList<String>();
		locations.add("Lot A GMU, Fairfax, VA");
		locations.add("Lot C, Fairfax, VA");
		locations.add("Lot K General, Patriot Circle, Fairfax, VA");
		locations.add("LOT L General Permit Parking, Fairfax, VA");
		locations.add("Lot M GMU, University Dr, Fairfax, VA"); 
		locations.add("Lot O General, Fairfax, VA");
		locations.add("Lot P, University Drive, Fairfax, VA");
		locations.add("West Campus, Fairfax, VA");
		locations.add("Rapidan River Road, Fairfax, VA");
		locations.add("Lot R Resident, Fairfax, VA");
		locations.add("Lot I Reserved, Aquia Creek Lane, Fairfax, VA");
		locations.add("Lot J, Mason Pond Drive, Fairfax, VA");
		locations.add("Mason Pond Parking Deck, Fairfax, VA");
		locations.add("Mason Global Center (INTO George Mason University), Mason Pond Drive, Fairfax, VA");
		locations.add("Shenandoah Parking Deck, Fairfax, VA");
		locations.add("Rappahannock River Parking Deck, Rappahannock River Parking Deck, Fairfax, VA");

		//TODO get desired destination from user
		String destination = "Starbucks, Johnson Center, 4400 University Dr Johnson Center, Fairfax, VA 22030";
		
		//Create API context
		context = new GeoApiContext.Builder().apiKey(key).build();
		
		// Make API requests
		ArrayList<Route> routes = new ArrayList<Route>();
		for (String loc : locations) {
			routes.add(new Route(loc, destination));
		}

		// Wait until all routes are calculated
		System.out.println("Calculating optimal location...");
		boolean wait = true;
		while (wait) {
			//System.out.println("Waiting 200ms");
			Thread.sleep(200);
			wait = false;
			for (Route r : routes) {
				if (!r.isComplete()) {
					wait = true;
					break;
				}
			}
		}
		
		System.out.println();
		routes.sort(new SortRoutesByTime());
		/*
		 * for (Route r : routes) { System.out.println(r); }
		 */
		System.out.println(routes.get(0));
	}
	public static void readConfig() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("ParkingData.txt"));
		String line = "";
		String[] format = {"Location", "Google Name", "Levels", "Faculty spaces","General Spaces","Reserved Spaces","Disabled Spaces","Visitor Spaces"};
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if (line.startsWith(format[0]+": ")) {
				String[] data = new String[format.length];
				System.out.println("===");
				for (int i = 0; i < data.length; i++) {
					//System.out.println(line);
					data[i] = line.split(format[i]+": ")[1];
					if (!sc.hasNextLine()) break;
					line = sc.nextLine();
				}
				for (int i = 0; i < data.length; i++) {
					System.out.println(data[i]);
				}
				
			}else {
				//System.out.println(line);
			}
		}
		sc.close();
	}
}
