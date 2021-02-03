import java.util.ArrayList;

public class Game {
	private boolean running = false;
	private int currentID = 0;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<Person> persons = new ArrayList<Person>();
	private Gui gui;
	Game(){
		setRunning(true);
		gui = new Gui();
		Update update = new Update(this);
		
		//Create and start the npcs
		persons.add(new Player("Player", 0, currentID));
		currentID++;
		persons.add(new Npc("Steffo", 0, currentID));

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

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public Gui getGui() {
		return gui;
	}
}
