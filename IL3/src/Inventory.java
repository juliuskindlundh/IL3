import java.util.Arrays;

public class Inventory {
	
	
	private GameObject[] inventory;
	private int nrOfItems = 0;
	
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
		Arrays.stream(inventory).forEach(a->{
			if(a == null) {
				a = go;
			}
		});
		nrOfItems++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Arrays.stream(inventory).forEach(a->sb.append(a.toString()+"\n"));
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
	

}
