import java.io.Serializable;

public class Container extends GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Inventory inventory;
	private boolean isLocked;
	private int lockId;
	
	Container(String name,int id,boolean isMovable,int size,boolean isLocked,int lockId) {
		super(id, name);
		this.setIsContainer(true);
		this.setMovable(isMovable);
		this.inventory = new Inventory(size);
		this.setLocked(isLocked);
		this.lockId = lockId;
	}
	
	// Generates a string describing the container
	@Override
	public String toString() {
		String s;
		s = this.getName() + " (container,";
		if(this.isLocked) {
			s = s + "locked)";
		}
		else {
			s = s + "unlocked)";
		}
		return s;
	}
	
	//attempts to unlock the container
	public void unlock(Key k) {
		if(k.getLockId() == lockId) {
			isLocked = false;
		}
		else {
			//do nothing
		}
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Inventory getInventory() {
		if(isLocked) {
			return null;
		}
		else {
			return inventory;
		}
	}
	
	public Inventory getInventoryForce() {
		return inventory;
	}
	
}
