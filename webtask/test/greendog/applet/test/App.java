package greendog.applet.test;

import java.applet.Applet;
import java.awt.Graphics;

public class App extends Applet {

	public App() {
		super();
	}

	public void destroy() {
		// Put your code here
	}

	public String getAppletInfo() {
		return "This is my default applet created by Eclipse";
	}

	public void init() {
		// Put your code here
	}

	public void start() {
		// Put your code here
	}

	public void stop() {
		// Put your code here
	}
	
	public void paint(Graphics g) {
	      g.drawRect(0, 0, 499, 149);
	      g.drawString("Printing...", 5, 70);
	}

}
