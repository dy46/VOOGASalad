package exceptions;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class WompException extends Exception{

	private String myMessage;
	
	public WompException(String message) {
		super(message);
		this.myMessage = message;
	}
	
	public void displayMessage(){
		Dialog alert = new Dialog();
		alert.setTitle("WOMP EXCEPTION");
		alert.setHeaderText("ERROR: " + myMessage);
		ButtonType buttonTypeOk = new ButtonType("Okay");
		alert.getDialogPane().getButtonTypes().add(buttonTypeOk);
		DialogPane dialogPane = alert.getDialogPane();
		alert.showAndWait();
	}

}