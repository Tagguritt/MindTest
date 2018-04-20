package main;

public class StopWatch {
	private long startTime;
	private int timerLength;
	
	public void StartTimer(int Durration) {
		startTime = System.nanoTime() + 1000000000;
		timerLength = Durration;
	}
	
	public boolean IsTimerRunning() {
		long now = System.nanoTime();
		//find elapsed time in seconds
		long elapsedTime = (now - startTime) / 1000000000;
		if( elapsedTime < timerLength) {
			return true;
		}else {
			return false;
		}
	}

}
