
public class Key extends GameObject{

	private int lockId;

	Key(int id, String name,int lockId) {
		super(id, name);
		this.lockId = lockId;
		this.setKey(true);
		this.setMovable(true);
	}

	public int getLockId() {
		return lockId;
	}

}
