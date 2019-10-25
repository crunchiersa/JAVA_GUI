package dicepassword;

import java.security.SecureRandom;

public class RandomLetterCombination {

    private int iteration;
	private SecureRandom secrnd = new SecureRandom();

    protected final String diceWordNumber(int min, int max){
	String complete_number;
	complete_number = "";
	for ( int i = 1; i <= 5; i++){
	    iteration = secrnd.nextInt(max-min)+min;
	    complete_number = complete_number + iteration;
	}
	return complete_number;
    }

    protected final String randomString (int number_of_chars){
	final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+-./&%$§";
	char buchstabe;
	String rnd_password = "";
	for (int i = 0; i <= number_of_chars; i++) {
	    int randomnumber = secrnd.nextInt(59-0+1)+0;
	    buchstabe = alphabet.charAt(randomnumber);
	    rnd_password = buchstabe + rnd_password;
	}
	return rnd_password;
    }

}
