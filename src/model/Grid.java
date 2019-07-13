package model;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;



public class Grid {
	public Grid(int width, int height, int panelWidth, int panelHeight, int islands) {
		this.width = width;
		this.height = height;
		this.panelWidth = (int) (panelWidth * .95);
		this.panelHeight = (int) (panelHeight * .95);
		this.islands = islands;
		isOccupied =  new boolean [width][height];
		createGrid();
		setStartPoint();
		
	}

	
	private int[] xCoordinates;
	private int[] yCoordinates;
	private boolean [][] isOccupied; 
	private int width;
	private int height;
	private int panelWidth;
	private int panelHeight;
	private int islands;
	private Set<Coordinate> pathOfBridges;
	private LinkedList<Island> connectedIslands = new LinkedList<Island>();
	
	
	public int[] getxCoordinates() {
		return xCoordinates;
	}

	public int[] getyCoordinates() {
		return yCoordinates;
	}
	
	public LinkedList<Island> getIslands() {
		return connectedIslands;
	}

	
	// Berechnet die x- und y-Koordinaten des Spielfeld-Gitters (beginnend bei 0 bis zur Größe des Panels).
	//TODO: Zentrierung der Koordinaten verbessern -> Verbleibende Abstände zu den Rändern berechnen und Feld entsprechend verschieben!
	private void createGrid() {
		xCoordinates = new int[width];
		yCoordinates = new int[height];
		double tempValue;
		
		for (int i = 0; i < width; i++ )
		{
			xCoordinates[i] = Math.round(i  * (panelWidth / (width - 1))) + 5;
			//xCoordinates[i] = (int) tempValue + 5;
			
		}
		for (int i = 0; i < height; i++ )
		{
			yCoordinates[i] = Math.round(i * (panelHeight / (height - 1))) + 5;
			//yCoordinates[i] = (int) tempValue + 5;
		}
		
		
	}
	
	// Positioniert die erste Insel auf dem Spielfeld
	private void setStartPoint() {		
		Island firstIsland = new Island(); 
		firstIsland.setX(ThreadLocalRandom.current().nextInt(0, width));
		firstIsland.setY(ThreadLocalRandom.current().nextInt(0, width));
		firstIsland.setxPos(getxCoordinates()[firstIsland.getX()]);
		firstIsland.setyPos(getyCoordinates()[firstIsland.getY()]);
		connectedIslands.add(firstIsland);		
		isOccupied[firstIsland.getX()][firstIsland.getY()] = true;
		
		positionIslands();
		
	}
	
	// Positioniert die verbleibenden Inseln auf dem Spielfeld ausgehend von der ersten
	private void positionIslands() {
		// Läuft, solange noch mögliche Bauorte zur Verfügung stehen
		while (connectedIslands.size() < islands) {
			
			// Prüfen, ob eine Sackgasse erreicht ist, dann werden alle Inseln bis auf die Startinseln gelöscht
			if (checkDeadEnd()) resetIslandsToStart();
			
			// Per Zufallsgenerator zwischen horizontaler oder vertikaler Suche nach neuer Inselposition wählen.
			int direction = ThreadLocalRandom.current().nextInt(0, 2);
			
			// Falls direction = 0 und es keine horizontale Sackgasse gibt: Per Zufallsgenerator in horizontaler 
			// Richtung Index wählen
			if ((direction == 0) && !checkHorizontalDeadEnd()) {
				int tempXPos = connectedIslands.getLast().getX(); 
				//While-Schleife prüft, ob Zufallsgenerator einen anderen Index gewählt hat als die letzte gebaute Insel und ob der Weg zwischen beiden Inseln frei ist, ansonsten wird neuer Index gesucht.
				while ((tempXPos == connectedIslands.getLast().getX()) || isRoadBlocked(connectedIslands.getLast().getX(), tempXPos, true)) tempXPos = ThreadLocalRandom.current().nextInt(0, width);
				
				// Insel setzen - TODO: Brückenbau einfügen
				Island newIsland = new Island();
				newIsland.setX(tempXPos);
				newIsland.setY(connectedIslands.getLast().getY());
				newIsland.setxPos(getxCoordinates()[newIsland.getX()]);
				newIsland.setyPos(getyCoordinates()[newIsland.getY()]);
				isOccupied[newIsland.getX()][newIsland.getY()] = true;
				connectedIslands.add(newIsland);
				}
			else
				//In vertikaler Richtung nach Index suchen
			{
				int tempYPos = connectedIslands.getLast().getY(); 
			//While-Schleife prüft, ob Zufallsgenerator einen anderen Index gewählt hat als die letzte gebaute Insel und ob der Weg zwischen beiden Inseln frei ist, ansonsten wird neuer Index gesucht.
			while ((tempYPos == connectedIslands.getLast().getY()) || isRoadBlocked(connectedIslands.getLast().getY(), tempYPos, false)) tempYPos = ThreadLocalRandom.current().nextInt(0, height);
			
			// Insel setzen - TODO: Brückenbau einfügen
			Island newIsland = new Island();
			newIsland.setY(tempYPos);
			newIsland.setX(connectedIslands.getLast().getX());
			newIsland.setxPos(getxCoordinates()[newIsland.getX()]);
			newIsland.setyPos(getyCoordinates()[newIsland.getY()]);
			isOccupied[newIsland.getX()][newIsland.getY()] = true;
			connectedIslands.add(newIsland);
			}
				
			
		}
		
	}

	// Prüft, ob Weg zwischen bestehender und geplanter Insel frei ist. "Pos" steht für die bestehende X oder Y-Koordinate der Insel, tempPos für die geplante.
	// "horizontal" gibt an, ob es sich um die x (true) oder y (false)-Koordinate handelt.
	private boolean isRoadBlocked(int pos, int tempPos, boolean horizontal) {
		boolean occupied = false;
		if (horizontal) {
			while (pos > tempPos) {
				pos -= 1;
				if (isOccupied[pos][connectedIslands.getLast().getY()]) occupied = true; 
			}

			while (pos < tempPos) {
				pos += 1;
				if (isOccupied[pos][connectedIslands.getLast().getY()]) occupied = true; 
			}
		}
		else if (!horizontal) {
			while (pos > tempPos) {
				pos -= 1;
				if (isOccupied[connectedIslands.getLast().getX()][pos]) occupied = true; 
			}
			while (pos < tempPos) {
				pos += 1;
				if (isOccupied[connectedIslands.getLast().getX()][pos]) occupied = true; 
			}
		}
		return occupied;
		
	}

	private Boolean checkDeadEnd() {
		if (!checkHorizontalDeadEnd() && !checkVerticalDeadEnd()) 
			return false;
		else
			return true;
	}
	
	private Boolean checkHorizontalDeadEnd() {
		boolean occupied = true;
		Island lastIsland = connectedIslands.getLast();
		// Zunächst schauen, ob links noch Platz ist
		if (lastIsland.getX() > 0){ 
			if (!(isOccupied[lastIsland.getX() - 1][lastIsland.getY()]))
			occupied = false;
		}
		// nach rechts schauen
		else if (lastIsland.getX() < width) { 
			if (!(isOccupied[lastIsland.getX() + 1][lastIsland.getY()]))
			occupied = false; 
		}
		
		return occupied;
	}
	
	private Boolean checkVerticalDeadEnd() {
		boolean occupied = true;
		Island lastIsland = connectedIslands.getLast();
		// nach oben schauen
		if (lastIsland.getY() > 0) { 
			if (!(isOccupied[lastIsland.getX()][lastIsland.getY() - 1]))
				occupied = false;
		}
		// nach unten schauen
		else if (lastIsland.getY() < height) 
			if (!(isOccupied[lastIsland.getX()][lastIsland.getY() + 1]))
				occupied = false;
		
		return occupied;
	}
	
	private void resetIslandsToStart() {
		while (connectedIslands.size() > 1) connectedIslands.removeLast();		
	}


}
