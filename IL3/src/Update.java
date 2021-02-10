
public class Update implements Runnable {

	private Game game;
	private String action;
	private String lastAction;
	private ActionHandler actionHandler;
	private boolean running;
	StringBuilder sb = new StringBuilder();
	Update(Game game){
		this.game = game;
		this.actionHandler = new ActionHandler(this.game,this);
	}
	//updates the gui ~30 time/s
	@Override
	public void run(){
		this.game.getLock().lock();
		this.setRunning(this.game.isRunning());
		long LT = 0;
		long CT = System.currentTimeMillis();
		int updateTime = 33;
		this.game.getGui().setShowInventory(this.game.getRooms().get(this.game.getCurrentRoom()).getInvenory().toString());		
		this.game.getLock().unlock();
		while(this.isRunning()) {
			try {				
				CT = System.currentTimeMillis();
				if(CT - LT >= updateTime) {
					this.game.getLock().lock();
					this.setRunning(this.game.isRunning());
					LT = CT;
					updateGui();
					action = readGui();
					if(action != null && action != lastAction) {
						this.actionHandler.handleAction(action);
						lastAction = action;
					}
					this.game.getLock().unlock();
				}
				else {
					Thread.sleep(1);
				}				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}
	
	private String readGui() {
		return this.game.getGui().getCommand();
	}
	
	private void updateGui() {
		updatePersons();
		updateRoom();
		updateInventory();
	}
	
	private void updatePersons() {
		this.game.getGui().setShowPersons(this.game.getRooms().get(this.game.getCurrentRoom()).showPersons());
	}
	
	private void updateRoom() {
		this.game.getGui().setShowRoom(this.game.getRooms().get(this.game.getCurrentRoom()).showRoom());
	}
	
	private void updateInventory() {
		this.game.getGui().setShowInventory(this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().toString());
	}
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}

}
