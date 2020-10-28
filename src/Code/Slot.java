package Code;
import java.util.ArrayList;
//import java.util.Collections;

public class Slot {

	private int volume;
	public int freeVolume;
	private ArrayList<Item> items;
	private int slotID;
	
	public Slot(int volume) {
		this.volume = volume;
		freeVolume = volume;
		items= new ArrayList<>();
		//Harvey V1.0 - automatical ID
		this.slotID = Warehouse.getInstance().assignSlotID();
	}
	
	public int getVolume() {
		return this.volume;
	}
	public int getFreeVolume() {
		return this.freeVolume;
	}
	public int getSlotID() {
		return this.slotID;
	}
	public ArrayList<Item> getItemsList() {
		return items;
	}

	//Adding item to Slot
	public void addItem(Item item) {
		//add item to items ArrayList
		items.add(item);
		//Calculate remaining Volume
		this.freeVolume = this.freeVolume - item.getDimensions();
		//Set current slot to assigned slot
		item.setCurrentSlot(this);
	}
	
	//added by Denny V1.0 - consideration required
	public void removeItem(Item item) {
		//restore the volume of the slot
		this.freeVolume+=item.getDimensions();
		//remove item from slot's itemList
		items.remove(item);
		//Harvey V1.0 - set current slot to null
		item.setCurrentSlot(null);
	}
	

	public void printItemsInSlot() {
		for(int i =0;i<items.size();i++) {
			System.out.println(i+1+". "+items.get(i).toString());
		}
	}
	
}
