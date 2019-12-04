package ParkingProj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import data.Restrictions;
public class GUI {
	
	private static JCheckBox[] checkLocs;
	private static JCheckBox[] restrictions;
	private static JTextArea dest;
	
	private static final int width = 640;
	private static final int height = 480;
	
    public static void initGui(ArrayList<String> names) {    
        // Creating instance of JFrame
        JFrame frame = new JFrame("Parking Project");
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
        
        
        /* calling user defined method for adding components
         * to the panel.
         */
        
        placeComponents(panel,names);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel, ArrayList<String> names) {

        /* We will discuss about layouts in the later sections
         * of this tutorial. For now we are setting the layout 
         * to null
         */
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
        
        int numR = Restrictions.values().length;
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
        
        /*JCheckBox check5 = new JCheckBox("Handicap");
        check5.setBounds(95, 280, 100, 30);
        panel.add(check5);
        
        JCheckBox check6 = new JCheckBox("Faculty");
        check6.setBounds(215, 280, 100, 30);
        panel.add(check6);
        
        JCheckBox check7 = new JCheckBox("Reserved");
        check7.setBounds(335, 280, 100, 30);
        panel.add(check7);*/
        
        /* Creating text field where user is supposed to
         * enter text
         */
        
        JLabel destLabel = new JLabel("Enter Destination:");
        destLabel.setBounds(20, 335, 100, 25);
        panel.add(destLabel);
        
        dest = new JTextArea();
        dest.setBounds(20,360,580,18);
        panel.add(dest);

        // Creating login button
        JButton search = new JButton("Search");
        search.setBounds(240, 390, 80, 25);
        panel.add(search);
        
        //Opens pop up window
        JFrame newFrame = new JFrame("New Window");
        search.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		newFrame.pack();
        		newFrame.setVisible(true);
        		newFrame.getContentPane().setLayout(null);
        		newFrame.setSize(500,500);
        		JLabel Label = new JLabel("Map");
        		Label.setBounds(100,230,100,25);
                newFrame.add(Label);
        	}
        });
    }
}
