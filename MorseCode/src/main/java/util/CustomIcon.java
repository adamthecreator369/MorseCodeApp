package main.java.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import main.java.resources.R;

public class CustomIcon implements Icon {
	Color color;
    public CustomIcon() {
	    this(Color.WHITE);
    }
    public CustomIcon(Color c) {
    	color = c;
    }
    public void paintIcon(Component c, Graphics g, int x, int y) {
    	g.setColor(color);
    	g.fillOval(x, y, getIconWidth(), getIconHeight());
    	g.setColor(R.color.DARK_GRAY);
    	g.drawOval(x, y, getIconWidth(), getIconHeight());
    }
    public int getIconWidth() {
    	return 10;
    }
    public int getIconHeight() {
    	return 10;
    }
    public void setColor(Color c) {
    	color = c;
    }
    
}