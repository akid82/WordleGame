package wordleGame.controller;

import wordleGame.Scores;
import wordleGame.view.WordleGUI;
import wordleGame.model.GameModel;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class GameController
{
    private WordleGUI view; //instantiate view object
    private GameModel model; //instantiate model object
    private Scores scores; // instantiates scores file
    private int numGuess;
    
    public GameController(GameModel model, boolean loadScores)
    {
        this.model = model;

        if (loadScores) // if user said they want to load scores, get Scores class from file
        {
            try
            {
                loadFromFile();
            }
            catch (Exception error)
            {
                System.out.println(error.getMessage());
            }
        }
        else // if user said they DO NOT want to load scores, create new Scores class
        {
            this.scores = new Scores();
        }

        // send whichever Scores object is created/loaded to model
        this.model.setScoresClass(this.scores);

        start();
    }

    public void start()
    {
        this.view = new WordleGUI(this.model, this, this.scores);
    }
    
    public void setNumberGuesses(int guesses)
    {
        this.numGuess = guesses;
        this.model.setNumGuess(this.numGuess);
    }

    public void sendUserGuess(char[] userGuess)
    {
        this.model.setMostRecentGuess(userGuess);
    }

    public void loadFromFile() throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("gameData.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            this.scores = (Scores)objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Loaded from file");
            int numGamesPlayed = this.scores.getGamesPlayed();
        }
        catch (NullPointerException exception)
        {
            System.out.println(exception.getMessage());
            this.scores = new Scores(); // if the Scores file is NULL, create new Scores object
        }
    }

    public void saveToFile() 
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("gameData.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.scores);
            objectOut.close();
            fileOut.close();
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}