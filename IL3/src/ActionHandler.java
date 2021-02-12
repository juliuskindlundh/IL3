import java.io.Serializable;

public class ActionHandler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game game;
	Update update;
	
	ActionHandler(Game game, Update update){
		this.game = game;
		this.update = update;
	}
	
	//Go though all possible action and check if the player wanted to do any of them
	public void handleAction(String action) {
		handelMove(action);
		handleShowInv(action);
		handlePickUp(action);
		handlePutDown(action);
		handleOpen(action);
		handleSaveLoad(action);
	}
	
	//Moves player one room left or right
	private void handelMove(String action) {
		if(action.equalsIgnoreCase("move left") || action.equalsIgnoreCase("ml")) {
			this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).move("left");
		}
		else if(action.equalsIgnoreCase("move right") || action.equalsIgnoreCase("mr")) {
			this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).move("right");
		}
		
	}
	
	//Calle the save or load function in "game"
	private void handleSaveLoad(String action) {
		if(action.equalsIgnoreCase("save")) {
			this.game.serialize();
		}
		if(action.equalsIgnoreCase("load")) {
			this.game.deserialize();
		}
		
	}

	//attempts to open a container
	private void handleOpen(String action) {
		String targetStr;
		String usingStr;

		if(action.startsWith("open") && action.contains("with")) {
			//Break down the input and attempt to get the two gameobjects the player is interacting with
			action = action.substring(4);		
			int temp = action.lastIndexOf("with");
			targetStr = action.substring(1,temp-1);
			usingStr = action.substring(temp+5,action.length());
			
			GameObject target = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().getByName(targetStr);
			GameObject using = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().getByName(usingStr);
			
			if(target != null && using != null) {
				//if the target is a container and the "using" is a key attempt to open the container
				//if successful set the target inventory of player to the containers inventory
				//if we unlcok the door end the game
				if(target.isContainer() && using.isKey()) {
					Container lock = (Container)target;
					Key key = (Key)using;
					lock.unlock(key);
					if(!lock.isLocked()) {
						this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).setTargetInventory(lock.getInventory());
						if(target.getName().equalsIgnoreCase("door")) {
							this.game.end();
						}
					}
					
				}
			}
		}
		
	}
	
	//attempts to put down a gameobject into the targeted inventory 
	private void handlePutDown(String action) {
		if(action.contains("put down")) {
			String target = action.substring(9);
			GameObject targetGameObject = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().removeByName(target);
			if(targetGameObject != null) {
				this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().add(targetGameObject);
			}
		}
		
	}

	//attempts to pick up a gameobject
	private void handlePickUp(String action) {
		if(action.contains("pick up")) {
			String target = action.substring(8);
			GameObject targetGameObject = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().getByName(target);
			if(targetGameObject != null) {
				if(targetGameObject.isMovable()) {
					 this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().removeByName(target);
					this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().add(targetGameObject);
				}
			}
		}
		
	}
	
	// attempts to put the contents of a objects inventory on display in the gui
	private void handleShowInv(String action) {
		String outputString = "";
		if(action.equalsIgnoreCase("show inv") || action.equalsIgnoreCase("show inventory") || action.equalsIgnoreCase("si")) {			
			outputString = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().toString();			
		}
		else if(action.contains("show inv") || action.contains("show inventory") || action.contains("si")) {
			int temp = action.lastIndexOf(" ");
			String target = action.substring(temp+1);
			GameObject targetGameObject = this.game.getRooms().get(this.game.getCurrentRoom()).getInvenory().getByName(target);
			if(targetGameObject != null) {
				if(!targetGameObject.isContainer()) {
					this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).setTargetInventory(null);
				}
				else if(!((Container)targetGameObject).isLocked()){
					this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).setTargetInventory(((Container)targetGameObject).getInventory());
					outputString = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().toString();
				}
			}
			
			if(target.equalsIgnoreCase("room")) {
				this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).setTargetInventory(this.game.getRooms().get(this.game.getCurrentRoom()).getInvenory());
				outputString = this.game.getRooms().get(this.game.getCurrentRoom()).getInvenory().toString();
			}
			
			if(target.equalsIgnoreCase("player")) {
				this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).setTargetInventory(this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory());
				outputString = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().toString();
			}
			
		}
		this.game.getGui().setShowInventory(outputString);		
	}
}
