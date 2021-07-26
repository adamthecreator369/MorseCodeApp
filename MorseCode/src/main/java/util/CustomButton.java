/* Created by Neha Metlapalli on 07/25/2021 */
/* Class is a modified version of the code from this source: https://stackoverflow.com/a/14627771 */

package main.java.util;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import main.java.resources.R;

@SuppressWarnings("serial")
public class CustomButton extends JButton {

	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    // Default constructor
    public CustomButton() {
    	this(null);
    }

    // Constructor
    public CustomButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setBackground(R.color.WHITE);
        setHoverBackgroundColor(Color.decode("#f5f7f6"));
        setPressedBackgroundColor(Color.decode("#ededed"));
        setBorder(new LineBorder(R.color.WHITE));
    }

    @Override
    protected void paintComponent(Graphics g) {
    	if (getModel().isPressed()) { // If the button is pressed
    		g.setColor(pressedBackgroundColor); // Set the background color of the button to pressedBackgroundColor
        } else if (getModel().isRollover()) { // Else if the mouse if over the button
            g.setColor(hoverBackgroundColor); // Set the background color of the button to hoverBackgroundColor
        } else { // Else if the mouse is not on the button
            g.setColor(getBackground()); // Set the background color of the button to the default background color
        }
        g.fillRect(0, 0, getWidth(), getHeight()); // Now create the background rectangle of the button
        super.paintComponent(g); 
    }

    // Getters
    public Color getHoverBackgroundColor() {
    	return hoverBackgroundColor;
    }
    
    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    // Setters
    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}
