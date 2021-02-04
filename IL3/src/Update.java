
public class Update implements Runnable {

	private Game game;
	private String action;
	private String lastAction;
	StringBuilder sb = new StringBuilder();
	Update(Game game){
		this.game = game;
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
						game.getPersons().getLock().lock();
						game.getPersons().add(new Npc(action,0,0));
						game.getPersons().getLock().unlock();
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
		game.getPersons().getLock().lock();
		//update persons
		System.out.println(game.getPersons().getArrayList().size());
		for(Object i:game.getPersons().getArrayList()) {
			sb.append(i.toString());
			sb.append("\n");
		}
		this.game.getGui().setShowPersons(sb.toString());
		sb.delete(0, sb.length());
		game.getPersons().getLock().unlock();
	}

}
