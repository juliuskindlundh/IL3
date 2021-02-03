
public class Update implements Runnable {

	private Game game;
	private String action;
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
		//update persons
		for(Person i:game.getPersons()) {
			sb.append(i.toString());
			sb.append("\n");
		}
		this.game.getGui().setShowPersons(sb.toString());
		sb.delete(0, sb.length());
	}

}
