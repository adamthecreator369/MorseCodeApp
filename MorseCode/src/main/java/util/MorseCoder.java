/* Created by Adam Jost on 07/20/2021 */

package main.java.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import main.java.resources.R;

public class MorseCoder {

	// Data fields
	private MorseNode root;

	// Constructor
	public MorseCoder() {
		root = new MorseNode();
	}

	/**
	 * Builds the Morse Code tree using data from an input file stream.
	 * 
	 * @return: {true} if input file was available. {false} otherwise.
	 */
	public boolean buildMorseTree() {
		// Create a input file stream object
		FileInputStream fin;
		// Attempt to locate the input file.
		try {
			fin = new FileInputStream("characters.txt");
		} catch (FileNotFoundException e) {
			// Returning false here will cause the Decoder tool
			// to display an error message informing the user
			// that the system is down due to the missing data file.
			// The system will perform a visual count down to program
			// termination and subsequently close the frame, ending the
			// program.
			return false;
		}
		// Object used to read input file line-by-line.
		Scanner scanner = new Scanner(fin);
		// Parse the input file.
		while (scanner.hasNext()) {
			// Account for badly formatted input that may produce unexpected
			// results by...
			// Removing white spaces from the input line,
			String line = scanner.nextLine().replaceAll("[\\s\\p{Z}]", "");
			// and skipping empty lines.
			if (line.equals("")) {
				continue;
			}
			// Create a new MorseNode using the read-in data and add it to
			// the tree.
			add(line);
		}
		// Close the open input streams.
		scanner.close();
		try {
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// If we made it to this point then data was successfully retrieved from
		// the file and the tree has been built, so we inform the system that the
		// data is available and everything is good to go.
		return true;
	}

	/**
	 * Branches left or right while traversing the tree dependent on the current
	 * Morse Code character. If the character is a '.' it will return the left
	 * MorseNode. If the character is a '-' it will return the right MorseNode. When
	 * branching left or right if a MorseNode is null it will create a new MorseNode
	 * in that location.
	 * 
	 * @param current: reference to the current MorseNode of the MorseTree.
	 * @param dotDash: the current Morse Code character being analyzed.
	 * @return: the current MorsNode's left or right descendant MorseNode.
	 */
	private MorseNode nextNode(MorseNode current, char dotDash) {
		switch (dotDash) {
		case '.':
			// If the current MorseNode does not have a descendant
			// left MorseNode then create one.
			if (current.getLeft() == null) {
				current.setLeft(new MorseNode());
			}
			// Return the descendant left MorseNode.
			return current.getLeft();
		case '-':
			// If the current MorseNode does not have a descendant
			// right MorseNode then create one.
			if (current.getRight() == null) {
				current.setRight(new MorseNode());
			}
			// Return the descendant right MorseNode.
			return current.getRight();
		default:
			// As long as the input is valid we should never throw this exception.
			throw new IllegalArgumentException(R.string.UNRECOGNIZED_CODE);
		}
	}

	/**
	 * Adds a MorseNode to the MorseNode tree.
	 * 
	 * @param letterCodePair: the letter and code data of the MorseNode to be added.
	 */
	private void add(String charCodePair) {
		MorseNode current = root;
		// The letter value of the MorseNode to be added to the tree.
		char letter = charCodePair.charAt(0);
		// The code value of the MorseNode to be added to the tree.
		String mCode = charCodePair.substring(1).trim();

		// Iterate through each character of the Morse Code character,
		// branching left and right until its proper place is reached.
		for (char c : mCode.toCharArray()) {
			current = nextNode(current, c);
		}

		// Set the code and character data values of the MorseNode.
		current.setCharacter(letter);
		current.setCode(mCode);
	}

	/**
	 * Gets and returns the Morse Code that represents a specified character.
	 * 
	 * @param current: reference to the current node.
	 * @param c: the character whose Morse Code representation is being retrieved.
	 * @return: the Morse Code representation of the parameter character.
	 */
	private String getCode(MorseNode current, char c) {
		// Base case: returns an empty string.
		if (current == null)
			return "";
		// If the MorseNode's character value matches that of the input character,
		// return the code value for that letter.
		if (current.getCharacter() == c) {
			return current.getCode();
		}
		// Recurrence relation: search the left and the right subtrees.
		return "" + getCode(current.getLeft(), c) + getCode(current.getRight(), c);
	}

	/**
	 * Encodes a text message to Morse Code.
	 * 
	 * @param msg: the plain text message to be encoded.
	 * @return: the encoded message.
	 */
	public String encode(String msg) {
		// Transform the message String to all lowercase characters.
		msg = msg.toLowerCase();
		MorseNode current = root;
		// Builds the encoded result String.
		StringBuilder sb = new StringBuilder();
		// Builds a String of all invalid characters, if any.
		StringBuilder badChars = new StringBuilder();
		boolean hasBadChars = false;

		for (char c : msg.toCharArray()) {
			String code = "";
			// Append all white space characters as a single space.
			if (Character.isWhitespace(c)) {
				sb.append(' ');
			} else {
				// Retrieve the morse code symbol for the corresponding character.
				code = getCode(current, c);
				// If code is an empty String then it was found to be an unsupported
				// character so we append the current character to the badChars StringBuilder.
				if (code.equals("")) {
					hasBadChars = true;
					badChars.append(c).append(' ');
				} else {
					// Otherwise, the code has been successfully retreived so we now append it
					// to the end of the encoded result String.
					sb.append(code).append(' ');
				}
			}
		}
		// If invalid/unsupported characters were found in the input message we append
		// an error message followed by the list of all invalid characters that we found.
		if (hasBadChars) {
			sb.append(R.string.SPACE_BETWEEN + R.string.ERROR_UNSUPPORTED).append(badChars);
		}
		
		// Return the built String.
		return sb.toString();
	}

	/**
	 * Decodes a Morse Code message.
	 * 
	 * @param msg: the message to be decoded.
	 * @return: the decoded message.
	 */
	public String decode(String msg) {
		MorseNode current = root;
		StringBuilder result = new StringBuilder();
		StringBuilder badChars = new StringBuilder();
		boolean isCode = false, hasBadChars = false;

		// Iterate through the coded message branching left at
		// each occurrence of '.' and right at each occurrence of '-'.
		// When a white space character is encountered then we have
		// reached the end of the code character so we have arrived at
		// the correct MorseNode and append its letter value to the
		// StringBuilder.
		for (char c : msg.toCharArray()) {
			if (c == '.') {
				current = current.getLeft();
				isCode = true;
			} else if (c == '-') {
				current = current.getRight();
				isCode = true;
			} else if (Character.isWhitespace(c)) {
				result.append(current.getCharacter());
				current = root;
			} else {
				// The encountered character is invalid so
				// we append the character to the end of the
				// StringBuilder for the bad characters.
				hasBadChars = true;
				badChars.append(c).append(' ');
				result.append(current.getCharacter());
				current = root;
			}
		}
		result.append(current.getCharacter());
		
		// If the argument String did not contain a single Morse Code character
		// then throw this exception which in turn will trigger an error message
		// to the user "* Error: Unrecognized Morse Code".
		if (!isCode) { 
			throw new IllegalArgumentException(R.string.UNRECOGNIZED_CODE);
		}

		// If the coded message was found to contain invalid characters we add
		// an error message and the list of invalid characters to the end of the
		// result StringBuilder.
		if (hasBadChars) {
			result = result.append(R.string.SPACE_BETWEEN + R.string.ERROR_UNSUPPORTED).append(badChars.toString());
		}
		
		// Return the built result String.
		return result.toString();
	}

}