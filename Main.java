package MapsAPI;

import java.io.IOException;
import java.util.ArrayList;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;

public class Main {
	public static GeoApiContext context;
	
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		long before = System.currentTimeMillis();
		
		//Create API context
		context = new GeoApiContext.Builder().apiKey("KEY_HERE").build();
		
		System.out.println(System.currentTimeMillis() - before);
		
		//list of parking locations
		// lot a, lot c, lot k, lot l, lot m, lot o, lot p, west campus, rapidan rr, lot r, lot i, lot j, mason pond,
		//global center, shenn, rap deck
		
		// read parking locations from config
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

		//get desired destination from user
		String destination = "Starbucks, Johnson Center, 4400 University Dr Johnson Center, Fairfax, VA 22030";
		
		//TODO check that location is on campus
		
		//Make API requests
		ArrayList<Route> routes = new ArrayList<Route>();
		for (String loc : locations) {
			routes.add(new Route(loc, destination));
		}
		
		//Wait until all routes are calculated
		boolean wait = true;
		while (wait) {
			System.out.println("Waiting 200ms");
			Thread.sleep(200);
			wait = false;
			for (Route r : routes) {
				if (!r.isComplete()) {
					wait = true;
				}
			}
		}
		System.out.println();
		routes.sort(new SortRoutesByTime());
		/*for (Route r : routes) {
			System.out.println(r);
		}*/
		System.out.println(routes.get(0));
		System.out.println(System.currentTimeMillis() - before);
	}
}
