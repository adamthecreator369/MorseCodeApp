/* Created by Adam Jost on 07/20/2021 */

package main.java.windows;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.resources.R;

public abstract class ApplicationWindow {

	private Frame frame;

	/** Gets and returns a Frame. */
	protected Frame getFrame() {
		return this.frame;
	}

	/** Sets the Frame. */
	protected void setFrame(Frame frame) {
		this.frame = frame;
	}

	/** Renders the ApplicationWindow. */
	protected void render() {
		// Instantiate all layouts and show those
		// to be shown at time of rendering.
		initLayout();
		// Frame settings
		setFrameSpecs();
		// Listen for click events and perform set actions for any
		// button when they are clicked by the user.
		performClickActions();
		// Window exit event.
		windowClosingEvent();
	}

	/** Initializes all of the layouts used in a ApplicationWindow. */
	protected abstract void initLayout();

	/** Click listeners for all of the buttons of a given application Frame window. */
	protected abstract void performClickActions();

	/** Sets the Frame's specifications and settings. */
	protected void setFrameSpecs() {
		frame.setLocation(340, 120);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setTitle(R.string.APP_TITLE);
		frame.setBackground(R.color.BG_COLOR);
		frame.setVisible(true);
	}

	/**
	 * Pauses the executing thread for a specified amount of time.
	 * 
	 * @param millis: the amount of time to wait in milliseconds.
	 */
	protected void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Specifies what action to take when the window closing event occurs */
	protected void windowClosingEvent() {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				frame.dispose();
			}
		});
	}

}
