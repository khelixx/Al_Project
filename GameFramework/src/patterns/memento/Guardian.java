package patterns.memento;

import java.util.LinkedList;

public class Guardian {
	private LinkedList<Memento> mementoList = new LinkedList<Memento>();

	public void add(Memento state) {
		mementoList.add(state);
	}

	public Memento get(int index) {
		return mementoList.get(index);
	}
	
	public Memento getLast(){
		return mementoList.getLast();
	}
	
	public Memento getFirst(){
		return mementoList.getFirst();
	}
	
	public int size(){
		return mementoList.size();
	}
}
