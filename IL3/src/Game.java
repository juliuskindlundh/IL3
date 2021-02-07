import java.util.ArrayList;

public class Game {
	private boolean running = false;
	private int currentID = 0;
	private ArrayListLock gameObjects = new ArrayListLock("GameObject");
	private ArrayListLock persons = new ArrayListLock("Person");
	private Room room;
	private Gui gui;
	Game(){
		setRunning(true);
		gui = new Gui();
		Update update = new Update(this);
		

		
		makeStuff();

		
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

	public ArrayListLock getGameObjects() {
		return gameObjects;
	}

	public ArrayListLock getPersons() {
		return persons;
	}

	public Gui getGui() {
		return gui;
	}
	
	private void makeStuff() {
		persons.getLock().lock();
		persons.getArrayList().add(new Player("Player", 0, currentID));
		currentID++;
		persons.getArrayList().add(new Npc("npc_1", 0, currentID));
		currentID++;
		persons.getLock().unlock();
		
		gameObjects.getLock().lock();
		gameObjects.add(new Container("Desk", currentID, false, 10, false, 0));
		currentID++;
		gameObjects.getLock().unlock();
	}
}
