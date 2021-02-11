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
				this.getGame().getLock().lock();
				setRunning(this.getGame().isRunning());
				int next = this.rng.nextInt(100);
				if(next < 10) {
					if(next % 2 == 0) {
						this.move("left");
						System.out.println("npc move left");
					}
					else {
						this.move("rigth");
						System.out.println("npc move rigth");
					}
				}
				else if(next < 30){
					if(this.getInventory().getNrOfItems() > 0) {
						this.putDown();
						System.out.println("npc put down");
					}
					else{
						this.pickUp();
						System.out.println("npc pick up");
					}

				}
				else{
					//do nothing
				}				
				this.getGame().getLock().unlock();
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

	private void pickUp() {
		GameObject target = null;
		for(int i = 0; i < this.getTargetInventory().getInventoryArray().length;i++) {
			if(this.getTargetInventory().getInventoryArray()[i] != null) {
				if(this.rng.nextInt(this.getTargetInventory().getNrOfItems()) == 0 && this.getTargetInventory().getInventoryArray()[i].isMovable()) {
					target = this.getTargetInventory().removeByName(this.getTargetInventory().getInventoryArray()[i].getName());
					this.getInventory().add(target);
					return;
				}
			}
		}		
	}

	private void putDown() {
		this.getTargetInventory().add(this.getInventory().removeByIndex(0));
		
	}

	private boolean isRunning() {
		// TODO Auto-generated method stub
		return this.running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
