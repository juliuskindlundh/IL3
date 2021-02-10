import java.util.ArrayList;

public class Factory {
	
	private int pid = 0;
	private int id = 100;
	private Game game;
	private int startRoom = 0;
	private int lockId = 1000;
	private ArrayList<Key> keys = new ArrayList<Key>();
	
	Factory(Game game){
		this.game = game;
	}
	
	public void start() {
		game.getRooms().add(new Room("Room 1"));
		game.getRooms().get(0).getInvenory().add(createGameObject(new Item("Flower pot",id,true)));
		game.getRooms().get(0).getInvenory().add(createGameObject(new Item("Underpants",id,true)));
		
		//box with stuff and key
		game.getRooms().get(0).getInvenory().add(createGameObject(new Container("Box",id,true, 5, true, createKey("Box key"))));
		((Container)(game.getRooms().get(0).getInvenory().getByName("Box"))).getInventoryForce().add(createGameObject(new Item("Bottle of fine scotch",id,true)));
		game.getRooms().get(0).getInvenory().add(keys.get(0));
		
		
		game.getRooms().get(0).getPersons().add(createPerson(new Player("Player",0,pid,this.game)));
		game.getRooms().get(0).getPersonById(0).getInventory().add(createGameObject(new Item("Fork",id,true)));;
		
		game.getRooms().get(0).getPersons().add(createPerson(new Npc("Michigan J Frog",0,pid,this.game)));
		
		
		game.getRooms().add(new Room("Room 2"));
		game.getRooms().add(new Room("Room 3"));
		game.getRooms().add(new Room("Room 4"));
	}
	
	
	private int createKey(String keyName) {
		keys.add(new Key(id,keyName,lockId));
		return lockId;
		
	}

	public GameObject createGameObject(GameObject go) {
		id++;
		return go;
	}
	
	public Person createPerson(Person p) {
		pid++;
		return p;
	}

	public int getStartRoom() {
		return startRoom;
	}
	
}
