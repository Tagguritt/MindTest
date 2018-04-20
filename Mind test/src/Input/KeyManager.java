package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class KeyManager implements KeyListener {
	
	private Main main;
	public KeyManager(Main m){
		main = m;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// I don't know why this is but when e.getKeyChar is passed it changes to an integer
		main.manageInputCharNumber(e.getKeyChar());
	}
	
	
}
