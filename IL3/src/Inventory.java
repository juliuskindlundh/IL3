import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameObject[] inventory;
	private int nrOfItems = 0;
	private int cnt = 0;
	boolean added = false;
	private GameObject returnObj;
	
	Inventory(int size){
		inventory = new GameObject[size];
	}
	
	//removes an object from this inventory and returns it (by its id)
	public GameObject remove(int id) {
		returnObj = null;
		Arrays.stream(inventory).forEach(a->{
			if(a != null) {
				if(a.getId() == id) {
					returnObj = a;
					inventory[cnt] = null;
				}
			}
			cnt++;
		});
		cnt = 0;
		nrOfItems--;
		return returnObj;		
	}
	
	//removes an object from this inventory and returns it (by its name)
	public GameObject removeByName(String name) {
		returnObj = null;
		Arrays.stream(inventory).forEach(a->{
			if(a != null) {
				if(a.getName().equalsIgnoreCase(name)) {
					returnObj = a;
					inventory[cnt] = null;
				}
			}
			cnt++;
		});
		cnt = 0;
		nrOfItems--;
		return returnObj;		
	}
	
	//Adds an object to the first empty spot(if there is space)
	public void add(GameObject go) {				
		if(this.hasSpace()) {
			Arrays.stream(this.inventory).forEach(a->{
				if(a == null && !added) {
					added = true;
					nrOfItems++;
					inventory[cnt] = go;
				}
				cnt++;
			});
			added = false;
			cnt = 0;
		}
	}
	
	//Create a string describing the contents of  this inventory
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Arrays.stream(inventory).forEach(a->{
			if(a != null) {
				sb.append(a.toString()+"\n");
			}
		});
		return sb.toString();
	}
	
	public boolean hasSpace() {
		if(nrOfItems < inventory.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//returns the first (!= null) object in this inventory
	public String getFirst() {
		if(this.nrOfItems > 0) {
			for(int i = 0; i < this.inventory.length; i++) {
				if(this.inventory[i] != null) {
					return this.inventory[i].toString();
				}
			}
		}
		return "nothing";
	}
	
	public int getNrOfItems() {
		return nrOfItems;
	}

	//Get a gameobjct by its name
	public GameObject getByName(String target) {
		int i = 0;
		while(i < this.inventory.length) {
			if(this.inventory[i] != null) {
				if(this.inventory[i].getName().equalsIgnoreCase(target)) {
					return this.inventory[i];
				}
			}
			i++;
		}
		return null;		
	}
	
	public GameObject[] getInventoryArray(){
		return inventory;	
	}
	
	//remove and return a game object by index
	public GameObject removeByIndex(int i) {
		returnObj = null;
		Arrays.stream(inventory).forEach(a->{
				if(cnt == i) {
					returnObj = a;
					inventory[cnt] = null;
				}
			cnt++;
		});
		cnt = 0;
		nrOfItems--;
		return returnObj;
	}
	

}
