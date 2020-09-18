package kranchie.java.kommunikation.woerter;

public class GermanWord extends Word {

	String sprache = "deutsch";
	
	public GermanWord() {
		super();
	}

	public GermanWord(String inhalt) {
		super(inhalt);
		this.spracheeingeben(sprache);
	}

	public GermanWord(String inhalt, int laenge) {
		super(inhalt, laenge);
	}

	public GermanWord(String inhalt, int laenge, String sprache) {
		super(inhalt, laenge, sprache);
	}

}
