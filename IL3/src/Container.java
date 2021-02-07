
public class Container extends GameObject{
	
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
	
	public void unlock(Key k) {
		if(k.getId() == lockId) {
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
	
}
