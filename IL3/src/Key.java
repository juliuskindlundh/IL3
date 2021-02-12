import java.io.Serializable;

public class Key extends GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
