package Start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MenuFrame.MenuFrame;
import Start.Start;

public class Start extends JFrame implements ActionListener {
	JLabel pwLabel;
	JTextField pwTF;
	JButton loginBTN;
	JLabel programInfo;
	JPanel mainP = new JPanel();
	JPanel centerP = new JPanel();

	Start() {
		this.setSize(350, 100);
		this.setLocation(300, 100);
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void init() {
		mainP.setLayout(new BorderLayout());
		pwLabel = new JLabel("����� ��ȣ :");
		centerP.add(pwLabel);
		pwTF = new JTextField(12);
		centerP.add(pwTF);
		loginBTN = new JButton("�α���");
		centerP.add(loginBTN);
		mainP.add(centerP, "Center");
			

		loginBTN.addActionListener(this);

		programInfo = new JLabel("Pantone Colorbook v1.0", JLabel.CENTER);

		this.add(mainP, "Center");
		this.add(programInfo, "South");
		System.out.println("555");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String pw = pwTF.getText();
		if (pw.equals("1111")) {
			this.setVisible(false);
			new MenuFrame();
		}
	}
}
