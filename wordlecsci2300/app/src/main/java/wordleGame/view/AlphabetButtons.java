package wordleGame.view;

import java.awt.*;
import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;

public class AlphabetButtons extends JPanel implements ActionListener
{
    JButton[] alphabetButtons;
    GameGUI gameGUI;

    public AlphabetButtons(GameGUI gameGUI)
    {
        this.setLayout(new GridLayout(3,9)); // position the buttons in a grid
        //this.setBackground(new Color(96,96,96)); // make this a bit darker
        this.gameGUI = gameGUI;
        this.alphabetButtons = new JButton[26];
        int j = 0;
        for (char letter = 'a'; letter <= 'z'; letter++)
        {
            this.alphabetButtons[j] = new JButton(Character.toString(letter));
            this.add(this.alphabetButtons[j]);
            this.alphabetButtons[j].addActionListener(this);
            j++;
        }
    }

    public void disableButtons()
    {
        for (int i = 0; i < 26; i++)
        {
            this.alphabetButtons[i].setEnabled(false);
        }
    }

    public void updateButtonColor(char letter, String stringColor, Color color)
    {
        for (int i = 0; i < 26; i++)
        {
            if (this.alphabetButtons[i].getText().charAt(0) == letter)
            {
                if (!this.alphabetButtons[i].getBackground().equals(new Color(0,153,0))) // if button is already green, we don't want to change it
                {
                    if (this.alphabetButtons[i].getBackground().equals(new Color(204,204,0))) // checks if button is already yellow
                    {
                        if (stringColor.equals("green")) // if button is already yellow, we only want to change it if its supposed to be green now
                        {
                            this.alphabetButtons[i].setBackground(color);
                            this.alphabetButtons[i].setOpaque(true);
                        }
                    }
                    else // means button background is either nothing or gray so we can change it to whatever it needs to be
                    { 
                        this.alphabetButtons[i].setBackground(color);
                        this.alphabetButtons[i].setOpaque(true);
                    }
                }
            }
        }
    }

    // implements ActionListener
    @Override
    public void actionPerformed(ActionEvent event)
    {
        JButton clickedButton = (JButton)event.getSource();
        this.gameGUI.setMostRecentLetter(clickedButton.getText().charAt(0));
        this.gameGUI.letterPressed();
    }
}
