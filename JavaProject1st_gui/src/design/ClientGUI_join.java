package design;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.net.Socket;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

public class ClientGUI_join extends JFrame implements ActionListener, ItemListener {

	ClientGUI_start gui1;
	JPanel popp;
	JPanel con;
	JLabel namel;
	JLabel idl;
	JButton idchk;
	JTextPane idt;
	JTextPane namet;

	JCheckBox licensechk;
	JButton btnNewButton;
	JLabel pwl;
	JTextPane pwt;
	String sortchk = "x";

	ClientGUI_join(ClientGUI_start one) {

		this.gui1 = one;

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 295, 131);
		this.setVisible(true);

		con = new JPanel();
		con.setBackground(SystemColor.controlHighlight);
		con.setBorder(new EmptyBorder(5, 5, 5, 5));
		con.setLayout(null);
		getContentPane().add(con);

		namel = new JLabel("이름");
		namel.setBounds(23, 65, 57, 15);
		con.add(namel);

		idl = new JLabel("ID");
		idl.setBounds(31, 12, 24, 15);
		con.add(idl);

		idchk = new JButton("ID확인");
		idchk.setForeground(Color.RED);
		idchk.setFont(new Font("굴림", Font.PLAIN, 11));
		idchk.setBounds(166, 10, 97, 18);
		con.add(idchk);
		idchk.addActionListener(this);

		idt = new JTextPane();
		idt.setBounds(56, 10, 98, 17);
		con.add(idt);

		namet = new JTextPane();
		namet.setBounds(56, 63, 98, 17);
		con.add(namet);

		licensechk = new JCheckBox("사업자고객");
		licensechk.setBounds(166, 33, 97, 23);
		con.add(licensechk);
		licensechk.addItemListener(this);

		btnNewButton = new JButton("가입하기");
		btnNewButton.setBounds(166, 60, 97, 23);
		con.add(btnNewButton);
		btnNewButton.addActionListener(this);

		getContentPane().add(con);

		pwl = new JLabel("PW");
		pwl.setBounds(25, 39, 32, 15);
		con.add(pwl);

		pwt = new JTextPane();
		pwt.setBounds(56, 37, 98, 17);
		con.add(pwt);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String btnName = e.getActionCommand();
		gui1.tiki(btnName);
		System.out.println(btnName);
		if (btnName.equals("ID확인")) {

			gui1.tiki(idt.getText());
			String result = gui1.taka();
			messageBox(this, result);

		} else if (btnName.equals("가입하기")) {

			gui1.tiki(idt.getText() + "/" + pwt.getText() + "/" + namet.getText() + "/" + sortchk);
			this.setVisible(false);
			ClientGUI_start frame = new ClientGUI_start();
			frame.setVisible(true);

		}

	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void itemStateChanged(ItemEvent a) {
		if (a.getSource() == licensechk) {
			if (a.equals(true))
				sortchk = "o";
		}

	}

}
