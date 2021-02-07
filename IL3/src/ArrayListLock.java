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
	
	public Person getPersonById(int id) {
		for(int i = 0; i < arrList.size();i++) {
			if(((Person)arrList.get(i)).getId() == id) {
				return ((Person)arrList.get(i));
			}
		}
		return null;
	}
	
	public GameObject getGameObjectById(int id) {
		for(int i = 0; i < arrList.size();i++) {
			if(((GameObject)arrList.get(i)).getId() == id) {
				return ((GameObject)arrList.get(i));
			}
		}
		return null;
	}
	
	public Person removePersonById(int id){
		for(int i = 0; i < arrList.size();i++) {
			if(((Person)arrList.get(i)).getId() == id) {
				Person p = ((Person)arrList.get(i));
				arrList.remove(i);
				return p;
			}
		}
		return null;
	}
	
	public GameObject removeGameObjectById(int id) {
		for(int i = 0; i < arrList.size();i++) {
			if(((GameObject)arrList.get(i)).getId() == id) {
				GameObject go = ((GameObject)arrList.get(i));
				arrList.remove(i);
				return go;
			}
		}
		return null;
	}
	
	public void add(Object o) {
		arrList.add(o);
	}
	
	public Lock getLock() {
		return lock;
	}
	
}
