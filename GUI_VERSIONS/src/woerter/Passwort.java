package woerter;

import java.security.SecureRandom;

public class Passwort extends Wort {
	private SecureRandom secrnd = new SecureRandom();
	
	//Erstellt ein zufälliges Passwort der gewünschten Länge.
	public String genpasswd(int laenge) {
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+-./&%$§";
		char buchstabe;
		String rnd_password = "";
		for (int i = 0; i < laenge; i++) {
			int randomnumber = secrnd.nextInt(59-0+1)+0;
			buchstabe = alphabet.charAt(randomnumber);
			rnd_password = buchstabe + rnd_password;
		}
		this.eingeben(rnd_password);
		Passwort.setClipboard(this.auslesen());
		return this.auslesen();
	}
}
