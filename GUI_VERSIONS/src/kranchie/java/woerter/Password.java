package kranchie.java.woerter;

import java.security.SecureRandom;

public class Password extends Word {
    private SecureRandom secrnd = new SecureRandom();
    
    public Password () {
    	super();
    }
    
    public Password (String wort, String sprache) {
    	super(wort, sprache);
    }
	// Erstellt ein zufälliges Passwort der gewünschten Länge.
    public String genpasswd(int laenge) {
	final String alphabet	  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+-./&%$§";
	char	     buchstabe;
	String	     rnd_password = "";
	for (int i = 0; i < laenge; i++) {
	    int randomnumber = secrnd.nextInt(59 - 0 + 1) + 0;
	    buchstabe	 = alphabet.charAt(randomnumber);
	    rnd_password = buchstabe + rnd_password;
	}
	this.eingeben(rnd_password);
	Password.setClipboard(this.inhaltauslesen());
	return this.inhaltauslesen();
    }
}
