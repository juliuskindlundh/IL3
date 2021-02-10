import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
	
	private GameObject[] inventory;
	private int nrOfItems = 0;
	private Lock lock = new ReentrantLock();
	private int cnt = 0;
	boolean added = false;
	private GameObject returnObj;
	
	Inventory(int size){
		inventory = new GameObject[size];
	}
	
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
	
	public String getFirst() {
		if(this.nrOfItems > 0) {
			return this.inventory[0].toString();
		}
		else {
			return "nothing";
		}
	}
	
	public int getNrOfItems() {
		return nrOfItems;
	}

	public Lock getLock() {
		return lock;
	}

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
	

}
