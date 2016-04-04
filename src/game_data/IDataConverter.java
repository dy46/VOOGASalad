package game_data;

public interface IDataConverter<T> {
	
	public void saveElement(T gameElement, String filePath);
	
	public T loadElement(String filePath);
	
	
}
