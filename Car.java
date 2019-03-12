// Race-Game
//our race game for project 4

package Assignment04.GUI;
/*
 *  @author Firas
 * 
 */

public class Car {
	
	private double speed;
	private String color;
	private char start;
	private char end;
	private boolean tireStatus;
        private String name;
        private static int num=0;
	
	public Car() {
		
		speed = 10;
		color = " ";
		start = 0;
		end = 0;
		tireStatus=false;
                name = "Car";
	}
        public Car(String c,String n){
                speed = 10;
		color = c;
		start = 0;
		end = 0;
		tireStatus=false;
                name = n;
                num++;
            
        }
	
	public double getSpped() {
		return speed;
		
	}
	
	public void setSpeed(double s) {
		speed=s;
	}
	
	public String getColor() {
		return color;
		
	}
        public String getName(){
            return name;
        }
        
        public void setName(String n){
            name = n;
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
        
        public String toString(){
            String s = "Player "+num+": "+name;
            s+="            Color: "+ color;
            return s;
        }
	

}
 