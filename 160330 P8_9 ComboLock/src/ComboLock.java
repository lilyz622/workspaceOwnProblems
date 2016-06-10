/**
 * This imitates a combination lock with three numbers. 
 */

import java.util.Arrays;
import java.util.Scanner;

public class ComboLock {
	static final int range = 40;
	private int position;
	private int num1;
	private int num2;
	private int num3;
	private int count; //ensures that lock only opens when the right combination numbers are turned consecutively. 
	private boolean[] open = new boolean[3];
	
	/**
	 * constructor sets the lock's combination.
	 * @param a	first combination number
	 * @param b	second combo number
	 * @param c	third combo number
	 */
	public ComboLock(int a, int b, int c){
		num1 = a;
		num2 = b;
		num3 = c;
		position = 0;
		this.reset();		
	}
	
	/**
	 * resets the lock, but not the position.
	 */
	public void reset(){
		open[0]=false;
		open[1]=false;
		open[2]=false;
		count = 0;
		//System.out.println("The lock has been reset.");
	}
	
	/**
	 * turns lock the to the left by the number of ticks.
	 * @param ticks
	 */
	public void turnLeft(int ticks){
		position = (position + ticks)%40;
		count++;
		checkTurn(ticks);
	}
	
	/**
	 * turns lock the to the right by the number of ticks.
	 * @param ticks
	 */
	public void turnRight(int ticks){
		position = (position - ticks)%40;
		if (position < 0)
			position+=40;
		count++;
		checkTurn(ticks);
	}
	
	/**
	 * checks if the lock has been over-turned.
	 * @param ticks
	 */
	private void checkTurn(int ticks){
		if (ticks>40){
			//System.out.println("Sorry, you have over-spun. The lock has been reset. Please try again.");
			this.reset();
		}
	}
	
	/**
	 * tries to open the lock.
	 * @return whether the lock has been successfully opened.
	 */
	public boolean open(){
		boolean allopen = true;
		for (boolean element: open){
			if (element ==false)
				allopen = false;
		}
		return allopen;		
	}
	
	/**
	 * runs the lock-opening interface. Closes once lock is opened.
	 */
	public void run(){
		System.out.println("Hello, please open the lock!");
		while(true){
			System.out.println("Please type \"left\" to turn left.");
			System.out.println("Please type \"right\" to turn right.");
			System.out.println("Please type \"reset\" to reset the lock.");
			System.out.println("Please type \"open\" to attempt to open the lock.");
			
			Scanner kb = new Scanner (System.in);
			System.out.print("Your answer:");
			String word = kb.next();
			
			//System.out.println("Combination: "+num1+", "+num2+", "+num3);
			
			if (word.equalsIgnoreCase("right")){
				System.out.print("Number of ticks:");
				int ticks = kb.nextInt();
				turnRight(ticks);
				
				//check if first combination number has been opened.
				if (position == num1){
					open[0] = true;
					count = 1;
				}
				
				//check if third combination number has been opened.
				else if	(position == num3 && open[0] == true && open[1]== true && count == 3){
					open[2] = true;
				}
				
				else{
					this.reset();
				}
			}	
			else if (word.equalsIgnoreCase("left")){
				System.out.print("Number of ticks:");
				int ticks = kb.nextInt();
				turnLeft(ticks);
				
				//check if second combination number has been opened.
				if (position == num2 && open[0] == true && count == 2){
					open[1] = true;
				}
				
				else{
					this.reset();
				}
			}
			else if (word.equalsIgnoreCase("reset")){
				reset();
				position = 0;
			}
			else if (word.equalsIgnoreCase("open")){
				if (open()){
					System.out.println();
					System.out.println("Congratulations! You have opened the lock!");
					break;
				}
				else{
					System.out.println("Sorry, you did could not open the lock. The lock has been reset. Please try again.");
					reset();
					position = 0;
				}
			}
			//System.out.println(Arrays.toString(open)+"\nCount is:"+count);
			System.out.println("Your current position:"+position);
			System.out.println();
		}
		
	}
	

}
