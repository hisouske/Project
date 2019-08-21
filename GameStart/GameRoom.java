package GameStart;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameRoom extends JFrame implements KeyListener {
	MyCanvas mc = new MyCanvas();
	JLabel title = new JLabel("My game!!");

	int speed = 4; // 비행기 이동속도
	int myairNum = 3; // 내 비행기 수

	int score = 0; // 점수
	int monTimer = 500; // 괴물 생기는 속도

	int keyInfo[] = new int[] { -1, -1, -1, -1, -1 }; // 키가 눌린 정보

	boolean missileMake = false;

	MyAir myair = new MyAir();
	ArrayList<MonCenter> redList = new ArrayList<MonCenter>();
//	ArrayList<RedMon> redList = new ArrayList<RedMon>();
	ArrayList<Missile> missileList = new ArrayList<Missile>();

	GameRoom() {

		this.setBounds(1700, 100, 400, 500);
		this.setLayout(new BorderLayout());
		this.add(mc, "Center");
		this.add(title, "South");

		this.addKeyListener(this);

		createMon();
		key();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	private void key() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(20);
						// System.out.println("key le");
						keyAction();
						mc.repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	private void keyAction() {
		if (myair != null) {
			if (keyInfo[0] == 1) {
				myair.setY(speed * -1);
			}
			if (keyInfo[1] == 1) {
				myair.setY(speed);
			}
			if (keyInfo[2] == 1) {
				myair.setX(speed * -1);
			}
			if (keyInfo[3] == 1) {
				myair.setX(speed);
			}
			if (keyInfo[4] == 1) {
				if (missileMake == false) {

					if (score > 10) {
						monTimer = monTimer + 10;
						Missile m = new Missile(myair.getX() - 4, myair.getY());
						missileList.add(m);
						m = new Missile(myair.getX() + 4, myair.getY());
						missileList.add(m);

					} else {
						Missile m = new Missile(myair.getX(), myair.getY());
						missileList.add(m);
					}
					missileMake = true;
				}
			}
		}
	}

	private void createMon() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Random r = new Random();
				MonCenter m = null;
				while (true) {
					try {
						Thread.sleep(monTimer);
						if (redList.size() < 15) {
							int x = r.nextInt(350) + 10;
							int y = r.nextInt(20) + 10;
							if(redList.size()%2==0) {
								m = new RedMon(x, y);
							}else {
								m = new BlueMon(x, y);
							}
							redList.add(m);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	class MyCanvas extends Canvas {

		MyCanvas() {
			this.setSize(400, 500);
			this.setBackground(Color.black);

		}

		@Override
		public void paint(Graphics g) {
			for (int i = 0; i < missileList.size(); i++) {
				Missile nowm = missileList.get(i);
				int mWidth = 7;
				int mHeight = 7;
				int mnowX = nowm.getX();
				int mnowY = nowm.getY();
				if (mnowY <= 20) {
					missileList.remove(i);
					i--;
					continue;
				}
				for (int j = 0; j < redList.size(); j++) {
					MonCenter r = redList.get(j);
					int monWidthStart = r.getX() - mWidth;
					int monWidthEnd = r.getX() + r.getWidth();
					if (monWidthStart < mnowX && mnowX < monWidthEnd) {
						int monHeightStart = r.getY();
						int monHeightEnd = r.getY() + r.getHeight();
						if (monHeightStart < mnowY && mnowY < monHeightEnd) {
							// System.out.println("hit");
							score++;
							redList.remove(r);
							missileList.remove(i);
							i--;
							break;
						}
					}
				}
			}

			if (myair != null) {
					for (int j = 0; j < redList.size(); j++) {
						MonCenter r = redList.get(j);
						int monWidthStart = r.getX() - 7;
						int monWidthEnd = r.getX() + r.getWidth();

						int airnowX = myair.getX();
						int airnowY = myair.getY();

						if (monWidthStart < airnowX && airnowX < monWidthEnd) {
							int monHeightStart = r.getY();
							int monHeightEnd = r.getY() + r.getHeight();
							if (monHeightStart < airnowY && airnowY < monHeightEnd) {
								System.out.println("사망");
								myair = null;
								airDel();
								break;
						}
					}
				}

			}
			// TODO Auto-generated method stub
			if (myair != null) {
				g.setColor(Color.white);
				g.fillRect(myair.getX(), myair.getY(), 5, 5);

			}

			Image redImage = null;
			for (int i = 0; i < redList.size(); i++) {
				//if (redImage == null) {
					redImage = redList.get(i).getMyMon();
			//	}
				if (redList.get(i).getY() >= 380) {
					redList.remove(i);
					i--;
				} else {
					g.drawImage(redImage, redList.get(i).getX(), redList.get(i).getY(), this);
				}
			}

			for (int i = 0; i < missileList.size(); i++) {
				g.setColor(Color.CYAN);
				g.fillRect(missileList.get(i).getX(), missileList.get(i).getY(), 7, 7);
			}
			String s = String.valueOf(score);
			g.setColor(Color.red);
			g.setFont(new Font("궁서", Font.BOLD, 15));// 폰트설정
			g.drawString("Score : " + s, 300, 20);
			g.drawString("비행기 : " + myairNum, 300, 40);

			if (myairNum == 0) {
				g.setFont(new Font("궁서", Font.BOLD, 30));// 폰트설정
				g.drawString("GameOver", 120, 140);
			}

		}
	}

	public void airDel() {
		score = 0;
		if (myairNum > 0) {                       
			myairNum--;       
			if (myairNum != 0)
				myair = new MyAir();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyInfo[0] = 1;
			break;
		case KeyEvent.VK_DOWN:
			keyInfo[1] = 1;
			break;
		case KeyEvent.VK_LEFT:
			keyInfo[2] = 1;
			break;
		case KeyEvent.VK_RIGHT:
			keyInfo[3] = 1;
			break;
		case KeyEvent.VK_SPACE:
			keyInfo[4] = 1;
			break;
		}

		// System.out.println(keyInfo[0]+"/"+keyInfo[1]+"/"+keyInfo[2]+"/"+keyInfo[3]+"/"+keyInfo[4]+"/");
		// mc.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (keyInfo[4] != 1) {
			if (keyInfo[0] == 1) {
				keyInfo[0] = -1;
			}
			if (keyInfo[1] == 1) {
				keyInfo[1] = -1;
			}
			if (keyInfo[2] == 1) {
				keyInfo[2] = -1;
			}
			if (keyInfo[3] == 1) {
				keyInfo[3] = -1;
			}
		} else {
			keyInfo[4] = -1;
			missileMake = false;
		}
	}

}
