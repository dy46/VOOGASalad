package auth_environment.view;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: va
 *
 * This Interface provides a base for any display/window used for creating new Game Elements.
 */

public interface IElementView {
	public void show();

	public GameElement build(); // instantiates the Game Element with the new Properties set by the Developer
}
