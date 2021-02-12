import java.util.ArrayList;

public class Factory {
	/*
	 * This class has a method called start that creates the starting conditions of the game
	 */
	
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
		game.getRooms().get(0).getInvenory().add(createGameObject(new Item("Bowling ball",id,true)));
		
		//box with stuff and key
		game.getRooms().get(0).getInvenory().add(createGameObject(new Container("Box",id,false, 5, true, createKey("Box key"))));
		((Container)(game.getRooms().get(0).getInvenory().getByName("Box"))).getInventoryForce().add(createGameObject(new Item("Bottle of fine scotch",id,true)));
		
		
		
		
		game.getRooms().get(0).getPersons().add(createPerson(new Player("Player",0,pid,this.game)));
		game.getRooms().get(0).getPersonById(0).getInventory().add(createGameObject(new Item("Fork",id,true)));;
		
		game.getRooms().get(0).getPersons().add(createPerson(new Npc("Jason",0,pid,this.game)));
		game.getRooms().get(0).getPersonById(1).getInventory().add(createGameObject(new Item("dog",id,true)));

		
		game.getRooms().add(new Room("Room 2"));
		game.getRooms().get(1).getPersons().add(createPerson(new Npc("Freddy",1,pid,this.game)));
		game.getRooms().get(1).getPersonById(2).getInventory().add(createGameObject(new Item("Hat",id,true)));
		game.getRooms().get(1).getInvenory().add(createGameObject(new Item("Gold coin",id,true)));
		game.getRooms().get(1).getInvenory().add(createGameObject(new Item("Carpet",id,true)));
		
		
		game.getRooms().add(new Room("Room 3"));
		game.getRooms().get(2).getPersons().add(createPerson(new Npc("Ture Sventon",2,pid,this.game)));
		game.getRooms().get(2).getPersonById(3).getInventory().add(createGameObject(new Item("Temla",id,true)));
		game.getRooms().get(2).getInvenory().add(createGameObject(new Item("Sofa",id,false)));
		game.getRooms().get(2).getInvenory().add(createGameObject(new Item("Underpants",id,true)));
		game.getRooms().get(2).getInvenory().add(keys.get(0));
		
		game.getRooms().add(new Room("Room 4"));
		game.getRooms().get(3).getInvenory().add(createGameObject(new Item("Flower pot XL",id,false)));
		game.getRooms().get(3).getInvenory().add(createGameObject(new Container("Door",id,false, 5, true, createKey("Door key"))));
		
		((Container)(game.getRooms().get(0).getInvenory().getByName("Box"))).getInventoryForce().add(keys.get(1));
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
