package exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class CorrectableException extends Exception{

	private HashMap<CorrectableException, String> myResults;

	public CorrectableException(String topMessage, String message, Object requiredInputType){
		myResults = new HashMap<>();
		myResults.put(this, correctMessage(message, requiredInputType));
	}

	public CorrectableException(String message){
		myResults = new HashMap<>();
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle(message);
		dialog.setHeaderText("Yes or No?");
		dialog.setContentText("Yes or no?");
		// create two buttons YES or NO
	}

	private String correctMessage(String message, Object type){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle(message);
		dialog.setHeaderText(message);
		dialog.setContentText("Please enter new argument.");
		Optional<String> unresolved = dialog.showAndWait();
		if(unresolved.isPresent()){
			//			boolean in = unresolved.get()
		}
		return null;
	}

	public String getResult(CorrectableException w){
		return myResults.get(w);
	}

}