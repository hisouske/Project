package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JList;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class ClientGUI_sellerm extends JFrame implements ActionListener, MouseListener {

	ClientGUI_seller t;
	JPanel con;
	String cname;
	String port;

	JButton chatb;
	JButton backb;
	JButton removeb;
	JLabel licenselist;
	String member;
	JFormattedTextField chatbox;
	DefaultListModel model = new DefaultListModel();
	DefaultListModel idmodel = new DefaultListModel();
	JList chatlist;// = new JList<String>(chat);
	ArrayList<String> mem;
	private JList nowcl;

	ClientGUI_sellerm(ClientGUI_seller clientGUI22, String port, String cname) {
		getContentPane().setBackground(SystemColor.controlHighlight);

		this.t = clientGUI22;
		this.port = port;
		this.cname = cname;

//		String nowid = t.taka();
//		System.out.println("아이디"+nowid);
		idset(t.taka());
		basicP();
		taka();

	}

	private void idset(String set) {
		idmodel.removeAllElements();
		StringTokenizer slash = new StringTokenizer(set, "/");
		while (slash.hasMoreTokens()) {
			idmodel.addElement(slash.nextToken());

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnName = e.getActionCommand();
		System.out.println(btnName);

		switch (btnName) {

		case "Send":
			t.tiki(chatbox.getText());
			StringTokenizer st = new StringTokenizer(chatbox.getText(), ">");
			st.nextToken();
			if (st.hasMoreTokens()) {
				String msg = st.nextToken();

				model.addElement("나 :" + msg);
				chatlist = new JList(model);
			}
			chatbox.setText("");

			break;
		case "Remove":
			model.removeAllElements();
			chatlist = new JList(model);
			break;
		case "Back":
			t.tiki("<<<종료>>>");
			t.setVis();
//			new ClientGUI22(t.gui1, t.port, t.cname);
			this.setVisible(false);

//			this.rem
			break;

		}

	}

	public void setVis() {
		this.setVisible(true);
	}

	void basicP() {
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 377, 251);
		this.setVisible(true);
		getContentPane().setLayout(null);

		chatbox = new JFormattedTextField();
		chatbox.setText("id> 보낼내용");
		chatbox.setBounds(12, 183, 192, 21);
		getContentPane().add(chatbox);

		chatlist = new JList(model);
		chatlist.setBackground(Color.WHITE);
		chatlist.setBounds(12, 10, 260, 163);
		getContentPane().add(chatlist);

		chatb = new JButton("Send");
		chatb.addActionListener(this);
		chatb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));

		chatb.setBounds(208, 182, 64, 23);
		getContentPane().add(chatb);

		backb = new JButton("Back");
		backb.addActionListener(this);
		backb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		backb.setBounds(284, 10, 67, 23);
		getContentPane().add(backb);

		removeb = new JButton("Remove");
		removeb.addActionListener(this);
		removeb.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		removeb.setBounds(284, 37, 67, 23);
		getContentPane().add(removeb);

		licenselist = new JLabel("<접속중C>");
		licenselist.setHorizontalAlignment(SwingConstants.CENTER);
		licenselist.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		licenselist.setBounds(284, 70, 67, 15);
		getContentPane().add(licenselist);

		nowcl = new JList(idmodel);
		nowcl.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		nowcl.setBounds(284, 96, 67, 108);
		nowcl.addMouseListener(this);
		getContentPane().add(nowcl);
	}

	private void taka() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (;;) {
						
						byte[] read1 = new byte[100];
						t.is.read(read1);
						String tiki = new String(read1).trim();
						System.out.println(tiki);
						if (tiki.indexOf(":") != -1) {
							model.addElement(tiki);
							chatlist = new JList(model);
						} else {
							idset(tiki);
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (nowcl == e.getComponent()) {

			chatbox.setText(nowcl.getSelectedValue() + ">");

		}
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
