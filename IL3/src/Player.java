
public class Player extends Person{
		
	Player(String name, int startRoom, int id,Game game) {
		super(name, startRoom, id,game);
		this.setInventory(new Inventory(5));
		this.setTargetInventory(this.getGame().getRooms().get(this.getCurrentRoom()).getInvenory());
	}
	
	@Override
	public final boolean move(String direction) {
		System.out.println("move");
		if(direction.startsWith("l") && getCurrentRoom() >= 1) {
			setCurrentRoom(getCurrentRoom() - 1);
			this.getGame().setCurrentRoom(getCurrentRoom());
			this.getGame().getRooms().get(this.getGame().getCurrentRoom()).getPersons().add(this);
			this.getGame().getRooms().get(this.getGame().getCurrentRoom() + 1).removePersonById(this.getId());
			this.setTargetInventory(this.getGame().getRooms().get(getCurrentRoom()).getInvenory());
			return true;
		}
		else if(direction.startsWith("r") && getCurrentRoom() <= 2){
			setCurrentRoom(getCurrentRoom() + 1);
			this.getGame().setCurrentRoom(getCurrentRoom());
			this.getGame().getRooms().get(this.getGame().getCurrentRoom()).getPersons().add(this);
			this.getGame().getRooms().get(this.getGame().getCurrentRoom() - 1).removePersonById(this.getId());
			this.setTargetInventory(this.getGame().getRooms().get(getCurrentRoom()).getInvenory());
			return true;
		}
		else {
			return false;
		}
	}
	


}
