package game_data;

import com.thoughtworks.xstream.XStream;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import game_data.exceptions.SerializerException;

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
	
	public void saveElement(Object o) {
		
		File f = this.chooser.save(myNamesBundle.getString("chooseLocationMessage"));
		if (f != null) {
			XStream xstream = new XStream();
			String xml = xstream.toXML(o);
			try {
				FileWriter writer = new FileWriter(f);
				writer.write(xml);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				throw new SerializerException(myNamesBundle.getString("saveErrorMessage") + f.getAbsolutePath());
//				System.out.println(myNamesBundle.getString("saveErrorMessage") + f.getAbsolutePath());
			}
		}
		else {
//			System.out.println(myNamesBundle.getString("nullFileMessage"));
			throw new SerializerException(myNamesBundle.getString("nullFileMessage"));
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public T loadElement() {
//		try {
//			File f = chooser.chooseXML(myNamesBundle.getString("chooseFileMessage"));
//			if (f != null) {
//				XStream xstream = new XStream();
//				return (T) xstream.fromXML(f);
//			}
//			else {
//				System.out.println(myNamesBundle.getString("nullFileError"));
//				return null;
//			}
//		}
//		catch (ClassCastException e) {
//			System.out.println(myNamesBundle.getString("wrongFormatMessage"));
//			return null;
//		}
		File f = chooser.chooseXML(myNamesBundle.getString("chooseFileMessage"));
		if(f != null){
			XStream xstream = new XStream();
			Object o = xstream.fromXML(f);
			try{
				return ((T) o);
			}
			catch(ClassCastException e){
				throw new SerializerException(myNamesBundle.getString("wrongFormatMessage"));
			}
		}
		else{
			throw new SerializerException(myNamesBundle.getString("nullFileError"));
		}
	}

}