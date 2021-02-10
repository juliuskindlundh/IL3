import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Room {
	private String name;
	private Inventory invenory;
	private ArrayList<Person> persons;
	private int roomSize = 100;
	
	Room(String name){
		this.name = name;
		this.invenory = new Inventory(roomSize);
		this.persons = new ArrayList<Person>();
	}
	
	
	public String showRoom() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name+"\n");
		sb.append(this.invenory.toString());
		return sb.toString();
	}


	public String getName() {
		return name;
	}


	public Inventory getInvenory() {
		return invenory;
	}


	public int getRoomSize() {
		return roomSize;
	}


	public ArrayList<Person> getPersons() {
		return persons;
	}
	
	public Person getPersonById(int id) {
		int i = 0;
		while(i < this.persons.size()) {
			if(this.persons.get(i).getId() == id) {
				return this.persons.get(i);
			}
			i++;
		}
		return null;
	}

	public void removePersonById(int id) {
		int i = 0;
		while(i < this.persons.size()) {
			if(this.persons.get(i).getId() == id) {
				this.persons.remove(i);
			}
			i++;
		}
		
	}


	public String showPersons() {
		StringBuilder sb = new StringBuilder();
		sb.append("Persons in "+this.name+"\n");
		for(int i = 0; i< persons.size(); i++) {
			sb.append(this.persons.get(i).toString()+" carrying "+this.persons.get(i).getInventory().getFirst()+"\n");
		}
		return sb.toString();
	}
}
