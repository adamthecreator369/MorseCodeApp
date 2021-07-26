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

    public CustomButton() {
    	this(null);
    }

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
    	if (getModel().isPressed()) {
    		g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public Color getHoverBackgroundColor() {
    	return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}