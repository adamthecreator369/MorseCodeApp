/* Created by Adam Jost on 07/14/2021 */
/* Updated to fit current project on 07/20/2021 */

package main.java.layouts;

import java.awt.Frame;

import javax.swing.JComponent;

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

}
