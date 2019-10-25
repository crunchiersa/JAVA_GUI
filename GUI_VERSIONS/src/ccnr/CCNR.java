package ccnr;
/**Das Programm nimmt die Kreditkartennummer(16-Stellig) entgegen und pr�ft ob dies eine zul�ssige Kreditkartennummer ist.
Die Kreditkartennummer wird als String eingelesen. Sollte der eingegebene String k�rzer oder l�nger als 
16-Stellen sein wird eine Fehlermeldung ausgegeben. Es wird ein Int Array erstellt mit der L�nge 
des Strings,in dieses wird der String einzeln eingelesen und in ein Int umgewandelt. 
Jede zweite Ziffer der Kreditkartennummer wird verdoppelt. Sollte dadurch ein wert �ber 10 entstehen, wird von dem Ergebnis neun 
subtrahiert. Danach wird die Summe aller Ziffern gebildet und gepr�ft ob die Summe Modulo 10 den Wert 0 ergibt. Wenn der
Wert 0 erreicht wird ist die Kreditkartennummer g�ltig, sonst nicht.
Das Ergebnis der �berpr�fung wird ausgegeben und es wird abgefragt ob eine weitere Kreditkartennummer 
�berpr�ft werden soll.*/

import java.io.IOException;
import javax.swing.JOptionPane;

public class CCNR{

	public static void main(String[] args) throws IOException{

		//Deklaration der Variablen und Konstanten
		String ccnr;
		boolean repeat, antwort, lenghtok = false;
		//final BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
		Creditcard c = new Creditcard();

		do{
			do {
				//Aufforderung zur Eingabe der Kreditkartennummer.
				ccnr = JOptionPane.showInputDialog(null, "Dieses Programm dient dazu zu �berpr�fen, ob die eingegebenen Kreditkartennummer zul�ssig ist. \n Bitte geben Sie die Kreditkartennummer ein: ");

				//Pr�fung ob eingegebene Zahl 16 Stellen hat

				if(ccnr.length() < 16){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu kurz und daher keine g�ltige Kreditkartennummer.");
					lenghtok = false;
					continue;
				}else if (ccnr.length() > 16){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu lang und daher keine g�ltige Kreditkartennummer.");
					lenghtok = false;
					continue;
				}else if (ccnr.length() == 16){
					antwort = c.gueltigkeitcc(ccnr);
					lenghtok = true;
					if (antwort == true) {
						JOptionPane.showMessageDialog (null, "Die eingegebene Kreditkartennummer (" + ccnr +") ist g�ltig!");
					}else if (antwort == false) {
						JOptionPane.showMessageDialog (null, "Die eingegebene Kreditkartennummer ist nicht g�ltig!");
					}
				}
			}while (!lenghtok);
			repeat = isMoreCredit();
		}while (repeat);
		
		JOptionPane.showMessageDialog(null, "Das Programm wird nun beendet");
	}

	protected static boolean isMoreCredit () throws IOException {
		boolean repeat;
		// Abfrage ob weitere Kreditkartennummer gepr�ft werden soll.
		int button = JOptionPane.YES_OPTION; 
		int response = JOptionPane.showConfirmDialog(null, "M�chten Sie eine weitere Kreditkartennummer �berpr�fen?", "Weiter machen?", button);
		if (button == response) {
			repeat = true;
		} else {
			repeat = false;
		}
		return repeat;
	}
}


