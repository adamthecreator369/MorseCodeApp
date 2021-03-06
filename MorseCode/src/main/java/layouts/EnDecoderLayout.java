/* Created by Adam Jost on 07/20/2021 */
/* Updated by Neha Metlapalli on 07/25/2021 to include CustomButton items. */
/* Updated by Adam Jost on 07/26/2021. Addition of customJScrollPane() method calls to 
 * when initializing JScrollPanes, and spaceBtnsEvenly() method.
 */

package main.java.layouts;

import java.awt.Frame;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import main.java.util.CustomButton;

import main.java.resources.R;


public class EnDecoderLayout extends Layout {

	// Data fields
	private JLabel titleLabel, msgLabel, conversionLabel;
	private JTextArea msgTxtArea, conversionTxtArea;
	private CustomButton encodeBtn, decodeBtn, resetBtn, demoBtn;
	private JScrollPane mScrollPane, cScrollPane;

	// Constructor
	public EnDecoderLayout(Frame frame) {
		initComponents();
		componentSettings();
		addComponents(frame);
		show();
	}

	// Getters
	/**
	 * Gets and returns the JTextArea, msgTxtArea.
	 * 
	 * @return: the text area used for user input.
	 */
	public JTextArea getMsgTxtArea() {
		return msgTxtArea;
	}

	/**
	 * Gets and returns the JTextArea, conversionTxtArea.
	 * 
	 * @return: the text area used for displaying the decoded/encoded messages.
	 */
	public JTextArea getConversionTxtArea() {
		return conversionTxtArea;
	}

	/**
	 * Gets and returns all text currently contained within the JTextArea,
	 * msgTxtArea.
	 * 
	 * @return: the text currently in the text area.
	 */
	public String getMsgTxt() {
		return getMsgTxtArea().getText();
	}

	/**
	 * Gets and returns the CustomButton, encodeBtn.
	 * 
	 * @return: the encode button.
	 */
	public CustomButton getEncodeBtn() {
		return encodeBtn;
	}
	
	/**
	 * Gets and returns the CustomButton, decodeBtn.
	 * 
	 * @return: the decode button.
	 */
	public CustomButton getDecodeBtn() {
		return decodeBtn;
	}
	
	/**
	 * Gets and returns the CustomButton, demoBtn.
	 * 
	 * @return: the demo button.
	 */
	public CustomButton getDemoBtn() {
		return demoBtn;
	}

	/**
	 * Gets and returns the CustomButton, resetBtn.
	 * 
	 * @return: the reset button.
	 */
	public CustomButton getResetBtn() {
		return resetBtn;
	}

	/**
	 * Gets and returns the array containing all CustomButtons from this layout.
	 * 
	 * @return: a CustomButton array.
	 */
	public CustomButton[] getBtns() {
		return new CustomButton[] { encodeBtn, decodeBtn, resetBtn, demoBtn };
	}

	/** Enables all buttons of the layout. */
	public void enableBtns() {
		for (CustomButton btn : getBtns()) {
			btn.setEnabled(true);
		}
	}

	/** Disables all buttons of the layout. */
	public void disableBtns() {
		for (CustomButton btn : getBtns()) {
			btn.setEnabled(false);
		}
	}

	/** Removes the text from both of the Layout's text areas. */
	public void removeAllTxt() {
		msgTxtArea.setText(null);
		conversionTxtArea.setText(null);
	}

	/**
	 * Sets the text of the JTextArea, conversionTxtArea, to the argument String.
	 */
	public void setConversionText(String text) {
		getConversionTxtArea().setText(text);
	}

	/** Sets the text of the JTextArea, msgTxtArea, to the argument String. */
	public void setMessageText(String text) {
		getMsgTxtArea().setText(text);
	}

	@Override
	protected void initComponents() {
		titleLabel = new JLabel(R.string.SUBTITLE);
		msgLabel = new JLabel(R.string.MSG_LBL_TXT);
		conversionLabel = new JLabel(R.string.ENDECODE_LABEL);
		msgTxtArea = new JTextArea();
		conversionTxtArea = new JTextArea();
		encodeBtn = new CustomButton(R.string.ENCODE_BTN_TXT);
		decodeBtn = new CustomButton(R.string.DECODE_BTN_TXT);
		demoBtn = new CustomButton(R.string.DEMO_BTN_TXT);
		resetBtn = new CustomButton(R.string.RESET_BTN);
		// JTextArea bounds must be set here before instantiate their JScrollPanes.
		msgTxtArea.setBounds(R.dimens.TOOL_TXT_AREA_X, R.dimens.TOOL_MSG_AREA_Y, R.dimens.TOOL_TXT_AREA_WIDTH,
				R.dimens.TOOL_TXT_AREA_HEIGHT);
		conversionTxtArea.setBounds(R.dimens.TOOL_TXT_AREA_X, R.dimens.CONV_AREA_Y, R.dimens.TOOL_TXT_AREA_WIDTH,
				R.dimens.TOOL_TXT_AREA_HEIGHT);
		mScrollPane = createCustomScrollPane(msgTxtArea);
		cScrollPane = createCustomScrollPane(conversionTxtArea);
		setComponents(new JComponent[] { titleLabel, msgLabel, conversionLabel, encodeBtn, decodeBtn, demoBtn, resetBtn, mScrollPane,
				cScrollPane });
	}

	@Override
	protected void componentSettings() {
		// Wrap text and add padding to the text areas.
		msgTxtArea.setLineWrap(true);
		msgTxtArea.setMargin(new Insets(10, 12, 10, 12));
		conversionTxtArea.setLineWrap(true);
		conversionTxtArea.setMargin(new Insets(10, 12, 10, 12));
		// Wrap Style to break at between words and not middle of word.
		msgTxtArea.setWrapStyleWord(true);
		conversionTxtArea.setWrapStyleWord(true);
		// Disallow editing of the area that displays the
		// converted message.
		getConversionTxtArea().setEditable(false);
		setFont();
		// Set all component bounds
		// Label bounds
		titleLabel.setBounds(R.dimens.TOOL_TITLE_X, R.dimens.TOOL_TITLE_Y, R.dimens.TOOL_TITLE_WIDTH, R.dimens.TITLE_HEIGHT);
		msgLabel.setBounds(R.dimens.TOOL_LABEL_X, R.dimens.TOOL_MSG_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		conversionLabel.setBounds(R.dimens.TOOL_LABEL_X, R.dimens.CONV_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		// Scroll pane bounds
		mScrollPane.setBounds(R.dimens.TOOL_TXT_AREA_X, R.dimens.TOOL_MSG_AREA_Y, R.dimens.TOOL_TXT_AREA_WIDTH,
				R.dimens.TOOL_TXT_AREA_HEIGHT);
		cScrollPane.setBounds(R.dimens.TOOL_TXT_AREA_X, R.dimens.CONV_AREA_Y, R.dimens.TOOL_TXT_AREA_WIDTH,
				R.dimens.TOOL_TXT_AREA_HEIGHT);
		// Button bounds
		spaceBtnsEvenly(getBtns(), R.dimens.BTN_X, R.dimens.BTN_Y);
		demoBtn.setBounds(R.dimens.BTN_X + R.dimens.BTN_SPACING * 2, R.dimens.DEMO_BTN_Y, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);
		// Add scroll bars to the two JScrollPanes.
		mScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	@Override
	protected void setFont() {
		titleLabel.setFont(R.font.LARGE);
		msgLabel.setFont(R.font.SMALL);
		conversionLabel.setFont(R.font.SMALL); 
	}

}
