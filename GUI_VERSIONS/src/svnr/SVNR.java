package svnr;
/**Das Programm nimmt die SV-NR (10-Stellig) entgegen und prüft ob dies eine zulässige SVNR ist.
Die SV-Nr. wird als String eingelesen. Sollte der eingegebene String kürzer oder länger als 
10-Stellen sein wird eine entsprechende Fehlermeldung ausgegeben, und der Benutzer wird gefragt 
ob er eine andere SV-Nr. prüfen möchte.. Soweit der String genau 10-Stellen lang ist, wird er 
als Parameter in die Methode Gueltigkeit der erstellten "Versicherung" übergeben. Als Antwort 
kommt ein boolean der interpretiert wird. Soweit der Wert des retournierten boolean "true" ist, 
wird entsprechend ausgegeben, dass die SV-Nr. gültig ist. Bei dem Wert "false" wird entsprechend 
ausgegeben, dass die SV-Nr. ungültig ist. Danach erfolgt eine Abfrage ob eine weitere SV-Nr. 
überprüft werden soll.*/

import java.io.IOException;
import javax.swing.JOptionPane;

public class SVNR{

	public static void main(String[] args) throws IOException{

		//Deklaration der Variablen und Konstanten
		String svnr;
		boolean repeat, antwort, lenghtok = false;
		Versicherung v = new Versicherung();
		do{
			do {
				//Aufforderung zur Eingabe der SV-Nr.
				svnr = JOptionPane.showInputDialog(null, "Dieses Programm dient dazu zu überprüfen, ob die eingegebenen SV-NR zulässig ist. \n Bitte geben Sie die SV-NR ein: ");
				if (svnr.length() < 10){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu kurz und daher keine gültige SV-Nr.");
					lenghtok = false;
					continue;
				}else if (svnr.length() > 10){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu lang und daher keine gültige SV-Nr.");
					lenghtok = false;
					continue;
				}else if (svnr.length() == 10){
					antwort = v.gueltigkeitsv(svnr);
					lenghtok = true;
					/*Vergleichen ob Restwert der Summe mit der 4. Stelle der SV-Nr. (xe) übereinstimmt. 
                	Entsprechend Ausgabe ob SV-Nr. 	korrekt oder nicht. */
					if (antwort == true) {
						JOptionPane.showMessageDialog(null, "Die eingegebene SV-Nr. (" + svnr +") ist gültig!");
					}else if (antwort == false) {
						JOptionPane.showMessageDialog(null, "Die eingegebene SV-Nr. ist nicht gültig!\n\n");
					}
				}
			} while (!lenghtok);
			//Abfrage ob weitere SV-Nr. geprüft werden soll.
			repeat = isMoreSocialSec();								
		}while (repeat);
		JOptionPane.showMessageDialog(null, "Das Programm wird nun beendet");
	}
	protected static boolean isMoreSocialSec () throws IOException {
		boolean repeat;
		// Abfrage ob weitere Kreditkartennummer geprüft werden soll.
		int button = JOptionPane.YES_OPTION; 
		int response = JOptionPane.showConfirmDialog(null, "Möchten Sie eine weitere SV-Nr. überprüfen?", "Weiter machen?", button);
		if (button == response) {
			repeat = true;
		} else {
			repeat = false;
		}
		return repeat;
	}

}


