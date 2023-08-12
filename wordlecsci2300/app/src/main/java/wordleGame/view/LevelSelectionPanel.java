package wordleGame.view;

import wordleGame.controller.*;
import wordleGame.model.*;
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

public class LevelSelectionPanel extends JPanel implements ActionListener
{
    private int numberOfGuesses;
    private GameController controller;
    private GameModel model;
    private Scores scores;

    public LevelSelectionPanel(GameController controller, GameModel model, Scores scores)
    {
        this.controller = controller;
        this.model = model;
        this.scores = scores;

        this.setPreferredSize(new Dimension(500,140));
        this.setBackground(new Color(104, 152, 60));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel welcomeMessage = new JLabel("Welcome to Wordle");
        welcomeMessage.setFont(new Font("Neretto", Font.PLAIN, 25));

        JLabel instructions = new JLabel("Choose a level of difficulty");
        instructions.setFont(new Font("Neretto", Font.PLAIN, 20)); 

        JLabel normalTerms = new JLabel("Normal: 6 guesses to guess the target word");
        normalTerms.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel hardTerms = new JLabel("Hard: 4 guesses to guess the target word");
        hardTerms.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(welcomeMessage);
        this.add(instructions);
        this.add(normalTerms);
        this.add(hardTerms);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(500,50));
        buttonsPanel.setBackground(new Color(104, 152, 60));
        
        JButton normalButton = new JButton("Normal");
        JButton hardButton = new JButton("Hard");
        
        buttonsPanel.add(normalButton);
        buttonsPanel.add(hardButton);

        normalButton.addActionListener(this);
        hardButton.addActionListener(this);

        this.add(buttonsPanel);
        
    }

    private void showGameBoard()
    {
        Container parentContainer = this.getParent();
        JFrame frame = (JFrame)SwingUtilities.getRoot(this);
        this.getParent().remove(this);
        
        GameGUI game = new GameGUI(this.numberOfGuesses, this.controller, this.model, this.scores);

        parentContainer.add(game);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        JButton clickedButton = (JButton)event.getSource();

        // determine which button was clicked
        String buttonText = clickedButton.getText();

        if (buttonText.equals("Normal"))
        {
            this.numberOfGuesses = 6;
        }
        else 
        {
            this.numberOfGuesses = 4;
        }
        this.controller.setNumberGuesses(this.numberOfGuesses);
        showGameBoard();

    }
}