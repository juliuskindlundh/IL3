import java.util.Random;

public class Npc extends Person implements Runnable{
	Npc(String name, int startRoom, int id) {
		super(name, startRoom, id);
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
