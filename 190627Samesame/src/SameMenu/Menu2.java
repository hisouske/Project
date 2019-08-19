package SameMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu2 extends JFrame implements MouseListener 
	{ 
	  private JButton jbOK = new JButton("OK"); 
	  private JButton jbReset = new JButton("RESET"); 
	  private JButton jb[] = new JButton[9];//배열로 변경 
	  private JTextField jtf = new JTextField(); 
	  private JPanel jp1 = new JPanel(new BorderLayout()); 
	  private JPanel jp2 = new JPanel(new FlowLayout()); 
	  private JPanel jp3 = new JPanel(new GridLayout(3, 3)); 
	  private String iStr = ""; 
	  private String password = "1234"; 
	  private String iResult = ""; 
	  public Menu2()   { 
	    super("도어락프로그램"); 
	    init(); 
	    start(); 
	    shift();//렌덤 숫자 배열 
	 } 
	 public void shift() 
	 { 
	   for(int i=0;i < 9;i++) 
	   { 
	     int idx = (int)(Math.random() * 9);//랜덤 위치 얻어오기 
	     String tmp = jb[i].getText(); 
	     jb[i].setText(jb[idx].getText()); 
	     jb[idx].setText(tmp);//숫자만 바꿔주기 
	  } 
	} 
	public void init() 
	{ 
	  jp1.add("North", jtf); 
	  jp1.add("South", jp2); 
	  jp2.add(jbOK); 
	  jp2.add(jbReset); 
	  jp1.add("Center", jp3); 
	  for(int i=0;i < 9;i++)//배열로 변경해서 버튼 생성 
	  { 
	    jb[i] = new JButton((i+1) + ""); 
	    jp3.add(jb[i]); 
	    jb[i].addMouseListener(this); 
	 } 
	 this.add(jp1); 
	 this.setSize(300, 400); 
	 this.setVisible(true); 
	} 
	public void start() 
	{ 
	  jbOK.addMouseListener(this); 
	  jbReset.addMouseListener(this); 
	} 
	@Override 
	public void mouseClicked(MouseEvent e) 
	{ 
	  String num = ((JButton)e.getSource()).getText();//누룬 버튼의 숫자 얻어오기  
	  if (num.equals("1")) 
	  { 
	    iStr = iStr + "1"; 
	    jtf.setText(iStr); 
	 } else if (num.equals("2")) 
	 { 
	   iStr = iStr + "2"; 
	   jtf.setText(iStr); 
	} else if (num.equals("3")) 
	{ 
	  iStr = iStr + "3"; 
	  jtf.setText(iStr); 
	} else if (num.equals("4")) 
	{ 
	  iStr = iStr + "4"; 
	  jtf.setText(iStr); 
	} else if (num.equals("5")) 
	{ 
	  iStr = iStr + "5"; 
	  jtf.setText(iStr); 
	} else if (num.equals("6")) 
	{ 
	  iStr = iStr + "6"; 
	  jtf.setText(iStr); 
	} else if (num.equals("7")) 
	{ 
	  iStr = iStr + "7"; 
	  jtf.setText(iStr); 
	} else if (num.equals("8")) 
	{ 
	  iStr = iStr + "8"; 
	  jtf.setText(iStr); 
	} else if (num.equals("9")) 
	{ 
	  iStr = iStr + "9"; 
	  jtf.setText(iStr); 
	} else if (e.getSource() == (JButton) jbOK) 
	{ 
	  System.out.println(iStr); 
	  System.out.println(password); 
	  if (password.equals(iStr)) 
	  { 
	    iResult = "문이열립니다."; 
	 } else 
	 { 
	   iResult = "오류입니다."; 
	} 
	//DoorDialog dd = new DoorDialog(this, "알림창", true, iResult); 
	} else if (e.getSource() == (JButton) jbReset) 
	{ 
	  iStr = ""; 
	  iResult = ""; 
	  jtf.setText(iStr); 
	} 
	} 
	@Override 
	public void mouseEntered(MouseEvent arg0) 
	{ 
	} 
	@Override 
	public void mouseExited(MouseEvent arg0) 
	{ 
	} 
	@Override 
	public void mousePressed(MouseEvent arg0) 
	{ 
	} 
	@Override 
	public void mouseReleased(MouseEvent arg0) 
	{ 
	} 
	public static void main(String args[]) 
	{ 
	  new Menu2(); 
	} 
	} 

