# WordleGame

## Directions to run:
1. After cloning down the repository, navigate to the project repository on your local machine
2. Change directory to 'wordlecsci2300' (If typing 'ls' results in 5 items including the 'app' directory, you're in the right place)
3. Run: gradle run

## Project Information
Development of this project took place throughout the second half of the Fall 2022 semester in CSCI 2300 by team members Anna Kidwell, Kate Cannell, and Humza Rehman. Our assignment was to work as a team to develop a game utilizing the skills we had learned throughout the course such as Java development, design principles, git commands, Gradle build framework, unit testing with JUnit, GUI building with Java Swing, and proper code writing practices. 

Our team organized Wordle using the Model View Controller and Observer design patterns. 
* Model
  * Selects random secret word
  * Analyses the user guess against the secret word
  * Keeps track of if the game is over
  * Updates scores and counters
  * Notifies View when the state changes
* View
  * Allows user to interact with game to create and submit their guess
  * Displays accuracy of the user’s guess
  * Visually reminds user of how many guesses they have left
  * Announces messages to user about the state of the game
  * Updates its state when Model sends notice
* Controller
  * Sends user guess from view to model for analysis
  * Handles control of loading and saving the state of the game
 

