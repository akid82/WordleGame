/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package wordleGame;

import wordleGame.model.*;
import wordleGame.controller.*;
import wordleGame.view.ConfirmationGame;

import java.awt.*;
import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;

public class App {
    public static void main(String[] args) throws IOException
    {

        boolean loadScores;
        
        if (ConfirmationGame.confirmLoadGame())
        {
            loadScores = true; 
        }
        else
        {
            loadScores = false;
        }
        
        GameModel model = new GameModel(); // create model
        GameController controller = new GameController(model, loadScores); // create controller with boolean to indicate if scores need to be loaded
        
    }
}
