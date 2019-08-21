package GameStart;

import java.awt.Image;

import javax.swing.ImageIcon;

import GameStart.GameRoom.MyCanvas;

public class BlueMon extends MonCenter{

//	static String imgName="./img/mon_blue.jpg";
	
	BlueMon(int x, int y){
		super(x,y,"./img/mon_blue.jpg");
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
						setY(getY()+1);
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
