import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {
	private boolean running = false;
	private int currentID = 0;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private int currentRoom;
	private Gui gui;
	private Factory factory = new Factory(this);
	public boolean debug = true;
	private Lock lock = new ReentrantLock();
	Game(){
		setRunning(true);
		gui = new Gui();
		Update update = new Update(this);
		factory.start();
		setCurrentRoom(factory.getStartRoom());
		this.startAllNpcs();
		Thread t = new Thread(update);
		System.out.println("starting update");
		t.start();;
	}
	
	public void startAllNpcs() {
		for(Room r:this.getRooms()) {
			for(Person p:r.getPersons()) {
				if(p.getId()!=0) {
					Thread t = new Thread(((Npc)(p)));
					t.start();
				}				
			}
		}
		System.out.println("all npcs running");
	}
		
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getCurrentID() {
		return currentID;
	}

	public Gui getGui() {
		return gui;
	}
	

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public int getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(int currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Lock getLock() {
		return lock;
	}
}
