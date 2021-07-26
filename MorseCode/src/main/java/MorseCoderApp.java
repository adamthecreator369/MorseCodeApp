/* Created by Adam Jost on 07/22/2021 */ 

package main.java;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.java.windows.Menu;

public class MorseCoderApp {
	public static void main (String[] args) {
		// Ensure the LookAndFeel is the same across all platforms. 
		// This accounts for differences between operating systems.
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Open the application menu. 
		new Menu();
	}
}
