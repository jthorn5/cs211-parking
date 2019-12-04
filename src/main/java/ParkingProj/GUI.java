import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class SwingFirstExample {
    
    public static void main(String[] args) {    
        // Creating instance of JFrame
        JFrame frame = new JFrame("My First Swing Example");
        // Setting the width and height of frame
        frame.setSize(500, 500);
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
        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        /* We will discuss about layouts in the later sections
         * of this tutorial. For now we are setting the layout 
         * to null
         */
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("Parking Search");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(200,20,100,25);
        panel.add(userLabel);
        
        
        /*
         * Check boxes
         */
        
        JCheckBox check1 = new JCheckBox("Parking lot 1");
        check1.setBounds(190, 70, 100, 30);
        panel.add(check1);
        
        JCheckBox check2 = new JCheckBox("Parking lot 2");
        check2.setBounds(190, 110, 100, 30);
        panel.add(check2);
        
        JCheckBox check3 = new JCheckBox("Parking lot 3");
        check3.setBounds(190, 150, 100, 30);
        panel.add(check3);

        JCheckBox check4 = new JCheckBox("Parking lot 4");
        check4.setBounds(190, 190, 100, 30);
        panel.add(check4);
        
        JCheckBox check5 = new JCheckBox("Handicap");
        check5.setBounds(95, 250, 100, 30);
        panel.add(check5);
        
        JCheckBox check6 = new JCheckBox("Faculty");
        check6.setBounds(215, 250, 100, 30);
        panel.add(check6);
        
        JCheckBox check7 = new JCheckBox("Reserved");
        check7.setBounds(335, 250, 100, 30);
        panel.add(check7);
        
        /* Creating text field where user is supposed to
         * enter text
         */
        JTextArea userText = new JTextArea(4,5);
        userText.setBounds(100,300,300,100);
        panel.add(userText);
        
        

        /*This is similar to text field but it hides the user
         * entered data and displays dots instead to protect
         * the password like we normally see on login screens.
         */
        

        // Creating login button
        JButton search = new JButton("Search");
        search.setBounds(200, 420, 80, 25);
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