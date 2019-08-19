package SameMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements MouseListener, ActionListener {

	Image img = new ImageIcon().getImage();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel buttonP = new JPanel();
	JPanel scoreP = new JPanel();

	Label score = new Label("Score   :0");
	int sum1 = 0;
	int point = 0;
	int click = 0;
	Random r = new Random();

	JButton[] bt = new JButton[16];
	JButton[] btcov = new JButton[16];
	JButton replay = new JButton("다시하기");
	JButton end = new JButton("종료");

	final ImageIcon main = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\main.jpg");

	ImageIcon[] icon = new ImageIcon[9];

	JButton start = new JButton("start", main);
	
	int clickCnt=-1;
	int[] clickbtn=new int[2];
	
	JButton okbtn = null;

	public Menu() {
		setTitle("같은그림 찾기 v1.0");

		scoreP.setLayout(new BoxLayout(scoreP, BoxLayout.X_AXIS));
		scoreP.setPreferredSize(new Dimension(3, 20));
		scoreP.add(score);

		buttonP.add(end);
		buttonP.add(replay);
		p1.setLayout(new GridLayout(4, 4));
	//	p2.setLayout(new GridLayout(4, 4));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container con = getContentPane();
		this.setLayout(new BorderLayout());
		this.setSize(580, 680);
		this.add(start);
		this.setLocation(150, 150);
		this.setVisible(true);

		init();
		shift();
		setDB();

		start.addActionListener(this);
		replay.addActionListener(this);

	}

//	public void shift() {
//		for (int i = 0; i < 16; i++) {
//			int idx = (int) (Math.random() * 16);
//			String tmp = bt[i].getText();
//			bt[i].setText(bt[idx].getText());
//			bt[idx].setText(tmp);// 숫자만 바꿔주기. 배열은 그대로있음
//		}
//		
//		for(int i=0; i< 16; i++) {
//			System.out.println(i+"/"+bt[i].getActionCommand());
//		}
//		
//	}
//
//	public void init() {
//
//		for (int i = 0; i < 16; i++)// 배열로 변경해서 버튼 생성
//		{
//			bt[i] = new JButton((i + 1) + "");
//			p1.add(bt[i]);
//			bt[i].addMouseListener(this);
//
//		}
//	}

	// - kim

	public void shift() {
		boolean[] flag = new boolean[16];
	//	int[] idxlist = new int[16];
		for (int i = 0; i < 16; i++) {
			int idx = r.nextInt(16);
//			System.out.println(idx);

			if (flag[idx] == false) {
				p1.add(bt[idx]);
			//	p2.add(btcov[idx]);
				flag[idx] = true;
			//	idxlist[i] = idx+1;
			//	System.out.println(idxlist[i]+"aaaa");
			} else {
				i--;
			}
			System.out.println(i);
			// String tmp = bt[i].getText();
			// bt[i].setText(bt[idx].getText());
			// bt[idx].setText(tmp);// 숫자만 바꿔주기. 배열은 그대로있음
		}

		// for(int i=0; i< 16; i++) {
		// System.out.println(i+"/"+bt[i].getActionCommand());
		// }

	}

	
	public void init() {

		icon[0] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\1.jpg");
		icon[1] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\2.jpg");
		icon[2] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\3.jpg");
		icon[3] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\4.jpg");
		icon[4] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\5.jpg");
		icon[5] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\6.jpg");
		icon[6] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\7.jpg");
		icon[7] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\8.jpg");
		icon[8] = new ImageIcon("C:\\Users\\HU-203-17\\Pictures\\0.jpg");
		for (int i = 0; i < 8; i++)// 배열로 변경해서 버튼 생성
		{
			int aint = i + 1;
			bt[i] = new JButton(aint + "", icon[i]);
		//	btcov[i] = new JButton(aint + "", icon[8]);
			bt[i].addMouseListener(this);
		//	btcov[i].addMouseListener(this);
			int dint = 16 - i;
			bt[15 - i] = new JButton(dint + "", icon[i]);
		//	btcov[15 - i] = new JButton(dint + "", icon[8]);
			bt[15 - i].addMouseListener(this);
		//	btcov[15 - i].addMouseListener(this);
		}
		
		
		
		for(int i=0; i<bt.length;i++) {
			bt[i].setIcon(icon[8]);
		}

		
	}

	// - kim end

	private void setDB() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {

		String btnName = e.getActionCommand();

		if (btnName.equals("start")) {

			System.out.println("aa");
			this.remove(start);
			this.add(buttonP, "South");
			this.add(scoreP, "North");
			this.add(p1, "Center");
			this.setVisible(true);
			this.pack();

		}
		if (btnName.equals("다시하기")) {
			this.setVisible(false);
			new Menu();

		}

	}
	
	public int imgIndex(int value) {
		for(int i=0; i < bt.length; i++) {
			int k = Integer.valueOf(bt[i].getText());
			System.out.println("비교 : "+i+ " / "+k+" / "+value);
			if(value == k) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		//Object btn = e.getSource();
		String cBtn = ((JButton) e.getSource()).getText();
		
		if(clickCnt == -1 ) {
			clickCnt++;
			clickbtn[0]=Integer.valueOf(cBtn);
		}else if(clickCnt == 0) {
			clickCnt=-1;
			clickbtn[1]=Integer.valueOf(cBtn);
			
			int checkSum = clickbtn[0]+clickbtn[1];
			if(checkSum == 17) {
				System.out.println("같은그림");
				System.out.println(clickbtn[0]+"/"+clickbtn[1]);
				int n1 = imgIndex(clickbtn[0]);
				int n2 = imgIndex(clickbtn[1]);
				System.out.println("aa : "+n1+"/"+n2);
				
				int imgIndex1=0;
				int imgIndex2=0;		
				if(clickbtn[0] < 8) {
					imgIndex1 = clickbtn[0]%8-1;
					imgIndex2= 16-clickbtn[1];
				}else {
					imgIndex1 = 16- clickbtn[0];
					imgIndex2= clickbtn[1]%8-1;
					if(clickbtn[0]==8) {
						imgIndex1--;
					}
					
				}
				bt[n1].setIcon(icon[imgIndex1]);
				bt[n2].setIcon(icon[imgIndex2]);
			//	bt[n1].setIcon(icon[n1-1]);
			//	bt[n2].setIcon(icon[n2-1]);
				
			} else {
				System.out.println("다른그림");
			}
		}
		
//		for(int i=0; i< bt.length; i++) {
//			if(clickbtn[0] !=i && clickbtn[1] !=i) {
//				bt[imgIndex(clickbtn[0])].setIcon(icon[8]);
//			}else {
//				System.out.println("aa111");
//				bt[imgIndex(Integer.valueOf(bt[i].getText()))].setIcon(icon[i-1]);
//			}
//		}
//		for (int i = 0; i < bt.length; i++) {
//			if (btn == bt[i]) {
//				System.out.println(i);
//			}
//		}
//
//		String num = ((JButton) e.getSource()).getText();
//
//		int num1 = Integer.parseInt(((JButton) e.getSource()).getText());
//		int sum2 = 0;
//
//		if (num.equals("1")) {
//			p2.remove(btcov[0]);
//
//			p2.add(bt[0]);
//			this.setVisible(true);
//			click++;
//
//		} else if (num.equals("2")) {
//			p2.remove(btcov[1]);
//
//			p2.add(bt[1]);
//			this.setVisible(true);
//			click++;
//		} else if (num.equals("3")) {
//
//			click++;
//		} else if (num.equals("4")) {
//
//			click++;
//		} else if (num.equals("5")) {
//
//			click++;
//		} else if (num.equals("6")) {
//
//			click++;
//		} else if (num.equals("7")) {
//
//			click++;
//		} else if (num.equals("8")) {
//
//			click++;
//		} else if (num.equals("9")) {
//
//			click++;
//		} else if (num.equals("10")) {
//
//			click++;
//		} else if (num.equals("11")) {
//
//			click++;
//		} else if (num.equals("12")) {
//
//			click++;
//		} else if (num.equals("13")) {
//
//			click++;
//		} else if (num.equals("14")) {
//
//			click++;
//		} else if (num.equals("15")) {
//
//			click++;
//		} else if (num.equals("16")) {
//
//			click++;
//
//		}
//		if (click != 0) {
//			if (click % 2 == 1) {
//				sum1 = num1;
//			}
//			if (click % 2 == 0) {
//				sum2 = num1;
//				System.out.println(sum2);
//				System.out.println(sum1);
//
//				if ((sum1 + sum2) == 17) {
//					point = point + 10;
//					String pointS = Integer.toString(point);
//					System.out.println("맞췄다");
//					score.setText("Score    :" + pointS);
//				} else {
//					point = point - 15;
//					String pointS = Integer.toString(point);
//					System.out.println("틀렸다");
//					score.setText("Score    :" + pointS);
//				}
//			}
//
//		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
