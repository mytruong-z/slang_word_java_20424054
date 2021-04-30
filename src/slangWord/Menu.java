package slangWord;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener {
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
	SlangReadFile slangWord;

	Menu() {
		slangWord = SlangReadFile.getInstance();
		// A Label
		JLabel label = new JLabel("Slang Words");
		label.setForeground(Color.red);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		label.setAlignmentX(CENTER_ALIGNMENT);
		
		btn1 = new JButton("1. Danh Sách");
		btn1.addActionListener(this);
		btn1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn1.setFocusable(false);

		btn2 = new JButton("2. Tìm Kiếm Theo Slang word");
		btn2.addActionListener(this);
		btn2.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn2.setFocusable(false);

		btn3 = new JButton("3. Thêm Slang word");
		btn3.addActionListener(this);
		btn3.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn3.setFocusable(false);

		btn4 = new JButton("4. Random Slang Words");
		btn4.addActionListener(this);
		btn4.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn4.setFocusable(false);

		btn5 = new JButton("5. Lịch Sử Tìm Kiếm");
		btn5.addActionListener(this);
		btn5.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn5.setFocusable(false);

		btn6 = new JButton("6. Xóa Slang Word");
		btn6.addActionListener(this);
		btn6.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn6.setFocusable(false);

		btn7 = new JButton("7. Reset Danh Sách Slang Word Gốc");
		btn7.addActionListener(this);
		btn7.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn7.setFocusable(false);

		btn8 = new JButton("8. Thoát");
		btn8.addActionListener(this);
		btn8.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn8.setFocusable(false);

		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(3, 3, 10, 10));
		panelCenter.add(btn1);
		panelCenter.add(btn2);
		panelCenter.add(btn3);
		panelCenter.add(btn4);
		panelCenter.add(btn5);
		panelCenter.add(btn6);
		panelCenter.add(btn7);
		panelCenter.add(btn8);

		Dimension size2 = new Dimension(600, 500);
		panelCenter.setMaximumSize(size2);
		panelCenter.setPreferredSize(size2);
		panelCenter.setMinimumSize(size2);
		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(label);
		con.add(Box.createRigidArea(new Dimension(0, 30)));
		con.add(panelCenter);

		// Setting Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Menu Window");
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn1) {
			this.dispose();
			try {
				new ListWFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == btn2) {
			System.out.println("Change Actitity");
			this.dispose();
			try {
				new FindFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == btn3) {
			// Add a slang word
			this.dispose();
			new AddSlangWordFrame();

		} else if (e.getSource() == btn4) {
			this.dispose();
			new RandomSlangWordFrame();

		} else if (e.getSource() == btn5) {
			this.dispose();
			new HistoryFrame();

		} else if (e.getSource() == btn6) {
			this.dispose();
			try {
				new DeleteFrame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == btn7) {
			int n = JOptionPane.showConfirmDialog(this, "Bạn có có chắc muốn reset Slang Word?", "Thoát",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				slangWord.reset();
				JOptionPane.showMessageDialog(this, "Reset thành công!!!");
			}
		} else if (e.getSource() == btn8) {
			this.dispose();
			new QuizFrame();
		}
	}

}
