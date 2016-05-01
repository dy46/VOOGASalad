package game_data;

import java.io.File;

public interface IDataConverter<T> {
	
	/**
	 * @return File that the data was saved to
	 */
	public File saveElement(T data);
	
	/**
	 * @return XML file the user selected
	 */
	public File chooseXMLFile();
	
	/**
	 * @return The object loaded from file
	 */
	public T loadFromFile(File file);
	
	public default T loadElement() {
		return loadFromFile(chooseXMLFile());
	}
	
}
