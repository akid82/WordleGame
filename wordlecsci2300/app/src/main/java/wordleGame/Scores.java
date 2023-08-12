package wordleGame;

import java.io.Serializable;

public class Scores implements Serializable
{
    private int gamesPlayed;
    private int gamesWon;
    private int currentStreak;
    private int maxStreak;
    private int[] guessTotals;

    public Scores() 
    {
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.currentStreak = 0;
        this.maxStreak = 0;
        this.guessTotals = new int[6];
    }

    public void setGamesPlayed(int newVal) 
    {
        this.gamesPlayed = newVal;
    }

    public int getGamesPlayed() 
    {
        return this.gamesPlayed;
    }

    public void setGamesWon(int newVal) 
    {
        this.gamesWon = newVal;
    }

    public int getGamesWon() 
    {
        return this.gamesWon;
    }

    public void setCurrentStreak(int newVal) 
    {
        this.currentStreak = newVal;
    }

    public int getCurrentStreak() 
    {
        return this.currentStreak;
    }

    public void setMaxStreak(int newVal) 
    {
        this.maxStreak = newVal;
    }

    public int getMaxStreak() 
    {
        return this.maxStreak;
    }

    public void setGuessTotals(int index, int newVal) 
    {
        this.guessTotals[index] = newVal;
    }

    public int[] getGuessTotals() 
    {
        return this.guessTotals;
    }

    public void incrementGuessTotal(int index)
    {
        int oldVal = this.guessTotals[index];
        this.guessTotals[index] = oldVal + 1;
    }
}