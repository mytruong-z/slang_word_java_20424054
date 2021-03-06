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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class HistoryFrame extends JFrame implements ActionListener {
	JButton btnReturn, btnExit;
	SlangReadFile slangW = SlangReadFile.getInstance();

	HistoryFrame() {
		Container con = this.getContentPane();

		JLabel hisLabel = new JLabel();
		hisLabel.setText("Lịch Sử Tìm Kiếm");
		hisLabel.setForeground(Color.blue);
		hisLabel.setFont(new Font("Georgia Bold", Font.PLAIN, 35));
		hisLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.black);

		String data[][] = slangW.readHistory();
		String column[] = { "#", "Slang Word", "Definition" };
                //https://shareprogramming.net/huong-dan-su-dung-jtable-trong-java-swing/
		JTable jt = new JTable(data, column);
		jt.setRowHeight(30);
                JTableHeader header = jt.getTableHeader();
                header.setFont(new Font("Georgia Bold", Font.PLAIN, 20));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                jt.setGridColor(Color.orange);
                jt.setCellSelectionEnabled(true);
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		jt.setEnabled(false);
		JScrollPane sp = new JScrollPane(jt);
		panelTable.setLayout(new GridLayout(1, 1));
		panelTable.add(sp);

		JPanel bottomPanel = new JPanel();
		btnExit = new JButton("Trở Về");
		btnExit.addActionListener(this);
                btnExit.setFocusable(false);
                btnExit.setForeground(Color.RED);
		btnExit.addActionListener(this);
		btnExit.setAlignmentX(CENTER_ALIGNMENT);
		bottomPanel.add(btnExit);

		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(hisLabel);
		con.add(Box.createRigidArea(new Dimension(0, 50)));
		con.add(panelTable);
		con.add(Box.createRigidArea(new Dimension(0, 50)));
		con.add(bottomPanel);

		this.setTitle("Lịch Sử");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			this.dispose();
			new Menu();
		}
	}

}
