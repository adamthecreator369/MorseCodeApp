/* Created by Adam Jost on 07/20/2021 */
/* Updated by Neha Metlapalli on 07/25/2021 */

package main.java.windows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.util.CustomButton;

import main.java.layouts.DecoderLayout;
import main.java.resources.R;
import main.java.util.MessageStrings;
import main.java.util.MorseCoder;

public class EncoderDecoderTool extends ApplicationWindow {

	// Data fields
	private DecoderLayout layout;
	private MorseCoder morseCoder;


	// Constructor
	public EncoderDecoderTool() {
		// Create a new Frame.
		setFrame(new Frame());
		morseCoder = new MorseCoder();
		render();
	}
	
	@Override
	protected void render() {
		super.render();
		// Closes the application window after a count down
		// if the data file is found to be non-existent.
		if (!morseCoder.buildMorseTree()) {
			destroy();
		}
	}

	@Override
	protected void initLayout() {
		// Set the Frame's layout.
		layout = new DecoderLayout(getFrame());
	}

	@Override
	protected void setFrameSpecs() {
		super.setFrameSpecs();
		// Set the Frame's size and location. 
		getFrame().setLocation(340, 0);
		// Set the Frame's height and width.
		getFrame().setSize(R.dimens.CODER_FRAME_WIDTH, R.dimens.CODER_FRAME_HEIGHT);
	}
	
	@Override
	protected void performClickActions() {
		for (CustomButton btn : layout.getBtns()) {
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Cast sender of event from object to JButton
					// since we know that is what it is and what we
					// need it to be.
					CustomButton sender = (CustomButton) e.getSource();

					// Empty the text area of any text.
					layout.setConversionText(null);

					// If the sender of the click event is the Encode/Decode button.
					if (sender.equals(layout.getEncodeBtn()) || sender.equals(layout.getDecodeBtn())) {
						// If the user neglected to enter a message before clicking the Encode/Decode
						// button then display an error message.
						if (layout.getMsgTxt().replaceAll("[\\s\\p{Z}]", "").equals("")) {
							layout.setConversionText(R.string.ERROR_BLANK_MSG);
							return;
						}
						String msg = layout.getMsgTxt().trim();
						if (sender.equals(layout.getEncodeBtn())) {
							// If the message text area contains the demo text.
							if (layout.getMsgTxt().equals(R.string.INTRO_TXT)) {
								// Encode the message and then decode the encoded message and display both to
								// the end user.
								layout.setConversionText(R.string.ENCODE_HEADING + morseCoder.encode(layout.getMsgTxt())
										+ R.string.SPACE_BETWEEN + R.string.DECODE_HEADING
										+ morseCoder.decode(morseCoder.encode(layout.getMsgTxt())));
								// Pause for 3 seconds to allow the end user
								// to see the morse code conversion.
								pause(3000);
								// Demonstrate how clicking the reset button resets
								// both of the JTextAreas.
								layout.getResetBtn().doClick();
								// Notify the end user that the tool is now operational and ready to use.
								layout.setConversionText(R.string.READY_MSG);
								// Allow the user to enter text into the top JTextArea.
								layout.getMsgTxtArea().setEditable(true);
								return;
							} 
							// Encode the message and then decode the encoded message and display both to
							// the end user.
							msg = MessageStrings.reduceTextWhiteSpaces(msg);
							layout.setConversionText(R.string.ENCODE_HEADING + morseCoder.encode(msg)
									+ R.string.SPACE_BETWEEN + R.string.DECODE_HEADING
									+ morseCoder.decode(morseCoder.encode(msg)).substring(0, msg.length()));
						} else if (sender.equals(layout.getDecodeBtn())) {
							try {
								// Decode the message and then encode the encoded message and display both to
								// the end user.
								msg = MessageStrings.reduceCodeWhiteSpaces(msg);
								layout.setConversionText(R.string.DECODE_HEADING + morseCoder.decode(msg)
										+ R.string.SPACE_BETWEEN + R.string.ENCODE_HEADING
										+ morseCoder.encode(morseCoder.decode(msg)).substring(0, msg.length()));
							} catch (Exception e1) {
								// Catches both StringIndexOutOfBoundsExceptions and NullPointerExceptions
								// resulting from invalid user input. When either of these two cases occur
								// an error message is displayed to the user.
								layout.setConversionText(R.string.ERROR_MORSE_CODE);
							}
						}

					} else if (sender.equals(layout.getResetBtn())) {
						// If the reset button is clicked then remove
						// all text from both of the JTextAreas.
						layout.removeAllTxt();
					} else if (sender.equals(layout.getDemoBtn())) {
						// Short demo demonstrating to the user how the tool
						// works. Starts playing when the Play Demo button is
						// clicked.
						performDemo();
					}
				}
			});
		}
	}

	/**
	 * Displays an error message informing the user that the system is down and then
	 * closes the frame after a ten second count down. This only occurs when the
	 * input data file is not found.
	 */
	// Make this a method in the abstract class.. get layout data field to work.
	// Make setText method in each layout to work with this method.
	// Somehow figure out how to keep from duplicating this method like i currently
	// am.
	private void destroy() {
		Thread thread = new Thread() {
			public void run() {
				// Disable layout components since the system is down.
				layout.disableBtns();
				layout.getMsgTxtArea().setEnabled(false);
				for (int i = 10; i > 0; i--) {
					layout.setConversionText(R.string.ERROR_SYSTEM_DOWN + i + R.string.SECONDS);
					pause(1000);
				}
				getFrame().dispose();
			}
		};
		thread.start();
	}

	/** Performs a short demonstration of how to use the converter tool */
	private void performDemo() {
		// Execute separately by creating a new Thread. Failing to do this will appear
		// as though the program has froze until after the demo has completed.
		Thread thread = new Thread() {
			public void run() {
				// Remove any text that may be contained in the JTextArea before starting to append
				// the demo text to it. 
				layout.setMessageText(null);
				// Keep the user from manipulating the text while the demo is being played.
				layout.getMsgTxtArea().setEditable(false);
				// Disable buttons so that the demo will perform properly without interruption. 
				layout.disableBtns();
				// Wait one second before starting the demo.
				pause(1000);
				// Print a demonstration message character-by-character pausing briefly between
				// each. This gives the appearance of the text being slowly typed by the
				// program.
				for (char c : R.string.INTRO_TXT.toCharArray()) {
					layout.getMsgTxtArea().setText(layout.getMsgTxtArea().getText() + c);
					pause(150);
				}
				layout.enableBtns();
				// Pause for 3/4 of a second.
				pause(750);
				// Perform a click event on the convert button.
				// This will trigger the click event listener and display
				// the converted demo message as morse code in the lower JTextArea.
				layout.getEncodeBtn().doClick();
				layout.enableBtns();
			}
		};
		thread.start();
	}
}
