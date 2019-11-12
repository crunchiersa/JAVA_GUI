package kranchie.java.customExceptions;

import java.io.Serializable;

public class CustomUnchecked extends RuntimeException {
	
	public CustomUnchecked(String mesg) {
		super(mesg);
		//ADD.
	}
}
