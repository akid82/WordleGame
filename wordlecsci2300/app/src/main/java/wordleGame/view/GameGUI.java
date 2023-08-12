package wordleGame.view;

import wordleGame.controller.*;
import wordleGame.model.*;
import wordleGame.Observer;
import wordleGame.Scores;

import java.awt.*;
import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;

public class GameGUI extends JPanel implements Observer
{
    JPanel buttonsPanel;
    JPanel gameBoardPanel;
    LetterBox[][] boxes;
    JPanel[] rows;
    JPanel statsPanel;
    JPanel overallStatsPanel;
    JPanel guessDistributionPanel;
    JLabel numGamesPlayedLabel;
    JLabel numGamesWonLabel;
    JLabel currentStreakLabel;
    JLabel maxStreakLabel;
    JLabel numOneGuesses;
    JLabel numTwoGuesses;
    JLabel numThreeGuesses;
    JLabel numFourGuesses;
    JLabel numFiveGuesses;
    JLabel numSixGuesses;
    char mostRecentLetter;
    AlphabetButtons alphabetButtons;
    DeleteButton deleteButton;
    SubmitButton submitButton;
    JLabel userMsg;
    JLabel[] guessTotalLabels;
    
    int numberOfGuesses;
    int numberOfLetters;
    int currentRow;
    int currentColumn;
    char[] currentUserGuess;
    
    private GameController controller;
    private GameModel model;
    private Scores scores;
    
    public GameGUI(int numberOfGuesses, GameController controller, GameModel model, Scores scores) 
    {
        this.controller = controller;
        this.model = model;
        this.scores = scores;

        this.model.register(this); // register GameGUI with model

        this.numberOfGuesses = numberOfGuesses;
        this.numberOfLetters = 5;
        this.currentRow = 0;
        this.currentColumn = 0;
        this.currentUserGuess = new char[this.numberOfLetters];

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // align the components of the main panel vertically
        this.setPreferredSize(new Dimension(800,770));

        // creation of title label
        JLabel titleLabel = new JLabel("WORDLE");
        titleLabel.setFont(new Font("Neretto", Font.PLAIN, 45)); 
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // addition of title label
        this.add(titleLabel);

        //creation of userMsg
        this.userMsg = new JLabel(" ");
        this.userMsg.setFont(new Font("Neretto", Font.PLAIN, 18)); 
        this.userMsg.setForeground(Color.RED);
        this.userMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this.userMsg);
        
        // creation of statistics panel
        this.statsPanel = new JPanel();
        this.statsPanel.setPreferredSize(new Dimension(800,120));
        this.statsPanel.setLayout(new GridLayout(1,2));
        
        // creation of statsLabel
        JLabel statsLabel = new JLabel("STATISTICS");
        statsLabel.setFont(new Font("Neretto", Font.PLAIN, 20)); 
        statsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // addition of statsLabel
        this.add(statsLabel);

        // creation of overallStatsPanel
        this.overallStatsPanel = new JPanel();
        this.overallStatsPanel.setPreferredSize(new Dimension(400,120));
        this.overallStatsPanel.setLayout(new BoxLayout(this.overallStatsPanel, BoxLayout.Y_AXIS));
        this.overallStatsPanel.setBackground(new Color(204,204,0));

        // creation of overallStatsTitleLabel
        JLabel overallStatsLabel = new JLabel("OVERALL STATISTICS");
        overallStatsLabel.setFont(new Font("Neretto", Font.PLAIN, 15));
        overallStatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        // creation of labels for overallStatsPanel
        this.numGamesPlayedLabel = new JLabel("Games Played: " + this.scores.getGamesPlayed()); 
        this.numGamesWonLabel = new JLabel("Games Won: " + this.scores.getGamesWon());
        this.currentStreakLabel = new JLabel("Current Streak: " + this.scores.getCurrentStreak());
        this.maxStreakLabel = new JLabel("Max Streak: " + this.scores.getMaxStreak());
        this.numGamesPlayedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.numGamesWonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.currentStreakLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.maxStreakLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        // addition of labels to overallStatsPanel
        this.overallStatsPanel.add(overallStatsLabel);
        this.overallStatsPanel.add(this.numGamesPlayedLabel);
        this.overallStatsPanel.add(this.numGamesWonLabel);
        this.overallStatsPanel.add(this.currentStreakLabel);
        this.overallStatsPanel.add(this.maxStreakLabel);

        // creation of gameDistributionPanel
        this.guessDistributionPanel = new JPanel();
        this.guessDistributionPanel.setPreferredSize(new Dimension(800,120));
        this.guessDistributionPanel.setBackground(new Color(0,153,0));
        this.guessDistributionPanel.setLayout(new BoxLayout(this.guessDistributionPanel, BoxLayout.Y_AXIS));

        // creation of labels for gameDistributionPanel
        JLabel guessDistributionLabel = new JLabel("GUESS DISTRIBUTION");
        guessDistributionLabel.setFont(new Font("Neretto", Font.PLAIN, 15));
        this.guessDistributionPanel.add(guessDistributionLabel);
        
        int[] scoresGuessTotals = this.scores.getGuessTotals();
        this.guessTotalLabels = new JLabel[6];
        for (int i = 0; i < 6; i++) 
        {
            this.guessTotalLabels[i] = new JLabel( (i+1) + ": " + scoresGuessTotals[i] );
            this.guessTotalLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            this.guessDistributionPanel.add(this.guessTotalLabels[i]);
        }

        // addition of overallStatsPanel to statsPanel
        this.statsPanel.add(this.overallStatsPanel);

        // addition of gameDistributionPanel to statsPanel
        this.statsPanel.add(this.guessDistributionPanel);

        // addition of statsPanel
        this.add(statsPanel);

        // creation of gameBoardPanel
        this.gameBoardPanel = new JPanel();
        this.gameBoardPanel.setPreferredSize(new Dimension(600,450));
        this.gameBoardPanel.setLayout(new BoxLayout(this.gameBoardPanel, BoxLayout.Y_AXIS));
        this.gameBoardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.rows = new JPanel[this.numberOfGuesses];
        this.boxes = new LetterBox[this.numberOfGuesses][this.numberOfLetters];
        for (int i = 0; i < numberOfGuesses; i++)
        {
            this.rows[i] = new JPanel();
            for (int j = 0; j < numberOfLetters; j++) // each row needs 5 boxes
            {
                this.boxes[i][j] = new LetterBox();
                this.rows[i].add(this.boxes[i][j]);
            }
            this.gameBoardPanel.add(this.rows[i]);
        }

        // addition of gameBoardPanel
        this.add(gameBoardPanel);

        this.alphabetButtons = new AlphabetButtons(this);
        this.deleteButton = new DeleteButton(this);
        this.submitButton = new SubmitButton(this);

        this.add(this.alphabetButtons);
        this.add(this.deleteButton);
        this.add(this.submitButton);
    }

    public void guessMade() // called every time submit button is clicked
    {
        if (this.currentRow == this.numberOfGuesses)
        {
            this.gameOver();
        }
    } 

    public char getMostRecentLetter()
    {
        return this.mostRecentLetter;
    }

    public void setMostRecentLetter(char letterClicked)
    {
        this.mostRecentLetter = letterClicked;
    }

    public void letterPressed() // called every time a button is clicked
    {
        int nextIndex = currentColumn;
        if (nextIndex++ < this.numberOfLetters) // keeps user from trying to enter more than 5 letters 
        {
            this.boxes[currentRow][currentColumn].setText(Character.toString(this.mostRecentLetter));
            this.currentUserGuess[currentColumn] = this.mostRecentLetter;
            this.currentColumn++;
        }
    } 

    public void deletePressed() // called every time a button is clicked
    {
        int lastIndex = currentColumn;
        if (lastIndex-- > 0) // keeps user from trying to delete past the beginning of the row 
        {
            this.currentColumn--;
            this.boxes[currentRow][currentColumn].setText(" ");
        }
    } 

    public void submitPressed() // called every time a button is clicked
    {
        if (this.currentColumn == this.numberOfLetters) // keeps user from submitting an guess of less than 5 letters
        {  
            this.controller.sendUserGuess(this.currentUserGuess); // expects char array
        }
        else
        {
            this.userMsg.setText("NOT ENOUGH LETTERS");
        }
    } 

    public void gameOver()
    {
        this.controller.saveToFile();
        
        this.alphabetButtons.disableButtons();
        this.deleteButton.disableButton();
        this.submitButton.disableButton();

        if (this.model.getGameWon())
        {
            this.userMsg.setText("YOU WON!");
        }
        else 
        {
            this.userMsg.setText("SECRET WORD: " +  this.model.getSecretWord());
        }

        this.updateStatistics();

    }

    public void updateStatistics()
    {
        this.numGamesPlayedLabel.setText("Games Played: " + this.scores.getGamesPlayed()); 
        this.numGamesWonLabel.setText("Games Won: " + this.scores.getGamesWon());
        this.currentStreakLabel.setText("Current Streak: " + this.scores.getCurrentStreak());
        this.maxStreakLabel.setText("Max Streak: " + this.scores.getMaxStreak());

        int[] scoresGuessTotals = this.scores.getGuessTotals();
        for (int i = 0; i < 6; i++) 
        {
            this.guessTotalLabels[i].setText( (i+1) + ": " + scoresGuessTotals[i] );
        }
    }
    
    @Override
    public void update()
    {
        // only have two possible ways of being updated
        // one is that the user guess is not a valid word, meaning we need to print a message to them to guess another word
        // second is that the user guess was valid, and we need to update color of boxes to reflect rules of game and move to the next row of boxes
        
        if (this.model.getValidGuessStatus()) 
        {
			
            String [] colorArray = this.model.getColorArray();

			for (int i = 0; i < numberOfLetters; i++){
				this.boxes[currentRow][i].setColor(this.getColor(colorArray[i]));
                this.alphabetButtons.updateButtonColor(this.currentUserGuess[i], colorArray[i], this.getColor(colorArray[i]));
			}
			
            this.currentRow++;
            this.currentColumn = 0;
            this.userMsg.setText(" ");
        }
        else
        {
            this.userMsg.setText("NOT A VALID WORD");
        }

        if (this.model.getGameOver())
        {
            this.gameOver();
        }
    }
    
   public Color getColor(String s){
		Color c;
		if (s.equals("green"))
        {
			c = new Color(0,153,0);
		}
		else if(s.equals("yellow"))
        {
			c = new Color(204,204,0);
		}
		else
        {
			c = new Color(96,96,96);
		}
		return c;
	}
}

