package exceptions;

import java.util.List;

public class ExceptionHandler {

	public String getCorrectedType(String topMessage, String message, List<Object> types){
		CorrectableException we = new CorrectableException(topMessage, message, types);
		return we.getResult(we);
	}
	
	public boolean getYesOrNo(){
		CorrectableException we = new CorrectableException("Override? Yes or no.");
		return we.getResult(we).toLowerCase().equals("yes");
	}
	
}