/* Created by Adam Jost on 07/22/2021 */ 

package main.java;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.java.windows.MainMenu;

public class MorseCoderApp {
	public static void main (String[] args) {	
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Open the app menu. 
		new MainMenu();
	}
}
