package game_data;

public interface IDataConverter<T> {
	
	public void saveElement(T gameElement);
	
	public T loadElement();
	
	
}
