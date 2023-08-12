package wordleGame.view;

import javax.swing.JOptionPane;

public class ConfirmationGame
{
    public static boolean confirmLoadGame()
    {
        int input = JOptionPane.showConfirmDialog(null, "Do you want to load previous scores?", "Select Yes or No", JOptionPane.YES_NO_OPTION);
        if (input == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}