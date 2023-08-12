package wordleGame.model;

import org.junit.*;

import wordleGame.model.GameModel;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.io.IOException;

public class GameModelTest {

    @Test
    public void testCheckValidWordMethodTrue() throws IOException
    {
        GameModel model = new GameModel();
        char[] testWord = new char[] {'t','r','a','i','n'};
        model.setMostRecentGuess(testWord);
        model.checkValidWord();
        assertTrue(model.getValidGuessStatus());
    }

    @Test
    public void testCheckValidWordMethodFalse() throws IOException
    {
        GameModel model = new GameModel();
        char[] testWord = new char[] {'z','z','z','z','z'};
        model.setMostRecentGuess(testWord);
        model.checkValidWord();
        assertFalse(model.getValidGuessStatus());
    }

    @Test
    public void testSetNumGuesses() throws IOException
    {
        GameModel model = new GameModel();
        model.setNumGuess(4);
        assertEquals(4, model.getNumGuessesAllowed());
    }

    @Test 
    public void testGameOverFalse() throws IOException
    {
        GameModel model = new GameModel();
        assertFalse(model.getGameOver());
    }

    @Test 
    public void testSetMostRecentGuess() throws IOException
    {
        GameModel model = new GameModel();
        char[] testWord = new char[] {'t','r','a','i','n'};
        model.setMostRecentGuess(testWord);
        assertArrayEquals(model.getMostRecentGuess(), testWord);
    }
    
}
