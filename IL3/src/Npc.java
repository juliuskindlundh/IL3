import java.io.Serializable;
import java.util.Random;

public class Npc extends Person implements Runnable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		long LT = 0;
		while(this.isRunning()) {
			long CT = System.currentTimeMillis();
			//Then npc will consider taking a action very 1000ms when it does so i acquires the lock on game
			if(CT >= LT+1000) {
				LT = CT;
				this.getGame().getLock().lock();
				setRunning(this.getGame().isRunning());
				//Generate a number 0-100 to determine what to do
				int next = this.rng.nextInt(100);			
				if(next < 10) {
					if(next % 2 == 0) {
						this.move("left");
					}
					else {
						this.move("right");
					}
				}
				else if(next < 30){
					if(this.getInventory().getNrOfItems() > 0) {
						this.putDown();
					}
					else{
						this.pickUp();
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

	//Attempts to pick up an item from the targeted inventory
	//There is a chance to not pick something up
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

	//removes an objcet from this.invntory and puts it in target inventory
	private void putDown() {
		if(this.getTargetInventory().hasSpace()) {
			this.getTargetInventory().add(this.getInventory().removeByIndex(0));
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
