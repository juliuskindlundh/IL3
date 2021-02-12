import java.io.Serializable;

public class Item extends GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Item(String name, int id, boolean isMovable) {
		super(id, name);
		this.setMovable(isMovable);		
	}

}
