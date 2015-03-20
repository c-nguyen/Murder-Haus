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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * This class represents the building that the {@link Player}, {@link Enemy},
 * {@link PowerUp}, and briefcase are in.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class Grid implements Serializable {
	
	/**
	 * This field represents the layout of the {@link Tile} and {@link Room}
	 * in the building.
	 */
	private Floor[][] floorLayout = new Floor[9][9];
	
	/**
	 * This field represents the coordinates of the {@link Player}.
	 */
	private Player[][] playerGrid = new Player[9][9];
	
	/**
	 * This field represents the coordinate of each {@link Enemy}.
	 */
	private Enemy[][] enemyGrid = new Enemy[9][9];
	
	/**
	 * This field represents the layout of each {@link PowerUp} in the building.
	 */
	private PowerUp[][] powerUpGrid = new PowerUp[9][9];
	
	/**
	 * This field represents player's "x" coordinate or the first number in the
	 * array, where the player is.
	 */
	private int playerCoordinate1;
	
	/**
	 * This field represents player's "y" coordinate or the second number in the
	 * array, where the player is.
	 */
	private int playerCoordinate2;
	
	/**
	 * This field represents the random number generator used to randomly spawn
	 * {@link Enemy} and {@link PowerUp}
	 */
	Random randomNumGen = new Random();
	
	/**
	 * This field represents the {@link GameEngine}.
	 */
	GameEngine ge = new GameEngine();
	
	/**
	 * This field represents the message that will appear if a player receives
	 * a {@link PowerUp} or error.
	 */
	private String message;
	
	/**
	 * This field represents the validity of the look function.
	 */
	boolean isLookOkay;
	
	/**
	 * This field represents the validity of the {@link Player} movement.
	 */
	boolean isPlayerMoveOkay;
	
	/**
	 * This field represents the state of the game: normal or debug.
	 */
	int gameMode;
	
	/**
	 * This constructor method sets initial values for {@link #message}
	 * and {@link #isLookOkay}.
	 */
	public Grid() {
		message = "";
		isLookOkay = true;
	}
	
	/**
	 * This method changes the {@link #message} in the game depending on what
	 * the {@link Player} is doing.
	 * @param str
	 */
	public void changeMessage(String str) {
		message = str;
	}
	
	/**
	 * This method sets {@link #gameMode}.
	 */
	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}
	
	/**
	 * This method initializes the grid that represents the layout of the
	 * {@link Tile} and {@link Room} in the building.
	 */
	public void initializeBuildingGrid() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				floorLayout[i][j] = new Tile(' ');
			}
		}
		
		floorLayout[1][1] = new Room('R');
		floorLayout[1][4] = new Room('R');
		floorLayout[1][7] = new Room('R');
		floorLayout[4][1] = new Room('R');
		floorLayout[4][4] = new Room('R');
		floorLayout[4][7] = new Room('R');
		floorLayout[7][1] = new Room('R');
		floorLayout[7][4] = new Room('R');
		floorLayout[7][7] = new Room('R');
	}
	
	/**
	 * This method initializes the layout of the {@link Player} to get the
	 * coordinates.
	 */
	public void initializePlayerGrid() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j)
				playerGrid[i][j] = new Player(false);
		}
		playerGrid[8][0] = new Player(true);
	}
	
	/**
	 * This method initializes the layout of the {@link Enemy} and places each
	 * object randomly on the {@link Grid} and at least 3 spaces away from the
	 * {@link Player}.
	 */
	public void initializeEnemyGrid() {
		int numberOfEnemy = 0;
		
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j)
				enemyGrid[i][j] = new Enemy(false);
		}
		
		while (numberOfEnemy <= 5) {
			int coordinate1 = randomNumGen.nextInt(6);
			int coordinate2 = randomNumGen.nextInt(9);
			if (coordinate1 > -1 && coordinate1 < 6 && coordinate2 > 2 && coordinate2 < 9) {
				if (numberOfEnemy == 0) {
					if  (floorLayout[coordinate1][coordinate2].checkRoom() == false) {
						enemyGrid[coordinate1][coordinate2] = new Enemy(true);
						++numberOfEnemy;
					}
				}
				else if (numberOfEnemy > 0) {
					if (enemyGrid[coordinate1][coordinate2].getEnemyPlace() == false &&
							floorLayout[coordinate1][coordinate2].checkRoom() == false) {
						enemyGrid[coordinate1][coordinate2] = new Enemy(true);
						++numberOfEnemy;
					}
				}
			}
		}
	}
	
	/**
	 * This method creates random coordinates by the {@link #randomNumGen} and
	 * uses these coordinates to place each {@link PowerUp} on the grid.
	 */
	public void setPowerUpPosition() {
		int bullet1 = randomNumGen.nextInt(9);
		int bullet2 = randomNumGen.nextInt(9);
		int invincibility1 = randomNumGen.nextInt(9);
		int invincibility2 = randomNumGen.nextInt(9);
		int radar1 = randomNumGen.nextInt(9);
		int radar2 = randomNumGen.nextInt(9);
		int briefcase1 = randomNumGen.nextInt(9);
		int briefcase2 = randomNumGen.nextInt(9);
		boolean isBriefcaseInRoom = false;
		
		while ((bullet1 == 8 && bullet2 == 0) ||
			(floorLayout[bullet1][bullet2].checkRoom() == true)) {
				bullet1 = randomNumGen.nextInt(9);
				bullet2 = randomNumGen.nextInt(9);
		}
		while ((invincibility1 == 8 && invincibility2 == 0) ||
			(floorLayout[invincibility1][invincibility2].checkRoom() == true)) {
				invincibility1 = randomNumGen.nextInt(9);
				invincibility2 = randomNumGen.nextInt(9);
		}
		while ((radar1 == 8 && radar2 == 0) ||
			(floorLayout[radar1][radar2].checkRoom() == true)) {
				radar1 = randomNumGen.nextInt(9);
				radar2 = randomNumGen.nextInt(9);
		}
		while (isBriefcaseInRoom == false) {
			briefcase1 = randomNumGen.nextInt(9);
			briefcase2 = randomNumGen.nextInt(9);
			
			if (floorLayout[briefcase1][briefcase2].checkRoom() == true) {
				isBriefcaseInRoom = true;
			}
		}
		
		for (int i = 0; i < 9; ++ i) {
			for (int j = 0; j < 9; ++j)
				powerUpGrid[i][j] = new Radar(false);
		}
		
		powerUpGrid[bullet1][bullet2] = new AdditionalBullet(true);
		powerUpGrid[invincibility1][invincibility2] = new Invincibility(true);
		powerUpGrid[radar1][radar2] = new Radar(true);
		powerUpGrid[briefcase1][briefcase2] = new Briefcase(true);
		
		ge.setRadar(briefcase1, briefcase2);
		
		if (gameMode == 2) {
			floorLayout[bullet1][bullet2] = new Tile('A');
			floorLayout[invincibility1][invincibility2] = new Tile('I');
			floorLayout[radar1][radar2] = new Tile('B');
			floorLayout[briefcase1][briefcase2].changeTile('!');
		}
	}

	/**
	 * This method find the coordinates of the {@link Player} and changes the
	 * {@link Tile} value to the {@link Player} to see where the {@link Player}
	 * is in the game.
	 */
	public void findPlayerPosition() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (playerGrid[i][j].getPlayerPosition() == true) {
					floorLayout[i][j].changeTile('P');
					playerCoordinate1 = i;
					playerCoordinate2 = j;
				}
			}
		}
	}
	
	/**
	 * This method find the coordinates of the {@link Enemy} and changes the
	 * {@link Tile} value to the {@link Enemy} to see where the {@link Enemy}
	 * is in the game.
	 */
	public void findEnemyPosition() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (gameMode == 2) {
					if (powerUpGrid[i][j].getPlace() == true) {
						if (powerUpGrid[i][j].getPowerUpFace() == 'A') {
							if (powerUpGrid[i][j].getABEffect() == false)
								floorLayout[i][j].changeTile('A');
						}
						else if (powerUpGrid[i][j].getPowerUpFace() == 'I') {
							if (powerUpGrid[i][j].getInvincible() == false)
								floorLayout[i][j].changeTile('I');
						}
						else if (powerUpGrid[i][j].getPowerUpFace() == 'B') {
							if (powerUpGrid[i][j].getRadar() == false)
								floorLayout[i][j].changeTile('B');
						}
					}
					if (enemyGrid[i][j].getEnemyPlace() == true)
						floorLayout[i][j].changeTile('E');
				}
			}
		}
	}
	
	/**
	 * This method changes the tile face whenever the {@link Player} moves
	 * across the board.
	 */
	public void changeTileFaceForPlayer() {
		playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(false);
		floorLayout[playerCoordinate1][playerCoordinate2].changeTile(' ');
		
		if (floorLayout[playerCoordinate1][playerCoordinate2].checkRoom() == true)
			floorLayout[playerCoordinate1][playerCoordinate2].changeTile('R');
	}
	
	/**
	 * This method checks if the {@link Player} is on a {@link PowerUp} or not.
	 * This method also determines which {@link PowerUp} and affects the
	 * {@link Player} accordingly.
	 */
	public void checkPlayerAndPowerUp() {
		char whichPowerUp;
		if (powerUpGrid[playerCoordinate1][playerCoordinate2].getPlace() == true) {
			whichPowerUp = powerUpGrid[playerCoordinate1][playerCoordinate2].getPowerUpFace();
			if (whichPowerUp == 'A') {
				powerUpGrid[playerCoordinate1][playerCoordinate2].turnOnAB();
				ge.setAdditionalBullet(ge.getAmmo());
				if (ge.getABEffect() == false)
					changeMessage("The Additional Bullet Power Up does not affect you.");
				else
					changeMessage("You got an additional bullet from the power up.");
				powerUpGrid[playerCoordinate1][playerCoordinate2] = new Radar(false);
			}
			else if (whichPowerUp == 'I') {
				powerUpGrid[playerCoordinate1][playerCoordinate2].turnOnInvincible();
				ge.setInvincibility();
				changeMessage("You are now invincible for 5 turns.");
				powerUpGrid[playerCoordinate1][playerCoordinate2] = new Radar(false);
			}
			else if (whichPowerUp == 'B') {
				powerUpGrid[playerCoordinate1][playerCoordinate2].turnOnRadar();
				changeMessage(ge.getRadarEffect());
				powerUpGrid[playerCoordinate1][playerCoordinate2] = new Radar(false);
			}
			else if (whichPowerUp == '!') {
				ge.setGameOverWin();
			}
		}
	}
	
	/**
	 * This method prints an instance of the grid at any moment of the game.
	 */
	public void printBuildingGrid() {
		int counter = 1;
		int row = 1;
		for (Floor[] a : floorLayout) {
			for (Floor b : a) {
				if (counter < 9) {
					System.out.print("[" + b.getFloorLayout() + "] ");
					++counter;
				}
				else {
					if (row == 2) {
						System.out.println("[" + b.getFloorLayout() + "] "
						+ "//Ammo: " + ge.getAmmo());
					}
					else if (row == 3) {
						System.out.println("[" + b.getFloorLayout() + "] "
						+ "//Invincibility Turns: " + ge.getInvincibilityTurns());
					}
					else if (row == 4) {
						System.out.println("[" + b.getFloorLayout() + "] "
						+ "//Number Of Moves: " + ge.getNumberOfMoves());
					}
					else if (row == 5) {
						System.out.println("[" + b.getFloorLayout() + "] "
						+ "//Number Of Lives: " + ge.getNumberOfLives());
					}
					else if (row == 7) {
						System.out.println("[" + b.getFloorLayout() + "] "
						+ "//Message: ");
					}
					else if (row == 8) {
						System.out.print("[" + b.getFloorLayout() + "] ");
						System.out.println(message);
					}
					else {
						System.out.println("[" + b.getFloorLayout() + "] ");
					}
					counter = 1;
					++row;
				}
			}
		}
		changeMessage("");
	}
	
	/**
	 * This method represents the look function. If the {@link Player} is
	 * using the look function before moving or shooting, then the
	 * {@link Player} will get 3 spaces above him/her for vision. Else,
	 * the {@link Player} will get 2 spaces above for vision.
	 * @param playerLook represents the direction the Player is looking
	 * @param lookDistance represents the number of spaces of vision
	 * @param isLooking represents is the Player looking or moving
	 */
	public void playerLook(int playerLook, int lookDistance, boolean isLooking) {
		if (gameMode == 1)
			hidePowerUps();
		isLookOkay = true;
		int lookCoord[][] = new int[2][3];
		lookCoord[0][0] = playerCoordinate1;
		lookCoord[0][1] = playerCoordinate1;
		lookCoord[0][2] = playerCoordinate1;
		lookCoord[1][0] = playerCoordinate2;
		lookCoord[1][1] = playerCoordinate2;
		lookCoord[1][2] = playerCoordinate2;
		
		if (playerLook == 1) {
			lookCoord[1][0]-=1;
			lookCoord[1][1]-=2;
			lookCoord[1][2]-=3;
		}
		else if (playerLook == 2) {
			lookCoord[1][0]+=1;
			lookCoord[1][1]+=2;
			lookCoord[1][2]+=3;
		}
		else if (playerLook == 3) {
			lookCoord[0][0]-=1;
			lookCoord[0][1]-=2;
			lookCoord[0][2]-=3;
		}
		else if (playerLook == 4) {
			lookCoord[0][0]+=1;
			lookCoord[0][1]+=2;
			lookCoord[0][2]+=3;
		}
		try {
			for (int i = 0; i < lookDistance; ++i) {
				if (lookCoord[0][i] > -1 && lookCoord[1][i] < 9) {
					if (enemyGrid[lookCoord[0][i]][lookCoord[1][i]].getEnemyPlace() == true)
						floorLayout[lookCoord[0][i]][lookCoord[1][i]].changeTile('E');
					if (powerUpGrid[lookCoord[0][i]][lookCoord[1][i]].getPlace() == true
							&& powerUpGrid[lookCoord[0][i]][lookCoord[1][i]].getPowerUpFace() != '!') {
						floorLayout[lookCoord[0][i]][lookCoord[1][i]].changeTile(powerUpGrid[lookCoord[0][i]][lookCoord[1][i]].getPowerUpFace());
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			if (isLooking == false)
				;
			else if (playerCoordinate1 == 0 && playerLook == 3) {
				System.out.println("You cannot look in this direction.");
				isLookOkay = false;
			}
			else if (playerCoordinate1 == 8 && playerLook == 4) {
				System.out.println("You cannot look in this direction.");
				isLookOkay = false;
			}
			else if (playerCoordinate2 == 0 && playerLook == 1) {
				System.out.println("You cannot look in this direction.");
				isLookOkay = false;
			}
			else if (playerCoordinate2 == 8 && playerLook == 2) {
				System.out.println("You cannot look in this direction.");
				isLookOkay = false;
			}
			else {
				--lookDistance;
				playerLook(playerLook, lookDistance, isLooking);
			}
		}
	}
	
	/**
	 * This method hides the {@link PowerUp} and {@link Enemy} after the
	 * look function.
	 */
	public void hidePowerUps() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (powerUpGrid[i][j].getPlace() == true)
//				if (powerUpGrid[i][j].getPowerUpFace() != '!') 
					floorLayout[i][j].changeTile(' ');
				if (floorLayout[i][j].checkRoom() == true)
					floorLayout[i][j].changeTile('R');
			}
		}
	}
	
	/**
	 * This method returns {@link #isLookOkay} to determine if an out of bounds
	 * exception occurred or not
	 * @return isLookOkay represents the validity of the look function
	 */
	public boolean getLook() {
		return isLookOkay;
	}
	
	/**
	 * This method returns {@link #isPlayerMoveOkay} to determine if an out of
	 * bounds exception occurred or not
	 * @return isPlayerMoveOkay represents the validity of the player movement
	 */
	public boolean getPlayerMove() {
		return isPlayerMoveOkay;
	}
	
	/**
	 * This method shoots the bullet in the direction fired until it hits an
	 * {@link Enemy} or the wall.
	 * @param playerShoot represents the direction the bullet is moving
	 */
	public void setShoot(int playerShoot) {
		if (ge.getAmmo() == 0)
			changeMessage("No ammo. You just lost your turn to move.");
		ge.setAmmo(0);
		if (playerShoot == 1) {
			int i = playerCoordinate1;
			for (int j = playerCoordinate2; j >= 0; --j) {
				if (enemyGrid[i][j].getEnemyPlace() == true) {
					changeMessage("You killed an enemy!");
					enemyGrid[i][j].changeEnemyPlace(false);
					floorLayout[i][j].changeTile(' ');
					break;
				}
			}
		}
		else if (playerShoot == 2) {
			int i = playerCoordinate1;
			for (int j = playerCoordinate2; j < 9; ++j) {
				if (enemyGrid[i][j].getEnemyPlace() == true) {
					changeMessage("You killed an enemy!");
					enemyGrid[i][j].changeEnemyPlace(false);
					floorLayout[i][j].changeTile(' ');
					break;
				}
			}
		}
		else if (playerShoot == 3) {
			int j = playerCoordinate2;
			for (int i = playerCoordinate1; i >= 0; --i) {
				if (enemyGrid[i][j].getEnemyPlace() == true) {
					changeMessage("You killed an enemy!");
					enemyGrid[i][j].changeEnemyPlace(false);
					floorLayout[i][j].changeTile(' ');
					break;
				}
			}
		}
		else if (playerShoot == 4) {
			int j = playerCoordinate2;
			for (int i = playerCoordinate1; i < 9; ++i) {
				if (enemyGrid[i][j].getEnemyPlace() == true) {
					changeMessage("You killed an enemy!");
					enemyGrid[i][j].changeEnemyPlace(false);
					floorLayout[i][j].changeTile(' ');
					break;
				}
			}
		}
		ge.setNumberOfMoves();
	}
	
	/**
	 * This method determines which way the {@link Player} moves depending on
	 * the user's input.
	 * @param playerMove represents the integer value for the direction the
	 * {@link Player} chooses to move
	 */
//	FIX PLAYER ONTOP OF ENEMY MOVE
	public void movePlayer(int playerMove) {
		isPlayerMoveOkay = true;
		changeTileFaceForPlayer();
		int coordNew1 = playerCoordinate1, coordNew2 = playerCoordinate2;
		if (playerMove == 1) {
			--coordNew2;
		}
		else if (playerMove == 2) {
			++coordNew2;
		}
		else if (playerMove == 3) {
			--coordNew1;
		}
		else if (playerMove == 4) {
			++coordNew1;
		}
		try {
			if (floorLayout[coordNew1][coordNew2].checkRoom() == true && playerMove == 4) {
				playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(true);
				if (powerUpGrid[playerCoordinate1][playerCoordinate2].getPlace() == false) {
					changeMessage("The briefcase is not in this room.");
				}
			}
			else if (enemyGrid[coordNew1][coordNew2].getEnemyPlace() == true) {
				isPlayerMoveOkay = false;
			}
			else if (floorLayout[coordNew1][coordNew2].checkRoom() == true && playerMove != 4) {
				isPlayerMoveOkay = false;
			}
			else if (floorLayout[playerCoordinate1][playerCoordinate2].checkRoom() == true && playerMove != 3) {
				isPlayerMoveOkay = false;
			}
			else if (floorLayout[playerCoordinate1][playerCoordinate2].checkRoom() == true && playerMove == 3) {
				playerGrid[coordNew1][coordNew2].changePlayerPosition(true);
				playerCoordinate1 = coordNew1;
				playerCoordinate2 = coordNew2;
				playerLook(playerMove, 2, false);
			}
			else if (floorLayout[coordNew1][coordNew2].checkRoom() == false && !enemyGrid[coordNew1][coordNew2].getEnemyPlace()) {
				playerGrid[coordNew1][coordNew2].changePlayerPosition(true);
				playerCoordinate1 = coordNew1;
				playerCoordinate2 = coordNew2;
				playerLook(playerMove, 2, false);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(true);
			isPlayerMoveOkay = false;
			
		} finally {
			findPlayerPosition();
			if (ge.checkInvincibility() == true)
				ge.decrementInvincibility();
			checkPlayerAndPowerUp();
			if (isPlayerMoveOkay == true)
				ge.setNumberOfMoves();
		}
	}
	
	/**
	 * This method goes through the {@link #characterGrid} and uses the
	 * {@link #randomNumGen} to randomly move each {@link Enemy} across the grid.
	 */
	public void moveEnemy() {
		int coordOrig1 = 0, coordOrig2 = 0, coordNew1 = 0, coordNew2 = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; ++j) {
				if (enemyGrid[i][j].hasMoved() == true)
					enemyGrid[i][j].toggleMove();
			}
		}
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (enemyGrid[i][j].getEnemyPlace() == true &&
					floorLayout[i][j].checkRoom() == false && enemyGrid[i][j].hasMoved() == false) {
					if (ge.getInvincibilityTurns() == 0) {
						if (playerCoordinate1 == i && playerCoordinate2+1 == j ||
								playerCoordinate1 == i && playerCoordinate2-1 == j ||
								playerCoordinate1-1 == i && playerCoordinate2 == j ||
								playerCoordinate1+1 == i && playerCoordinate2 == j) {
							changeMessage("You've been stabbed! Sent back to spawn point!");
							playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(false);
							changeTileFaceForPlayer();
							playerCoordinate1 = 8;
							playerCoordinate2 = 0;
							playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(true);
							floorLayout[playerCoordinate1][playerCoordinate2].changeTile('P');
							ge.setNumberOfLives();
						}
					}
					int enemyPosition = randomNumGen.nextInt(4);
					coordOrig1 = i;
					coordOrig2 = j;
					if (enemyPosition == 0) {
						coordNew1 = coordOrig1;
						coordNew2 = --coordOrig2;
					}
					else if (enemyPosition == 1) {
						coordNew1 = coordOrig1;
						coordNew2 = ++coordOrig2;
					}
					else if (enemyPosition == 2) {
						coordNew1 = --coordOrig1;
						coordNew2 = coordOrig2;
					}
					else if (enemyPosition == 3) {
						coordNew1 = ++coordOrig1;
						coordNew2 = coordOrig2;
					}
					if (coordNew1 > -1 && coordNew1 < 9 && coordNew2 > -1 && coordNew2 < 9) {
						if (enemyGrid[coordNew1][coordNew2].getEnemyPlace() == false) { 
							if (floorLayout[coordNew1][coordNew2].checkRoom() == false) {
								if (playerGrid[coordNew1][coordNew2].getPlayerPosition() == false) {
									try {
										floorLayout[i][j].changeTile(' ');
										enemyGrid[i][j].changeEnemyPlace(false);
										enemyGrid[coordNew1][coordNew2].changeEnemyPlace(true);
										enemyGrid[coordNew1][coordNew2].toggleMove();
									} catch (ArrayIndexOutOfBoundsException e) {
										enemyGrid[i][j].changeEnemyPlace(true);
									}
								}
								else if (playerGrid[coordNew1][coordNew2].getPlayerPosition() == true) {
									changeMessage("You've been stabbed! Sent back to spawn point!");
									playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(false);
									changeTileFaceForPlayer();
									playerCoordinate1 = 8;
									playerCoordinate2 = 0;
									playerGrid[playerCoordinate1][playerCoordinate2].changePlayerPosition(true);
									floorLayout[playerCoordinate1][playerCoordinate2].changeTile('P');
									ge.setNumberOfLives();
								}
							}
						}
					}
				}
			}
		}
		findEnemyPosition();
	}
	
	/**
	 * This method checks if the {@link Player} life has reached {@code 0}.
	 * If {@code 0}, then the {@link Player} lost the game and the program
	 * will exit.
	 */
	public void checkGameOver() {
		if (ge.getNumberOfLives() == 0)
			ge.setGameOverLose();
	}
	
	/**
	 * This method saves the game at its current state.
	 * @param fileName represents the user's choice for file name
	 */
	public void saveGame(String fileName) {
		try {
			FileOutputStream fstream = new FileOutputStream(fileName + ".dat");
			ObjectOutputStream outputFile = new ObjectOutputStream(fstream);
			
			outputFile.writeObject(floorLayout);
			outputFile.writeObject(playerGrid);
			outputFile.writeObject(enemyGrid);
			outputFile.writeObject(powerUpGrid);
			outputFile.writeObject(ge.getNumberOfMoves());
			outputFile.writeObject(ge.getNumberOfLives());
			outputFile.writeObject(ge.getAmmo());
//			outputFile.writeObject(ge.getABEffect());
			outputFile.close();
		} catch (IOException e) {
			System.out.println("Error: File Cannot Be Saved. Try Again.");
		}
	}
	
	/**
	 * This method loads a previously saved game file
	 * @param fileName represents the name of the file the user is trying to load
	 */
	public void loadGame(String fileName) {
		try {
			int numMoves = 0;
			int numLives = 0;
			int ammo = 0;
			FileInputStream fstream = new FileInputStream(fileName + ".dat");
			ObjectInputStream inputFile = new ObjectInputStream(fstream);
			
			floorLayout = (Floor[][]) inputFile.readObject();
			playerGrid = (Player[][]) inputFile.readObject();
			enemyGrid = (Enemy[][]) inputFile.readObject();
			powerUpGrid = (PowerUp[][]) inputFile.readObject();
			numMoves = (int) inputFile.readObject();
			numLives = (int) inputFile.readObject();
			ammo = (int) inputFile.readObject();
			ge.setNumberOfMoves(numMoves);
			ge.setNumberOfLives(numLives);
			ge.setAmmo(ammo);
			inputFile.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error: File Cannot Be Loaded. Try Again.");
		}
	}
}
