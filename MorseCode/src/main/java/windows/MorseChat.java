/* Created by Adam Jost on 07/21/2021 */
/* Updated by Neha Metlapalli on 07/25/2021 */

package main.java.windows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.java.util.CustomButton;
import main.java.util.CustomIcon;
import main.java.layouts.ChatLayout;
import main.java.resources.R;
import main.java.util.MessageStrings;
import main.java.util.MorseCoder;

public class MorseChat extends ApplicationWindow {

	// Data fields
	private ChatLayout layout;

	private int status;

	private ServerSocket hostServer;
	private Socket socket;

	private InputStreamReader inputStreamReader;
	private OutputStreamWriter outputStreamWriter;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private static StringBuffer toSend;
	private boolean justSentMsg;

	private Thread thread;

	private MorseCoder morseCoder;

	// Constructor
	public MorseChat() {
		setFrame(new Frame());
		morseCoder = new MorseCoder();
		toSend = new StringBuffer("");
		justSentMsg = false;
		status = R.status.NULL;
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
		// Key listeners and actions to execute when
		// a key event occurs.
		performKeyActions();
		// ActionListeners for the radio buttons.
		radioActionListeners();
	}

	/**
	 * Formats and prepares the sent message for the BufferWriter used when sending
	 * a message.
	 */
	private static void sendString(String s) {
		synchronized (toSend) {
			toSend.append(s.trim() + "\n");
		}
	}

	@Override
	protected void initLayout() {
		layout = new ChatLayout(getFrame());
	}

	@Override
	protected void setFrameSpecs() {
		super.setFrameSpecs();
		// Set the size and location of the application window frame.
		getFrame().setLocation(520, 120);
		getFrame().setSize(R.dimens.CHAT_FRAME_WIDTH, R.dimens.CHAT_FRAME_HEIGHT);
	}

	@Override
	protected void performClickActions() {
		// Add a ActionListener to each button of the Frame's layout.
		for (CustomButton btn : layout.getBtns()) {
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Cast sender of event from object to JButton
					// since we know that is what it is and what we
					// need it to be.
					CustomButton sender = (CustomButton) e.getSource();

					if (sender.equals(layout.getConnectBtn())) {
						// When the connect button is pressed changed the status to
						// connected and then execute the following.
						status = R.status.CONNECTING;
						// Remove any present error messages in the chat area.
						layout.removeChatText();
						// Create and run a new Thread.
						thread = new Thread() {
							public void run() {
								while (true) {
									// Wait ten milliseconds before each iteration.
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										// Nothing to be done here, but keep the
										// program from crashing.
									}
									switch (status) {
									case R.status.CONNECTING:
										layout.changeConnectionStatus(R.string.CONNECTING_STATUS);
										layout.connectionSettings();
										layout.getMsgTxtArea().setEditable(false);
										// If connecting
										try {
											// Determine if to connect as host (server) or guest (client).
											if (layout.isHost()) {
												// Host/Server connection.
												hostServer = new ServerSocket(layout.getPort());
												socket = hostServer.accept();
											} else {
												// Client/Guest connection.
												socket = new Socket(layout.getIP(), layout.getPort());
											}
											// Disallow changing of all of the connection options.
											layout.connectionSettings();

											// Objects used for sending and receiving messages.
											inputStreamReader = new InputStreamReader(socket.getInputStream());
											outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
											bufferedReader = new BufferedReader(inputStreamReader);
											bufferedWriter = new BufferedWriter(outputStreamWriter);
											// Change status to connected.
											status = R.status.CONNECTED;
										}
										// If error, clean up.
										catch (Exception e) {
											status = R.status.DISCONNECTED;
											layout.changeConnectionStatus(R.string.DISCONNECTED_STATUS);
											layout.appendText(R.string.ERROR_CONNECTION_FAILED + "\n");
											layout.disconnectionSettings();
											break;
										}
										break;
									case R.status.CONNECTED:
										layout.changeConnectionStatus(R.string.CONNECTED_STATUS);
										// Send and receive messages when they are ready.
										try {
											// Sending messages.
											if (toSend.length() != 0) {
												String msg = toSend.toString();

												bufferedWriter.write(msg);
												bufferedWriter.newLine();
												bufferedWriter.flush();
												// Reset.
												toSend.setLength(0);
												continue;
											}
											// Receiving messages.
											String incoming = null;
											if (bufferedReader.ready()) {
												incoming = bufferedReader.readLine().trim();
											}
											// Disconnect if the disconnect message is received.
											if (incoming != null && incoming.equals(R.string.DISCONNECT_COMMAND)) {
												status = R.status.DISCONNECTED;
												layout.changeConnectionStatus(R.string.DISCONNECTED_STATUS);
												cleanUp();
												layout.disconnectionSettings();
												break;
											}
											// If the message is not the one the user just sent.
											if (!justSentMsg && incoming != null && incoming.length() != 0) {
												layout.appendText(R.string.INCOMING + incoming + "\n");
											}
											justSentMsg = false;
										} catch (IOException e) {
											// This should never occur, but if something drastically goes
											// wrong then disconnect.
											status = R.status.DISCONNECTED;
											layout.changeConnectionStatus(R.string.DISCONNECTED_STATUS);
											layout.appendText(R.string.ERROR_DISCONNECTING);
										}
										break;
									case R.status.DISCONNECTED:
										layout.changeConnectionStatus(R.string.DISCONNECTED_STATUS);
										// Perform the disconnect process.
										sendDisconnectMsg();
										cleanUp();
										layout.disconnectionSettings();
										break;
									}
									if (status == R.status.DISCONNECTED) {
										// Join thread when disconnecting.
										try {
											thread.join();
										} catch (InterruptedException e) {
											System.out.println(R.string.JOIN_FAILED);
										}
										break;
									}
								}
							}
						};
						thread.start();
					} else if (sender.equals(layout.getDisconnectBtn())) {
						// If the user was in the process of connecting and waiting for another user
						// to join the connection when the disconnect button was pushed then close the
						// server socket and other items immediately.
						if (layout.getConnectionStatus().equals(R.string.CONNECTING_STATUS)) {
							cleanUp();
						}
						// If the disconnect button is pushed, change the status
						// to "disconnected" and which will trigger the disconnect
						// process to occur.
						status = R.status.DISCONNECTED;
						layout.changeConnectionStatus(R.string.DISCONNECTING_STATUS);
					}
				}
			});
		}
	}

	/** Key listeners for the Application and defined actions to execute. */
	private void performKeyActions() {
		layout.getMsgTxtArea().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// This method does not apply and therefore
				// remains unimplemented.
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				// If the user has pressed the enter key while focus is on the message text
				// area.
				// this event will be triggered.
				if (key == KeyEvent.VK_ENTER) {
					// Retrieve any and all text contained within the text area.
					String msgToSend = layout.getMsgTxtArea().getText().trim();
					try {
						// If the input message is proven to be Morse Code.
						if (MessageStrings.msgIsCode(msgToSend)) {
							msgToSend = MessageStrings.reduceCodeWhiteSpaces(msgToSend);
							// This below test will throw an exception if there are errors contained in
							// the input coded message String. If the system cannot initialize the
							// below variable then the input String MUST contain errors. This is used to
							// catch those errors and prohibit invalid messages from being sent.
							String test = morseCoder.decode(msgToSend);
						} else {
							// If the input message is not Morse Code then encode the message.
							msgToSend = morseCoder.encode(MessageStrings.reduceTextWhiteSpaces(msgToSend));
						}
					} catch (Exception e1) {
						// The input message was invalid and therefore we output an error message and
						// return to keep the message from sending.
						layout.appendText(R.string.MSG_NOT_SENT_CODE);
						// Remove the erroneous input message.
						layout.removeMsgText();
						return;
					}

					if (msgToSend.contains(R.string.ERROR)) {
						// If when the message was encoded it contained unsupported
						// character symbols the message would have returned with the
						// an error message, so if the returned encoded message in fact
						// contains this error message the we return and do not send the
						// message, after notifying the user that their message did not send.
						layout.appendText(R.string.MSG_NOT_SENT_CHARS);
						// Remove the erroneous input message.
						layout.removeMsgText();
						return;
					}

					if (!msgToSend.equals("")) {
						// If the message is not an empty string then format the message accordingly,
						// and then send the message.
						layout.appendText(R.string.OUTGOING + msgToSend + "\n");
						// Send the string
						sendString(msgToSend);
						// Remove the sent message from the input message text area.
						layout.removeMsgText();
						// Set this to true to inhibit the message returning to us as an incoming
						// message. This is a work around to a problem caused by running both the
						// server and the client off of the same script.
						justSentMsg = true;
					} else {
						// This line keeps the enter key from inserting new line characters into the
						// text area if it is empty when the event occurs.
						layout.removeMsgText();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// This method does not apply and therefore
				// remains unimplemented.
			}
		});
	}
	
	/** ActionListeners for the radio buttons of the Frame's layout. */
	private void radioActionListeners() {
		// Change radio button icon's format
		CustomIcon unselected = new CustomIcon();
		CustomIcon selected = new CustomIcon(R.color.DARK_GRAY);
		layout.getHostRadio().setIcon(selected);
		layout.getGuestRadio().setIcon(unselected);
		
		// Adds an ActionListener to the radio buttons to see if either is selected and change the icons depending on which radio button is selected
		layout.getHostRadio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
		        layout.getHostRadio().setIcon(selected);
		        layout.getGuestRadio().setIcon(unselected);
		    }
		});
		layout.getGuestRadio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				layout.getGuestRadio().setIcon(selected);
		        layout.getHostRadio().setIcon(unselected);
		    }
		});
	}

	/**
	 * Closes all open objects used for connection and communication and then sets
	 * them to null.
	 */
	private void cleanUp() {
		try {
			if (hostServer != null) {
				hostServer.close();
				hostServer = null;
			}
		} catch (IOException e) {
			hostServer = null;
		}

		try {
			if (socket != null) {
				socket.close();
				socket = null;
			}
		} catch (IOException e) {
			socket = null;
		}

		try {
			if (bufferedReader != null) {
				bufferedReader.close();
				bufferedReader = null;
			}
		} catch (IOException e) {
			bufferedReader = null;
		}

		if (bufferedWriter != null) {
			try {
				bufferedWriter.close();
				bufferedWriter = null;
			} catch (IOException e) {
				bufferedWriter = null;
			}

		}
	}

	/**
	 * Send a message to the user on the other end of the connection informing them
	 * that the other user has disconnected and left the chat.
	 */
	private void sendDisconnectMsg() {
		try {
			if (bufferedWriter != null) {
				bufferedWriter.write(R.string.DISCONNECT_COMMAND);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		} catch (IOException e1) {
			// Simply keep the program from crashing.
		}
	}

	@Override
	protected void windowClosingEvent() {
		getFrame().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				// Clean up and then exit the system.
				status = R.status.DISCONNECTED;
				layout.changeConnectionStatus(R.string.DISCONNECTING_STATUS);
				sendDisconnectMsg();
				cleanUp();
				System.exit(1);
			}
		});
	}

	/**
	 * Displays an error message informing the user that the system is down and then
	 * closes the frame after a ten second count down. This only occurs when the
	 * input data file is not found.
	 */
	private void destroy() {
		Thread thread = new Thread() {
			public void run() {
				// Disable components since the system is down.
				layout.connectionSettings();
				layout.getDisconnectBtn().setEnabled(false);
				layout.getMsgTxtArea().setEditable(false);
				// Display an error message containing a count down until Frame closure.
				for (int i = 10; i > 0; i--) {
					layout.getMsgTxtArea().setText(R.string.ERROR_SYSTEM_DOWN + i + R.string.SECONDS);
					pause(1000);
				}
				// Close the Frame.
				getFrame().dispose();
			}
		};
		thread.start();
	}
}
