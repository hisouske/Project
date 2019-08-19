package design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ClientGUI_seller extends JFrame implements ActionListener {

	Socket client2;
	ClientGUI_start gui1;
	JPanel con;
	String cname;
	InputStream is = null;
	OutputStream os = null;
	String port;

	ClientGUI_sellerm cGUI_m = null;

	JTextPane regiont;
	JTextPane pricet;
	Panel picpan;
	JLabel idL;
	JLabel pwL;
	JLabel version;
	JButton loginB;
	JButton joinB;
	private JLabel startl;
	private JLabel endl;
	private JButton reb;
	private JButton nowb;
	private JButton newregib;
	private JButton resib;
	private JButton delb;

	private FileInputStream fn = null;
	private BufferedInputStream br = null;

	JPanel resP = new JPanel();
	JTextPane namet;
	JLabel lblHouseNumber;

	JTextPane pict;
	JButton picb;
	// 검색
	JPanel delP = new JPanel();
	JLabel dell;
	JTextPane delt;
	JButton delnumb;
	private JButton messagb;

	ClientGUI_seller(ClientGUI_start one, String port, String cname) {
		System.out.println(cname);
		this.cname = cname;
		this.gui1 = one;
		this.port = port;
		System.out.println(port);
		ready(Integer.parseInt(port.trim()));
		set();
		basicP();
		

	}




	private void basicP() {

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 491, 268);
		this.setVisible(true);

		con = new JPanel();
		con.setBackground(SystemColor.controlHighlight);
		con.setBorder(new EmptyBorder(5, 5, 5, 5));
		con.setLayout(null);
		getContentPane().add(con);

		JLabel label = new JLabel(cname);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label.setBounds(92, 10, 212, 15);
		con.add(label);

		picpan = new Panel();
		picpan.setBackground(Color.DARK_GRAY);
		picpan.setBounds(92, 32, 285, 175);
		con.add(picpan);
		picpan.setLayout(new CardLayout(0, 0));

		nowb = new JButton("예약현황");
		nowb.addActionListener(this);
		nowb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		nowb.setBounds(8, 32, 78, 23);
		con.add(nowb);

		newregib = new JButton("신규등록");
		newregib.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		newregib.addActionListener(this);
		newregib.setBounds(8, 84, 78, 23);
		con.add(newregib);

		resib = new JButton("등록현황");
		resib.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		resib.addActionListener(this);
		resib.setBounds(8, 58, 78, 23);
		con.add(resib);

		delb = new JButton("숙소삭제");
		delb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		delb.addActionListener(this);
		delb.setBounds(8, 111, 78, 23);
		con.add(delb);

		messagb = new JButton("Message");
		messagb.addActionListener(this);
		messagb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		messagb.setBounds(8, 138, 78, 23);
		con.add(messagb);

	}

	private void delP() {

		delP.setBackground(SystemColor.controlHighlight);
		delP.setBounds(383, 32, 80, 175);
		con.add(delP);
		delP.setLayout(null);
		delP.setVisible(false);

		dell = new JLabel("<숙소 Num >");
		dell.setBounds(1, 5, 79, 15);
		dell.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		delP.add(dell);

		delt = new JTextPane();
		delt.setBounds(1, 30, 79, 16);
		delP.add(delt);

		delnumb = new JButton("삭제");
		delnumb.setBounds(1, 56, 79, 23);
		delP.add(delnumb); // 검색끝
		delnumb.addActionListener(this);

		resP.setVisible(false);
		delP.setVisible(true);
	}

	private void resP() {

		resP = new JPanel(); // 예약
		resP.setBackground(SystemColor.controlHighlight);
		resP.setBounds(383, 32, 80, 175);
		con.add(resP);
		resP.setLayout(null);
		resP.setVisible(false);

		reb = new JButton("정보등록");
		reb.addActionListener(this);
		reb.setBounds(0, 0, 80, 23);
		resP.add(reb);
		reb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));

		namet = new JTextPane();
		namet.setBounds(0, 46, 80, 17);
		resP.add(namet);

		lblHouseNumber = new JLabel("숙소이름");
		lblHouseNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHouseNumber.setBounds(0, 28, 80, 15);
		resP.add(lblHouseNumber);

		startl = new JLabel("지역");
		startl.setHorizontalAlignment(SwingConstants.CENTER);
		startl.setBounds(0, 67, 80, 15);
		resP.add(startl);

		regiont = new JTextPane();
		regiont.setBounds(0, 85, 80, 17);
		resP.add(regiont);

		endl = new JLabel("1박가격");
		endl.setHorizontalAlignment(SwingConstants.CENTER);
		endl.setBounds(0, 107, 80, 15);
		resP.add(endl);

		pricet = new JTextPane();
		pricet.setBounds(0, 123, 80, 17);
		resP.add(pricet); // 예약끝

		picb = new JButton("사진등록");
		picb.addActionListener(this);
		picb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		picb.setBounds(299, 7, 78, 23);
		con.add(picb);

		pict = new JTextPane();
		pict.setBounds(383, 10, 80, 17);
		con.add(pict);

		delP.setVisible(false);
		resP.setVisible(true);

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
		case "예약현황":
			tiki(btnName);
			String a = taka();
			messageBox(this, a);
			break;
		case "등록현황":
			tiki(btnName);
			String b = taka();
			messageBox(this, b);
			break;
		case "신규등록":
			resP();
			break;
		case "사진등록":
			tiki(btnName);
			tiki(pict.getText());
System.out.println(pict.getText());
			new CfileS((Integer.parseInt(port) - 5000), pict.getText());
			tiki("파일전송완료");
			System.out.println("aaaa");

			break;

		case "숙소삭제":

			delP();

			break;

		case "삭제":
			tiki(btnName);
			tiki(delt.getText());
			String re = taka();
			messageBox(this, re);
			break;
		case "등록":
			tiki(btnName);
			tiki(namet.getText() + "/" + regiont.getText() + "/" + pricet.getText());

			messageBox(this, "등록되었습니다");
			break;

		case "Message":
			tiki(btnName);
			if (cGUI_m == null) {
				cGUI_m = new ClientGUI_sellerm(this, port, cname);
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

	public static void messageBox(Object obj, String message) {
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
