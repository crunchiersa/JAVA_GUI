package kranchie.java.karten;

public class ECARD_AT extends Karte {

    // Variablen
    private final int k1 = 3;
    private final int k2 = 7;
    private final int k3 = 9;
    private final int k4 = 5;
    private final int k5 = 8;
    private final int k6 = 4;
    private final int k7 = 2;
    private final int k8 = 1;
    private final int k9 = 6;

    // Constructors
    public ECARD_AT(String svnr) {
	super(svnr, 10);
	super.setKartenArt("Sozialversicherung");
    }

    public ECARD_AT() {
	super();
	super.setKartenArt("Sozialversicherung");
	super.setkartennrlaenge(10);
    }

    // Methoden

    /**
     * Description: Die Methode errechnet die Gültigkeit anhand folgendem
     * Vorgehen: 1. Die Werte der Stellen der Sozialversicherungsnummer,
     * ausgenommen Stelle 4) werden mit Konstanten multipliziert. Die Zahlen
     * werden summiert. 2. Die Summe wird Modulo 11 gerechnet. 3. Der Restwert
     * wird mit der vierten Stelle der Sozialversicherung verglichen. Falls die
     * Werte über- einstimmen, ist die Sozialversicherungsnummer gültig.
     * 
     * @author      crunchie
     * @return      Boolean. True = Die Sozialversicherungsnummer ist gültig.
     *              False = Die Sozialversicherungsnummer ist nicht gültig.
     */
    public boolean checkSVNR() {
	boolean	gueltigkeit;
	int	repeat	    = 0;
	int	summe	    = 0;
	int	xe	    = 0;
	boolean	nrinordnung = this.checkLaenge(this.getkartennrlaenge(), this.getKartenArt());
	if (nrinordnung == true) {
	    for (int i = 0; i < 10; i++) {
		int wert = super.setwertcc(this.getKartenNummer().charAt(i));
		if (i != 3) {
		    switch (i) {
			case 0:
			    wert *= k1;
			    break;
			case 1:
			    wert *= k2;
			    break;
			case 2:
			    wert *= k3;
			    break;
			case 4:
			    wert *= k4;
			    break;
			case 5:
			    wert *= k5;
			    break;
			case 6:
			    wert *= k6;
			    break;
			case 7:
			    wert *= k7;
			    break;
			case 8:
			    wert *= k8;
			    break;
			case 9:
			    wert *= k9;
			    break;
			default:
			    break;
		    }
		    summe += wert;
		} else {
		    xe = wert;
		}

		repeat = repeat + 1;
	    }
	    summe %= 11;
	    if (xe == summe) {
		gueltigkeit = true;
	    } else {
		gueltigkeit = false;
	    }
	} else {
	    gueltigkeit = false;
	}
	return gueltigkeit;
    }
}
