package ccnr;
/**Die Klasse Versicherung wird beschrieben durch den String ccnr und die einzelnen Integer des 
**Strings. Mit dem Konstruktur "Creditcard()" wird der Wert des Strings ccnr auf "0000000000000000" 
**gesetzt.*/

public class Creditcard{
    private String ccnr;
    private int a, b, c, d, e, f, g, h, q, j, k, l, m, n, o, p, summe; //Variablen
    
    
	/*Die Methode "Creditcard()" setzt "ccnr" auf "0000000000000000" (String). */	
    Creditcard(){
	ccnr = "0000000000000000";
    }
	/*Die Methode "Gueltigkeit" benötigt als parameter einen 10-stelligen String und returned einen boolean.
	**Innerhalb der Methode wird dieser dann per Aufruf der Methode "setwertsv()" in 10 einzelne Integer
	**umgewandelt und den einzelnen Stellen der SV-Nr. zugeordnet.
	**Danach wird die Prüfsumme gebildet, sowie Modulo 11 der Prüfsumme errechnet.
	**Das Ergebnis wird mit dem Integer xe verglichen.
	**Abhängig von dem Ergebnis des Vergleiches der zwei Integer "summe" und "xe" wird, wenn xe = summe "true" 
	**und wenn xe != summe "false" zurückgeliefert.*/
	boolean gueltigkeitcc(String u){
	//Deklaration der lokalen Variablen
	ccnr = u;
	boolean antwort;
	
	//Zuordnung einzelne Integer des String "ccnr"
	a = setwertcc(ccnr.charAt(0));
	b = setwertcc(ccnr.charAt(1));	
	c = setwertcc(ccnr.charAt(2));	
	d = setwertcc(ccnr.charAt(3));	
	e = setwertcc(ccnr.charAt(4));	
	f = setwertcc(ccnr.charAt(5));	
	g = setwertcc(ccnr.charAt(6));	
	h = setwertcc(ccnr.charAt(7));	
	q = setwertcc(ccnr.charAt(8));	
	j = setwertcc(ccnr.charAt(9));
	k = setwertcc(ccnr.charAt(10));
	l = setwertcc(ccnr.charAt(11));
	m = setwertcc(ccnr.charAt(12));
	n = setwertcc(ccnr.charAt(13));
	o = setwertcc(ccnr.charAt(14));
	p = setwertcc(ccnr.charAt(15));
	
	/*Kreditkartennummerstellen werden entsprechend den Multiplikatoren multipliziert.	
	Jeder zweite Zahl wird abhängig von ihrer Größe mit 2 multipliziert, bzw. mit 2 multipliziert und es werden 9 abgezogen.*/
	if (a * 2 >= 10){
	a = a * 2 - 9;
	}
	else {
	a *= 2;
	}
	
	if (c * 2 >= 10){
	c = c * 2 - 9;
	}
	else {
	c *= 2;
	}
	
	if (e * 2 >= 10){
	e = e * 2 - 9;
	}
	else {
	e *= 2;
	}
	
	if (g * 2 >= 10){
	g = g * 2 - 9;
	}
	else {
	g *= 2;
	}
	
	if (q * 2 >= 10){
	q = q * 2 - 9;
	}
	else {
	q *= 2;
	}
	
	if (k * 2 >= 10){
	k = k * 2 - 9;
	}
	else {
	k *= 2;
	}
	
	if (m * 2 >= 10){
	m = m * 2 - 9;
	}
	else {
	m *= 2;
	}
	
	if (o * 2 >= 10){
	o = o * 2 - 9;
	}
	else {
	o *= 2;
	}		
	
	//Summe aller Variablen bilden. Auf Teilbarkeit durch 10 prüfen.
	summe = +a + b + c + d + e + f + g + h + q + j + k + l + m + n + o + p;
	summe = summe%10;
					
	/*Vergleichen ob die Summe Modulo 10 0 ergibt. Wenn dies der Fall ist, ist die Kreditkartennummer gültig, 
	 * wenn nicht, dann ist sie ungültig. */
	if (summe == 0){
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
    private int setwertcc(char u){
	char wert = u;
	int a = Character.getNumericValue(wert);
	return a;
    }
}
