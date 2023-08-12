package wordleGame.model;

import org.junit.*;

import wordleGame.model.SecretWord;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class SecretWordTest
{
    // @Test
    // public void testTemplate()
    // {
    //     SecretWord secretWord = new SecretWord("");
        
    //     String[] actual = secretWord.analyzeGuess(new char[] {'','','','',''});
    //     String[] expected = new String[] {"","","","",""};
        
    //     assertArrayEquals(expected, actual);
    // }
    
    @Test
    public void testNoDuplicateLetters1Green1Yellow()
    //Testing a case where the guess has no duplicate letters
    {
        SecretWord secretWord = new SecretWord("train");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'d','r','e','a','m'});
        String[] expected = new String[] {"gray","green","gray","yellow","gray"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testNoDuplicateLetters5Gray()
    //testing a case where no letters in the guess are in the secret word
    {
        SecretWord secretWord = new SecretWord("train");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'m','o','u','s','e'});
        String[] expected = new String[] {"gray","gray","gray","gray","gray"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testNoDuplicateLetters5Green()
    //Testing the case where the guess matches the secret word
    {
        SecretWord secretWord = new SecretWord("train");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'t','r','a','i','n'});
        String[] expected = new String[] {"green","green","green","green","green"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDoubleEinGuess()
    //Testing a case where there are two 'e's in the guess, only one should register as in the word
    {
        SecretWord secretWord = new SecretWord("stare");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'s','e','r','v','e'});
        String[] expected = new String[] {"green","gray","yellow","gray","green"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDoubleMinGuess()
    //testing a second case where there are two 'm's in the guess
    {
        SecretWord secretWord = new SecretWord("datum");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'m','o','d','e','m'});
        String[] expected = new String[] {"gray","gray","yellow","gray","green"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDoubleSinGuess()
    //testing a third case where there are two 's's in the guess, only one should register as in the word
    {
        SecretWord secretWord = new SecretWord("cares");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'s','t','a','s','h'});
        String[] expected = new String[] {"gray","gray","yellow","yellow","gray"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTripleRinGuess()
    //testing a case where there are three 'r's in the guess, only one should register as in the word
    {
        SecretWord secretWord = new SecretWord("cares");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'e','r','r','o','r'});
        String[] expected = new String[] {"yellow","gray","green","gray","gray"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDoubleTinGuessYellow()
    //testing a case where there are two 't's in the guess, only one should register as in the word

    {
        SecretWord secretWord = new SecretWord("hadst");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'t','o','t','e','m'});
        String[] expected = new String[] {"gray","gray","yellow","gray","gray"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDoubleTinGuessGreen()
    //testing a case where there are two 't's in the guess, only one should register as in the word

    {
        SecretWord secretWord = new SecretWord("hadst");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'t','r','u','s','t'});
        String[] expected = new String[] {"gray","gray","gray","green","green"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTripleRGuessDoubleRSecret()
    //testing a case where there are three 'r's in the guess, only one should register as in the word

    {
        SecretWord secretWord = new SecretWord("river");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'e','r','r','o','r'});
        String[] expected = new String[] {"yellow","yellow","gray","gray","green"};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTripleRGuessDoubleRSecretGreens()
    //testing a case where there are three 'r's in the guess, only one should register as in the word
    {
        SecretWord secretWord = new SecretWord("river");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'r','a','r','e','r'});
        String[] expected = new String[] {"green","gray","gray","green","green"};
        
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void checkFourLetters()
    {
		SecretWord secretWord = new SecretWord("eeeem");
        
        String[] actual = secretWord.analyzeGuess(new char[] {'e','e','e','p','e'});
        String[] expected = new String[] {"green","green","green","gray","yellow"};
        
        assertArrayEquals(expected, actual);
	}
		
}
