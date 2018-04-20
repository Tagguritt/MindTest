package main;

import java.awt.Font;
import java.awt.Graphics;


public class math {
	int type = 0;
	int one = 0;
	int two = 0;
	int score = 0;
	int time = 0;
	int seconds = 0;
	int totalRight = 0;
	int right = 0;
	int wrong = 0;
	long lasttime;
	int answer = 0; 
	long pAnswer = 0;
	
	private final int ADD = 1;
	private final int SUBTRACT = 2;
	private final int MALTIPLY = 3;
	
	public boolean isGoing = false; 
	
	public void newProblem() {
		pAnswer = 0; 
		type = (int) (Math.random() * 3 +1);
		one = (int) (Math.random() * 10 +1);
		two = (int) (Math.random() * 10 +1);
		if(type == ADD) {
		answer = (one + two);	
		}
		if(type == SUBTRACT) {
		answer = (one - two);
		}
		if(type == MALTIPLY) {
		answer = (one * two);
		}
		
	}
	
	public void input(int n) {
		//if Game is Running
		if(Main.GameIsRunning) {
			if(n == 27) {
				reset();
			}
			if(n >= 48 && n <=57) {
			 	addtopanswer(n);
		 	}
		 	if(n == 10) {
			 	check();
		 	}
		}else if(n == 10){
			Main.GameIsRunning = true;
		}
	}
	
	public void addtopanswer(int n) {
		//change from key code to the corresponding number
		//if 5 is passed in from key code it will be 53 so it removes 48 to get 5
		// the correct number then is paced in the ones column and the previous answer is changed to the tens column
		pAnswer = (pAnswer * 10) + (n - 48);
		
	}
	
	public void check(){
		if(pAnswer ==  Math.abs(answer)) {
			System.out.println("right");
			score ++;
			right ++;
			totalRight ++;
		}
		if(pAnswer !=  Math.abs(answer)) {
			showwork();
			score --;
			wrong ++;
		}
		newProblem();
	}

	//this will allow anyone running the program with a console to have a temporary record of their work
	public void showwork() {
		if(type == ADD) {
		System.out.println("\n" +one + " + " + two + " =" + answer);
		}
		if(type == SUBTRACT) {
		System.out.println("\n" +one + " - " + two + " =" + answer);
		}
		if(type == MALTIPLY) {
		System.out.println("\n" +one + " * " + two + " =" + answer);
		}
		System.out.println("your answer:" + pAnswer);
		System.out.println("wrong \n");
		
	}
	public void render(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("score: " + score + ",", 100, 50);
		g.drawString("right: " + right + ",", 300, 50);
		g.drawString("wrong: " + wrong, 500, 50);
		if(isGoing) {
			if(type == 1) {
				g.drawString(one + "+" + two + " =" + pAnswer, 350, 125); 	
			}
			if(type == 2) {
				g.drawString(one+ "-" + two + " =" + pAnswer, 350, 125);
			}
			if(type == 3) {
				g.drawString(one + "*" + two + " =" + pAnswer, 350, 125);
			}
		}
	
	}
	
	public void Start(){
		isGoing = true;
		reset();
		newProblem();
	}
	public void Stop(){
		isGoing = false;
	}
	public void reset() {
		score = 0;
		right = 0;
		wrong = 0;
	}
	public boolean isRunning() {
		return isGoing;
	}
	public int getTotalRight() {
		return totalRight;
	}
	

}
