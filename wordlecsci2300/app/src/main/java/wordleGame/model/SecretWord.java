package wordleGame.model;

import java.util.HashMap;

import wordleGame.model.GameModel;

public class SecretWord
{
	private char [] secret;
	private HashMap<Character, Integer> secretLetterOccurences;
	
    public SecretWord(String word)
    {
		this.secret = word.toCharArray();
		System.out.println(word);
		
		// creation of hashmap for letter occurence frequencies in secret word
		this.secretLetterOccurences = new HashMap<>();
		for (int i = 0; i < secret.length; i++)
		{
			if (this.secretLetterOccurences.containsKey(secret[i]))
			{
				int oldNum = this.secretLetterOccurences.get(secret[i]);
				int newNum = oldNum + 1;
				this.secretLetterOccurences.replace(secret[i], newNum);
			}
			else
			{
				this.secretLetterOccurences.put(secret[i],1);
			}
		}
    }


	public String[] analyzeGuess(char[] userGuess){
		// takes char array that we pass from analyzeMostRecentGuess --> from the model
		// builds a hashmap of letter occurence frequencies in user guess
		HashMap<Character, Integer> guessLetterOccurences = new HashMap<>();
		for (int i = 0; i < userGuess.length; i++)
		{
			if (guessLetterOccurences.containsKey(userGuess[i]))
			{
				int oldNum = guessLetterOccurences.get(userGuess[i]);
				int newNum = oldNum + 1;
				guessLetterOccurences.replace(userGuess[i], newNum);
			}
			else
			{
				guessLetterOccurences.put(userGuess[i],1);
			}
		}

		// code to build color array
		String[] colorArray = new String[5];
		for (int i = 0; i < this.secret.length; i++)
		{
			boolean greenFound = false;
			boolean yellowFound = false;
			for (int j = 0; j < this.secret.length; j++)
			{
				if (!greenFound) 
				{
					if (this.secret[j] == userGuess[i])
					{
						if (i == j)
						{	
							colorArray[i] = "green";
							greenFound = true;
						}
						else 
						{
							colorArray[i] = "yellow";
							yellowFound = true;
						}
					}
				}
			}

			if (!greenFound && !yellowFound)
			{
				colorArray[i] = "gray";
			}
			else
			{ // only check for duplicates if current userGuess letter is actually in secretWord
				int dupCounter = 0;
				for (int k = 0; k < i; k++)
				{
					if (userGuess[i] == userGuess[k]) // if userGuess has duplicate letter
					{
						dupCounter++;
						if (guessLetterOccurences.get(userGuess[i]) > secretLetterOccurences.get(userGuess[i]) && dupCounter >= secretLetterOccurences.get(userGuess[i])) // if more occurrences of letter in guess than secret word
						{
							if (colorArray[k].equals("green"))
							{
								colorArray[i] = "gray";
							}
							else if (colorArray[k].equals("yellow"))
							{
								colorArray[k] = "gray";
							}
						}
					}
				}
			}
		}

		return colorArray;

	}
	
	public String reveal()
	{
		return String.valueOf(this.secret);
		
	}
	
}
