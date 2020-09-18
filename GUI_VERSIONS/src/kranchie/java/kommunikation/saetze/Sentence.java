package kranchie.java.kommunikation.saetze;

import kranchie.java.kommunikation.woerter.*;
import java.util.ArrayList;
import java.util.List;

public class Sentence {

	private List<Word> satz = new ArrayList<Word>();
	private Word wort = new GermanWord();
	private String sprache;
	
	public void setSprache(String sprache) {
		this.sprache = sprache;
	}
	
	public String getSprache() {
		return this.sprache;
	}
	
	public void setSatz(GermanWord wort) {
		satz.add(wort);
	}
	
	private List<Word> getSatz(){
		return this.satz;
	}

	private void setWord(Word wort, int zahl) {
		satz.add(zahl, wort);
	}
	
	private String getWord(int zahl) {
		wort =  satz.get(zahl);
		return wort.inhaltauslesen();
	}
	
	private String getCompleteSentence(List<Word> sentence) {
		
		String completeSentence = "";
		for (int i=0; i<= sentence.size();i++) {
			Word wort = sentence.get(i);
			completeSentence = completeSentence + wort.inhaltauslesen();
		}
		return completeSentence;
	}
	
	public String getSentence() {
		return this.getCompleteSentence(this.satz);		
	}
	
	public List<Word> getCompleteSentence(){
		return this.getSatz();
	}
	
	public void setCompleteSentence(List<Word> sentence) {
		this.satz = sentence;
	}
	
	public void setSentence(Word wort, int position) {
		this.setWord(wort, position);
	}
	
	public String getPublicWord(int position) {
		return this.getWord(position);
	}
}

