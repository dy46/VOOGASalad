package exceptions;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

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

}