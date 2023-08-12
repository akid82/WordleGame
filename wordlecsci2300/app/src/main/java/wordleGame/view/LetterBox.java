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

public class LetterBox extends JPanel
{
    private Color color;
    private int size;
    JLabel letterLabel;
    String letter;

    public LetterBox()
    {
        this.size = 60;
        setPreferredSize(new Dimension(this.size, this.size));
        this.color = Color.WHITE; // boxes all start as 
        this.letterLabel = new JLabel();
        this.letterLabel.setFont(new Font("Neretto", Font.PLAIN, 40));
        this.add(this.letterLabel);
        setBackground(Color.WHITE);
    }

    public void setColor(Color c)
    {
        setBackground(c);
    }

    public void setText(String userLetter)
    {
        this.letterLabel.setText(userLetter);
    }

}