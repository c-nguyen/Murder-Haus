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
 * This class represents the game engine.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class GameEngine implements Serializable {
	
	/**
	 * This field represents the number of moves the {@link Player} has made.
	 */
	private int numberOfMoves;
	
	/**
	 * This field represents the number of bullets the {@link Player} has.
	 */
	private int ammo;
	
	/**
	 * This field represents how many turns the {@link Player} has for
	 * {@link Invincibility}.
	 */
	int invincibilityTurns;
	
	/**
	 * This field represents the number of lives the {@link Player} has.
	 */
	private int numberOfLives;
	
	/**
	 * This field represents if the {@link Player} gets the
	 * {@link AdditionalBullet} {@link PowerUp} or not.
	 */
	private boolean additionalBulletEffect;
	
	/**
	 * This field represents if the {@link Player} is currently invicincible
	 * or not.
	 */
	private boolean isInvincible;
	
	/**
	 * This field represents the location of the {@link Briefcase}.
	 */
	private String briefcaseLocation;
	
	/**
	 * This constructor method sets the initial values for the fields.
	 */
	public GameEngine() {
		numberOfMoves = 0;
		numberOfLives = 5;
		briefcaseLocation = "";
		ammo = 1;
		invincibilityTurns = 0;
		isInvincible = false;
	}
	
	/**
	 * This method sets the amount of {@link #ammo} for the {@link Player}.
	 * @param ammo represents the amount of bullets
	 */
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	/**
	 * This method increments the {@link #numberOfMoves} every time the
	 * {@link Player} moves.
	 */
	public void setNumberOfMoves() {
		++numberOfMoves;
	}
	
	/**
	 * This method sets the {@link #numberOfMoves} every time the game is loaded.
	 * @param numMoves represents the number of moves
	 */
	public void setNumberOfMoves(int numMoves) {
		numberOfMoves = numMoves;
	}
	
	/**
	 * This method decrements the {@link #numberOfLives} every time the
	 * {@link Player} is stabbed by an {@link Enemy}.
	 */
	public void setNumberOfLives() {
		--numberOfLives;
	}
	
	/**
	 * This method sets the {@link #numberOfLives} every time the game is loaded.
	 * @param numLives represents the number of lives
	 */
	public void setNumberOfLives(int numLives) {
		numberOfLives = numLives;
	}
	
	/**
	 * This method sets {@link #additionalBulletEffect} to {@code true} if the
	 * {@link Player} has already used a bullet previously or {@code false} if
	 * the {@link Player} has not already used a bullet.
	 */
	public void setAdditionalBullet(int ammo) {
		if (ammo == 1)
			additionalBulletEffect = false;
		else if (ammo == 0)
			additionalBulletEffect = true;
		
		if (additionalBulletEffect == true)
			setAmmo(1);
	}
	
	/**
	 * This method sets {@link #invincibilityTurns} to 5 when the
	 * {@link Invincibility} {@link PowerUp} has been activated.
	 */
	public void setInvincibility() {
		invincibilityTurns = 5;
	}
	
	/**
	 * This method decrements {@link #invincibilityTurns} after the
	 * {@link Player} has activated the {@link Invincibility} {@link PowerUp}.
	 */
	public void decrementInvincibility() {
		--invincibilityTurns;
	}
	
	/**
	 * This method checks if {@link Invincibility} has been activated and will
	 * return {@code true} if it has.
	 * @return isInvincible represents if the power up has been activated or not
	 */
	public boolean checkInvincibility() {
		if (invincibilityTurns != 0)
			isInvincible = true;
		else if (invincibilityTurns == 0)
			isInvincible = false;
		return isInvincible;
	}
	
	/**
	 * This method sets the {@link Briefcase} location according the to its
	 * coordinates on the {@link Grid}.
	 * @param coord1 represents the x coordinate of the briefcase
	 * @param coord2 represents the y coordinate of the briefcase
	 */
	public void setRadar(int coord1, int coord2) {
		if (coord1 == 1) {
			if (coord2 == 1)
				briefcaseLocation = "The briefcase is in the room in the 1st row, 1st column of rooms.";
			else if (coord2 == 4)
				briefcaseLocation = "The briefcase is in the room in the 1st row, 2nd column of rooms.";
			else if (coord2 == 7)
				briefcaseLocation = "The briefcase is in the room in the 1st row, 3rd column of rooms.";
		}
		else if (coord1 == 4) {
			if (coord2 == 1)
				briefcaseLocation = "The briefcase is in the room in the 2nd row, 1st column of rooms.";
			else if (coord2 == 4)
				briefcaseLocation = "The briefcase is in the room in the 2nd row, 2nd column of rooms.";
			else if (coord2 == 7)
				briefcaseLocation = "The briefcase is in the room in the 2nd row, 3rd column of rooms.";
		}
		else if (coord1 == 7) {
			if (coord2 == 1)
				briefcaseLocation = "The briefcase is in the room in the 3rd row, 1st column of rooms.";
			else if (coord2 == 4)
				briefcaseLocation = "The briefcase is in the room in the 3rd row, 2nd column of rooms.";
			else if (coord2 == 7)
				briefcaseLocation = "The briefcase is in the room in the 3rd row, 3rd column of rooms.";
		}
	}
	
	/**
	 * This method displays a message when the {@link Player} wins and exits
	 * the program.
	 */
	public void setGameOverWin() {
		System.out.println("Congratulations! You found the briefcase. Game Over.");
		System.exit(0);
	}
	
	/**
	 * This method displays a message the {@link Player} loses and exits
	 * the program.
	 */
	public void setGameOverLose() {
		System.out.println("You lost all of your lives. Game Over.");
		System.exit(0);
	}
	
	/**
	 * This method returns the value of {@link #ammo}.
	 * @return ammo represents how many bullets the player has left
	 */
	public int getAmmo() {
		return ammo;
	}
	
	/**
	 * This method returns if the {@link AdditionalBullet} has been activated
	 * or not.
	 * @return additionalBulletEffect represents if the power has been activated
	 */
	public boolean getABEffect() {
		return additionalBulletEffect;
	}
	
	/**
	 * This method returns the location of the {@link Briefcase}.
	 * @return briefcaseLocation represents the location of the briefcase
	 */
	public String getRadarEffect() {
		return briefcaseLocation;
	}
	
	/**
	 * This method returns the amount of moves the {@link Player} has taken.
	 * @return numberOfMoves represents the amount of moves the player has taken
	 */
	public int getNumberOfMoves() {
		return numberOfMoves;
	}
	
	/**
	 * This method returns the amount of lives the {@link Player} has left.
	 * @return numberOfLives represents the amount of lives the player has left
	 */
	public int getNumberOfLives() {
		return numberOfLives;
	}
	
	/**
	 * This method returns the amount of turns of being invincible.
	 * @return invincibilityTurns represents the amount of turns left of being
	 * invincible
	 */
	public int getInvincibilityTurns() {
		return invincibilityTurns;
	}
}
