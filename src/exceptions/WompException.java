package exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

public class WompException extends Exception{

	private HashMap<WompException, List<String>> myResults;

	public WompException(String message) {
		super(message);
		myResults = new HashMap<>();
		@SuppressWarnings("rawtypes")
		Dialog alert = new Dialog();
		alert.setTitle("WOMP EXCEPTION");
		alert.setHeaderText("ERROR: " + message);
		ButtonType buttonTypeOk = new ButtonType("Okay");
		alert.getDialogPane().getButtonTypes().add(buttonTypeOk);
		DialogPane dialogPane = alert.getDialogPane();
		alert.showAndWait();
	}

	public WompException(String message, List<String> messages, List<Object> types){
		super(message);
		myResults = new HashMap<>();
		List<String> results = new ArrayList<>();
		for(int x=0; x<messages.size(); x++){
			
		}
	}

	private String correctMessage(String message, Object type){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle(message);
		dialog.setHeaderText(message);
		dialog.setContentText("Please enter new argument.");
		Optional<String> unresolved = dialog.showAndWait();
		if(unresolved.isPresent()){
			
		}
	}

	public WompException(){
		myResults = new HashMap<>();
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Override old results, Yes or No?");
		dialog.setHeaderText("Yes or No?");
		dialog.setContentText("Yes or no?");
		// create two buttons YES or NO
	}

	public List<String> getResults(WompException w){
		return myResults.get(w);
	}

}