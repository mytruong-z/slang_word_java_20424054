package slangWord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FindFrame extends JFrame implements ActionListener, TableModelListener {
	JButton btnBack, btnFind;
	JTextField textField;
	JTable jt;
	JLabel titleLabel1;
	DefaultTableModel model;
	SlangReadFile slangW;
	String[][] result;
	final JOptionPane optionPane = new JOptionPane("Bạn có chắc muốn đóng nó?", JOptionPane.QUESTION_MESSAGE,
			JOptionPane.YES_NO_OPTION);
	String data[][] = { { "", "", "" } };

	FindFrame() throws Exception {
		Container con = this.getContentPane();
		slangW = SlangReadFile.getInstance();
		// Title Label
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Tìm Kiếm Slang Words");
		titleLabel.setForeground(Color.blue);
		titleLabel.setFont(new Font("Georgia Bold", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);

		// Result Label
		titleLabel1 = new JLabel();
		titleLabel1.setText("Nhập slang word bạn muốn tìm nghĩa ");
		titleLabel1.setForeground(Color.black);
		titleLabel1.setFont(new Font("Georgia Bold", Font.PLAIN, 18));
		titleLabel1.setAlignmentX(CENTER_ALIGNMENT);

		// Form
		JPanel form = new JPanel();
		JLabel formLabel = new JLabel("Nhập Slang word");
		textField = new JTextField();
		btnFind = new JButton("Tìm");
		btnFind.addActionListener(this);
		btnFind.setMnemonic(KeyEvent.VK_ENTER);
		form.setLayout(new BorderLayout(10, 10));

		form.add(formLabel, BorderLayout.LINE_START);
		form.add(textField, BorderLayout.CENTER);
		form.add(btnFind, BorderLayout.LINE_END);
		Dimension size = new Dimension(600, 50);
		form.setMaximumSize(size);
		form.setPreferredSize(size);
		form.setMinimumSize(size);
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.black);

		String column[] = { "#", "Slag Word", "Ý Nghĩa" };

		jt = new JTable(new DefaultTableModel(column, 0));
		jt.setRowHeight(30);
                JTableHeader header = jt.getTableHeader();
                header.setFont(new Font("Georgia Bold", Font.PLAIN, 20));
		model = (DefaultTableModel) jt.getModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		jt.getModel().addTableModelListener(this);
                jt.setGridColor(Color.orange);
                jt.setCellSelectionEnabled(true);
		JScrollPane sp = new JScrollPane(jt);
		panelTable.setLayout(new GridLayout(1, 1));
		panelTable.add(sp);

		// Button Back
		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Trở về");
		btnBack.setFocusable(false);
		bottomPanel.add(btnBack);
                btnBack.setForeground(Color.red);
		btnBack.addActionListener(this);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);

		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(titleLabel);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(form);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(panelTable);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(btnBack);
		this.setTitle("Tìm Kiếm Slang Words");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFind) {
			String key = textField.getText();
			if (key.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không được để trống", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Object[] options = { "Tìm kiếm theo slang word", "Tìm kiếm theo definition" };
			int n = JOptionPane.showOptionDialog(this, "Bạn muốn tìm kiếm theo chế độ nào? ", "Chế độ tìm",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			String[][] temp = null;
			if (n == 0) {
				this.clearTable();
				temp = slangW.getMeaning(key);
			} else if (n == 1) {
				this.clearTable();
				temp = slangW.findDefinition(key);
			}
                        
                        result = temp;
                        if(result != null) {
                            for (int i = 0; i < result.length; i++) {
                                String ss[] = result[i];
                                    model.addRow(ss);
                            }            
                        }
                        
			try {
                            if(result != null) {
                                for (int ii = 0; ii < temp.length; ii++) {
                                    slangW.saveHistory(temp[ii][1], temp[ii][2]);
                                }
                            }
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == btnBack) {
			this.dispose();
			new Menu();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = jt.getSelectedRow();
		int col = jt.getSelectedColumn();
		if (row == col && row == -1)
			return;
		String Data = (String) jt.getValueAt(row, col);
		if (col == 2) {
			slangW.set((String) jt.getValueAt(row, 1), result[row][2], (String) jt.getValueAt(row, 2));
			JOptionPane.showMessageDialog(this, "Cập nhật");
		}
		jt.setFocusable(false);
	}

	void clearTable() {
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
