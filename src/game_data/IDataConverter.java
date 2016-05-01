package game_data;

import java.io.File;

public interface IDataConverter<T> {
	
	/**
	 * Returns a string representing the file that the data was saved to.
	 */
	public File saveElement(T data);
	
	public File chooseXMLFile();
	
	public T loadFromFile(File file);
	
	public default T loadElement() {
		return loadFromFile(chooseXMLFile());
	}
	
}
