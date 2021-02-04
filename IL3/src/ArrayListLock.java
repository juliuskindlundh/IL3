import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayListLock {
	private ArrayList arrList;
	private Lock lock = new ReentrantLock();
	
	ArrayListLock(String type){
		if(type.equals("GameObject")) {
			arrList = new ArrayList<GameObject>();
		}
		else if(type.equals("Person")) {
			arrList = new ArrayList<Person>();
		}
	}

	public ArrayList getArrayList() {
		return arrList;
	}
	
	public Object get(int i) {
		return arrList.get(i);
	}
	
	public void add(Object o) {
		arrList.add(o);
	}
	
	public Lock getLock() {
		return lock;
	}
	
}
