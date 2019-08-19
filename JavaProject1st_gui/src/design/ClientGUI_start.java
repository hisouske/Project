package design;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JPasswordField;

public class ClientGUI_start extends JFrame implements ItemListener, ActionListener {

	Socket client1 = null;
	Scanner in = new Scanner(System.in);
	InputStream is = null;
	OutputStream os = null;
	JPanel contentPane;
	JTextPane idt;
	JLabel idL;
	JLabel pwL;
	JLabel version;
	JButton loginB;
	JButton joinB;
	private JPasswordField pwt;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ClientGUI_start frame = new ClientGUI_start();
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void set() {
		try {
			os = client1.getOutputStream();
			is = client1.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClientGUI_start() {
		ready();
		set();

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 295, 131);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		getContentPane().add(contentPane);

		idt = new JTextPane();
		idt.setBounds(50, 10, 103, 21);
		contentPane.add(idt);

		idL = new JLabel("ID");
		idL.setBounds(22, 14, 22, 15);
		contentPane.add(idL);

		pwL = new JLabel("PW");
		pwL.setBounds(20, 42, 22, 15);
		contentPane.add(pwL);

		version = new JLabel("project v 1.0");
		version.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		version.setBounds(75, 71, 115, 15);
		contentPane.add(version);

		loginB = new JButton("로그인");
		loginB.addActionListener(this);

		loginB.setBounds(165, 10, 97, 49);
		contentPane.add(loginB);

		joinB = new JButton("회원가입");
		joinB.addActionListener(this);

		joinB.setBounds(165, 65, 97, 23);
		contentPane.add(joinB);

		pwt = new JPasswordField();
		pwt.setBounds(50, 39, 103, 21);
		contentPane.add(pwt);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnName = e.getActionCommand();
		System.out.println(btnName);
		if (btnName.equals("로그인")) {
//			try {
			tiki(btnName);
			tiki(idt.getText() + "/" + pwt.getText());
			String cname = taka();
			System.out.println(cname);
	

			if (!cname.equals("다시")) {

				String license = taka();
				System.out.println(license);
				StringTokenizer slash = new StringTokenizer(license, "/");
				String port = slash.nextToken();
				System.out.println(port);
				String licen = slash.nextToken();
				System.out.println(licen);

				if (licen.equals("o")) {

					this.setVisible(false);
					new ClientGUI_seller(this, port, cname);
					
				} else if (licen.equals("x")) {
					this.setVisible(false);
					new ClientGUI_customer(this, port, cname);

				}
			}

		}

		if (btnName.equals("회원가입")) {
			tiki(btnName);
			this.setVisible(false);
			new ClientGUI_join(this);

		}
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

	private void ready() {
		try {
			client1 = new Socket("127.0.0.1", 8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
