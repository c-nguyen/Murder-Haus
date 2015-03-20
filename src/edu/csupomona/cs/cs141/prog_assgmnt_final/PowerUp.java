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
 * This is an abstract class.  It represents the power-ups made available in
 * the game, which are {@link AdditionalBullet}, {@link Invincibility},
 * and {@link Radar}.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public abstract class PowerUp implements Serializable {
	
	/**
	 * This field represents the truth value if the power-up is at a certain
	 * location on the {@link Grid} or not.
	 */
	private boolean isHere;
	
	/**
	 * This field represents the face value of each power up.
	 */
	private char powerUpFace;
	
	/**
	 * This field represents if the {@link AdditionalBullet} has been activated
	 * or not.
	 */
	private boolean isBulletEffectOn;
	
	/**
	 * This field represents if the {@link Invincibility} has been activated
	 * or not.
	 */
	private boolean isInvincibleEffectOn;
	
	/**
	 * This field represents if the {@link Radar} has been activated
	 * or not.
	 */
	private boolean isRadarEffectOn;
	
	/**
	 * This method sets the value of {@link #isTrue} to true or false, depending
	 * on if it is at the location on the {@link Grid} or not.
	 */
	public PowerUp(boolean isTrue, char whichPowerUp) {
		isHere = isTrue;
		powerUpFace = whichPowerUp;
		isBulletEffectOn = false;
		isInvincibleEffectOn = false;
		isRadarEffectOn = false;
	}
	
	/**
	 * This method will return the value of {@link #isHere}.
	 * @return the value of {@link #isHere}
	 */
	public boolean getPlace() {
		return isHere;
	}
	
	/**
	 * This method will return the character value of the current power up.
	 * @return powerUpFace represents the character value of the power up
	 */
	public char getPowerUpFace() {
		return powerUpFace;
	}
	
	/**
	 * This method turns {@link #isBulletEffectOn} to {@code true} once
	 * the power up has been activated.
	 */
	public void turnOnAB() {
		isBulletEffectOn = true;
	}
	
	/**
	 * This method turns {@link #isInvincibleEffectOn} to {@code true} once
	 * the power up has been activated.
	 */
	public void turnOnInvincible() {
		isInvincibleEffectOn = true;
	}
	
	/**
	 * This method turns {@link #isRadarEffectOn} to {@code true} once
	 * the power up has been activated.
	 */
	public void turnOnRadar() {
		isRadarEffectOn = true;
	}
	
	/**
	 * This method returns if the power up has been activated or not.
	 * @return isBulletEffectOn represents if power up has been activated or not
	 */
	public boolean getABEffect() {
		return isBulletEffectOn;
	}
	
	/**
	 * This method returns if the power up has been activated or not.
	 * @return isInvincibleEffectOn represents if power up has been activated or not
	 */
	public boolean getInvincible() {
		return isInvincibleEffectOn;
	}
	
	/**
	 * This method returns if the power up has been activated or not.
	 * @return isRadarEffectOn represents if power up has been activated or not
	 */
	public boolean getRadar() {
		return isRadarEffectOn;
	}
}
