package ccnr;
/**Das Programm nimmt die Kreditkartennummer(16-Stellig) entgegen und prüft ob dies eine zulässige Kreditkartennummer ist.
Die Kreditkartennummer wird als String eingelesen. Sollte der eingegebene String kürzer oder länger als 
16-Stellen sein wird eine Fehlermeldung ausgegeben. Es wird ein Int Array erstellt mit der Länge 
des Strings,in dieses wird der String einzeln eingelesen und in ein Int umgewandelt. 
Jede zweite Ziffer der Kreditkartennummer wird verdoppelt. Sollte dadurch ein wert über 10 entstehen, wird von dem Ergebnis neun 
subtrahiert. Danach wird die Summe aller Ziffern gebildet und geprüft ob die Summe Modulo 10 den Wert 0 ergibt. Wenn der
Wert 0 erreicht wird ist die Kreditkartennummer gültig, sonst nicht.
Das Ergebnis der Überprüfung wird ausgegeben und es wird abgefragt ob eine weitere Kreditkartennummer 
überprüft werden soll.*/

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
				ccnr = JOptionPane.showInputDialog(null, "Dieses Programm dient dazu zu überprüfen, ob die eingegebenen Kreditkartennummer zulässig ist. \n Bitte geben Sie die Kreditkartennummer ein: ");

				//Prüfung ob eingegebene Zahl 16 Stellen hat

				if(ccnr.length() < 16){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu kurz und daher keine gültige Kreditkartennummer.");
					lenghtok = false;
					continue;
				}else if (ccnr.length() > 16){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu lang und daher keine gültige Kreditkartennummer.");
					lenghtok = false;
					continue;
				}else if (ccnr.length() == 16){
					antwort = c.gueltigkeitcc(ccnr);
					lenghtok = true;
					if (antwort == true) {
						JOptionPane.showMessageDialog (null, "Die eingegebene Kreditkartennummer (" + ccnr +") ist gültig!");
					}else if (antwort == false) {
						JOptionPane.showMessageDialog (null, "Die eingegebene Kreditkartennummer ist nicht gültig!");
					}
				}
			}while (!lenghtok);
			repeat = isMoreCredit();
		}while (repeat);
		
		JOptionPane.showMessageDialog(null, "Das Programm wird nun beendet");
	}

	protected static boolean isMoreCredit () throws IOException {
		boolean repeat;
		// Abfrage ob weitere Kreditkartennummer geprüft werden soll.
		int button = JOptionPane.YES_OPTION; 
		int response = JOptionPane.showConfirmDialog(null, "Möchten Sie eine weitere Kreditkartennummer überprüfen?", "Weiter machen?", button);
		if (button == response) {
			repeat = true;
		} else {
			repeat = false;
		}
		return repeat;
	}
}


