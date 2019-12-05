package ParkingProj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.google.maps.model.LatLng;

import data.Driver;
import data.ParkingLocation;
import data.ParkingPass;
import data.Restrictions;
import maps.Route;
public class GUI {
	
	private static JCheckBox[] checkLocs;
	private static JCheckBox[] restrictions;
	
	private static JTextArea dest;
	private static JTextArea license;
	
	public static JLabel progress;
	private static final int width = 640;
	private static final int height = 480;
	private static JFrame frame;
	
    public static void initGui(ArrayList<String> names) {    
        // Creating instance of JFrame
        frame = new JFrame("Parking Project");
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("Parking Search");
        userLabel.setFont(userLabel.getFont().deriveFont(24.0f));
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are coordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(200,10,300,25);
        panel.add(userLabel);
        
        
        /*
         * Check boxes
         */
        JLabel allowedTitle = new JLabel("Allowed Parking Lots");
        allowedTitle.setBounds(215, 45, 150, 25);
        panel.add(allowedTitle);
        checkLocs = new JCheckBox[names.size()];
        for (int i = 0; i < names.size(); i++) {
        	int j = i / 6;
        	JCheckBox check = new JCheckBox(names.get(i));
            check.setBounds(20+200*j, 90+30*(i%6), 200, 30);
            check.setSelected(true);
            checkLocs[i] = check;
            panel.add(check);
        }
        
        //https://stackoverflow.com/questions/3599908/how-to-check-that-a-jcheckbox-is-checked
        JCheckBox checkAll = new JCheckBox("Select/Deselect All");
        checkAll.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                for (int i = 0; i < checkLocs.length; i++) {
                	checkLocs[i].setSelected(checked);
                }
            }
        });
        checkAll.setBounds(20, 60, 150, 30);
        checkAll.setSelected(true);
        panel.add(checkAll);
        
        JLabel restrLabel = new JLabel("Parking Permissions");
        restrLabel.setBounds(width/2-100, 275, 150, 25);
        panel.add(restrLabel);
        
        int numR = Restrictions.values().length-1;
        restrictions = new JCheckBox[numR];
        for (int i = 0; i < numR; i++) {
        	Restrictions r = Restrictions.values()[i];
        	if (r.toString().equals("NONE")) continue;
        	String name = r.toString().toLowerCase();
        	name = name.substring(0,1).toUpperCase()+name.substring(1);
        	
        	JCheckBox check = new JCheckBox(name);
        	check.setBounds(20+(i)*125,300,100,25);
        	restrictions[i] = check;
        	panel.add(check);
        }
        
        JLabel destLabel = new JLabel("Enter Destination:");
        destLabel.setBounds(20, 335, 100, 25);
        panel.add(destLabel);
        
        dest = new JTextArea();
        dest.setBounds(20,360,380,18);
        panel.add(dest);

        JLabel licenseLabel = new JLabel("Enter License Plate:");
        licenseLabel.setBounds(420, 335, 150, 25);
        panel.add(licenseLabel);
        
        license = new JTextArea();
        license.setBounds(420,360,150,18);
        panel.add(license);
        
        // Creating login button
        JButton search = new JButton("Search");
        search.setBounds(240, 390, 80, 25);
        panel.add(search);
        
        progress = new JLabel("");
        progress.setBounds(20, 390, 200, 25);
        panel.add(progress);
        
        //Opens pop up window
        
        search.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		try {
        			submit();
        		}catch(InterruptedException ex) {
        			System.out.println(ex.toString());
            		System.exit(0);
        		}
        	}
        });
        
        // Setting the frame visibility to true
        frame.setVisible(true);
    }
    private static void submit() throws InterruptedException {
    	if (dest.getText().equals("")) {
    		progress.setText("No destination specified.");
    		return;
    	}
    	if (license.getText().equals("")) {
    		progress.setText("No license specified.");
    		return;
    	}
    	if (!dest.getText().toLowerCase().contains(Main.campus)) {
    		dest.setText(dest.getText()+Main.campus);
    	}
    	Route r = new Route(Main.center,dest.getText());
    	while (!r.isComplete()) {
    		Thread.sleep(200);
    	}
    	if (!Util.checkDist(r.getOrigCoords(), r.getDestCoords(), Main.radius)) {
    		progress.setText("Location off campus.");
    		return;
    	}
    	ArrayList<String> locs = new ArrayList<String>();
    	for (JCheckBox b : checkLocs) {
    		if (b.isSelected()) {
    			locs.add(b.getText());
    		}
    	}
    	ArrayList<Restrictions> rest = new ArrayList<Restrictions>();
    	for (JCheckBox b : restrictions) {
    		if (b.isSelected()) {
    			rest.add(Restrictions.valueOf(b.getText().toUpperCase()));
    		}
    	}
    	ParkingPass p = new ParkingPass(locs,rest);
    	Driver d = new Driver(license.getText(),p);
    	ParkingLocation park = Main.checkLocations(locs,dest.getText(),d);
    	if (park == null) {
    		return;
    	}
    	try {
    		popup(park,dest.getText(),Util.toReadable(Main.latestRoute.getSeconds()));
    	}catch(Exception e) {
    		System.out.println("Error loading image.");
    	}
    	
    }
    
    private static void popup(ParkingLocation loc, String d,String time) throws MalformedURLException {
    	String o = loc.getGoogleName();
    	int pW = width*7/8;
    	int pH = height*7/8;
    	
    	JFrame pop = new JFrame("Results");
    	pop.setSize(pW,pH); //560 x 420
        pop.setLocationRelativeTo(null);
        
    	JPanel panel = new JPanel();
    	pop.add(panel);
    	panel.setLayout(null);
        
        JLabel img = new JLabel(Main.mapImg(o, d,pW,pH-100));
        img.setBounds(0, 0, pW, pH-100);
        panel.add(img);
        //loc.getLocName()
        /*JLabel locLabel = new JLabel("Test");
        locLabel.setBounds(40, 400, 200, 25);
        panel.add(locLabel);*/

        // Creating JLabel
        JLabel locLabel = new JLabel("Parking Location: "+loc.getName());
        locLabel.setFont(locLabel.getFont().deriveFont(16.0f));
        locLabel.setBounds(20,320,500,25);
        panel.add(locLabel);
        
        JLabel timeLbl = new JLabel("Walk duration: "+time);
        timeLbl.setFont(locLabel.getFont().deriveFont(14.0f));
        timeLbl.setBounds(20,340,500,25);
        panel.add(timeLbl);
        pop.setVisible(true);
    }
}
