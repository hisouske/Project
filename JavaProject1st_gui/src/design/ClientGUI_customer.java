package design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ClientGUI_customer extends JFrame implements ActionListener {

	Socket client2;
	ClientGUI_start gui1;
	JPanel con;
	String cname;
	InputStream is = null;
	OutputStream os = null;
	String port;

	JTextPane startt;
	JTextPane endt;
	Panel picpan;
	JLabel idL;
	JLabel pwL;
	JLabel version;
	JButton loginB;
	JButton joinB;
	private JLabel startl;
	private JLabel endl;
	private JButton btnNewButton;
	private JButton searchb;
	private JButton bookinglb;
	private JButton booking;
	private JButton messageb;

	ClientGUI_customerm cGUI_m = null;

	JPanel bookingP = new JPanel();
	JTextPane numt;
	JLabel lblHouseNumber;

	// 검색
	JPanel searchP = new JPanel();
	JLabel searchl;
	JTextPane searcht;
	JButton searchb2;

	ClientGUI_customer(ClientGUI_start one, String port, String cname) {
		System.out.println(cname);
		this.cname = cname;
		this.gui1 = one;
		this.port = port;
		System.out.println(port);
		ready(Integer.parseInt(port.trim()));
		set();
		basicP();
	recieve(Integer.parseInt(port) - 5000);

	}

	private void recieve(int port) {
		int limit = Integer.parseInt(taka());
		System.out.println(limit);
		String[] nowFile = new String[limit];
		String filename = "";
		for (int i = 0; i < limit; i++) {
			filename = taka();
			nowFile[i]=filename;
			CfileR newsend = new CfileR(port, filename);
			
			String endmsg = taka();
			if (endmsg.equals("전송완료")) {
				newsend.closeAll();
			}
		}
		imageadd(filename,nowFile);

	}

	private void imageadd(String filename,String[] files) {
		for(int i=0; i < files.length; i++) {
			JPanel  picPanSub = new JPanel();
			ImageIcon img= new ImageIcon(files[i]);
			JLabel imgBox = new JLabel(img);
			imgBox.setPreferredSize(new java.awt.Dimension( 100, 100));
			imgBox.setLayout(null);
			System.out.println(files[i]);
			picPanSub.add(imgBox);
			picpan.add(picPanSub);
		}
	}

	void basicP() {
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//this.setBounds(100, 100, 491, 268);
		this.setBounds(100, 100, 700, 268);
		this.setVisible(true);

		con = new JPanel();
		con.setBackground(SystemColor.controlHighlight);
		con.setBorder(new EmptyBorder(5, 5, 5, 5));
		con.setLayout(null);
		getContentPane().add(con);

		JLabel label = new JLabel(cname);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label.setBounds(92, 10, 285, 15);
		con.add(label);

		picpan = new Panel();
		picpan.setBackground(Color.DARK_GRAY);
		picpan.setBounds(92, 32, 285, 175);
		picpan.setLayout(new FlowLayout());
		con.add(picpan);
	//	picpan.setLayout(new CardLayout(0, 0));
	

		searchb = new JButton("검색하기");
		searchb.addActionListener(this);
		searchb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		searchb.setBounds(8, 32, 78, 23);
		con.add(searchb);

		bookinglb = new JButton("예약내역");
		bookinglb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		bookinglb.addActionListener(this);
		bookinglb.setBounds(8, 84, 78, 23);
		con.add(bookinglb);

		booking = new JButton("예약하기");
		booking.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		booking.addActionListener(this);
		booking.setBounds(8, 58, 78, 23);
		con.add(booking);

		messageb = new JButton("Message");
		messageb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		messageb.addActionListener(this);
		messageb.setBounds(8, 111, 78, 23);
		con.add(messageb);

	}

	private void searchP() {

		searchP.setBackground(SystemColor.controlHighlight);
		searchP.setBounds(383, 32, 80, 175);
		con.add(searchP);
		searchP.setLayout(null);
		searchP.setVisible(false);

		searchl = new JLabel("<검색할 지역 >");
		searchl.setBounds(1, 5, 79, 15);
		searchl.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		searchP.add(searchl);

		searcht = new JTextPane();
		searcht.setBounds(1, 30, 79, 16);
		searchP.add(searcht);

		searchb2 = new JButton("찾기");
		searchb2.setBounds(1, 56, 79, 23);
		searchP.add(searchb2); // 검색끝
		searchb2.addActionListener(this);

		bookingP.setVisible(false);
		searchP.setVisible(true);
	}

	private void bookingP() {

		bookingP = new JPanel(); // 예약
		bookingP.setBackground(SystemColor.controlHighlight);
		bookingP.setBounds(383, 32, 80, 175);
		con.add(bookingP);
		bookingP.setLayout(null);
		bookingP.setVisible(false);

		btnNewButton = new JButton("예약");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(0, 0, 80, 23);
		bookingP.add(btnNewButton);
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 11));

		numt = new JTextPane();
		numt.setBounds(0, 81, 80, 17);
		bookingP.add(numt);

		lblHouseNumber = new JLabel("H number");
		lblHouseNumber.setBounds(11, 63, 69, 15);
		bookingP.add(lblHouseNumber);

		startl = new JLabel("check-in");
		startl.setBounds(11, 101, 69, 15);
		bookingP.add(startl);

		startt = new JTextPane();
		startt.setBounds(0, 120, 80, 17);
		bookingP.add(startt);

		endl = new JLabel("check-out");
		endl.setBounds(7, 139, 73, 15);
		bookingP.add(endl);

		endt = new JTextPane();
		endt.setBounds(0, 158, 80, 17);
		bookingP.add(endt); // 예약끝

		searchP.setVisible(false);
		bookingP.setVisible(true);

	}

	private void set() {
		try {
			os = client2.getOutputStream();
			is = client2.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ready(int port) {
		try {
			client2 = new Socket("127.0.0.1", port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String btnName = e.getActionCommand();
		System.out.println(btnName);

		switch (btnName) {
		case "검색하기":
			searchP();

			break;
		case "예약내역":
			tiki(btnName);
			String result2 = taka();
			messageBox(this, result2);
			break;
		case "예약하기":
			System.out.println("aa");
			bookingP();

			break;
		case "예약":
			tiki(btnName);

			tiki(numt.getText() + "/" + startt.getText() + "/" + endt.getText());
			String result = taka();
			messageBox(this, result);
			bookingP.setVisible(false);

			break;

		case "찾기":
			tiki(btnName);
			tiki(searcht.getText());
			String re = taka();
			messageBox(this, re);
			break;

		case "Message":
			tiki(btnName);
			if (cGUI_m == null) {
				cGUI_m = new ClientGUI_customerm(this, port, cname);
			} else {
				cGUI_m.setVis();
			}
			this.setVisible(false);
			break;

		}
	}

	public void setVis() {
		this.setVisible(true);
	}

	public void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

	String taka() {
		String tiki = null;
		try {

			byte[] read1 = new byte[500];
			is.read(read1);
			tiki = new String(read1).trim();
			return tiki;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tiki;
	}

	void tiki(String tiki) {
		try {
			os.write(tiki.trim().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
