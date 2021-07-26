/* Created by Adam Jost on 7/20/2021 */
/* Updated by Neha Metlapalli on 07/25/2021 */

package main.java.layouts;

import java.awt.Frame;

import javax.swing.JComponent;
import javax.swing.JLabel;

import main.java.util.CustomButton;

import main.java.resources.R;

public class MenuLayout extends Layout {
	
	private JLabel titleLabel;
	private CustomButton chatBtn, decoderBtn, bothBtn;
	private CustomButton[] btns;
	
	public MenuLayout(Frame frame) {
		initComponents();
		componentSettings();
		addComponents(frame);
		show();
	}
	
	/** Gets and returns the CustomButton, chatBtn. */
	public CustomButton getChatBtn() {
		return chatBtn;
	}
	
	/** Gets and returns the CustomButton, decoderBtn. */
	public CustomButton getDecoderBtn() {
		return decoderBtn;
	}
	
	/** Gets and returns the CustomButton, bothBtn. */
	public CustomButton getBothBtn() {
		return bothBtn;
	}
	
	/** Gets and returns a CustomButton[] containing both layout buttons. */
	public CustomButton[] getBtns() {
		return btns;
	}
	
	@Override
	protected void initComponents() {
		titleLabel = new JLabel(R.string.MENU_TITLE);
		chatBtn = new CustomButton(R.string.OPEN_CHAT_BTN);
		decoderBtn = new CustomButton(R.string.OPEN_DECODER_BTN);
		bothBtn = new CustomButton(R.string.OPEN_BOTH_BTN);
		btns = new CustomButton[] { chatBtn, decoderBtn, bothBtn };
		setComponents(new JComponent[] { titleLabel, chatBtn, decoderBtn, bothBtn });
	}

	@Override
	protected void componentSettings() {
		titleLabel.setBounds(R.dimens.MENU_TITLE_X, R.dimens.TOOL_TITLE_Y, R.dimens.MENU_TITLE_WIDTH, R.dimens.TITLE_HEIGHT);
		chatBtn.setBounds(R.dimens.MENU_BTN_X, R.dimens.MENU_BTN_Y, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);
		decoderBtn.setBounds(R.dimens.MENU_BTN_X + R.dimens.BTN_SPACING, R.dimens.MENU_BTN_Y, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);
		bothBtn.setBounds(R.dimens.MENU_BTN_X + R.dimens.BTN_SPACING * 2, R.dimens.MENU_BTN_Y, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);	
		setFont();
	}

	@Override
	protected void setFont() {
		titleLabel.setFont(R.font.LARGE);
	}
}
