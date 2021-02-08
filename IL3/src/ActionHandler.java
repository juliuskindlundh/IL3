
public class ActionHandler {
	Game game;
	Update update;
	
	ActionHandler(Game game, Update update){
		this.game = game;
		this.update = update;
	}
	
	public void handleAction(String action) {
		if(action.equalsIgnoreCase("move left") || action.equalsIgnoreCase("ml")) {
			this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).move("left");
		}
		else if(action.equalsIgnoreCase("move rigth") || action.equalsIgnoreCase("mr")) {
			this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).move("rigth");
		}
		
		handleShowInv(action);
	}

	private void handleShowInv(String action) {
		if(action.equalsIgnoreCase("show inv") || action.equalsIgnoreCase("show inventory") || action.equalsIgnoreCase("si")) {
			int startOfTarget = action.lastIndexOf(" ");
			if(true) {
				this.game.getGui().setShowInventory(this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().toString());
				System.out.println(this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().toString());
			}
		}
		
	}
}
