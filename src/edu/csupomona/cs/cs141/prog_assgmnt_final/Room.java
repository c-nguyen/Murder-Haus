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
 * This class represents the rooms in the building that the {@link Player}
 * can go into to search for the briefcase.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class Room extends Floor {
	
	/**
	 * This is a constructor method to set the initial value face of the room.
	 * @param room represents the value or what the room will show on the {@link Grid}.
	 */
	public Room(char roomFace) {
		super(roomFace, true);
	}
}
