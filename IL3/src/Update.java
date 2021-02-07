
public class Update implements Runnable {

	private Game game;
	private String action;
	private String lastAction;
	private ActionHandler actionHandler;
	StringBuilder sb = new StringBuilder();
	Update(Game game){
		this.game = game;
		this.actionHandler = new ActionHandler(this.game,this);
	}
	//updates the gui ~30 time/s
	@Override
	public void run(){
		long LT = 0;
		long CT = System.currentTimeMillis();
		int updateTime = 33;

		while(game.isRunning()) {
			try {				
				CT = System.currentTimeMillis();
				if(CT - LT >= updateTime) {
					LT = CT;					
					updateGui();
					action = readGui();
					if(action != null && action != lastAction) {
						this.actionHandler.handleAction(action);
						lastAction = action;
					}
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
	
	private void updateInventory() {

	}
	
	private void updateRoom() {
		game.getGameObjects().getLock().lock();
		sb.append("Room "+((Person) this.game.getPersons().get(0)).getCurrentRoom()+"\n");
		for(Object i:game.getGameObjects().getArrayList()) {
			sb.append(((GameObject) i).toString()); // change this
			sb.append("\n");
		}
		this.game.getGui().setShowRoom(sb.toString());
		sb.delete(0, sb.length());
		game.getGameObjects().getLock().unlock();
		
	}
	
	private void updatePersons() {
		game.getPersons().getLock().lock();
		//update persons
		for(Object i:game.getPersons().getArrayList()) {
			if(((Person) i).getCurrentRoom() == ((Person) this.game.getPersons().get(0)).getCurrentRoom()) {
				sb.append(i.toString());
				sb.append("\n");
			}
		}
		this.game.getGui().setShowPersons(sb.toString());
		sb.delete(0, sb.length());
		game.getPersons().getLock().unlock();
	}

}
