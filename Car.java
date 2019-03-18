
package racegame;



import java.util.Random;
/*
 *  @author Firas
 * 
 */

// The Car class represents a race car

// Firas wrote the initial car class
// Elizabeth made some minor changes
// Ben added to the Car class, enabling different types of car with different
// speeds and attributes
public class Car {
	
        // Elizabeth made some minor variable changes to ensure
        // secure code
	private double speed;
	private String color;
        private String name;
        private int num;
        private String type;
        
        private int delay;
        private Random r;
        private boolean invisible;
	
	public Car() {
		r = new Random();
		speed = 20;
		color = " ";
		num = 0;
                name = "Car";
                delay = 10;
                invisible = true;
                type = "none";
	}
        public Car(String c,String n,int i,String t){
                r = new Random();
		color = c;
                type = t;
                name = n;
                num = i;
                invisible = false;
                
            // Ben wrote this originally as an if/else series of statements,
            // Elizabeth changed it to a switch statement
            switch (type) {
                case "racecar":
                    speed = r.nextInt(5)+5;
                    delay = r.nextInt(5)+5;
                    break;
                case "truck":
                    speed = 20;
                    delay = r.nextInt(5);
                    break;
                case "hybrid":
                    speed = 10;
                    delay = r.nextInt(3);
                    break;
                case "car":
                    speed = r.nextInt(15)+5;
                    delay = 1;
                    break;
                default:
                    speed = 20;
                    delay=10;
                    break;
            }
            
        }
	
	public double getSpeed() {
                setSpeed();
		return speed;
		
	}
        
        public int getDelay(){
            return delay;
        }
	
	public void setSpeed(double s) {
            
		speed=s;
	}
        
        public void setSpeed(){
            // Ben wrote this as a series of if/else statements,
            // Elizabeth changed it to a switch statement
            switch (type) {
                case "racecar":
                    speed = r.nextInt(5)+5;
                    break;
                case "truck":
                    if(speed>5){
                        speed = speed-(r.nextInt(3)+4);
                    }else{
                        speed = 20-(r.nextInt(3)+4);
                    }   break;
                case "hybrid":
                    speed = 10;
                    break;
                case "car":
                    speed = r.nextInt(10)+7;
                    break;
                default:
                    break;
            }
           
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
	
        // Elizabeth added these two methods
	public boolean getInvisible(){
            return invisible;
        }
	
        public String getType(){
            return type;
        }
        
        public String toString(){
            String s;
            if(invisible){
                s="        ";
            }else{
                s = "Player "+num+":  "+name;
                s+="   Color: "+ color;
                s+="   Type: "+ type;
            }
            return s;
        }
	

}
 