package GameStart;

import java.awt.Image;

import javax.swing.ImageIcon;

import GameStart.GameRoom.MyCanvas;

public class RedMon extends MonCenter {
	
	//String imgName="./img/mon_red.jpg";
	RedMon(int x, int y){
		super(x,y,"./img/mon_red.jpg");
		autoPoint();
		//setTimer();
	}
	
	
	

	private void autoPoint() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(getY() < 400) {
					try {
						Thread.sleep(speed);
						setY(getY()+2);
						setX(getX()+1);
					//	mc.repaint();  // update(g) >> paint(g)
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}).start();
		
	}
	
	

}
