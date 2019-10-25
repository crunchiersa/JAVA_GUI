package svnr;
/**Die Klasse Versicherung wird beschrieben durch den String svnr und die einzelnen Integer des 
**Strings. Mit dem Konstruktur "Versicherung()" wird der Wert des Strings svnr auf "0000000000" 
**gesetzt.*/

public class Versicherung {
    private String svnr;
    private int a, b, c, xe, d1, d2, m1, m2, y1, y2, summe; //Variablen
  //Wertezuweisung der Konstanten
    private final int k1 = 3;
    private final int k2 = 7;
    private final int k3 = 9;
    private final int k4 = 5;
    private final int k5 = 8;
    private final int k6 = 4;
    private final int k7 = 2;
    private final int k8 = 1;
    private final int k9 = 6;
    
	/*Die Methode "Versicherung()" setzt den "svnr" auf "0000000000" (String). */	
    Versicherung(){
	svnr = "0000000000";
    }
	/*Die Methode "Gueltigkeit" benötigt als parameter einen 10-stelligen String und returned einen boolean.
	**Innerhalb der Methode wird dieser dann per Aufruf der Methode "setwertsv()" in 10 einzelne Integer
	**umgewandelt und den einzelnen Stellen der SV-Nr. zugeordnet.
	**Danach wird die Prüfsumme gebildet, sowie Modulo 11 der Prüfsumme errechnet.
	**Das Ergebnis wird mit dem Integer xe verglichen.
	**Abhängig von dem Ergebnis des Vergleiches der zwei Integer "summe" und "xe" wird, wenn xe = summe "true" 
	**und wenn xe != summe "false" zurückgeliefert.*/
	boolean gueltigkeitsv(String u){
	//Deklaration der lokalen Variablen
	svnr = u;
	boolean antwort;
	
	//Zuordnung einzelne Integer des String "svnr"
	a = setwertsv(svnr.charAt(0));
	b = setwertsv(svnr.charAt(1));	
	c = setwertsv(svnr.charAt(2));	
	xe = setwertsv(svnr.charAt(3));	
	d1 = setwertsv(svnr.charAt(4));	
	d2 = setwertsv(svnr.charAt(5));	
	m1 = setwertsv(svnr.charAt(6));	
	m2 = setwertsv(svnr.charAt(7));	
	y1 = setwertsv(svnr.charAt(8));	
	y2 = setwertsv(svnr.charAt(9));
	
	//SV-Nr. stellen werden entsprechend den Multiplikatoren multipliziert.
	a *= k1;
	b *= k2;
	c *= k3;
	d1 *= k4;
	d2 *= k5;
	m1 *= k6;
	m2 *= k7;
	y1 *= k8;
	y2 *= k9;
	
	//Prüfsumme bilden
	summe = +a +b +c +d1 + d2 + m1 + m2 + y1 +y2;
	
	//Restwert errechnen	
	summe %= 11;
	
	//Vergleich Ergebnis Prüfsumme und Integer xe.
	if (summe == xe){
	    antwort = true;
	    return antwort;
	}else{
	    antwort = false;
	    return antwort;
	}
	
	}
	/*Die Methode benötigt als Parameter einen Character und returned einen Integer.
	**Innerhalb der Methode wird der numerische Wert des mitgeschickten Charakter als
	**Integer interpretiert und returned.*/
    private int setwertsv(char u){
	char wert = u;
	int a = Character.getNumericValue(wert);
	return a;
    }
}
