/* Created by Adam Jost on 07/21/2021 */
/* Updated by Neha Metlapalli on 07/25/2021 */

package main.java.windows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.layouts.MenuLayout;
import main.java.resources.R;

import main.java.util.CustomButton;

public class MainMenu extends ApplicationWindow {

	// Data fields 
	private MenuLayout layout;

	// Constructor
	public MainMenu() {
		setFrame(new Frame());
		render();
	}

	@Override
	protected void initLayout() {
		layout = new MenuLayout(getFrame());
	}

	@Override
	protected void setFrameSpecs() {
		super.setFrameSpecs();
		getFrame().setLocation(520, 120);
		getFrame().setSize(R.dimens.MENU_FRAME_WIDTH, R.dimens.MENU_FRAME_HEIGHT);
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

					// If the Morse Chat button is pushed then open Morse Chat.
					if (sender.equals(layout.getChatBtn())) {
						new MorseChat();
					} else if (sender.equals(layout.getDecoderBtn())) {
						// If the Morse Coder button is pushed then open the Morse Endecoder tool. 
						new EncoderDecoderTool();
					} else if (sender.equals(layout.getBothBtn())) {
						// If the both button is pushed then open both of the windows. 
						new EncoderDecoderTool();
						new MorseChat();
					}
				}
			});
		}

	}

}
