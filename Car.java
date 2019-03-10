# Race-Game
our race game for project 4

package Assignment04.GUI;
/*
 *  @author Firas
 * 
 */

public class Car {
	
	private int speed;
	private String color;
	private char start;
	private char end;
	private boolean tireStatus;
	
	public Car() {
		
		speed = 0;
		color = " ";
		start = 0;
		end = 0;
		tireStatus=false;
	}
	
	public int getSpped() {
		return speed;
		
	}
	
	public void setSpeed(int s) {
		speed=s;
	}
	
	public String getColor() {
		return color;
		
	}
	
	public void setColor(String c) {
		color = c;
	}
	
	public boolean checTires() {
		return tireStatus;
	}
	// we still need to give value for int
	public void setTiresStatus(boolean b,int set) {
		tireStatus=b;
		
	}
	//i wasn't sure what this one for
	public void advanceCar() {
		
	}
	

}

