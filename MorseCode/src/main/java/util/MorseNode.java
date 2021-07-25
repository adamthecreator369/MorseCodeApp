/* Created by Adam Jost on 07/19/2021 */

package main.java.util;


public class MorseNode {
		 
	    // Data fields
	    private char character;
	    private String code;
	    private MorseNode left;
	    private MorseNode right;
	 
	    // Constructor
	    public MorseNode() {
	        character = ' ';
	        code = null;
	        left = null;
	        right = null;
	    }
	 
	    /**
	     * Gets and returns the character value of the MorseNode.
	     * @return: the character value. 
	     */
	    public char getCharacter() {
	        return character;
	    }
	 
	    /**
	     * Sets character of the MorseNode
	     * @param c: the character value to set.
	     */
	    public void setCharacter(char c) {
	        this.character = c;
	    }
	    
	    /**
	     * Gets and returns the Morse Code symbol of the MorseNode.
	     * 
	     * @return: the code value.
	     */
	    public String getCode() {
	    	return this.code;
	    }
	    
	    /**
	     * Sets the code value of the MorseNode.
	     * 
	     * @param code: the code value to set. 
	     */
	    public void setCode(String code) {
	    	this.code = code;
	    }
	 
	    /**
	     * Gets and returns the left descendant of a MorseNode.
	     * 
	     * @return: the left MorseNode.
	     */
	    public MorseNode getLeft() {
	        return left;
	    }
	 
	    /**
	     * Sets left descendant of a MorseNode.
	     * 
	     * @param left: the MorseNode to be set.
	     */
	    public void setLeft(MorseNode left) {
	        this.left = left;
	    }
	 
	    /** Gets and returns the right descendant of a MorseNode.
	     * 
	     * @return: the right descendant;
	     */
	    public MorseNode getRight() {
	        return right;
	    }
	 
	    /** Sets the right descendant of a MorseNode.
	     * 
	     * @param right: the MorseNode to set. 
	     */
	    public void setRight(MorseNode right) {
	        this.right = right;
	    }
	 
	}