package dicepassword;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

import javax.swing.JOptionPane;

public class GeneratePasswordGUI {
	public static void main (String[] args) throws NumberFormatException, IOException {
		String output = null;
		boolean repeat = true, wiederholung = true;
		
		
		do{
		    do{
			switch (getInput(1)){
			case (1):	output = diceWord(getInput(2));
						repeat = false;
						break;
			case (2):	output = germanDiceWord(getInput(2));
						repeat = false;
						break;
			case (3):	output = randPassword(getInput(3));
						repeat = false;
						break;
			default:	JOptionPane.showMessageDialog(null, "Keine gültige Eingabe! Bitte wählen Sie 1, 2 oder 3.");
			    		repeat = true;
			    		break;
			}
		    }while (repeat);
		    
		    JOptionPane.showMessageDialog(null, "Ihr Passwort lautet: " +output);
		    setClipboard(output);
		    clearClipboard();
		    output = "";
		    wiederholung = morepasswd();

		}while(wiederholung);
		
		JOptionPane.showMessageDialog(null, "Das Programm wird nun beendet!");
	    }

	    public static int getInput(int auswahl) throws IOException { 
		int choice = 0;
		boolean more = true;
		String eingabe = "";
		do {
		    while (more == true){
			switch(auswahl){
			case (1): eingabe = JOptionPane.showInputDialog("Möchten Sie ein englisches Dice-Password(1), deutsches Dice-Password(2) oder ein zufällig erstelltes Passwort(3) generieren?");
			break;
			case (2): eingabe = JOptionPane.showInputDialog("Wie viele Dice-Worte soll Ihr neues Passwort haben?");
			break;
			case (3): eingabe = JOptionPane.showInputDialog("Wie viele Zeichen soll das Passwort haben? ");
			break;
			case (4): eingabe = JOptionPane.showInputDialog("Wie viele Zahlen sollen generiert werden? ");
			break;
			}
			try {
			    choice = Integer.parseInt(eingabe);
			    more = false;
			}catch (NumberFormatException nf){
			    JOptionPane.showMessageDialog(null, "Keine gültige Zahl!");
			}
		   }
		}while (more == true);

		return choice;
	    }
	    public static String diceWord(int number_of_words){
		int b = 1;
		String reply, output = null;
		final RandomDiceWord randomWord = new RandomDiceWord();
		do{
		    reply = randomWord.diceWord();
		    if (b > 1){
			output = reply + "-" + output;
		    }else{
			output = reply;
		    }
		    b++;   
		}while(b <= number_of_words);
		return output;
	    }
	    
	    public static String germanDiceWord(int number_of_words){
		int b = 1;
		String reply, output = null;
		final RandomDiceWord randomWord = new RandomDiceWord();
		do{
		    reply = randomWord.germanDiceWord();
		    if (b > 1){
			output = reply + "-" + output;
		    }else{
			output = reply;
		    }
		    b++;   
		}while(b <= number_of_words);
		return output;
	    }

	    public static String randPassword(int number_of_chars){
	    RandomLetterCombination randompw = new RandomLetterCombination();
		String output = randompw.randomString(number_of_chars); 
		return output;
	    }
	    
		private static void clearClipboard (){
			StringSelection stringSelection = new StringSelection("");
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}

		private static void setClipboard ( String content ){
			StringSelection stringSelection = new StringSelection(content);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}
		
		private static boolean morepasswd() {
			boolean response;
			int button = JOptionPane.YES_OPTION; 
			int answer = JOptionPane.showConfirmDialog(null, "Wollen Sie ein weiteres Passwort generieren?", "Neues Passwort?", button);
			if (button == answer) {
				response = true;
			} else {
				response = false;
			}
			return response;
		}
}
