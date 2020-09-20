package kranchie.java.kommunikation.woerter;

import java.security.SecureRandom;

import kranchie.java.customExceptions.CustomUnchecked;

public class Password extends Word {
	private SecureRandom secrnd = new SecureRandom();

	// Erstellt ein zuf�lliges Passwort der gew�nschten L�nge.
	public String genpasswd(int laenge) {
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+-&$0123456789";
		char buchstabe;
		String rnd_password = "";
		for (int i = 0; i < laenge; i++) {
			int randomnumber = secrnd.nextInt(65 - 0 + 1) + 0;
			buchstabe = alphabet.charAt(randomnumber);
			rnd_password = buchstabe + rnd_password;
		}
		this.inhalteingeben(rnd_password);
		try {
			Password.setClipboard(this.inhaltauslesen());
			return this.inhaltauslesen();
		} catch (CustomUnchecked e) {
			String fehler = e.getFehler();
			return fehler;
		}
	}
}
