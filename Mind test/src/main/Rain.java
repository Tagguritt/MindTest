package main;

import java.awt.Color;
import java.awt.Graphics;

public class Rain {
	int Width;
	int Height;
	public float x = (float) (Math.random()* Width + 1);
	public float y = (float) -((Math.random()*200) +25);
	public float z = 0;
	public float grav = 0;
	public float speed = (float) (z)+2; // (float) ((Math.random()*15)+5)-z;
	public float dlength = 18;
	public float dwidth = 2;
	private int color = 1;
	
	public void rain(){
	}
	public void init(int width, int height) {
		color = 1;
	    Width = width;
	    Height = height;
	    x = (float) (Math.random()* Width + 1);
		y = (float) -((Math.random()*200) +25);
		z = (float) ((Math.random()*4));
		grav = 0;
		speed = (float)(z + 2);
		dlength = (float) (15-z);
		dwidth = dlength / 7;
	    
	}
	public void tick() {
		y = y + speed/3;
		if(y > Height){
		x = (float) (Math.random()* Width + 1);
		y = (float) -((Math.random()*100) +25);
		speed = (float) (z)+8;

		}
	}	
	
	public void render(Graphics g){


		if (color == 2) {
		g.setColor(Color.getHSBColor((float)0.5, (float)0.5, (float)0.5));
		}
		if (color == 1) {
			g.setColor(Color.getHSBColor((float)0.8, (float)0.5, (float)0.5));
		}
		g.fillRect((int)x, (int)y, (int)dwidth, (int)dlength);
	}

}
