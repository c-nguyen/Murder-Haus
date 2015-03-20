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
 * This class represents an enemy in the game.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class Enemy implements Serializable {
	
	/**
	 * This field represents the truthness of an {@link Enemy} being at a certain
	 * position in the {@link Grid}.
	 */
	private boolean isEnemyHere;
	
	/**
	 * This field represents the truthness of an {@link Enemy} moving or not.
	 * It is to make sure an {@link Enemy} does not move multiple times in
	 * one turn.
	 */
	private boolean hasMoved;

	/**
	 * This is a constructor method to set the initial value of the enemy's
	 * place on the {@link Grid}.
	 * @param enemy
	 */
	public Enemy(boolean isHere) {
		isEnemyHere = isHere;
		hasMoved = false;
	}
	
	/**
	 * This method changes the truthness of an {@link Enemy} being at a position
	 * in the {@link Grid}
	 * @param isHere represents if the Enemy is in this position or not
	 */
	public void changeEnemyPlace(boolean isHere) {
		isEnemyHere = isHere;
	}
	
	/**
	 * This method returns {@link #isEnemyHere}.
	 * @return isEnemyHere represents if the Enemy is at this position or not
	 */
	public boolean getEnemyPlace() {
		return isEnemyHere;
	}
	
	/**
	 * This method returns {@link #hasMoved}.
	 * @return hasMoved represents if the Enemy has moved in its turn or not
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	/**
	 * This method changes {@link #hasMoved} to the opposite value it is in to
	 * reset the values for each {@link Enemy}.
	 */
	public void toggleMove() {
		if (hasMoved == true)
			hasMoved = false;
		else
			hasMoved = true;
	}
}
