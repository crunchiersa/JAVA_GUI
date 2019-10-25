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
					
		//Begr��ung im Programm
		System.out.println("Dieses Programm dient dazu zu �berpr�fen, ob die eingegebenen Kreditkartennummer zul�ssig ist.");
		
		do{
			//Aufforderung zur Eingabe der Kreditkartennummer.
			System.out.print("Bitte geben Sie die Kreditkartennummer ein: ");
			//Einlesen der Kreditkartennummer.
			ccnr = eingabe.readLine();
			
			//Pr�fung ob eingegebene Zahl 16 Stellen hat
			if(ccnr.length() < 16){
			    System.out.println("Die eingegebene Zahl ist zu kurz und daher keine g�ltige Kreditkartennummer.");
	        		repeat = isMoreCredit(eingabe);
	        		continue;
			}else if (ccnr.length() > 16){
			    System.out.println("Die eingegebene Zahl ist zu lang und daher keine g�ltige Kreditkartennummer.");
	        		repeat = isMoreCredit(eingabe);
	        		continue;
			}else if (ccnr.length() == 16){
			    antwort = c.gueltigkeitcc(ccnr);
			    if (antwort == true) {
				System.out.println("Die eingegebene Kreditkartennummer (" + ccnr +") ist g�ltig!\n\n");
			    }else if (antwort == false) {
				System.out.println("Die eingegebene Kreditkartennummer ist nicht g�ltig!\n\n");
			    }
			}
			    //Abfrage ob weitere Kreditkartennummer gepr�ft werden soll.
			    repeat = isMoreCredit(eingabe);								
			}while (repeat);
		
	}

protected static boolean isMoreCredit (final BufferedReader eingabe) throws IOException {
    String antwort;
    boolean repeat = true;
    // Abfrage ob weitere Kreditkartennummer gepr�ft werden soll.
	System.out.print("M�chten Sie eine weitere Kreditkartennummer �berpr�fen? J/N \n ");
	antwort = eingabe.readLine();
	switch (antwort.toUpperCase()){//Switch zu entsprechender Antwort, Konvertierung zu Gro�buchstaben
	case ("J"): repeat = true;
	break;
	case ("N"): repeat = false;
	System.out.println("Auf Wiedersehen!");
	break;
	default: System.out.println("Ung�ltige Antwort!");
	repeat = isMoreCredit(eingabe);
	}
	return repeat;
}
}
		
	
