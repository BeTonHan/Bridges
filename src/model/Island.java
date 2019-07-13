package model;

import java.util.Iterator;
import java.util.LinkedList;

public class Island extends GridElement {
	
	
	public enum NumberOfNeighbors {
		ZERO, ONE, TWO;
	}
	
	public enum DirectionOfNeighbors {
		WEST, EAST, SOUTH, NORTH;
	}
	
	private short westNeighbors;
	private short eastNeighbors;
	private short southNeighbors;
	private short northNeighbors;
	private LinkedList<Island> neighbors;
	

	public LinkedList<Island> getNeighbors() {
		return neighbors;
	}

	
	// TODO: Fehlerbehandlung einfügen
	public void addNeighbor (DirectionOfNeighbors direction, NumberOfNeighbors neighbors) {
		switch (direction) {
			case NORTH:
				northNeighbors += 1;
			case SOUTH:
				southNeighbors += 1;
			case WEST:
				westNeighbors += 1;
			case EAST:
				eastNeighbors += 1;				
		}		
	}
	
	// TODO: Fehlerbehandlung einfügen
		public void removeNeighbor (DirectionOfNeighbors direction, NumberOfNeighbors neighbors) {
			switch (direction) {
				case NORTH:
					northNeighbors -= 1;
				case SOUTH:
					southNeighbors -= 1;
				case WEST:
					westNeighbors -= 1;
				case EAST:
					eastNeighbors -= 1;				
			}		
		}
		

	
}
