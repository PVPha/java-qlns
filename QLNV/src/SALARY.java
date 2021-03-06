import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class SALARY extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static JTextField txtID;
	private static JTextField txtSalary;
	private static JTextField txtDaywork;
	private static JTextField txtName;
	private static JTextField txtTax;
	private static JTextField txtReward;
	private static JTextField txtPunish;
	private static JTextField txtSumsalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SALARY frame = new SALARY();
					frame.setVisible(true);
					loadData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	static DefaultTableModel tbn = new DefaultTableModel(); // khai bao model cho table
	public static void loadData() {
		try {
			Connect a = new Connect();//lay ket noi toi co du lieu
			Connection conn = a.getConnection();
			int number;
			Vector row,column;//khai bao thu vien vector
			row = new Vector(); // khoi tạo row và column
			column = new Vector();
			Statement st = conn.createStatement(); //tao đoi tuong statement sql thuc thi câu lenh
			ResultSet rs = st.executeQuery("select nv.MaNV ,nv.TenNV,sl.ngaylv,sl.luongcb,sl.thuong,sl.phat,sl.thue,sl.tongluong from (salary sl right join NhanVien nv on sl.MaNV = nv.MaNV)"); // cau thuc thi lenh lay toan bo du lieu tu bang
			ResultSetMetaData metadata = rs.getMetaData();//tao đoi tuong lay sieu du lieu 
			number = metadata.getColumnCount(); // tra ve so cac cot
			//System.out.println(rs.next());//tesstttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttrs.next()
			for(int i = 1;i <= number;i++) {// vong lap lay tat ca cac cot cua bang
				column.add(metadata.getColumnName(i));//lay ra tieu de cua cac cot
			}
			tbn.setColumnIdentifiers(column);//đinh danh tieu đe cho các cot
			while(rs.next()) { // truong hop co so du lieu tro toi dong tiep theo
				
				row = new Vector();
				for(int i = 1;i<=number;i++) {
					row.addElement(rs.getString(i));//them vao hang du lieu tu vi tri i
				}
				tbn.addRow(row);//them cac gia tri vua lay vao hang 
				table.setModel(tbn); //gan gia tri của model vao bang
				
			}
			
			// hien thi table len textfield
			table.getSelectionModel().addListSelectionListener( new ListSelectionListener() {//lay thay doi moi tu selection bang
				//lay lua chon khi click vao lua chon trong bang
				@Override //ghi đe
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					if(table.getSelectedRow() >=0) { //neu lua chon gia tri trong hang lon hon 0
						txtID.setText(table.getValueAt(table.getSelectedRow(), 0)+"");//gan gia tri  vao textfield cac tuong ung tu vi tri lua chon trong bang 
						txtName.setText(table.getValueAt(table.getSelectedRow(), 1)+"");
						txtDaywork.setText(table.getValueAt(table.getSelectedRow(), 2)+"");
						txtSalary.setText(table.getValueAt(table.getSelectedRow(), 3)+"");
						txtReward.setText(table.getValueAt(table.getSelectedRow(), 4)+"");
						txtPunish.setText(table.getValueAt(table.getSelectedRow(), 5)+"");
						txtTax.setText(table.getValueAt(table.getSelectedRow(), 6)+"");
						txtSumsalary.setText(table.getValueAt(table.getSelectedRow(), 7)+"");
					}
				}});
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}//bao khi co loi
		}
	
	public void exportExcel(JTable table) {
		 JFileChooser chooser = new JFileChooser();
		 int i = chooser.showSaveDialog(chooser);
		 if (i == JFileChooser.APPROVE_OPTION) {
		  File file = chooser.getSelectedFile();
		  try {
		   FileWriter out = new FileWriter(file + ".xls");
		   BufferedWriter bwrite = new BufferedWriter(out);
		   DefaultTableModel model = (DefaultTableModel) table.getModel();
		   // ten Cot
		   for (int j = 0; j < table.getColumnCount(); j++) {
		    bwrite.write(model.getColumnName(j) + "\t");
		   }
		   bwrite.write("\n");
		   // Lay du lieu dong
		   for (int j = 0; j < table.getRowCount(); j++) {
		    for (int k = 0; k < table.getColumnCount(); k++) {
		     bwrite.write(model.getValueAt(j, k) + "\t");
		    }
		    bwrite.write("\n");
		   }
		   bwrite.close();
		   JOptionPane.showMessageDialog(null, "Lưu file thành công!");
		  } catch (Exception e2) {
		   JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
		  }
		 }
		}
	/**
	 * Create the frame.
	 */
	public SALARY() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TÍNH LƯƠNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(332, 10, 257, 43);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 332, 697, 179);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "s\u1ED1 ng\u00E0y l\u00E0m vi\u1EC7c", "l\u01B0\u01A1ng c\u01A1 b\u1EA3n", "th\u01B0\u1EDFng", "ph\u1EA1t", "Thu\u1EBF", "T\u1ED5ng l\u01B0\u01A1ng"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E3 Nh\u00E2n vi\u00EAn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 80, 93, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("S\u1ED1 ng\u00E0y l\u00E0m");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 202, 93, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("T\u00EAn Nh\u00E2n vi\u00EAn");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(370, 80, 106, 29);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("th\u01B0\u1EDFng");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(273, 202, 54, 29);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("L\u01B0\u01A1ng c\u01A1 b\u1EA3n");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(10, 146, 93, 29);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("ph\u1EA1t");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_5.setBounds(497, 202, 38, 29);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Thu\u1EBF TNCN");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(383, 146, 93, 29);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("T\u1ED5ng l\u01B0\u01A1ng");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_7.setBounds(337, 241, 93, 29);
		contentPane.add(lblNewLabel_1_7);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(113, 80, 214, 29);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(113, 146, 214, 29);
		contentPane.add(txtSalary);
		
		txtDaywork = new JTextField();
		txtDaywork.setColumns(10);
		txtDaywork.setBounds(113, 202, 150, 29);
		contentPane.add(txtDaywork);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(486, 82, 209, 29);
		contentPane.add(txtName);
		
		txtTax = new JTextField();
		txtTax.setColumns(10);
		txtTax.setBounds(486, 148, 209, 29);
		contentPane.add(txtTax);
		
		txtReward = new JTextField();
		txtReward.setColumns(10);
		txtReward.setBounds(337, 202, 150, 29);
		contentPane.add(txtReward);
		
		txtPunish = new JTextField();
		txtPunish.setColumns(10);
		txtPunish.setBounds(545, 202, 150, 29);
		contentPane.add(txtPunish);
		
		txtSumsalary = new JTextField();
		txtSumsalary.setEditable(false);
		txtSumsalary.setColumns(10);
		txtSumsalary.setBounds(440, 241, 196, 29);
		contentPane.add(txtSumsalary);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect a = new Connect(); //lay ket noi toi csdl
					Connection conn = a.getConnection();
					PreparedStatement chekID = conn.prepareStatement("select SUM(luongcb) from salary where MaNV = ?");
					chekID.setString(1, txtID.getText());
					ResultSet rs2 = chekID.executeQuery();
					while(rs2.next()){
						System.out.println(rs2.getString(1));
						if(rs2.getString(1) != null){
							System.out.println("update");
							PreparedStatement ps2 = conn.prepareStatement("update  salary set ngaylv =?, luongcb=?, thuong=?,phat=?,thue = ?, tongluong=? where MaNV = ?");
							ps2.setString(7, txtID.getText());//gan gia tri lay tu textfield vao vi tri xac đinh trong cau lenh update
							ps2.setInt(1, Integer.valueOf(txtDaywork.getText()));
							ps2.setInt(2, Integer.valueOf(txtSalary.getText()));
							ps2.setInt(3, Integer.valueOf(txtReward.getText()));
							ps2.setInt(4, Integer.valueOf(txtPunish.getText()));
							ps2.setDouble(5, ((Double.valueOf(txtTax.getText())/100)));
							if( ((Integer.valueOf(txtSalary.getText())/22)*Integer.valueOf(txtDaywork.getText())+ Integer.valueOf(txtReward.getText())-Integer.valueOf(txtPunish.getText())) >= 10000000) {
								System.out.println("co thue");
								ps2.setDouble(6,(((Integer.valueOf(txtSalary.getText())/22)*Integer.valueOf(txtDaywork.getText())+Integer.valueOf(txtReward.getText())-Integer.valueOf(txtPunish.getText())))*(Double.valueOf(txtTax.getText())/100));
							}else {
								System.out.println("ko thue");
								ps2.setDouble(6, (Integer.valueOf(txtSalary.getText())/22)*Integer.valueOf(txtDaywork.getText())+ Integer.valueOf(txtReward.getText())-Integer.valueOf(txtPunish.getText()));
							}
							
							int suatc = ps2.executeUpdate();//truy van hoat đong thay đoi tu csdl
								tbn.setRowCount(0);//set lai so hang ve 0
								loadData();//load lai du lieu 
						}else if(rs2.getString(1) == null){
							System.out.println("add");						PreparedStatement ps2 = conn.prepareStatement("insert into salary values(?,?,?,?,?,?,?)");
							ps2.setString(1, txtID.getText());//gan gia tri lay tu textfield vao vi tri xac đinh trong cau lenh update
							ps2.setString(2, Integer.valueOf(txtDaywork.getText())+"");	
							ps2.setString(3, Integer.valueOf(txtSalary.getText())+"");
							ps2.setString(4, Integer.valueOf(txtReward.getText())+"");
							ps2.setString(5, Integer.valueOf(txtPunish.getText())+"");
							ps2.setDouble(6, ((Double.valueOf(txtTax.getText())/100)));
							//ps2.setString(6, (Integer.valueOf(txtTax.getText())/100)+"");
							ps2.setString(7, 0+"");
							int suatc = ps2.executeUpdate();//truy van hoat đong thay đoi tu csdl
								tbn.setRowCount(0);//set lai so hang ve 0
								loadData();//load lai du lieu 
						}
					}
					// khoi tao đoi tuong thuc thi cau lenh update csdl
					 
				}catch (Exception ex) {
					
					System.out.println(ex.toString()); //bao khi co loi
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(113, 247, 119, 37);
		contentPane.add(btnUpdate);
		
		
		
		JButton btnEx = new JButton("Export");
		btnEx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel(table);
			}
			
		});
		btnEx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEx.setBounds(113, 285, 119, 37);
		contentPane.add(btnEx);
	}
}
