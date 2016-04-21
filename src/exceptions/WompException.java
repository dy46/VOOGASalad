package exceptions;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

public class WompException extends Exception{
	
	public WompException(String message) {
		super(message);
		@SuppressWarnings("rawtypes")
		Dialog alert = new Dialog();
		alert.setTitle("WOMP EXCEPTION");
		alert.setHeaderText("ERROR: " + message);
		ButtonType buttonTypeOk = new ButtonType("Okay");
		alert.getDialogPane().getButtonTypes().add(buttonTypeOk);
		DialogPane dialogPane = alert.getDialogPane();
		alert.showAndWait();
	}
	
	public WompException(String message, int numArguments){
		super(message);
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Instructions");
		dialog.setHeaderText(message);
		dialog.setContentText("Please enter new argument(s) (separated by space if more than one):");
//		unresolvedException = dialog.showAndWait();
	}
	
}