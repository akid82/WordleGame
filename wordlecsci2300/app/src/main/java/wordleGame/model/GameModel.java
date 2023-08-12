package wordleGame.model;

import wordleGame.Scores;
import wordleGame.Subject;
import wordleGame.Observer;
import java.util.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class GameModel implements Subject
{
    private ArrayList<Observer> observers;
    private int numGuessesAllowed;
    private int numGuessesMade;
    private char[] mostRecentUserGuess;
    private String[] mostRecentColorArray;
    Dictionary dictionary;
    private boolean validGuess;
    private SecretWord secretWord;
    private boolean gameOver;
    private boolean gameWon;
    private Scores scores;

    public GameModel() throws IOException
    {
        this.observers = new ArrayList<Observer>();
        // instantiate object of Dictionary class
        this.dictionary = new Dictionary("dictionary.txt");
        
        ArrayList<String> allWords = this.dictionary.getWords();
		Random randomizer = new Random();
		int randomLocation = randomizer.nextInt(allWords.size());
        
        this.secretWord = new SecretWord(allWords.get(randomLocation));
        this.numGuessesMade = 0;

        this.gameOver = false;
        this.gameWon = false;
        
    }

    public void setScoresClass(Scores scores) 
    {
        this.scores = scores;
    }

    public void gameFlow() 
    {
        // called every time a guess is made

        // first need to check if a valid guess has been made
        this.checkValidWord();
        if (this.validGuess)
        {
            this.analyzeMostRecentGuess();
            this.numGuessesMade++; // increment guess made by 1
            this.checkGameOver(); // check if game is over
            if (this.gameOver) // if game is over, do all the statistics work in deliverable 3
            {   
                this.updateStatistics();
            }
        }
         
        this.notifyObservers();
    }

    public void updateStatistics()
    {   
        if (this.gameWon) 
        {
            // if game is won, increment gamesWon, currentStreak, and check if maxStreak has been reached
            this.scores.setGamesPlayed(this.scores.getGamesPlayed() + 1); // increment the number of games played by 1 everytime we play the game
            this.scores.setGamesWon(this.scores.getGamesWon() + 1);
            this.scores.setCurrentStreak(this.scores.getCurrentStreak() + 1);
            if (this.scores.getCurrentStreak() > this.scores.getMaxStreak())
            {
                this.scores.setMaxStreak(this.scores.getCurrentStreak());
            }
            this.scores.incrementGuessTotal(numGuessesMade - 1);
        }
        else
        {
            // if game is lost, reset currentStreak to 0
            this.scores.setCurrentStreak(0);
        }
    }
    

    public void setNumGuess(int guesses)
    {
        this.numGuessesAllowed = guesses;
    }

    public void setMostRecentGuess(char[] userGuess)
    {   
        // once submit button is pressed in GUI, controller calls setMostRecentGuess in model
        this.mostRecentUserGuess = userGuess;
        this.gameFlow();
    }

    public void checkValidWord()
    {
		// make call to Dictionary class to see if word is valid
		this.validGuess = dictionary.getWords().contains(new String(this.mostRecentUserGuess));
    }
    
    public void analyzeMostRecentGuess() // called only when we're sure user guess is a valid word
    {
       this.mostRecentColorArray = this.secretWord.analyzeGuess(this.mostRecentUserGuess); 
    }

    public void checkGameOver() // called once a valid guess has been made and SecretWord class has analyzed it
    {    
        // array that models what a completely correct guess will look like
        String[] correctColorArray =  new String[] {"green", "green", "green", "green", "green"};

        // if color array is all green, meaning secret word has been revealed, then game is over
        if (Arrays.equals(this.mostRecentColorArray, correctColorArray))
        {
            this.gameOver = true; // set gameOver to true
            this.gameWon = true; // set gameWon to true
        }
        else if (this.numGuessesAllowed == this.numGuessesMade) // else if user has made all the guesses they're allowed, then game is over
        {
            this.gameOver = true; // set gameOver to true
            // gameWon is already false 
        }

    }

    public boolean getGameOver()
    {
        return this.gameOver;
    }

    public boolean getGameWon()
    {
        return this.gameWon;
    }

    public String getSecretWord()
    {
        return this.secretWord.reveal();
    }

    public String[] getColorArray() 
    {
        return this.mostRecentColorArray;
    }

    public boolean getValidGuessStatus()
    {
        return this.validGuess;
    }

    public int getNumGuessesAllowed()
    {
        return this.numGuessesAllowed;
    }

    public char[] getMostRecentGuess()
    {
        return this.mostRecentUserGuess;
    }

    public void register(Observer o)
    {
        this.observers.add(o);
    }

    public void unregister(Observer o)
    {
        this.observers.remove(o);
    }

    public void notifyObservers()
    {
        for (Observer o:observers)
        {
            o.update();
        }
    }
}
