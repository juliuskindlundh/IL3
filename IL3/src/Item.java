
public class Item extends GameObject{

	Item(String name, int id, boolean isMovable) {
		super(id, name);
		this.setMovable(isMovable);		
	}

}
