package dicepassword;

import java.security.SecureRandom;


public class RandomDiceWord {

    private int iteration;
    private SecureRandom secrnd = new SecureRandom();
    DiceWordRetrievalMySQL sqlconn = new DiceWordRetrievalMySQL();

    public final String rndInteger(int min, int max){
	String complete_number;
	complete_number = "";
	for ( int i = 1; i <= 5; i++){
	    iteration = secrnd.nextInt(max-min)+min;
	    complete_number = complete_number + iteration;
	}
	return complete_number;
    }

    public final String diceWord(){ 
	String complete_number;
	complete_number = "";
	for ( int i = 1; i <= 5; i++){
	    iteration = secrnd.nextInt(7-1)+1;
	    complete_number = complete_number + iteration;
	}
	complete_number = sqlconn.retrieveDiceWordfromDatabase(complete_number);
	return complete_number;
    }
    
    public final String germanDiceWord(){ 
	String complete_number;
	complete_number = "";
	for ( int i = 1; i <= 5; i++){
	    iteration = secrnd.nextInt(7-1)+1;
	    complete_number = complete_number + iteration;
	}
	complete_number = sqlconn.retrieveGermanDiceWordfromDatabase(complete_number);
	return complete_number;
    }
    
    public final String rndFloat(int number_of_iterations){
	String complete_number;
	complete_number = "";
	float current_float;
	for ( int i = 1; i <= number_of_iterations; i++){
	    current_float = secrnd.nextFloat();
	    complete_number = complete_number + " " + current_float;
	}
	return complete_number;
    }
    
    public final String rnddouble(int number_of_iterations){
	String complete_number;
	complete_number = "";
	double current_double;
	for ( int i = 1; i <= number_of_iterations; i++){
	    current_double = secrnd.nextDouble();
	    complete_number = complete_number + " " + current_double;
	}
	return complete_number;
    }
}




