package Code;
import java.util.ArrayList;
import java.util.Collections;

public class Slot {

	private int volume;
	public int freeVolume;
	private ArrayList<Item> items;
	public Slot(int volume) {
		this.volume = volume;
		freeVolume = volume;
		items= new ArrayList<>();
	}
	
	public int getVolume() {
		return this.volume;
		
	}
	public int getFreeVolume() {
		return this.freeVolume;
	}
	public void addItem(Item item) {
		items.add(item);
		this.freeVolume = this.freeVolume - item.getDimensions();
		item.setCurrentSlot(this);
	}
	
	//added by Denny V1.0 - consideration required
	public void removeItem(Item item) {
		//restore the volume of the slot
		this.freeVolume+=item.getDimensions();
		//remove item from slot's itemList
		items.remove(item);
	}
	
	public ArrayList<Item> getItemsList() {
		return items;
	}
	public void printItemsInSlot() {
		for(int i =0;i<items.size();i++) {
			System.out.println(i+1+". "+items.get(i).toString());
		}
	}
	
}
