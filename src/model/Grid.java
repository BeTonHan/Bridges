package model;

public class Grid {
	public Grid(int width, int height, int panelWidth, int panelHeight) {
		this.width = width;
		this.height = height;
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
	}

	private int[] xCoordinates;
	private int[] yCoordinates;
	private int width;
	private int height;
	private int panelWidth;
	private int panelHeight;

	public int[] getxCoordinates() {
		return xCoordinates;
	}

	public int[] getyCoordinates() {
		return yCoordinates;
	}

	
	// Berechnet die x- und y-Koordinaten des Spielfeld-Gitters (beginnend bei 0 bis zur Größe des Panels).
	public void createGrid() {
		xCoordinates = new int[width];
		yCoordinates = new int[height];
		for (int i = 0; i < xCoordinates.length; i++ )
		{
			xCoordinates[i] = (panelWidth / width) + i * (panelWidth / width);
			
		}
		for (int i = 0; i < xCoordinates.length; i++ )
		{
			yCoordinates[i] = (panelHeight / height) + i * (panelHeight / height);
		}
		
	}

}
