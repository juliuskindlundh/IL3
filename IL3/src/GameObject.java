
public abstract class GameObject {
	
	private int id;
	private String name;
	private boolean isContainer = false;
	private boolean isMovable = false;
	private boolean isKey = false;
	private String description;
	
	GameObject(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return name;		
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public boolean isContainer() {
		return isContainer;
	}

	public void setIsContainer(boolean isContainer) {
		this.isContainer = isContainer;
	}

	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public String getDescription() {
		if(description.equals(null)) {
			return name;
		}
		else {
			return description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	

}
