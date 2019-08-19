package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Character.Subset;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ClientGUI_customerm extends JFrame implements ActionListener, MouseListener {

	ClientGUI_customer t;
	JPanel con;
	String cname;
	String port;
	DefaultListModel model = new DefaultListModel();
	DefaultListModel idmodel = new DefaultListModel();
	JButton chatb;
	JButton backb;
	JButton removeb;
	JLabel licenselist;
	JList<String> list;
	JList chatlist;// = new JList(model);
	JList nowll;
	JFormattedTextField chatbox;

	ClientGUI_customerm(ClientGUI_customer two, String port, String cname) {
		getContentPane().setBackground(SystemColor.controlHighlight);

		this.t = two;
		this.port = port;
		this.cname = cname;

//		String nowid = t.taka();
//		System.out.println("아이디" + nowid);
		idset(t.taka());
		basicP();
		taka();

	}

	private void idset(String nowid) {
		idmodel.removeAllElements();
		StringTokenizer slash = new StringTokenizer(nowid, "/");
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
			// new ClientGUI2(t.gui1, t.port, t.cname);
			t.setVis();
			this.setVisible(false);
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

		licenselist = new JLabel("<접속중L>");
		licenselist.setHorizontalAlignment(SwingConstants.CENTER);
		licenselist.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		licenselist.setBounds(284, 70, 67, 15);
		getContentPane().add(licenselist);

		nowll = new JList(idmodel);
		nowll.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		nowll.setBounds(284, 95, 65, 108);
		nowll.addMouseListener(this);
		getContentPane().add(nowll);
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (nowll == e.getComponent()) {
			chatbox.setText(nowll.getSelectedValue() + ">");

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
