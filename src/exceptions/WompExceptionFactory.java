package exceptions;

import java.util.List;

public class WompExceptionFactory {

	public List<String> createCorrectableException(String message, List<String> messages, List<Object> types){
		WompException we = new WompException(message, messages, types);
		return we.getResults(we);
	}
	
	public boolean createYesOrNoException(){
		WompException we = new WompException();
		return we.getResults(we).get(0).equals("True");
	}
	
}