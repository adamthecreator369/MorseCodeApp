/* Created by Adam Jost on 07/14/2021 */
/* Updated by Adam Jost on 07/20/2021 to fit current project. */
/* Updated by Adam Jost on 07/26/2021. addition of customJScrollPane() and spaceBtnsEvenly() methods. */

package main.java.layouts;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.java.resources.R;
import main.java.util.CustomButton;

public abstract class Layout {
	
	// Data fields 
	private JComponent[] components;
	
	/** Sets the JComponennt[], components, to the argument JComponent[]. */
	protected void setComponents(JComponent[] components) {
		this.components = components;
	}
	
	/** Instantiates all layout components */
	protected abstract void initComponents();

	/** Sets all layout's component's settings */
	protected abstract void componentSettings();

	/** Sets the font of all components containing text. */
	protected abstract void setFont();

	/** Adds all LoginLayout components to a Frame */
	protected void addComponents(Frame frame) {
		for (JComponent component : components) {
			frame.add(component);
		}
	}
	
	/** Shows the layout by setting all component's visibility to true. */
	protected void show() {
		for (JComponent component : components) {
			component.setVisible(true);
		}
	}
	
	/** Sets bounds of a layouts buttons spaced evenly across a constant Y axis. */
	protected void spaceBtnsEvenly(CustomButton[] btns, int startingX, int constantY) {
		int btnX = startingX;
		// Iterate through the array increasing the X position of the button each iteration. 
		for (int i = 0; i < btns.length; i++, btnX = i==0 ? btnX : btnX + R.dimens.BTN_SPACING) {
			btns[i].setBounds(btnX, constantY, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);
		}
	}
	
	/** Creates a JScrollPane with a custom ScrollBar and ArrowButtons 
	 * 
	 * @param area: the JTextArea for the JScrollPane.
	 * @return: the customized JScrollPane.
	 */
	protected JScrollPane customJScrollPane(JTextArea area) {
		// Create a new JScrollPane to customize and return. 
		JScrollPane pane = new JScrollPane(area);
		// Change the colors of the ScrollBar and the ArrowButtons.
		pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = R.color.THUMB;
				this.thumbHighlightColor = R.color.THUMB_HIGHLIGHT;
				this.thumbLightShadowColor = R.color.BG_COLOR;
				this.thumbDarkShadowColor = R.color.DARK_GRAY;
				this.trackColor = R.color.MID_GRAY;
				this.trackHighlightColor = R.color.MID_GRAY;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				return new BasicArrowButton(orientation, thumbHighlightColor, R.color.BG_COLOR,
						thumbDarkShadowColor, R.color.BG_COLOR);
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return new BasicArrowButton(orientation, thumbHighlightColor, R.color.BG_COLOR,
						thumbDarkShadowColor, R.color.BG_COLOR);
			}
		});
		// Return the customized JScrollPane. 
		return pane;
	}

}
