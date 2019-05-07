package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Grid;
import views.MainWindow;

class MainWindowTest {
	private MainWindow mainWindow;
	private Grid grid;
	
	
	
	@Test
	void testCreateGrid() {
		//mainWindow  = new MainWindow();
		grid = new Grid(10, 10, 500, 500);
		grid.createGrid();
		for (int x: grid.getxCoordinates())
		{
			System.out.println(x);
		}
		for (int y: grid.getyCoordinates())
		{
			System.out.println(y);
		}
		
	}

	@Test
	void testIterateOverGrid() {
		//mainWindow  = new MainWindow();
		grid = new Grid(10, 10, 500, 500);
		grid.createGrid();
		
		for (int x: grid.getxCoordinates())
		{
			System.out.println("x: " + x);
		}
		for (int y: grid.getyCoordinates())
		{
			System.out.println("y: " + y);
		}
		
	}
}
