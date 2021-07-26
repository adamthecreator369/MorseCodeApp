/* Created by Neha Metlapalli on 07/25/2021 */
/* Code is a modified version of the following source: https://coderanch.com/t/456966/java/give-color-JRadioButtons */

package main.java.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import main.java.resources.R;

public class CustomIcon implements Icon {
	Color color;
	
	// Default constructor
    public CustomIcon() {
	    this(Color.WHITE);
    }
    
    // Constructor
    public CustomIcon(Color c) {
    	color = c;
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
    	g.setColor(color); // Set the color of the radio button icon.
    	g.fillOval(x, y, getIconWidth(), getIconHeight()); // Now fill an oval with that color.
    	// Change the color now to dark gray and create a border around the radio button icon
    	g.setColor(R.color.DARK_GRAY); 
    	g.drawOval(x, y, getIconWidth(), getIconHeight());
    }
    
    // Getters
    public int getIconWidth() {
    	return 10;
    }
    
    public int getIconHeight() {
    	return 10;
    }
    
    // Setter
    public void setColor(Color c) {
    	color = c;
    }
}
