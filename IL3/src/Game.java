import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game implements Serializable{
	private boolean running = false;
	private int currentID = 0;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private int currentRoom;
	private Gui gui;
	transient private Factory factory = new Factory(this);
	public boolean debug = true;
	private Lock lock = new ReentrantLock();
	private boolean loaded = false;
	
	Game(){
		this.start();
	}
	
	
	private void start() {
		setRunning(true);
		gui = new Gui();
		Update update = new Update(this);
		//if this is a new game (has not been saved) call the start method in factory to create all the gameobjects and persons that are supposed to be part of the game
		if(!loaded) {
			factory.start();
			setCurrentRoom(factory.getStartRoom());
			loaded = true;
		}
		this.startAllNpcs();
		Thread t = new Thread(update);
		t.start();
		
	}

	//Create and start threads for all npcs
	public void startAllNpcs() {
		for(Room r:this.getRooms()) {
			for(Person p:r.getPersons()) {
				if(p.getId()!=0) {
					Thread t = new Thread(((Npc)(p)));
					t.start();
				}				
			}
		}

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

	//save the current state of "game"
	public void serialize() {
		try {
			File f = new File("ser.ser");
			if(f.createNewFile()) {
				
			}
			FileOutputStream fileout = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fileout);

			out.writeObject(this);
			
			out.close();
			fileout.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//Load a saved instance of game and start it
	//close the current instance
	public void deserialize() {
	      try {
				File f = new File("ser.ser");
				if(f.createNewFile()) {
					
				}
		         FileInputStream fileIn = new FileInputStream(f);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		        
		         Game g = (Game) in.readObject();
		         g.start();
		         this.getGui().dispose();
		         
		         in.close();
		         fileIn.close();
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		
	}


	public void end() {
		this.getGui().dispose();
		
	}
}
