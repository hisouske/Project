package GameStart;

import java.awt.Image;

import javax.swing.ImageIcon;

import GameStart.GameRoom.MyCanvas;

public class MonCenter {
	int x = -1;
	int y = -1;
	int speed = 50;
	//MyCanvas mc = null;
	private Image myMon = null;
	int width = 0;
	int height = 0;
	String imgName=null;
	
	
	MonCenter(int x, int y, String name){
		this.x=x;
		this.y=y;
		this.imgName=name;
		//	this.mc=mc;
		
		myMon = new ImageIcon(imgName).getImage();
		this.width = myMon.getWidth(null);
		this.height = myMon.getHeight(null);
		
	//	autoPoint();
		//setTimer();
	}
	
	public Image getMyMon() {
		return myMon;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
