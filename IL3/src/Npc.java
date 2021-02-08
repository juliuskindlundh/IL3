import java.util.Random;

public class Npc extends Person implements Runnable{
	Npc(String name, int startRoom, int id, Game game) {
		super(name, startRoom, id,game);
		this.setInventory(new Inventory(1));
		// TODO Auto-generated constructor stub
	}

	private String[] phrases;
	private Random rng;
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public String[] getPhrases() {
		return phrases;
	}

	public void setPhrases(String[] phrases) {
		this.phrases = phrases;
	}
	
	
	
}
