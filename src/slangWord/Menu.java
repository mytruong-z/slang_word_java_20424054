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
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener {
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
	SlangReadFile slangWord;

	Menu() {
		slangWord = SlangReadFile.getInstance();
		// A Label
		JLabel label = new JLabel("Slang Words");
		label.setForeground(Color.blue);
		label.setFont(new Font("Georgia Bold", Font.PLAIN, 36));
		label.setAlignmentX(CENTER_ALIGNMENT);
                
                JLabel author = new JLabel("Trương Thị Ngọc Mỵ - 20424054");
		author.setForeground(Color.pink);
		author.setFont(new Font("Georgia Bold", Font.PLAIN, 12));
		author.setAlignmentX(CENTER_ALIGNMENT);
		
		btn1 = new JButton("1. Danh Sách");
		btn1.addActionListener(this);
                btn1.addMouseListener(new MouseAdapter() {
                    Color color = btn1.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn1.getForeground();
                       btn1.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn1.setForeground(color);
                    }
                 });
		btn1.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn1.setFocusable(false);

		btn2 = new JButton("2. Tìm Kiếm Theo Slang word");
		btn2.addActionListener(this);
                btn2.addMouseListener(new MouseAdapter() {
                    Color color = btn2.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn2.getForeground();
                       btn2.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn2.setForeground(color);
                    }
                 });
		btn2.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn2.setFocusable(false);

		btn3 = new JButton("3. Thêm Slang word");
		btn3.addActionListener(this);
                btn3.addMouseListener(new MouseAdapter() {
                    Color color = btn3.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn3.getForeground();
                       btn3.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn3.setForeground(color);
                    }
                 });
		btn3.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn3.setFocusable(false);
                
                btn4 = new JButton("4. Sửa một Slang Word");
		btn4.addActionListener(this);
                btn4.addMouseListener(new MouseAdapter() {
                    Color color = btn4.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn4.getForeground();
                       btn4.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn4.setForeground(color);
                    }
                 });
		btn4.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn4.setFocusable(false);
                
                btn5 = new JButton("5. Xóa một Slang Word");
		btn5.addActionListener(this);
                btn5.addMouseListener(new MouseAdapter() {
                    Color color = btn5.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn5.getForeground();
                       btn5.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn5.setForeground(color);
                    }
                 });
		btn5.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn5.setFocusable(false);


		btn6 = new JButton("6. Lịch Sử Tìm Kiếm");
		btn6.addActionListener(this);
                btn6.addMouseListener(new MouseAdapter() {
                    Color color = btn6.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn6.getForeground();
                       btn6.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn6.setForeground(color);
                    }
                 });
		btn6.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn6.setFocusable(false);
                
                btn7 = new JButton("7. Random Slang Words");
		btn7.addActionListener(this);
                btn7.addMouseListener(new MouseAdapter() {
                    Color color = btn7.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn7.getForeground();
                       btn7.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn7.setForeground(color);
                    }
                 });
		btn7.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn7.setFocusable(false);

		btn8 = new JButton("8. Reset Danh Sách Slang Word Gốc");
		btn8.addActionListener(this);
                btn8.addMouseListener(new MouseAdapter() {
                    Color color = btn8.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn8.getForeground();
                       btn8.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn8.setForeground(color);
                    }
                 });
		btn8.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn8.setFocusable(false);
                
                btn9 = new JButton("9. Chương Trình Đố Vui");
		btn9.addActionListener(this);
                btn9.addMouseListener(new MouseAdapter() {
                    Color color = btn9.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn9.getForeground();
                       btn9.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn9.setForeground(color);
                    }
                 });
		btn9.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn9.setFocusable(false);

		btn10 = new JButton("10. Thoát");
		btn10.addActionListener(this);
                btn10.addMouseListener(new MouseAdapter() {
                    Color color = btn10.getForeground();
                    public void mouseEntered(MouseEvent me) {
                       color = btn10.getForeground();
                       btn10.setForeground(Color.green);
                    }
                    public void mouseExited(MouseEvent me) {
                       btn10.setForeground(color);
                    }
                 });
		btn10.setFont(new Font("Georgia Bold", Font.PLAIN, 16));
		btn10.setFocusable(false);

		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(10, 1));
		panelCenter.add(btn1);
		panelCenter.add(btn2);
		panelCenter.add(btn3);
		panelCenter.add(btn4);
		panelCenter.add(btn5);
		panelCenter.add(btn6);
		panelCenter.add(btn7);
		panelCenter.add(btn8);
                panelCenter.add(btn9);
                panelCenter.add(btn10);

		Dimension size2 = new Dimension(400, 350);
		panelCenter.setMaximumSize(size2);
		panelCenter.setPreferredSize(size2);
		panelCenter.setMinimumSize(size2);
		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 15)));
		con.add(label);
                con.add(author);
		con.add(Box.createRigidArea(new Dimension(0, 40)));
		con.add(panelCenter);

		// Setting Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Menu");
		this.setVisible(true);
		this.setSize(500, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			this.dispose();
			try {
				new ListFrame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == btn2) {
			this.dispose();
			try {
				new FindFrame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btn3) {
			this.dispose();
			new AddSlangWordFrame();

		} else if (e.getSource() == btn4) {
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Chức năng chưa hoàn thành!!!");
			new Menu();

		} else if (e.getSource() == btn5) {
                    this.dispose();
			try {
				new DeleteFrame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btn6) {
                        this.dispose();
			new HistoryFrame();
		} else if (e.getSource() == btn7) {
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Chức năng chưa hoàn thành!!!");
                        new Menu();
		} else if (e.getSource() == btn8) {
			int n = JOptionPane.showConfirmDialog(this, "Bạn có có chắc muốn reset Slang Word?", "Message",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				slangWord.reset();
				JOptionPane.showMessageDialog(this, "Reset thành công!!!");
			}
		} else if (e.getSource() == btn9) {
			this.dispose();
                        JOptionPane.showMessageDialog(this, "Chức năng chưa hoàn thành!!!");
			new Menu();
		} else if (e.getSource() == btn10) {
			System.exit(0);
		}
	}

}
