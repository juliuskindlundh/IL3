import java.util.Random;

public class Npc extends Person implements Runnable{
	
	private Random rng;
	Npc(String name, int startRoom, int id, Game game) {
		super(name, startRoom, id,game);
		this.setInventory(new Inventory(1));
		this.rng = new Random(System.currentTimeMillis()+id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println(this.getName()+" is starting...");
		long LT = 0;
		while(this.getGame().isRunning()) {
			long CT = System.currentTimeMillis();
			if(CT >= LT+250) {
				LT = CT;
				
				if(this.rng.nextInt(4) == 3) {
					this.move("rigth");
					System.out.println(this.getCurrentRoom());
				}
			}
			else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}	
}
