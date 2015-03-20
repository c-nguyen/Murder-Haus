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

import java.io.Serializable;

/**
 * This class represents the building that the {@link Player}, {@link Enemy},
 * {@link Room}, {@link Tile}, and {@link Briefcase} is in.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public abstract class Floor implements Serializable {
	
	/**
	 * This field represents the layout of how the {@link Tile} and {link Room}
	 * are placed in the building.
	 */
	private char floorLayout;
	
	/**
	 * This field represents if a {@link Room} is in this position or not.
	 */
	private boolean isRoomHere;
	
	/**
	 * This is a constructor method to set the initial values of the {@link Tile}
	 * and {@link Room}.
	 * @param floor represents the value of the {@link Tile} or {@link Room}
	 */
	public Floor(char floor, boolean room) {
		floorLayout = floor;
		isRoomHere = room;
	}
	
	/**
	 * This method returns the value of {@link #floorLayout}.
	 * @return the value of either the {@link Tile} or {@link Room}
	 */
	public char getFloorLayout() {
		return floorLayout;
	}
	
	/**
	 * This method returns the value of {@link #isRoomHere}.
	 * @return isRoomHere represents if a Room is at the position or not
	 */
	public boolean checkRoom() {
		return isRoomHere;
	}
	
	/**
	 * This method changes the {@link Tile} to a different character value
	 * depending what is on that {@link Tile} at the moment.
	 * @param floor represents the new character value
	 */
	public void changeTile(char floor) {
		floorLayout = floor;
	}
}
