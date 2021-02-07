
public class Item extends GameObject{

	Item(int type, int id, String name,boolean isMovable) {
		super(id, name);
		this.setMovable(isMovable);		
	}

}
