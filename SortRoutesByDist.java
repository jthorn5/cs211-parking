package MapsAPI;

import java.util.Comparator;

public class SortRoutesByDist implements Comparator<Route> {

	@Override
	public int compare(Route r1, Route r2) {
		long diff = r1.getMeters() - r2.getMeters();
		if (diff == 0) {
			return 0;
		}else if (diff > 0) {
			return 1;
		}else {
			return -1;
		}
	}

}
