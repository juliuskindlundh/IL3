import java.io.Serializable;
import java.util.Random;

public abstract class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentRoom;
	private Inventory inventory;
	private Inventory targetInventory;
	private String name;
	private boolean pause;
	private int id;
	private Game game;
	
	
	Person(String name, int startRoom, int id,Game game){
		this.setName(name);
		this.setCurrentRoom(startRoom);
		this.setId(id);
		this.game = game;
		this.setTargetInventory(this.game.getRooms().get(this.getCurrentRoom()).getInvenory());
	}
	
	public final String toString() {
		return this.name;
	}
	
	//moves a person left or right by adding them to the room they are going to and removing them from the room they are leaving
	public boolean move(String direction) {
		if(direction.equals("left") && currentRoom >= 1) {
			setCurrentRoom(getCurrentRoom() - 1);
			this.getGame().getRooms().get(this.getCurrentRoom()).getPersons().add(this);
			this.game.getRooms().get(this.getCurrentRoom() + 1).removePersonById(this.getId());
			this.setTargetInventory(this.game.getRooms().get(this.getCurrentRoom()).getInvenory());
			return true;
		}
		else if(direction.equals("right") && currentRoom <= 2){
			setCurrentRoom(getCurrentRoom() + 1);
			this.getGame().getRooms().get(this.getCurrentRoom()).getPersons().add(this);
			this.game.getRooms().get(this.getCurrentRoom() - 1).removePersonById(this.getId());
			this.setTargetInventory(this.game.getRooms().get(this.getCurrentRoom()).getInvenory());
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
	
	public final Game getGame() {
		return this.game;
	}

	public Inventory getTargetInventory() {
		return targetInventory;
	}

	public void setTargetInventory(Inventory targetInventory) {
		this.targetInventory = targetInventory;
	}

	
}
