package Code;

import java.util.ArrayList;
import java.util.Collections;

public class Warehouse implements Functions{

	private static Warehouse instance = null;
	
	private ArrayList<Slot> slots;
	
	private Warehouse(ArrayList<Slot> slots) {
		this.slots = slots;
	}
	public static Warehouse getInstance() {
		
		return instance;
	}
	
	public void set(ArrayList<Slot> slots) {
		this.slots = slots;
		
	}
	public static void createInstance(ArrayList<Slot> slots ) {
		if(instance == null) {
			instance = new Warehouse(slots);
		}
		else {
			instance.set(slots);
		}
	}
		
	public void printItemsInSlots() {
		for(int i=0;i<slots.size();i++) {
			
			if(slots.get(i).getFreeVolume()==slots.get(i).getVolume()){
				System.out.println("Slot "+(i+1)+" is empty, volume is "+ slots.get(i).getVolume());
				System.out.println();
			}
			else {
				System.out.println("Slot "+(i+1)+", volume is "+ slots.get(i).getVolume()+ ", free volume is "+ slots.get(i).getFreeVolume());
				slots.get(i).printItemsInSlot();
				System.out.println();
			}
		}
	}
	
	
	//Nursultan - check availability don't forget to check SystemDate
	@Override
	public boolean checkAvailability(Item item) {
		
		return true;
	}
	
	
	//Harvey - putting in slots don't forget to check SystemDate
	
	@Override
	public void moveToSlot(Item item) {
		if(checkAvailability(item)) {
			//move to some slot
			
			System.out.print("Item #"+item.getID()+" is in Slot#slotnumber"+ " "+ SystemDate.getInstance());
		}
		
	}
	
	 // Denny - Optimize the storage function(Arrays of slots)  - void 
	@Override
	public void optimize(Slot[] slots, Item[] items) {
		
		//Please double-check the logic
		// check from the smallest item to the largest item that is smaller than the slot size - if found true
		// check from the smallest available slot space - if found true
		//sort items by size (smallest to largest)
		//sort slots by size (smallest to largest)
		
		boolean wasOptimized  = false;
		for(int i=0; i<items.length; i++) {

					if(items[i].getDimensions()<(items[i].getCurrentSlot()).getVolume()) {
						Slot next = findBetterSlot(slots, items[i]);
						if(next!=null) {
							//rearranging item
							Slot cur = items[i].getCurrentSlot();
							cur.removeItem(items[i]);
							
							items[i].setCurrentSlot(next);
							next.addItem(items[i]);
							wasOptimized = true;
						}
					}

		}
		
		if(wasOptimized) {
			System.out.println("Optimizing was done.");
		}
	}
	
	//Denny -> discuss this function
	//start from slot of item -- for next version (complexity improvement)
	private Slot findBetterSlot(Slot slots[], Item item) {
		boolean found = false;
		Slot bestSlot = slots[0];
		for(int j=0; j<slots.length; j++) {
			if(item.getDimensions()<=slots[j].getFreeVolume() && bestSlot.getFreeVolume()<=slots[j].getFreeVolume()) {
				bestSlot = slots[j];
			}
		}
		
		if(found) {
			System.out.println("A Better Slot was not found.");
			return bestSlot;
		}
		else {
			return null;
		}
		
	}
}
