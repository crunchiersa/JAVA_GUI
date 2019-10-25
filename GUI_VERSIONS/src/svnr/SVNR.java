package svnr;
/**Das Programm nimmt die SV-NR (10-Stellig) entgegen und pr�ft ob dies eine zul�ssige SVNR ist.
Die SV-Nr. wird als String eingelesen. Sollte der eingegebene String k�rzer oder l�nger als 
10-Stellen sein wird eine entsprechende Fehlermeldung ausgegeben, und der Benutzer wird gefragt 
ob er eine andere SV-Nr. pr�fen m�chte.. Soweit der String genau 10-Stellen lang ist, wird er 
als Parameter in die Methode Gueltigkeit der erstellten "Versicherung" �bergeben. Als Antwort 
kommt ein boolean der interpretiert wird. Soweit der Wert des retournierten boolean "true" ist, 
wird entsprechend ausgegeben, dass die SV-Nr. g�ltig ist. Bei dem Wert "false" wird entsprechend 
ausgegeben, dass die SV-Nr. ung�ltig ist. Danach erfolgt eine Abfrage ob eine weitere SV-Nr. 
�berpr�ft werden soll.*/

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
				svnr = JOptionPane.showInputDialog(null, "Dieses Programm dient dazu zu �berpr�fen, ob die eingegebenen SV-NR zul�ssig ist. \n Bitte geben Sie die SV-NR ein: ");
				if (svnr.length() < 10){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu kurz und daher keine g�ltige SV-Nr.");
					lenghtok = false;
					continue;
				}else if (svnr.length() > 10){
					JOptionPane.showMessageDialog(null, "Die eingegebene Zahl ist zu lang und daher keine g�ltige SV-Nr.");
					lenghtok = false;
					continue;
				}else if (svnr.length() == 10){
					antwort = v.gueltigkeitsv(svnr);
					lenghtok = true;
					/*Vergleichen ob Restwert der Summe mit der 4. Stelle der SV-Nr. (xe) �bereinstimmt. 
                	Entsprechend Ausgabe ob SV-Nr. 	korrekt oder nicht. */
					if (antwort == true) {
						JOptionPane.showMessageDialog(null, "Die eingegebene SV-Nr. (" + svnr +") ist g�ltig!");
					}else if (antwort == false) {
						JOptionPane.showMessageDialog(null, "Die eingegebene SV-Nr. ist nicht g�ltig!\n\n");
					}
				}
			} while (!lenghtok);
			//Abfrage ob weitere SV-Nr. gepr�ft werden soll.
			repeat = isMoreSocialSec();								
		}while (repeat);
		JOptionPane.showMessageDialog(null, "Das Programm wird nun beendet");
	}
	protected static boolean isMoreSocialSec () throws IOException {
		boolean repeat;
		// Abfrage ob weitere Kreditkartennummer gepr�ft werden soll.
		int button = JOptionPane.YES_OPTION; 
		int response = JOptionPane.showConfirmDialog(null, "M�chten Sie eine weitere SV-Nr. �berpr�fen?", "Weiter machen?", button);
		if (button == response) {
			repeat = true;
		} else {
			repeat = false;
		}
		return repeat;
	}

}


