package ParkingProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import data.Driver;
import data.ParkingDeck;
import data.ParkingLocation;
import data.ParkingLot;
import data.Restrictions;
import maps.Route;
import maps.SortRoutesByTime;

public class Main {
	private static String key;
	public static GeoApiContext context;
	
	private static HashMap<String,ParkingLocation> locNames = new HashMap<String,ParkingLocation>();
	private static ArrayList<ParkingLocation> locations = new ArrayList<ParkingLocation>();
	
	//defaults for in case of error, overwritten by readConfig
	public static String campus;
	public static String center;
	public static int radius;
	
	public static Route latestRoute;
	
	public static void main(String[] args) {
		// https://maps.googleapis.com/maps/api/staticmap?center=George+Mason+Statue,Fairfax,VA&zoom=15&size=600x300&maptype=roadmap&markers=color:blue|label:S|40.702147,-74.015794&markers=color:green|label:G|40.711614,-74.012318&markers=color:red|label:C|40.718217,-73.998284&key=AIzaSyAh3NSShZO_ErpmjyHRKtjtOFrwfk0t2FQ
		// Read in API key

		key = readKey();

		locations = readConfigSafe();
		
		// Create API context
		context = new GeoApiContext.Builder().apiKey(key).build();
		
		// TODO get desired destination from user
		//String destination = "Starbucks, Johnson Center, 4400 University Dr Johnson Center, Fairfax, VA 22030";
		
		for (ParkingLocation l : locations) {
			locNames.put(l.getName(), l);
		}
		
		GUI.initGui(new ArrayList<String>(locNames.keySet()));
		
		
	}
	
	public static ParkingLocation checkLocations(ArrayList<String> locs, String destination, Driver d) throws InterruptedException {
		if (locs.size() == 0) {
			GUI.progress.setText("No locations specified.");
			return null;
		}
		ArrayList<Route> routes = new ArrayList<Route>();
		for (ParkingLocation loc : locations) {
			//System.out.println(loc.getName());
			if (!loc.isAvailable(d)) {
				System.out.println("Unavailable: "+loc.getName());
			}
			if (locs.contains(loc.getName()) && loc.isAvailable(d)) {
				routes.add(new Route(loc, destination));
			}
		}
		return reqAPI(locs,destination,routes);
	}
	
	public static ParkingLocation reqAPI(ArrayList<String> locs, String destination, ArrayList<Route> routes) throws InterruptedException {
		

		// Wait until all routes are calculated
		System.out.println("Calculating optimal location...");
		boolean wait = true;
		while (wait) {
			// System.out.println("Waiting 200ms");
			Thread.sleep(200);
			wait = false;
			for (Route r : routes) {
				if (!r.isComplete()) {
					wait = true;
					break;
				}
			}
		}

		routes.sort(new SortRoutesByTime());

		System.out.println(routes.get(0));
		latestRoute = routes.get(0);
		return routes.get(0).getLoc();
	}
	
	public static ImageIcon mapImg(String orig, String dest,int width, int height) throws MalformedURLException {
		orig = URLEncoder.encode(orig);
		dest = URLEncoder.encode(dest);
		return new ImageIcon(new URL("https://maps.googleapis.com/maps/api/staticmap?center=George+Mason+Statue,Fairfax,VA&zoom=15&size="+width+"x"+height+"&maptype=roadmap&markers=color:blue|"+dest+"&markers=color:red|"+orig+"&key="+key));
	}

	private static String readKey() {
		String key = "";
		try {
			Scanner sc = new Scanner(new File("key.txt"));
			if (sc.hasNextLine()) {
				key = sc.nextLine();
			}
			sc.close();
			if (key.equals("")) {
				System.out.println("Key file empty");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("Error loading API key");
			System.exit(0);
		}
		return key;
	}

	//Random Driver generator created by Ching 
	public Driver randomDriver() {
		String[] parkingPasses = {"General", "Reserved", "Faculty", "Handicap"};
		String randomParkingPass = parkingPasses[(int)Math.random()*((4 - 0)+1)+0];
		int randomDriver = (int)Math.random()*(999999 - 100000)+100000;
		locations.clone()
		Driver random = new Driver(randomDriver, randomParkingPass );
		ParkingLocation.addCar(random);
		return random; 
	}
	private static ArrayList<ParkingLocation> readConfig() throws FileNotFoundException {
		ArrayList<ParkingLocation> locs = new ArrayList<ParkingLocation>();
		Scanner sc = new Scanner(new File("ParkingData.txt"));
		String line = "";
		String[] format = { "Location", "Google Name", "Levels", "Faculty spaces", "General Spaces", "Reserved Spaces",
				"Disabled Spaces", "Visitor Spaces" };
		int numStrings = 2;
		String[] stringData = new String[numStrings];
		int[] intData = new int[format.length - numStrings];
		String tmpData;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if (line.startsWith("Campus:")) {
				campus = line.split("Campus:")[1].toLowerCase();
			}else if (line.startsWith("Center:")) {
				center = line.split("Center: ")[1];
			}else if (line.startsWith("Radius ft: ")) {
				try {
					radius = Integer.parseInt(line.split("Radius ft: ")[1]);
				}catch (Exception e) {
					System.out.println("Error parsing config on line:" + "\n" + line);
					System.exit(0);
				}
			}else if (line.startsWith(format[0] + ": ")) {
				for (int i = 0; i < format.length; i++) {
					tmpData = line.split(format[i] + ": ")[1];
					if (i < numStrings) {
						stringData[i] = tmpData;
					} else {
						try {
							intData[i - numStrings] = Integer.parseInt(tmpData);
						} catch (Exception e) {
							System.out.println("Error parsing config on line:" + "\n" + line);
							System.exit(0);
						}
					}
					if (!sc.hasNextLine())
						break;
					line = sc.nextLine();
				}

				String name = stringData[0];
				String gName = stringData[1];
				int price = 0;
				int levels = intData[0];
				int handicapped = intData[4];
				int staff = intData[1];
				int visitor = intData[5];

				int totalSpaces = 0;
				for (int i = 1; i < intData.length; i++) {
					totalSpaces += intData[i];
				}
				//System.out.println(name);
				if (intData[0] > 1) { // Number of levels
					locs.add(new ParkingDeck(name, gName, price, levels, totalSpaces, handicapped, staff, visitor));
				} else {
					locs.add(new ParkingLot(name, gName, price, totalSpaces, handicapped, staff, visitor));
				}
			}
		}
		sc.close();
		
		if (campus.equals("") || center.equals("") || radius == 0) {
			System.out.println("Campus, center, and radius were not all specified.");
			System.exit(0);
		}
		
		return locs;
	}

	private static ArrayList<ParkingLocation> readConfigSafe() {
		try {
			return readConfig();
		} catch (Exception e) {
			System.out.println("Failed to load ParkingData.txt");
			System.exit(0);
			return null; // will never be called because of exit but required to compile
		}
	}
}
