import java.util.ArrayList;

public class Game {
	private boolean running = false;
	private int currentID = 0;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private int currentRoom;
	private Gui gui;
	private Factory factory = new Factory(this);
	public boolean debug = true;
	Game(){
		setRunning(true);
		gui = new Gui();
		Update update = new Update(this);
		
		factory.start();
		setCurrentRoom(factory.getStartRoom());
		
		

		
		//Start updating the gui
		Thread t = new Thread(update);
		t.run();
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
}
