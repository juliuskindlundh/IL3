
public class ActionHandler {
	Game game;
	Update update;
	
	ActionHandler(Game game, Update update){
		this.game = game;
		this.update = update;
	}
	
	public void handleAction(String action) {
		if(action.equals("move left")) {
			((Person) this.game.getPersons().getArrayList().get(0)).move("left");
		}
		if(action.equals("move rigth")) {
			((Person) this.game.getPersons().getArrayList().get(0)).move("rigth");
		}
	}
}
