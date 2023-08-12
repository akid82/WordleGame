package wordleGame.view;

import wordleGame.Observer;
import wordleGame.view.*;
import wordleGame.model.*;
import wordleGame.controller.*;
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

public class WordleGUI implements Observer
{
    private GameModel model;
    private GameController controller;
    private Scores scores;

    public WordleGUI(GameModel model, GameController controller, Scores scores)
    {
        this.controller = controller;
        this.model = model;
        this.scores = scores;
        this.model.register(this);

        JFrame mainFrame = new JFrame("WORDLE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        LevelSelectionPanel levelSelectorPanel = new LevelSelectionPanel(this.controller, this.model, this.scores);

        mainPanel.add(levelSelectorPanel);
        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void update()
    {

    }

}
    