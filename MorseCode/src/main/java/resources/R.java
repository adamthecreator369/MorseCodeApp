/* Created by Adam Jost on 07/21/2021 */

package main.java.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public final class R {

	public static final class string {
		public static final String APP_TITLE = "Morse Coder";
		public static final String SUBTITLE = "Morse Coder Endecoder Tool";
		public static final String INTRO_TXT = "This is a short demo. This tool is so easy to use. Simply enter your message here and click the button. It's that simple. Have fun and enjoy!";
		public final static String MSG_LBL_TXT = "Message (text only or morse code only)";
		public final static String ENDECODE_LABEL = "Encoded and decoded results";
		public final static String ENCODE_BTN_TXT = "Encode Message";
		public final static String DECODE_BTN_TXT = "Decode Message";
		public final static String DEMO_BTN_TXT = "Play Demo";
		public final static String RESET_BTN = "Reset";
		public final static String SEPARATOR = "----------------\n";
		public final static String DECODE_HEADING = SEPARATOR + "Decoded Message\n" + SEPARATOR + "\n";
		public final static String ENCODE_HEADING = SEPARATOR + "Encoded Message\n" + SEPARATOR + "\n";
		public static final String SPACE_BETWEEN = "\n\n";
		public static final String ERROR = "* Error: ";
		public static final String ERROR_BLANK_MSG = "* Error: Message cannot be blank.";
		public static final String ERROR_MORSE_CODE = "* Error: Unrecognized Morse code.";
		public static final String ERROR_SYSTEM_DOWN = "Error System is down. Character data cannot be found\n\nApplication will terminate in ";
		public static final String ERROR_CONNECTION_FAILED = "ERROR: Connection Failed";
		public static final String ERROR_DISCONNECTING = "Error: Something went wrong. Disconnecting.";
		public static final String MSG_NOT_SENT_CODE = "ERROR: Unrecognized Morse Code. Message didn't send.\n";
		public static final String MSG_NOT_SENT_CHARS = "ERROR: Unrecognized or unsupported characters. Message didn't send.\n";
		public static final String READY_MSG = "* Ready to use: Enter a message to begin.";
		public static final String ERROR_UNSUPPORTED = "* Error: Unsupported characters in message: ";
		public static final String UNRECOGNIZED_CODE = "Unrecognized Morse Code";
		public static final String MENU_TITLE = "Main Menu";
		public static final String OPEN_CHAT_BTN = "Morse Chat";
		public static final String OPEN_DECODER_BTN = "Morse Coder";
		public static final String OPEN_BOTH_BTN = "Both Apps";
		public static final String PORT_EXMPL = "5555";
		public static final String LOCALHOST = "localhost";
		public static final String SECONDS = " seconds.";
		public static final String DISCONNECT_COMMAND = "f22";
		public static final String JOIN_FAILED = "Join failed";
		public static final String OUTGOING = "OUTGOING: ";
		public static final String INCOMING = "INCOMING: ";
		public static final String DISCONNECT = "Disconnect";
		public static final String CONNECT = "Connect";
		public static final String GUEST = "Guest";
		public static final String HOST = "Host";
		public static final String PORT = "Port: ";
		public static final String HOST_IP = "Host IP: ";
		public static final String ENTER_MESSAGE = "Enter message";
		public static final String CHAT_WINDOW = "Chat window";
		public static final String CHAT_SUBTITLE = "The only chat for the morse code fanatic";
		public static final String CHAT_TITLE = "Morse Coder Chat";
		public static final String DISCONNECTED_STATUS = "Disconnected";
		public static final String CONNECTED_STATUS = "Connected";
		public static final String CONNECTING_STATUS = "Connecting";
		public static final String DISCONNECTING_STATUS = "Disconnecting";
		public static final String NOT_CONN_STATUS = "Not connected";
	}

	public static final class dimens {
		public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		public static final int screenHeight = (int) screenSize.getHeight();
		public static final int DIFFERENTIAL = 10;
		public static final int CODER_FRAME_HEIGHT = (int) (screenHeight <= 677 ? 541 : 677); // 20% decrease if true.
		public static final int CODER_FRAME_WIDTH = 723;
		public static final int TITLE_HEIGHT = 52;
		public static final int TOOL_TITLE_WIDTH = 331;
		public static final int TOOL_TITLE_X = 193;
		public static final int TOOL_TITLE_Y = (int) (screenHeight <= 677 ? 32 - (32 * .2) : 32);
		public static final int TOOL_LABEL_X = 65;
		public static final int TOOL_MSG_LABEL_Y = (int) (screenHeight <= 677 ? 82 - (86 * .2) : 86);
		public static final int LABEL_WIDTH = 450;
		public static final int LABEL_HEIGHT = 46;
		public static final int CONV_LABEL_Y = (int) (screenHeight <= 677 ? 359 - (363 * .2) : 363);
		public static final int TOOL_TXT_AREA_X = 65;
		public static final int TOOL_MSG_AREA_Y = (int) (screenHeight <= 677 ? 120 - (120 * .2) : 120);
		public static final int TOOL_TXT_AREA_WIDTH = 590;
		public static final int TOOL_TXT_AREA_HEIGHT = (int) (screenHeight <= 677 ? 192 - (192 * .2) : 192);
		public static final int CONV_AREA_Y = (int) (screenHeight <= 677 ? 396 - (396 * .2) : 396);
		public static final int BTN_WIDTH = 147;
		public static final int BTN_HEIGHT = 40;
		public static final int BTN_SPACING = BTN_WIDTH + DIFFERENTIAL;
		public static final int BTN_X = 194;
		public static final int BTN_Y = (int) (screenHeight <= 677 ? 320 - (320 * .2) : 320);
		public static final int DEMO_BTN_Y = (int) (screenHeight <= 677 ? 596 - (596 * .2) : 596);
		public static final int RADIO_WIDTH = 137;
		public static final int MENU_FRAME_WIDTH = 581;
		public static final int MENU_TITLE_WIDTH = 135;
		public static final int MENU_TITLE_X = 221;
		public static final int MENU_BTN_X = 57;
		public static final int MENU_BTN_Y = 92;
		public static final int MENU_FRAME_HEIGHT = 152;
		public static final int CHAT_FRAME_WIDTH = 810;
		public static final int CHAT_FRAME_HEIGHT = 500;
		public static final int CHAT_TITLE_X = 20;
		public static final int CHAT_BTN_Y = 199;
		public static final int CHAT_BTN_X = 16;
		public static final int CHAT_LABEL_Y = 20;
		public static final int CHAT_AREA_WIDTH = 450;
		public static final int CHAT_AREA_HEIGHT = 200;
		public static final int CHAT_MSG_AREA_WIDTH = 450;
		public static final int CHAT_MSG_AREA_HEIGHT = 170;
		public static final int CHAT_AREA_X = 340;
		public static final int CHAT_AREA_Y = 61;
		public static final int CHAT_MSG_LABEL_Y = 261;
		public static final int CHAT_MSG_Y = 302;
		public static final int CHAT_TITLE_Y = 25;
		public static final int CHAT_TITLE_WIDTH = 220;
		public static final int DISCONNECT_BTN_Y = 222;
		public static final int DISCONNECT_BTN_X = 169;
		public static final int CONNECT_BTN_Y = 222;
		public static final int CONNECT_BTN_X = 17;
		public static final int GUEST_RADIO_X = 201;
		public static final int RADIO_Y = 194;
		public static final int HOST_RADIO_X = 59;
		public static final int PORT_FIELD_Y = 152;
		public static final int PORT_LABEL_Y = 147;
		public static final int IP_LABEL_Y = 101;
		public static final int CHAT_FIELD_HEIGHT = 30;
		public static final int CHAT_FIELD_WIDTH = 227;
		public static final int IP_FIELD_Y = 106;
		public static final int CHAT_FIELD_X = 90;
		public static final int CONN_LABEL_X = 50;
		public static final int CONN_LABEL_Y = 264;
		public static final int CONN_COLOR_X = 20;
		public static final int CONN_COLOR_Y = 276;
		public static final int COLOR_WIDTH = 25;
		public static final int COLOR_HEIGHT = 20;
	}

	public static final class font {
		public static final Font BTN = new Font("Serif", Font.ROMAN_BASELINE, 16);
		public static final Font SMALL = new Font("Serif", Font.ROMAN_BASELINE, 18);
		public static final Font LARGE = new Font("Serif", Font.ROMAN_BASELINE, 28);
	}

	public static final class color {
		public static final Color BG_COLOR = Color.LIGHT_GRAY;
		public static final Color DARK_GRAY = Color.DARK_GRAY;
		public static final Color ORANGE = Color.ORANGE;
		public static final Color RED = Color.RED;
		public static final Color GREEN = Color.green;
		public static final Color YELLOW = Color.YELLOW;
		public static final Color WHITE = Color.WHITE;
	}

	public static final class status {
		public final static int NULL = 0;
		public final static int DISCONNECTED = 1;
		public final static int DISCONNECTING = 2;
		public final static int CONNECTING = 3;
		public final static int CONNECTED = 4;
	}

}
