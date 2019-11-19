package ParkingProj;

import java.io.IOException;
import java.util.ArrayList;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;

import maps.Route;
import maps.SortRoutesByTime;

public class Main {
	public static GeoApiContext context;
	
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		long before = System.currentTimeMillis();
		
		//Create API context
		context = new GeoApiContext.Builder().apiKey("AIzaSyAtJs3U_R4QQn7erwwovN1WZMHSOJ50yCs").build();
		
		System.out.println(System.currentTimeMillis() - before);
		
		// read parking locations from config
		ArrayList<String> locations = new ArrayList<String>();
		locations.add("Lot K General, Patriot Circle, Fairfax, VA");
		locations.add("Lot M GMU, University Dr, Fairfax, VA 22030");

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
					break;
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