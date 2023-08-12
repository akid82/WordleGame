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

public class SubmitButton extends JButton implements ActionListener
{
    GameGUI gameGUI;

    public SubmitButton(GameGUI gameGUI)
    {
        super("SUBMIT");
        addActionListener(this);
        this.gameGUI = gameGUI;
    }

    public void disableButton()
    {
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        this.gameGUI.submitPressed();
    }
}
