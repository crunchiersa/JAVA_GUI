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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCNR{
	
	public static void main(String[] args) throws IOException{
			
		//Deklaration der Variablen und Konstanten
	    	String ccnr;
		boolean repeat, antwort;
		final BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
		Creditcard c = new Creditcard();
					
		//Begrüßung im Programm
		System.out.println("Dieses Programm dient dazu zu überprüfen, ob die eingegebenen Kreditkartennummer zulässig ist.");
		
		do{
			//Aufforderung zur Eingabe der Kreditkartennummer.
			System.out.print("Bitte geben Sie die Kreditkartennummer ein: ");
			//Einlesen der Kreditkartennummer.
			ccnr = eingabe.readLine();
			
			//Prüfung ob eingegebene Zahl 16 Stellen hat
			if(ccnr.length() < 16){
			    System.out.println("Die eingegebene Zahl ist zu kurz und daher keine gültige Kreditkartennummer.");
	        		repeat = isMoreCredit(eingabe);
	        		continue;
			}else if (ccnr.length() > 16){
			    System.out.println("Die eingegebene Zahl ist zu lang und daher keine gültige Kreditkartennummer.");
	        		repeat = isMoreCredit(eingabe);
	        		continue;
			}else if (ccnr.length() == 16){
			    antwort = c.gueltigkeitcc(ccnr);
			    if (antwort == true) {
				System.out.println("Die eingegebene Kreditkartennummer (" + ccnr +") ist gültig!\n\n");
			    }else if (antwort == false) {
				System.out.println("Die eingegebene Kreditkartennummer ist nicht gültig!\n\n");
			    }
			}
			    //Abfrage ob weitere Kreditkartennummer geprüft werden soll.
			    repeat = isMoreCredit(eingabe);								
			}while (repeat);
		
	}

protected static boolean isMoreCredit (final BufferedReader eingabe) throws IOException {
    String antwort;
    boolean repeat = true;
    // Abfrage ob weitere Kreditkartennummer geprüft werden soll.
	System.out.print("Möchten Sie eine weitere Kreditkartennummer überprüfen? J/N \n ");
	antwort = eingabe.readLine();
	switch (antwort.toUpperCase()){//Switch zu entsprechender Antwort, Konvertierung zu Großbuchstaben
	case ("J"): repeat = true;
	break;
	case ("N"): repeat = false;
	System.out.println("Auf Wiedersehen!");
	break;
	default: System.out.println("Ungültige Antwort!");
	repeat = isMoreCredit(eingabe);
	}
	return repeat;
}
}
		
	
