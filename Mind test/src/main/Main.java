package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import Input.KeyManager;
import display.Display;

public class Main implements Runnable {

	private Thread thread;
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	private KeyManager k;
	private math math;
	private Rain[] drop = new Rain[LARGEST_AMT_OF_RAIN];
	private StopWatch timer;

	public static final int LARGEST_AMT_OF_RAIN = 1000;
	private int amt = 0;
	private int height = 0;
	private int width = 0;
	public int Frames;
	public static boolean running = false;
	public static boolean GameIsRunning = true;
	
	//start Thread. The new Thread will call run()
	 public synchronized void start(){
			if(running){
				return;
			}
			thread = new Thread(this);
			thread.start();
			running = true;
		}
	 
	 //Called by new Thread
	 public void run() {
		 
		 	//initialize objects
			init();
			
			//most of this regulates how often objects update
			double timePerTick = 1000000000 / 60;
			double delta = 0;
			long now;
			long lastTime = System.nanoTime();
			while(running){
				now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				lastTime = now;
				if(delta >= 1){
					//update objects
					tick();
					//display objects
					render();
				
				delta--;
				}
			}
	
		}
	 
	 public void init(){
		//initialize objects
		math = new math();
		timer = new StopWatch();
		k = new KeyManager(this);
		display = new Display("base");
		display.getFrame().addKeyListener(k);
		width = display.GetDisplayWidth();
		height = display.GetDisplayHeight();

		
		//The rain is a "reward" of the game or just a good laugh
		for(int i = 0; i < LARGEST_AMT_OF_RAIN; i++){
			drop[i] = new Rain();
		 }
		for(int i = 0; i < LARGEST_AMT_OF_RAIN; i++){
			drop[i].init(width, height);
		}
		
	 }
	 
	 public void tick(){
		 
		 if(GameIsRunning) {
			GameIsRunning = timer.IsTimerRunning();
		 }
		 if(math.isGoing == true && GameIsRunning == false) {
			 math.Stop();
		 }
		 if(math.isGoing == false && GameIsRunning == true) {
			 math.Start();
		 }

		 amt = math.getTotalRight() * 5;
		 for(int i = 0; i < amt; i++){
			 drop[i].tick();
		 }

	 }

	 public void render(){
		 
		 bs = display.getCanvas().getBufferStrategy();
		 if (bs == null){
			 display.getCanvas().createBufferStrategy(3);
			 return;
		 }
		 g = bs.getDrawGraphics();
		 g.clearRect(0, 0, width, height);
		 math.render(g);
		 //if the game isn't running display instruction on how to start
		 if(!GameIsRunning) {
			 g.drawString("Press enter to start", 300, 125);
		 }
		 for(int i = 0; i < amt; i++){
			 drop[i].render(g);
		 }
		 
		 //instructions for game
		 g.setColor(Color.BLACK);
		 g.drawString("you have 30 seconds to see how may you can get.", 100, 200);
		 g.drawString("Use absolute values only,", 250, 250);
		 g.drawString("and no backspace", 300, 300);
		 
		 bs.show();
		 g.dispose();
	 }
	 //called whenever user presses a key
	 public void manageInputCharNumber(int chr) {
		 //if Game is Running
		 if(Main.GameIsRunning) {
			 if(chr == KeyEvent.VK_ESCAPE) {
					math.reset();
				}
				if(chr >= KeyEvent.VK_0 && chr <= KeyEvent.VK_9) {
				 	math.addtopanswer(chr);
			 	}
			 	if(chr == KeyEvent.VK_ENTER) {
				 	math.check();
			 	}
			}else if(chr == KeyEvent.VK_ENTER){
				timer.StartTimer(30);
				Main.GameIsRunning = true;
			}
	 }

}
