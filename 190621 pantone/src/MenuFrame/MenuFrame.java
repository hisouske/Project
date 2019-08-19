package MenuFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.ColorDAO;
import DTO.ColorDTO;

public class MenuFrame extends JFrame implements ItemListener, ActionListener {

	List colorList = new List();
	List serchList = new List();
	JLabel colorListLabel = new JLabel("color list", JLabel.CENTER);

	JLabel r = new JLabel("R", JLabel.CENTER);
	JLabel g = new JLabel("G", JLabel.CENTER);
	JLabel b = new JLabel("B", JLabel.CENTER);
	JLabel c = new JLabel("C", JLabel.CENTER);
	JLabel m = new JLabel("M", JLabel.CENTER);
	JLabel y = new JLabel("Y", JLabel.CENTER);
	JLabel k = new JLabel("K", JLabel.CENTER);

	JLabel colnamel = new JLabel("color name ", JLabel.CENTER);
	JLabel coltypel = new JLabel("color type ", JLabel.CENTER);
	// 컬타입. 컬네임 바꿔야함 !

	JPanel ColorP = new JPanel();
	JPanel ListP = new JPanel();
	JPanel RgbP = new JPanel();
	JPanel CmykP = new JPanel();
	JPanel RgbcmykP = new JPanel();
	JPanel ButtonP1 = new JPanel();
	JPanel ButtonP2 = new JPanel();

	JPanel ButtonP3 = new JPanel();

	JPanel rgbcmykP = new JPanel();

	JButton addB = new JButton("등록");
	JButton delB = new JButton("삭제");
	JButton serB = new JButton("검색");

	JButton savB = new JButton("저장");
	JButton reB = new JButton("이전");

	JButton serB2 = new JButton("찾기");
	JButton reB2 = new JButton("이전");

	TextArea colnamet = new TextArea(1, 2);
	TextArea coltypet = new TextArea(1, 2);

	TextArea rt = new TextArea(1, 3);
	TextArea gt = new TextArea(1, 3);
	TextArea bt = new TextArea(1, 3);
	TextArea ct = new TextArea(1, 3);
	TextArea mt = new TextArea(1, 3);
	TextArea yt = new TextArea(1, 3);
	TextArea kt = new TextArea(1, 3);

	Label blank = new Label(" ");

	ColorDAO cdao = null;
	ArrayList<ColorDTO> clist = null;

	public MenuFrame() {
		// TODO Auto-generated constructor stub1111
		this.setBounds(100, 100, 500, 200);

		init();
		setDB();
		set();
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void init() {
		setTitle("Color Chart");
		this.setLayout(new FlowLayout());
		ColorP.setLayout(new BoxLayout(ColorP, BoxLayout.Y_AXIS));
		ListP.setLayout(new BoxLayout(ListP, BoxLayout.Y_AXIS));
		RgbP.setLayout(new GridLayout(2, 3));
		CmykP.setLayout(new GridLayout(2, 4));
		RgbcmykP.setLayout(new BoxLayout(RgbcmykP, BoxLayout.Y_AXIS));
		ButtonP1.setLayout(new BoxLayout(ButtonP1, BoxLayout.Y_AXIS));
		ButtonP2.setLayout(new BoxLayout(ButtonP2, BoxLayout.Y_AXIS));
		ButtonP2.setPreferredSize(new Dimension(65, 70));
		ButtonP3.setLayout(new BoxLayout(ButtonP3, BoxLayout.Y_AXIS));
		
		blank.setBackground(new Color(255, 255, 255));

		ListP.add(blank);
		ListP.add(colorListLabel);
		ListP.add(colorList);
		this.add(ListP);

		ColorP.add(colnamel);
		ColorP.add(colnamet);
		ColorP.add(coltypel);
		ColorP.add(coltypet);
		this.add(ColorP);

		RgbP.add(r);
		RgbP.add(g);
		RgbP.add(b);

		RgbP.add(rt);
		RgbP.add(gt);
		RgbP.add(bt);

		RgbcmykP.add(RgbP);

		CmykP.add(c);
		CmykP.add(m);
		CmykP.add(y);
		CmykP.add(k);

		CmykP.add(ct);
		CmykP.add(mt);
		CmykP.add(yt);
		CmykP.add(kt);

		RgbcmykP.add(CmykP);

		this.add(RgbcmykP);
		ButtonP1.add(addB);
		ButtonP1.add(serB);
		ButtonP1.add(delB);
		this.add(ButtonP1);

		ButtonP2.add(savB);
		ButtonP2.add(reB);

		ButtonP3.add(serB2);
		ButtonP3.add(reB2);

		colorList.addItemListener(this);
		addB.addActionListener(this);
		delB.addActionListener(this);
		serB.addActionListener(this);
		serB2.addActionListener(this);
		savB.addActionListener(this);
		reB.addActionListener(this);
		reB2.addActionListener(this);
	}

	public void setDB() {
		cdao = ColorDAO.getInstance();
		listUpdate();
	}

	public void listUpdate() {
		clist = cdao.getList();
		colorList.removeAll();
		if (clist != null) {
			ColorDTO imsi = null;
			for (int i = 0; i < clist.size(); i++) {
				imsi = clist.get(i);
				colorList.add(imsi.getColname());

				set();
			}
		}
	}

	public void set() {
		blank.setBackground(new Color(255, 255, 255));
		colnamet.setText("");
		coltypet.setText("");
		rt.setText("");
		gt.setText("");
		bt.setText("");
		ct.setText("");
		mt.setText("");
		yt.setText("");
		kt.setText("");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		set();
		int selNum = colorList.getSelectedIndex();
		ColorDTO imsi = clist.get(selNum);

		colnamet.append(imsi.getColname());
		coltypet.append(imsi.getColtype());
		rt.append(imsi.getR());
		gt.append(imsi.getG());
		bt.append(imsi.getB());
		ct.append(imsi.getC());
		mt.append(imsi.getM());
		yt.append(imsi.getY());
		kt.append(imsi.getK());

		int rr = Integer.parseInt(imsi.getR());
		int gg = Integer.parseInt(imsi.getG());
		int bb = Integer.parseInt(imsi.getB());
		System.out.println(rr + "/" + gg + "/" + bb);

		blank.setBackground(new Color(rr, gg, bb));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		set();
		String btnName = e.getActionCommand();
		System.out.println(btnName);

		if (btnName.equals("삭제")) {
			int selNum = colorList.getSelectedIndex();
			System.out.println(selNum);
			ColorDTO imsi = clist.get(selNum);
			cdao.del(imsi.getColname());
			listUpdate();

		} else if (btnName.equals("등록")) {
			set();
			this.remove(ButtonP1);
			this.add(ButtonP2);
			this.setVisible(true);

		} else if (btnName.equals("저장")) {
			ColorDTO imsi = new ColorDTO();

			imsi.setColname(colnamet.getText());
			imsi.setColtype(coltypet.getText());
			imsi.setR(rt.getText());
			imsi.setG(gt.getText());
			imsi.setB(bt.getText());
			imsi.setC(ct.getText());
			imsi.setM(mt.getText());
			imsi.setY(yt.getText());
			imsi.setK(kt.getText());

			if (colnamet.getText().equals("") || coltypet.getText().equals("") || rt.getText().equals("")
					|| gt.getText().equals("") || bt.getText().equals("") || ct.getText().equals("")
					|| mt.getText().equals("") || yt.getText().equals("") || kt.getText().equals("")) {
				System.out.println("널값들어갔슈");
				set();
			} else {
				cdao.insert(imsi);
				listUpdate();
			}
		} else if (btnName.equals("이전")) {
			new MenuFrame();
			this.setVisible(false);
		}

		else if (btnName.equals("검색")) {

			colorList.removeAll();
			this.remove(ButtonP1);
			this.add(ButtonP3);
			this.setVisible(true);

			colnamet.setEditable(false);
			rt.setEditable(false);
			gt.setEditable(false);
			bt.setEditable(false);
			ct.setEditable(false);
			mt.setEditable(false);
			yt.setEditable(false);
			kt.setEditable(false);

		} else if (btnName.equals("찾기")) {
			clist = cdao.selectCol(coltypet.getText());
			if (clist != null) {
			ColorDTO imsi = null;
				for (int i = 0; i < clist.size(); i++) {
					imsi = clist.get(i);
					colorList.add(imsi.getColname());
				}
			}

		}
	}
}
