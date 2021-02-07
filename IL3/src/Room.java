
public class Room {
	private String name;
	private Inventory invenory;
	
	Room(String name,int invSize){
		this.name = name;
		this.invenory = new Inventory(invSize);
	}
	
	
	public String showRoom() {
		
		
		return name;
	}
}
