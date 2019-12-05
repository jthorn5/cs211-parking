package maps;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import ParkingProj.Main;
import data.ParkingLocation;

public class Route {
	private String origin;
	private String destination;
	private long seconds;
	private long meters;
	private ParkingLocation loc;
	
	private LatLng origCoords;
	private LatLng destCoords;
	
	public Route(ParkingLocation loc, String destination) {
		this.origin = loc.getGoogleName();
		this.destination = destination;
		this.seconds = -1;
		this.meters = -1;
		this.loc = loc;
		try {
			reqAPI(origin, destination);
		} catch (Exception e) {
			System.out.println("Error with API request:");
			System.out.println(e.toString());
			System.exit(0);
		}
	}
	
	public Route(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
		this.seconds = -1;
		this.meters = -1;
		try {
			reqAPI(origin, destination);
		} catch (Exception e) {
			System.out.println("Error with API request:");
			System.out.println(e.toString());
			System.exit(0);
		}
	}

	public void reqAPI(String origin, String destination) throws Exception {
		DirectionsApiRequest req = DirectionsApi.newRequest(Main.context).origin(origin).destination(destination)
				.mode(TravelMode.WALKING);
		req.setCallback(new PendingResult.Callback<DirectionsResult>() {
			@Override
			public void onResult(DirectionsResult result) {
				DirectionsLeg leg = result.routes[0].legs[0];
				seconds = leg.duration.inSeconds;
				meters = leg.distance.inMeters;
				origCoords = leg.startLocation;
				destCoords = leg.endLocation;
			}

			@Override
			public void onFailure(Throwable e) {
				System.out.println("ERROR: " + e.toString());
				System.exit(0);
			}
		});
	}

	public boolean isComplete() {
		return seconds >= 0 && meters >= 0;
	}

	public ParkingLocation getLoc() {
		return loc;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public long getSeconds() {
		return seconds;
	}

	public long getMeters() {
		return meters;
	}
	
	public LatLng getOrigCoords() {
		return origCoords;
	}

	public LatLng getDestCoords() {
		return destCoords;
	}

	public String toString() {
		return "Origin: " + origin + "\nDestination: " + destination + "\n" + seconds + "s " + meters + "m\n";
	}

}
