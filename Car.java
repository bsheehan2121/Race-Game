/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;

// Race-Game

import java.util.Random;

//our race game for project 4
/*
 *  @author Firas
 * 
 */

public class Car {
	
	private double speed;
	private String color;
        private String name;
        private int num=0;
        public String type;
        
        private int delay;
        private Random r;
        public boolean invisible;
	
	public Car() {
		r = new Random();
		speed = 30;
		color = " ";
		
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
                
                if(type.equals("racecar")){
                    speed = r.nextInt(5)+5;
                    delay = r.nextInt(5)+5;
                }else if(type.equals("truck")){
                    speed = r.nextInt(15)+5;
                    delay = r.nextInt(5);
                }else if(type.equals("hybrid")){
                    speed = 15;
                    delay = r.nextInt(5)+5;
                }else if(type.equals("car")){
                    speed = r.nextInt(15)+5;
                    delay = 0;
                }else{
                    speed = 20;
                    delay=10;
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
            if(type.equals("racecar")){
                speed = r.nextInt(5)+5;
            }else if(type.equals("truck")){
                speed = r.nextInt(15)+5;
            }else if(type.equals("hybrid")){
                    speed = 15;
            }else if(type.equals("car")){
                speed = r.nextInt(10)+10;
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
	
	
	
        
        public String toString(){
            String s;
            if(invisible){
                s="        ";
            }else{
                s = "Player "+num+":  "+name;
                s+="   Color: "+ color;
            }
            return s;
        }
	

}
 