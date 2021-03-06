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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class QLUSER extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static JTextField txtUsename;
	private static JTextField txtPassword;
	private static JCheckBox chkAdmin;
	private static JCheckBox chkStaff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLUSER frame = new QLUSER();
					frame.setVisible(true);
					loadData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	static DefaultTableModel tbn = new DefaultTableModel(); // khai bao model cho table
	static StringBuilder sb = new StringBuilder();
	private static JTextField txtId;
	private JTextField txtTK;
	private static JCheckBox chkStaff_1;
	private static JCheckBox chkAdmin_1;
	public static void loadData() {
		try {
			Connect a = new Connect();//lay ket noi toi co du lieu
			Connection conn = a.getConnection();
			int number;
			Vector row,column;//khai bao thu vien vector
			row = new Vector(); // khoi tạo row và column
			column = new Vector();
			Statement st = conn.createStatement(); //tao đoi tuong statement sql thuc thi câu lenh
			ResultSet rs = st.executeQuery("Select * from Login"); // cau thuc thi lenh lay toan bo du lieu tu bang
			ResultSetMetaData metadata = rs.getMetaData();//tao đoi tuong lay sieu du lieu 
			number = metadata.getColumnCount(); // tra ve so cac cot
			System.out.println(number);//tesstttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttrs.next()
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
						txtId.setText(table.getValueAt(table.getSelectedRow(), 0)+ "");
						txtUsename.setText(table.getValueAt(table.getSelectedRow(), 1)+ "");//gan gia tri  vao textfield cac tuong ung tu vi tri lua chon trong bang 
						txtPassword.setText(table.getValueAt(table.getSelectedRow(), 2)+"");
						System.out.println(table.getValueAt(table.getSelectedRow(), 3).equals("1"));
						 if(table.getValueAt(table.getSelectedRow(), 3).equals("1")) {
							 chkAdmin_1.setSelected(true);
							 chkStaff_1.setSelected(false);
						 }else {
							 chkStaff_1.setSelected(true);
							 chkAdmin_1.setSelected(false);
						//chkAdmin.setSelected(true) == 1;
						 /*System.out.println(sb.toString().contains("selected"));
							if(sb.toString().contains("selected") == true) {
								chkAdmin.setSelected(true);
							}*/
						}
					}	
				}});
		}catch(Exception ex) {
			System.out.println(ex.toString());//bao khi co loi
		}
	}
	/**
	 * Create the frame.
	 */
	public QLUSER() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ NGƯỜI DÙNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(222, 10, 211, 51);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 213, 553, 139);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"User name", "Password", "Level"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Đăng Nhập");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(37, 90, 118, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(47, 126, 74, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phân Quyền");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(402, 65, 93, 26);
		contentPane.add(lblNewLabel_1_2);
		
		txtUsename = new JTextField();
		txtUsename.setBounds(165, 90, 198, 26);
		contentPane.add(txtUsename);
		txtUsename.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(165, 126, 198, 26);
		contentPane.add(txtPassword);
		
		chkAdmin_1 = new JCheckBox("Quản Lý");
		chkAdmin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkAdmin_1.setBounds(501, 68, 93, 21);
		contentPane.add(chkAdmin_1);
		
		chkStaff_1 = new JCheckBox("Nhân Viên");
		chkStaff_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkStaff_1.setBounds(501, 93, 93, 21);
		contentPane.add(chkStaff_1);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect a = new Connect();//lay ket noi tu csdl
					Connection conn = a.getConnection();
					//khoi tao đoi tuong prepare stament chen cau lenh insert du lieu vao bang sql
					PreparedStatement ps = conn.prepareStatement("insert into Login values(?,?,?,?)");
					ps.setString(1, txtId.getText());
					ps.setString(2, txtUsename.getText());//gan gia tri lay tu textfield vao vi tri xac dinh  trong cau lenh insert
					ps.setString(3, txtPassword.getText());
					if(chkAdmin_1.isSelected()) {
						ps.setString(4, 1+"");// lay gia tri tu radio button	
					} else ps.setString(4, 0+"");
					int themtc = ps.executeUpdate(); //khai bao ham truy van hoat đong khi csdl đuoc thay đoi
					if(themtc > 0) {
						JOptionPane.showMessageDialog(rootPane, "them thanh cong");//tao mot hop thoai thong bao 
						tbn.setRowCount(0);//dat vi tri hang ve 0 trc khi load lai du lieu tranh trung du lieu
						loadData();//load lai du lieu
					}
					chkAdmin_1.setSelected(false);
					chkStaff_1.setSelected(false);
				}catch(SQLException ex) {
					System.out.println(ex.toString());
					if(ex.toString().contains("com.microsoft.sqlserver.jdbc.SQLServerException: Violation of PRIMARY KEY constraint 'pk_login'. Cannot insert duplicate key in object 'dbo.login'.") == true) {
						JOptionPane.showMessageDialog(rootPane, "Trùng mã nhân viên");
					}else if(ex.toString().contains("com.microsoft.sqlserver.jdbc.SQLServerException: Violation of UNIQUE KEY constraint") == true) {
						JOptionPane.showMessageDialog(rootPane, "Trùng tên đăng nhập");
					}
					//JOptionPane.showMessageDialog(rootPane, "trung ma nhan vien");
				} 
			}
		});
		btnAdd.setBounds(70, 169, 100, 34);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Sửa");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect a = new Connect(); //lay ket noi toi csdl
					Connection conn = a.getConnection();
					// khoi tao đoi tuong thuc thi cau lenh update csdl
					PreparedStatement ps2 = conn.prepareStatement("Update Login set  usename=?, pass=?, level=? where MaNV=?");
					ps2.setString(4, txtId.getText());//gan gia tri lay tu textfield vao vi tri xac đinh trong cau lenh update
					ps2.setString(1, txtUsename.getText());
					ps2.setString(2, txtPassword.getText());
					if(chkAdmin_1.isSelected()) {
						ps2.setString(3, 1+"");// lay gia tri tu radio button	
					} else ps2.setString(3, 0+"");
					int suatc = ps2.executeUpdate();//truy van hoat đong thay đoi tu csdl
					
						tbn.setRowCount(0);//set lai so hang ve 0
						loadData();//load lai du lieu
						chkAdmin_1.setSelected(false);
						chkStaff_1.setSelected(false);	
				}catch (Exception ex) {
					System.out.println(ex.toString()); //bao khi co loi
					if(ex.toString().contains("com.microsoft.sqlserver.jdbc.SQLServerException: Violation of UNIQUE KEY constraint") == true) {
						JOptionPane.showMessageDialog(rootPane, "Trùng tên đăng nhập");
					}
					}
			}
		});
		btnUpdate.setBounds(263, 169, 100, 34);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect a = new Connect();//lay ket noi toi csdl
					Connection conn = a.getConnection();
					PreparedStatement ps3 = conn.prepareStatement("delete Login where MaNV=?");
					ps3.setString(1, txtId.getText());//lay gia tri tu textfield manv truyen vao đieu kien cau lenh xoa
					if(JOptionPane.showConfirmDialog(rootPane, "Xac Nhan Xoa?","Confirm",
							// tao mot hop thoai xac nhan neu chon xac nhan thi cau lenh xoa đuoc thuc thi
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						int xoatc = ps3.executeUpdate();//truy van hoat dong thay doi tu csdl
						tbn.setRowCount(0);//set lai so hang ve 0
						loadData();//load lai du  lieu
					}
				}catch(Exception ex){
					System.out.println(ex.toString());// bao khi co loi
				}
			}
		});
		btnDelete.setBounds(469, 169, 100, 34);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("ID");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(402, 126, 27, 26);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(439, 126, 151, 26);
		contentPane.add(txtId);
		
		txtTK = new JTextField();
		txtTK.setColumns(10);
		txtTK.setBounds(165, 54, 198, 26);
		contentPane.add(txtTK);
		
		JButton btnSearch = new JButton("Tìm Kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect com = new Connect();
					Connection conn = com.getConnection();
					PreparedStatement ps1 = conn.prepareStatement("select * from login where usename=?");
					ps1.setString(1, txtTK.getText());
					ResultSet tk = ps1.executeQuery();
					if(tk.next()){
						JOptionPane.showMessageDialog(rootPane, "Tìm thành công");
						for(int i = 0; i < table.getRowCount();i++) {
							if(table.getValueAt(i, 1).toString().toLowerCase().contains(txtTK.getText().toLowerCase())) {
								txtId.setText(table.getValueAt(i, 0).toString());
								txtUsename.setText(table.getValueAt(i, 1).toString());
								txtPassword.setText(table.getValueAt(i, 2).toString());
								if(table.getValueAt(i, 3).equals("1")) {
									chkAdmin_1.setSelected(true);
									chkStaff_1.setSelected(false);
								}else {
									chkAdmin_1.setSelected(false);
									chkStaff_1.setSelected(true);
								}
								/*cbTenPB.setSelectedItem(table.getValueAt(i, 3).toString());
								if((tableDA.getValueAt(i, 4)).equals("Có")) {
									chbN.setSelected(true);
								}		
								else {
									chbN.setSelected(false);
								}*/
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "Không tìm được");
					}
				}
				catch(SQLException ex){
					System.out.println(ex.toString());
					JOptionPane.showMessageDialog(rootPane, "Lỗi!");
				}
				txtTK.setText("");
			}
			
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(37, 54, 118, 26);
		contentPane.add(btnSearch);
	}
}
