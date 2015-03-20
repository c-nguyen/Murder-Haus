/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment Final Project
 *
 * This is a team project. The goal is to create a game that takes place inside
 * a building, which will be represented as a grid of 81 squares. Every square
 * represents a possible position for different entities in the game (player's
 * avatar, enemies, power-ups), with the exception of 9 special squares, which
 * represent rooms, and are equally distributed on the grid.
 *
 * Team Code Monkeys
 *   Christine Nguyen
 *   Wesley Lang
 *   Fernando Garcia
 *   Harry Tran
 */
package edu.csupomona.cs.cs141.prog_assgmnt_final;

/**
 * This class is the main class, which runs the whole game.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class Main {

	/**
	 * This method will create an {@link UserInterface} object and run the game.
	 * @param args
	 */
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.displayMainMenu();
	}
}
