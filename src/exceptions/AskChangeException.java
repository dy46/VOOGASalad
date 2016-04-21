package exceptions;

import javafx.scene.control.TextInputDialog;

public class AskChangeException extends Exception{

	private boolean myResult;
	
	public AskChangeException(){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("WOMP EXCEPTION");
		dialog.setHeaderText("Change content?");
		String yesOrNo = "YES OR NO";
		dialog.setContentText(yesOrNo);
	}
	
	public boolean getResult() {
		return myResult;
	}
	
}