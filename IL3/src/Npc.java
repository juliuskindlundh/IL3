import java.util.Random;

public class Npc extends Person implements Runnable{
	
	private Random rng;
	private boolean running;
	Npc(String name, int startRoom, int id, Game game) {
		super(name, startRoom, id,game);
		this.setInventory(new Inventory(1));
		this.rng = new Random(System.currentTimeMillis()+id);
		this.running = true;
	}

	@Override
	public void run() {
		System.out.println(this.getName()+" is starting...");
		long LT = 0;
		while(this.isRunning()) {
			long CT = System.currentTimeMillis();
			if(CT >= LT+250) {
				LT = CT;
				if(this.rng.nextInt(4) == 3) {
					this.getGame().getLock().lock();
					setRunning(this.getGame().isRunning());
					this.move("rigth");
					this.getGame().getLock().unlock();
					System.out.println("npc unlock");
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

	private boolean isRunning() {
		// TODO Auto-generated method stub
		return this.running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}	
}
