package slangWord;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

public class ListFrame extends JFrame implements ActionListener, TableModelListener {
	JButton btnBack;
	JTable jt;
	SlangReadFile slangW;
	String dataCopy[][];

	public ListFrame() throws Exception {
		Container con = this.getContentPane();
		slangW = SlangReadFile.getInstance();

		// Label
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Danh Sách Slang Words");
		titleLabel.setForeground(Color.blue);
		titleLabel.setFont(new Font("Georgia Bold", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);

		// Label
		JLabel resultLabel = new JLabel();
		resultLabel.setForeground(Color.black);
		resultLabel.setFont(new Font("Georgia Bold", Font.PLAIN, 18));
		resultLabel.setAlignmentX(CENTER_ALIGNMENT);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.black);
		String data[][] = slangW.getData();
		dataCopy = slangW.getData();
		String column[] = { "#", "Slag Word", "Ý Nghĩa" };
		jt = new JTable(data, column);
                JTableHeader header = jt.getTableHeader();
                header.setFont(new Font("Georgia Bold", Font.PLAIN, 20));
                jt.setGridColor(Color.orange);
                jt.setCellSelectionEnabled(true);
               
		jt.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                
                jt.getColumnModel().getColumn(0).setPreferredWidth(10);

                jt.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		jt.getModel().addTableModelListener(this);

		JScrollPane sp = new JScrollPane(jt);
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
		panelTable.add(sp);

		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Trở Về ");
		btnBack.addActionListener(this);
		btnBack.setForeground(Color.red);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);
		bottomPanel.add(btnBack);

		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(titleLabel);
		con.add(Box.createRigidArea(new Dimension(0, 20)));
		con.add(Box.createRigidArea(new Dimension(0, 20)));
		con.add(panelTable);
		con.add(Box.createRigidArea(new Dimension(0, 20)));
		con.add(bottomPanel);
		
		this.setTitle("Danh Sách Slang Words");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			this.dispose();
			new Menu();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = jt.getSelectedRow();
		int col = jt.getSelectedColumn();
		if (row == -1 || col == -1)
			return;
		String Data = (String) jt.getValueAt(row, col);

		if (col == 2) {
			slangW.set((String) jt.getValueAt(row, 1), dataCopy[row][2], (String) jt.getValueAt(row, 2));
			JOptionPane.showMessageDialog(this, "Cập nhật 1 dòng");
		}
	}
}
