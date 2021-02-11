
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
		handlePickUp(action);
		handlePutDown(action);
		handleOpen(action);
	}

	//open "target1" with "target2"
	private void handleOpen(String action) {
		String targetStr;
		String usingStr;
		if(action.startsWith("open")) {
			action = action.substring(4);
			
			int temp = action.lastIndexOf("with");
			targetStr = action.substring(1,temp-1);
			usingStr = action.substring(temp+5,action.length());
			
			GameObject target = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().getByName(targetStr);
			GameObject using = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().getByName(usingStr);
			System.out.println(targetStr+"asdf"+target);
			System.out.println(usingStr+"asdf"+using);
			if(target.isContainer() && using.isKey()) {
				Container lock = (Container)target;
				Key key = (Key)using;
				lock.unlock(key);
				if(!lock.isLocked()) {
					this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).setTargetInventory(lock.getInventory());
				}
				
			}
			
			
			System.out.println(targetStr);
			System.out.println(usingStr);
		}
		
	}

	private void handlePutDown(String action) {
		if(action.contains("put down")) {
			String target = action.substring(9);
			System.out.println("put down "+ target);
			GameObject targetGameObject = this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getInventory().removeByName(target);
			if(targetGameObject != null) {
				this.game.getRooms().get(this.game.getCurrentRoom()).getPersonById(0).getTargetInventory().add(targetGameObject);
			}
		}
		
	}

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

	private void handleShowInv(String action) {
		String outputString = ""; //this.game.getRooms().get(this.game.getCurrentRoom()).getInvenory().toString();
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
