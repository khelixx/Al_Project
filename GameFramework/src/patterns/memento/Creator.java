package patterns.memento;

import game_entities.Player;

public class Creator {
	
	   private Player state;

	   public void setState(Player player){
	      this.state = player;
	   }

	   public Player getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public Player getStateFromMemento(Memento Memento){
	      return state = Memento.getState();
	   }
}
