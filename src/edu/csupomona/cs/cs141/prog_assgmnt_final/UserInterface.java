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

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the user interface, which interacts with the player
 * and displays everything the player needs to know, such as main menu,
 * controls, and an instance of the game.
 * 
 * @author Christine Nguyen
 * @author Wesley Lang
 * @author Fernando Garcia
 * @author Harry Tran
 *
 */
public class UserInterface {
	
	/**
	 * This field represents the value of normal mode or debug mode.
	 */
	int gameMode;
	
	/**
	 * The user interface interacts with the {@link Grid}.
	 */
	private Grid floor = new Grid();
	
	/**
	 * This scanner will take in input from the player.
	 */
	Scanner input = new Scanner(System.in);
	
	/**
	 * This method displays the name of the game.s
	 */
	public void displayGameName() {
		 System.out.println("             .-.___________________________.-.\n"
				 		  + "      ___ _.' .-----.    ____________________|\n"
				 		  + "     /_._/   (      |   /____________________|\n"
				 		  + "       /      `  _ ____/ ___  ___              _\n"
				 		  + "      |_      .\\( \\\\     |  \\/  |             | |\n"
				 		  + "     .'  `-._/__`_//     | .  . |_   _ _ __ __| | ___ _ __\n"
				 		  + "   .\'       |\"\"\"\"\'       | |\\/| | | | | \'__/ _` |/ _ \\ \'__|\n"
				 		  + "  /        /  _   _      | |  | | |_| | | | (_| |  __/ |\n"
				 		  + " /        /  | | | |     \\_|  |_/\\__,_|_|  \\__,_|\\___|_|\n"
				 		  + "/        |   | |_| | __ _ _   _ ___\n"
				 		  + "|        '   |  _  |/ _` | | | / __|\n"
				 		  + "|         \\  | | | | (_| | |_| \\__ \\\n"
				 		  + " `-._____.-' \\_| |_/\\__,_|\\__,_|___/\n");
	}
	
	/**
	 * This method displays the main menu, where the player can play the game,
	 * read the background information, look at all the available controls, or
	 * load a previously saved game.
	 */
	public void displayMainMenu() {
		displayGameName();
		System.out.println("1 - Play Game");
		System.out.println("2 - Display Background Information");
		System.out.println("3 - Display Controls");
		System.out.println("4 - Load Previously Saved Game");
		getPlayerInputFromMenu();
	}
	
	/**
	 * This method take the user's input when the player is at the main menu.
	 */
	public void getPlayerInputFromMenu() {
		try {
			System.out.print("What would you like to do? (Just type the number) ");
			int mainMenuInput = input.nextInt();
			if (!(mainMenuInput > 0 && mainMenuInput < 5)) {
				System.out.println("Please only enter a number from 1 to 4.");
				displayMainMenu();
			}
			else
				movePlayerFromMenu(mainMenuInput);
		} catch (InputMismatchException e) {
			System.out.println("Please only enter a number from 1 to 4.");
			input.nextLine();
			displayMainMenu();
		} 
	}
	
	/**
	 * This method takes the user's input and moves the player from the main menu
	 * to the appropriate screen.
	 */
	public void movePlayerFromMenu(int userInput) {
		try {
			if (userInput == 1) {
				System.out.println("1 - Normal Mode");
				System.out.println("2 - Debug Mode");
				System.out.print("Which mode do you want to play? ");
				gameMode = input.nextInt();
				
				floor.setGameMode(gameMode);
				floor.initializeBuildingGrid();
				floor.initializePlayerGrid();
				floor.initializeEnemyGrid();
				floor.findPlayerPosition();
				floor.setPowerUpPosition();
				floor.findEnemyPosition();
				floor.printBuildingGrid();
				
				if (gameMode == 1)
					setLook();
				else if (gameMode == 2)
					askUserInput();
			}
			else if (userInput == 2) {
				displayBackgroundInfo();
			}
			else if (userInput == 3) {
				displayControls();
			}
			else if (userInput == 4) {
				loadGame();
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid command.");
			input.nextLine();
			displayMainMenu();
		}
	}
	
	/**
	 * This method displays the background information and the story behind the
	 * game.
	 */
	public void displayBackgroundInfo() {
		System.out.println("This game takes place inside a building, which"
				+ "\nhas a floor layout of 9x9. Every square represents a"
				+ "\npossible position for different entities in the game"
				+ "\n(your avatar, enemies, power-ups), with the exception of 9"
				+ "\nspecial squares which represent rooms, and are equally distributed on the grid."
				+ "\nYour character is a spy that is tasked with retrieving a"
				+ "\nbriefcase containing classified enemy documents, which is"
				+ "\nlocated in one of the rooms. The building is pitch-black."
				+ "\nThere are six ninja-assassins patrolling the building."
				+ "\nThe spy is initially placed at the bottom left-corner of the grid."
				+ "\nThe briefcase is randomly located in one of the nine rooms at the start of the game."
				+ "\nThe rooms can only be accessed from the north side.");
		displayMainMenu();
	}
	
	/**
	 * This method displays all of the controls and their functions made
	 * available to the player.
	 */
	public void displayControls() {
		System.out.println("1 - Look: The player can look in any direction, and the grid will reveal 3 spaces in that given direction."
				+ "\n2 - Move: The player can choose to move one space in any direction."
				+ "\n3 - Shoot: The player can choose to shoot in any direction, and if there is an enemy, the closest one will be killed.");
		displayMainMenu();
	}
	
	/**
	 * This method will ask the user to give input, which will represent the
	 * controls to move the player's avatar, shoot a bullet, save the game,
	 * or quit the program.
	 */
	public void askUserInput() {
		try {
			System.out.println("1 - Move");
			System.out.println("2 - Shoot");
			System.out.println("3 - Save");
			System.out.println("4 - Quit");
			System.out.print("What do you want to do next? ");
			int playerMovement = input.nextInt();
			
			if (playerMovement == 1)
				getMovement();
			else if (playerMovement == 2)
				setShoot();
			else if (playerMovement == 3)
				saveGame();
			else if (playerMovement == 4) {
				try {
					System.out.println("1 - Exit Program completely");
					System.out.println("2 - Go to Main Menu");
					System.out.print("What do you want to do? ");
					int quitFunction = input.nextInt();
				
					if (quitFunction == 1)
						System.exit(0);
					else if (quitFunction == 2)
						displayMainMenu();
					else if (quitFunction < 1 || quitFunction > 2) {
						System.out.println("Invalid command.");
						input.nextLine();
						askUserInput();
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid command.");
					input.nextLine();
					askUserInput();
				}
			}
			else if (playerMovement < 1 || playerMovement > 4) {
				System.out.println("Invalid command.");
				input.nextLine();
				askUserInput();
			}
			
			if (floor.getPlayerMove() == false) {
				input.nextLine();
				askUserInput();
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid command.");
			input.nextLine();
			askUserInput();
		}
	}
	
	/**
	 * This method represents the look function and asks the user which way the
	 * {@link Player} wants to look.
	 */
	public void setLook() {
		try {
			System.out.println("1 - Left");
			System.out.println("2 - Right");
			System.out.println("3 - Up");
			System.out.println("4 - Down");
			System.out.print("Which way do you want to look? ");
			int playerLook = input.nextInt();
			
			floor.playerLook(playerLook, 3, true);
			if (floor.getLook() == true) {
				floor.printBuildingGrid();
				askUserInput();
			}
			else
				setLook();
		} catch (InputMismatchException e) {
			input.nextLine();
			System.out.println("Invalid command.");
			setLook();
		}
		askUserInput();
	}
	
	/**
	 * This method sets the movement for the {@link Player} and asks the user
	 * which direction to go.
	 */
	public void getMovement() {
		try {
			System.out.println("1 - Left");
			System.out.println("2 - Right");
			System.out.println("3 - Up");
			System.out.println("4 - Down");
			System.out.print("Which way do you want to move? ");
			int playerMove = input.nextInt();
			
			floor.movePlayer(playerMove);
			if (floor.getPlayerMove() == false) {
				System.out.println("You can't move that way.");
				input.nextLine();
				getMovement();
			}
			else if (floor.getPlayerMove() == true) {
				floor.moveEnemy();
				floor.checkGameOver();
				floor.printBuildingGrid();
			}
			if (gameMode == 1)
				setLook();
			else if (gameMode == 2)
				askUserInput();
		} catch (InputMismatchException e) {
			System.out.println("Invalid command.");
			input.nextLine();
			getMovement();
		}
	}
	
	/**
	 * This method represents the shoot function and asks the user which
	 * direction to shoot in.
	 */
	public void setShoot() {
		try {
			System.out.println("1 - Left");
			System.out.println("2 - Right");
			System.out.println("3 - Up");
			System.out.println("4 - Down");
			System.out.print("Which way do you want to shoot? ");
			int playerShoot = input.nextInt();
			
			floor.setShoot(playerShoot);
			floor.moveEnemy();
			floor.printBuildingGrid();
			if (gameMode == 1)
				setLook();
			else if (gameMode == 2)
				askUserInput();
		} catch (InputMismatchException e) {
			System.out.println("Invalid command.");
			input.nextLine();
			setShoot();
		}
	}
	
	/**
	 * This method will save the instance of the game.
	 */
	public void saveGame() {
		System.out.print("What name do you want the file to be?"
				+ " (Do not include the file extension) ");
		String fileName = input.nextLine();
		input.nextLine();
		floor.saveGame(fileName);
		askUserInput();
	}

	/**
	 * This method will load a previously saved game file.
	 */
	public void loadGame() {
		System.out.print("Enter file name of load state."
				+ " (Do not include the file extension) ");
		String fileName = input.nextLine();
		input.nextLine();
		floor.loadGame(fileName);
		floor.printBuildingGrid();
	}

}
