package model;

public class Bridge {
	private boolean isDoubled;
	private Island startPoint;
	private Island endPoint;
	
	public boolean isDoubled() {
		return isDoubled;
	}
	public void setDoubled(boolean isDoubled) {
		this.isDoubled = isDoubled;
	}
	public Island getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Island startPoint) {
		this.startPoint = startPoint;
	}
	public Island getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Island endPoint) {
		this.endPoint = endPoint;
	}
	
}
