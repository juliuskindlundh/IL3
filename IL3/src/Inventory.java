import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
	
	private GameObject[] inventory;
	private int nrOfItems = 0;
	private Lock lock = new ReentrantLock();
	private int last = 0;
	
	Inventory(int size){
		inventory = new GameObject[size];
	}
	
	public GameObject remove(int id) {
		Arrays.stream(inventory).forEach(a->{
			if(a.getId() == id) {
				a = null;
			}
		});
		nrOfItems--;
		return null;		
	}
	
	public void add(GameObject go) {		
		Arrays.stream(this.inventory).forEach(a->{
			if(a == null && nrOfItems == last) {
				nrOfItems++;
				inventory[last] = go;
			}
		});
		last = nrOfItems;
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
	

}
