/* Created by Adam Jost on 07/21/2021 */

package main.java.util;

public class MessageStrings {
	/**
	 * Tests whether a character is a valid Morse Code character {.} or {-}.
	 * 
	 * @param c: the character being tested.
	 * @return: {true} if the character is indeed a Morse Code character; {false}
	 *          otherwise.
	 */
	public static boolean isCodeChar(char c) {
		return c == '.' || c == '-';
	}

	/**
	 * Test whether the entered input message contains Morse Code.
	 * 
	 * @param msg: the user-entered input message.
	 * @return: {true} if the message is comprised of over 80% Morse Code characters
	 *          {.} and {-}; {false} otherwise.
	 */
	public static boolean msgIsCode(String msg) {
		// Keep track of the number of characters that are not Morse Code.
		double numOfChars = 0;
		// Keep track of the messages length not including white space characters.
		// The reason for doing this is that we do not want white space characters
		// to effect the result. Example: ".- " would be determined to not
		// be Morse Code because over 20% of its characters are white space when it
		// should be determined to be Morse Code since it does not contain any text.
		double length = msg.trim().length();

		for (char c : msg.toCharArray()) {
			// Decrement the length is a white space character is encountered.
			if (Character.isWhitespace(c)) {
				length--;
				continue;
			}
			// If the character is not a Morse Code character then increment the value.
			if (!isCodeChar(c)) {
				numOfChars++;
			}
		}
		// If the message is comprised of less than 20% of characters proven not
		// to be Morse Code then the message is determined to, in fact, be Morse
		// Code so we return true. Otherwise, we return false.
		return numOfChars / length <= 0.2 ? true : false;
	}

	/**
	 * Removes all consecutive white space characters from an input String leaving
	 * only the first occurrence of white space.
	 * 
	 * @param msg: the input message String.
	 * @return: the message with all consecutive white space characters removed.
	 */
	public static String reduceTextWhiteSpaces(String msg) {
		StringBuilder sb = new StringBuilder();
		// Iterate through each individual character in the parameter message.
		for (int i = 0; i < msg.length(); i++) {
			if (Character.isWhitespace(msg.charAt(i))) {
				// Append the first occurrence of white space.
				sb.append(msg.charAt(i));
				// Skip and disregard all subsequent white space characters.
				if (i + 1 < msg.length() && Character.isWhitespace(msg.charAt(i + 1))) {
					while (Character.isWhitespace(msg.charAt(++i))) {
						continue;
					}
					i--;
				}
			} else {
				// If it is not a white space character then we simply append the
				// character to the StringBuilder.
				sb.append(msg.charAt(i));
			}
		}
		// Return the built String.
		return sb.toString();
	}
	
	/**
	 * Removes all consecutive white space characters but the first two from an input String.
	 * This method is used to properly format coded messages so that when they are decoded using
	 * the MorseChat Endecoder tool the spacing of the decoded message will be correct with a single 
	 * space in between words. 
	 * 
	 * @param codedMsg: the input coded message String.
	 * @return: the message with all consecutive white space characters reduced to the desired format.
	 */
	public static String reduceCodeWhiteSpaces(String codedMsg) {
		// Object used to build the result String.
		StringBuilder sb = new StringBuilder();
		// {true} after the first occurrence of a white space character.
		boolean oneSpace = false;
		// {true} after the second occurrence of a white space character.
		// Tells the below loop to start "skipping" white spaces.
		boolean startSkipping = false;
		// Iterate through each character in the coded message.
		for (char c : codedMsg.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (!oneSpace) {
					sb.append(' ');
					oneSpace = true;
				} else if (!startSkipping) {
					sb.append(' ');
					startSkipping = true;
				} else if (startSkipping) {
					continue;
				}
			} else {
				// Append the code character to the StringBuilder.
				sb.append(c);
				// Reset.
				oneSpace = false;
				startSkipping = false;
			}
		}
		
		// Return the coded message with reduced spaces
		// that was just built.
		return sb.toString();
	}
}
