
public class Key extends GameObject{

	private int lockId;

	Key(int id, String name,int lockId) {
		super(id, name);
		this.lockId = lockId;
	}

	public int getLockId() {
		return lockId;
	}

}
