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
 * This class represents the player and all of the attributes a player of a
 * game has.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class Player implements Serializable {

	/**
	 * This field represents the truthness of the {@link Player} being at
	 * the position in the {@link Grid}.
	 */
	private boolean isPlayerHere;
	
	/**
	 * This is a constructor method that sets initial values for the face of
	 * the player on the {@link Grid}, {@link #ammo}, {@link #livesLeft},
	 * and {@link #isInvincible}.
	 */
	public Player(boolean isHere) {
		isPlayerHere = isHere;
	}
	
	public void changePlayerPosition(boolean isHere) {
		isPlayerHere = isHere;
	}
	
	public boolean getPlayerPosition() {
		return isPlayerHere;
	}
}
