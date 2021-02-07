import java.util.Random;

public abstract class Person {
	private int currentRoom;
	private Inventory inventory;
	private String name;
	private boolean pause;
	private int id;

	
	Person(String name, int startRoom, int id){
		this.setName(name);
		this.setCurrentRoom(startRoom);
		this.setId(id);		
	}
	
	public final String toString() {
		return this.name;
	}
	
	public final boolean move(String direction) {
		if(direction.startsWith("l") && currentRoom >= 1) {
			currentRoom--;
			return true;
		}
		else if(direction.startsWith("r") && currentRoom <= 2){
			currentRoom++;
			return true;
		}
		else {
			return false;
		}
	}

	public final int getCurrentRoom() {
		return currentRoom;
	}

	public final void setCurrentRoom(int currentRoom) {
		this.currentRoom = currentRoom;
	}

	public final Inventory getInventory() {
		return inventory;
	}

	public final void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final boolean isPause() {
		return pause;
	}

	public final void setPause(boolean pause) {
		this.pause = pause;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	
}
