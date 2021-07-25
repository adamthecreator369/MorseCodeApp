/* Created by Adam Jost on 07/21/2021 */

package main.java.layouts;

import java.awt.Frame;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import main.java.resources.R;

public class ChatLayout extends Layout {

	// Data fields (JComponents)
	private JLabel titleLabel, subTitleLabel, chatLabel, msgLabel, ipLabel, portLabel, connectionLabel;
	private JTextField ipField, portField, connectionColor;
	private JRadioButton hostRadio, guestRadio;
	private ButtonGroup hostGuestGroup;
	private JButton connectBtn, disconnectBtn;
	private JTextArea msgTxtArea, chatTxtArea;
	private JScrollPane msgScrollPane, chatScrollPane;
	private JButton[] btns;

	// Constructor
	public ChatLayout(Frame frame) {
		initComponents();
		componentSettings();
		addComponents(frame);
		show();
	}

	/**
	 * Gets and returns the JButton, connectBtn.
	 * 
	 * @return: the connect button.
	 */
	public JButton getConnectBtn() {
		return connectBtn;
	}

	/**
	 * Gets and returns the JButton, disconnectBtn.
	 * 
	 * @return: the disconnect button.
	 */
	public JButton getDisconnectBtn() {
		return disconnectBtn;
	}

	/**
	 * Gets and returns the JButton[], btns.
	 * 
	 * @return: the buttons array.
	 */
	public JButton[] getBtns() {
		return btns;
	}

	public JTextArea getMsgTxtArea() {
		return msgTxtArea;
	}

	/**
	 * Gets and returns text contained in the message text area.
	 * 
	 * @return: msgTxtArea text.
	 */
	public String getMsgText() {
		return msgTxtArea.getText();
	}

	/**
	 * Returns the information in the ipField.
	 * 
	 * @return: the text field contents as a String.
	 */
	public String getIP() {
		return ipField.getText();
	}

	/**
	 * Returns the information in the port text field.
	 * 
	 * @return: text field contents parsed to an integer.
	 */
	public int getPort() {
		return Integer.parseInt(portField.getText());
	}
	
	/** Gets and returns the current text of the connection label.
	 * 
	 * @return: the connection labels text.
	 */
	public String getConnectionStatus() {
		return connectionLabel.getText();
	}

	/** Adds text to the chat area. */
	public void appendText(String text) {
		chatTxtArea.append(text);
	}

	/** Removes the text from the message text area. */
	public void removeMsgText() {
		msgTxtArea.setText(null);
	}
	
	/** Removes the text from the chat text area. */
	public void removeChatText() {
		chatTxtArea.setText(null);
	}

	/**
	 * Checks whether the host radio is currently selected.
	 * 
	 * @return: {true} if the host radio is selected; {false} otherwise.
	 */
	public boolean isHost() {
		return hostRadio.isSelected();
	}

	/**
	 * Checks if the guest radio button is currently selected.
	 * 
	 * @return: {true} if the guest radio button is selected; {false} otherwise.
	 */
	public boolean isGuest() {
		return guestRadio.isSelected();
	}
	
	/** Changes the visual representation of the current state of the chat's connection status.
	 *  It first sets the connection label to a passed in String, and then changes the color
	 *  of the label depending on the current status.
	 * 
	 * @param connectionStatus: the String used to set the connection label. 
	 */
	public void changeConnectionStatus(String connectionStatus) {
		// Change the text of the connection status label. 
		connectionLabel.setText(connectionStatus);
	    // Change the color of the connection status square. 
		if (connectionStatus.equals(R.string.CONNECTING_STATUS)) {
			connectionColor.setBackground(R.color.YELLOW);
		} else if (connectionStatus.equals(R.string.CONNECTED_STATUS)) {
			connectionColor.setBackground(R.color.GREEN);
		} else if (connectionStatus.equals(R.string.DISCONNECTED_STATUS)) {
			connectionColor.setBackground(R.color.RED);
		} else {
			connectionColor.setBackground(R.color.ORANGE);
		}
	}

	/** Makes the necessary changes to component settings after a connection has been made. */
	public void connectionSettings() {
		ipField.setEditable(false);
		portField.setEditable(false);
		connectBtn.setEnabled(false);
		hostRadio.setEnabled(false);
		guestRadio.setEnabled(false);
		msgTxtArea.setEditable(true);
		disconnectBtn.setEnabled(true);
	}

	/** Makes the necessary changes to component settings after disconnecting. */
	public void disconnectionSettings() {
		ipField.setEditable(true);
		portField.setEditable(true);
		connectBtn.setEnabled(true);
		hostRadio.setEnabled(true);
		guestRadio.setEnabled(true);
		msgTxtArea.setEditable(false);
		disconnectBtn.setEnabled(false);
		chatTxtArea.setText(null);
		msgTxtArea.setText(null);
	}

	@Override
	protected void initComponents() {
		titleLabel = new JLabel(R.string.CHAT_TITLE);
		subTitleLabel = new JLabel(R.string.CHAT_SUBTITLE);
		chatLabel = new JLabel(R.string.CHAT_WINDOW);
		msgLabel = new JLabel(R.string.ENTER_MESSAGE);
		ipLabel = new JLabel(R.string.HOST_IP);
		portLabel = new JLabel(R.string.PORT);
		connectionLabel = new JLabel(R.string.NOT_CONN_STATUS);
		ipField = new JTextField();
		portField = new JTextField();
		connectionColor = new JTextField(1);
		hostRadio = new JRadioButton(R.string.HOST, true);
		guestRadio = new JRadioButton(R.string.GUEST, false);
		connectBtn = new JButton(R.string.CONNECT);
		disconnectBtn = new JButton(R.string.DISCONNECT);
		msgTxtArea = new JTextArea();
		chatTxtArea = new JTextArea();
		msgScrollPane = new JScrollPane(msgTxtArea);
		chatScrollPane = new JScrollPane(chatTxtArea);
		hostGuestGroup = new ButtonGroup();
		hostGuestGroup.add(hostRadio);
		hostGuestGroup.add(guestRadio);
		btns = new JButton[] { connectBtn, disconnectBtn };
		setComponents(new JComponent[] { titleLabel, subTitleLabel, chatLabel, msgLabel, ipLabel, portLabel, ipField,
				portField, hostRadio, guestRadio, connectBtn, disconnectBtn, msgScrollPane, chatScrollPane, connectionLabel,
				connectionColor });
	}

	@Override
	protected void componentSettings() {
		// Set component bounds (positioning and size).
		titleLabel.setBounds(R.dimens.CHAT_TITLE_X, R.dimens.CHAT_TITLE_Y, R.dimens.CHAT_TITLE_WIDTH, R.dimens.TITLE_HEIGHT);
		subTitleLabel.setBounds(R.dimens.CHAT_TITLE_X, R.dimens.CHAT_AREA_Y - 5, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		ipLabel.setBounds(R.dimens.CHAT_TITLE_X, R.dimens.IP_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		ipField.setBounds(R.dimens.CHAT_FIELD_X, R.dimens.IP_FIELD_Y, R.dimens.CHAT_FIELD_WIDTH, R.dimens.CHAT_FIELD_HEIGHT);
		portLabel.setBounds(R.dimens.CHAT_TITLE_X, R.dimens.PORT_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		portField.setBounds(R.dimens.CHAT_FIELD_X, R.dimens.PORT_FIELD_Y, R.dimens.CHAT_FIELD_WIDTH, R.dimens.CHAT_FIELD_HEIGHT);
		connectionLabel.setBounds(R.dimens.CONN_LABEL_X, R.dimens.CONN_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		connectionColor.setBounds(R.dimens.CONN_COLOR_X, R.dimens.CONN_COLOR_Y, R.dimens.COLOR_WIDTH, R.dimens.COLOR_HEIGHT);
		
		
		
		// TODO:: FIX THESE TO : REFACTOR EXTRACT CONSTANTS
		hostRadio.setBounds(R.dimens.HOST_RADIO_X, R.dimens.HOST_RADIO_Y, R.dimens.BTN_WIDTH-10, R.dimens.BTN_HEIGHT);
		guestRadio.setBounds(R.dimens.GUEST_RADIO_X, R.dimens.CHAT_BTN_Y - 15, R.dimens.BTN_WIDTH-10, R.dimens.BTN_HEIGHT);
		
		
		
		connectBtn.setBounds(R.dimens.CONNECT_BTN_X, R.dimens.CONNECT_BTN_Y, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);
		disconnectBtn.setBounds(R.dimens.DISCONNECT_BTN_X, R.dimens.DISCONNECT_BTN_Y, R.dimens.BTN_WIDTH, R.dimens.BTN_HEIGHT);
		chatLabel.setBounds(R.dimens.CHAT_AREA_X, R.dimens.CHAT_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		chatTxtArea.setBounds(R.dimens.CHAT_AREA_X, R.dimens.CHAT_AREA_Y, R.dimens.CHAT_AREA_WIDTH, R.dimens.CHAT_AREA_HEIGHT);
		chatScrollPane.setBounds(R.dimens.CHAT_AREA_X, R.dimens.CHAT_AREA_Y, R.dimens.CHAT_AREA_WIDTH, R.dimens.CHAT_AREA_HEIGHT);
		msgTxtArea.setBounds(R.dimens.CHAT_AREA_X, R.dimens.CHAT_MSG_Y, R.dimens.CHAT_MSG_AREA_WIDTH, R.dimens.CHAT_MSG_AREA_HEIGHT);
		msgLabel.setBounds(R.dimens.CHAT_AREA_X, R.dimens.CHAT_MSG_LABEL_Y, R.dimens.LABEL_WIDTH, R.dimens.LABEL_HEIGHT);
		msgScrollPane.setBounds(R.dimens.CHAT_AREA_X, R.dimens.CHAT_MSG_Y, R.dimens.CHAT_MSG_AREA_WIDTH, R.dimens.CHAT_MSG_AREA_HEIGHT);
		// Disallow editing of the chat area.
		// Sent and received messages will appear here automatically.
		chatTxtArea.setEditable(false);
		// Wrap text and add padding to the text areas.
		msgTxtArea.setLineWrap(true);
		msgTxtArea.setMargin(new Insets(10, 12, 10, 12));
		chatTxtArea.setLineWrap(true);
		chatTxtArea.setMargin(new Insets(10, 12, 10, 12));
		// Wrap Style to break at between words and not middle of word.
		msgTxtArea.setWrapStyleWord(true);
		chatTxtArea.setWrapStyleWord(true);
		// Add vertical scroll bars to both scroll panes.
		msgScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ipField.setText(R.string.LOCALHOST);
		portField.setText(R.string.PORT_EXMPL);
		// Disallow editing of the text areas.
		chatTxtArea.setEditable(false);
		msgTxtArea.setEditable(false);
		// Disable the disconnect button.
		disconnectBtn.setEnabled(false);
		connectionColor.setEditable(false);
		connectionColor.setBackground(R.color.ORANGE);
		hostRadio.setBackground(null);
		guestRadio.setBackground(null);
		hostRadio.setFocusable(false);
		guestRadio.setFocusable(false);
		connectBtn.setOpaque(true);
		disconnectBtn.setOpaque(true);
		connectBtn.setBackground(R.color.WHITE);
		disconnectBtn.setBackground(R.color.WHITE);
		ipField.setBorder(null);
		portField.setBorder(null);
		connectionColor.setBorder(null);
		setFont();
	}

	@Override
	protected void setFont() {
		titleLabel.setFont(R.font.LARGE);
		subTitleLabel.setFont(R.font.SMALL);
		subTitleLabel.setForeground(R.color.DARK_GRAY);
		msgLabel.setFont(R.font.SMALL);
		chatLabel.setFont(R.font.SMALL);
		ipLabel.setFont(R.font.SMALL);
		portLabel.setFont(R.font.SMALL);
		connectionLabel.setFont(R.font.SMALL);
		connectionLabel.setForeground(R.color.DARK_GRAY);
		hostRadio.setFont(R.font.SMALL);
		guestRadio.setFont(R.font.SMALL);
		connectBtn.setFont(R.font.SMALL);
		connectBtn.setFont(R.font.BTN);
		disconnectBtn.setFont(R.font.BTN);
	}
}
