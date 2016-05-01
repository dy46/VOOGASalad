package game_data;

import com.thoughtworks.xstream.XStream;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import exceptions.WompException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class Serializer<T> implements IDataConverter<T> {
	
	private FileChooserDelegate chooser;
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public Serializer() {
		chooser = new FileChooserDelegate(); 
	}
	
	public File saveElement(Object o) {
		File f = this.chooser.save(myNamesBundle.getString("chooseLocationMessage"));
		if (f != null) {
			XStream xstream = new XStream();
			String xml = xstream.toXML(o);
			try {
				FileWriter writer = new FileWriter(f);
				writer.write(xml);
				writer.flush();
				writer.close();
				return f;
			} catch (IOException e) {
				new WompException(myNamesBundle.getString("saveErrorMessage")).displayMessage();
			}
		}
		else {
			new WompException(myNamesBundle.getString("nullFileMessage")).displayMessage();;
		}
		return null;
	}
	
	public File chooseXMLFile() {
		return chooser.chooseXML(myNamesBundle.getString("chooseFileMessage"));
	}
	
	@SuppressWarnings("unchecked")
	public T loadFromFile(File file) {
		if(file != null){
			try{
				XStream xstream = new XStream();
				return (T) xstream.fromXML(file);
			}
			catch(ClassCastException e){
				new WompException(myNamesBundle.getString("wrongFormatMessage")).displayMessage();
			}
		}
		else{
			new WompException(myNamesBundle.getString("nullFileMessage")).displayMessage();
		}
		return null;
	}

}