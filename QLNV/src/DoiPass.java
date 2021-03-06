import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DoiPass extends JFrame {

	private JPanel contentPane;
	private static JTextField txtUn;
	private static JTextField txtPw1;
	private static JTextField txtPw2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiPass frame = new DoiPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoiPass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0110\u0103ng Nh\u1EADp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(32, 20, 107, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNhpMtKhu = new JLabel("Nh\u1EADp m\u1EADt kh\u1EA9u");
		lblNhpMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhpMtKhu.setBounds(32, 73, 107, 27);
		contentPane.add(lblNhpMtKhu);
		
		JLabel lblNhpLiMt = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u");
		lblNhpLiMt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhpLiMt.setBounds(32, 125, 123, 27);
		contentPane.add(lblNhpLiMt);
		
		JButton btnS = new JButton("L\u01B0u M\u1EADt Kh\u1EA9u");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				//PreparedStatement ps =null;
				//PreparedStatement ps2=null;
				//ResultSet rs2 = null;
				
				int getlv;
				if(txtUn.getText().equals("") || txtPw1.getText().equals("") || txtPw2.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Ph???i ??i???n ????? th??ng tin");
					} else {
						try {
							//txtUn.setText(" ");
							//txtPw1.setText(" ");
							//txtPw2.setText(" ");
							Connect a = new Connect();//lay ket noi toi co du lieu
							conn = a.getConnection();
							String sql  = "update login set pass = '"+txtPw1.getText()+"' where usename = '"+txtUn.getText()+"'";
							//String level = "select level from login where usename=?";
							//ps2 = conn.prepareStatement(sql);
							//ps2.setString(1, txtUn.getText());
							//rs2 = ps2.executeQuery();
							Statement ps3 = conn.createStatement();
							//Statment ps2 = conn.createStatement("update login set pass = '"+txtPw1.getText()+"' where usename = '"+txtUn.getText()+"'");
							PreparedStatement ps = conn.prepareStatement("update login set pass = '"+txtPw1.getText()+"' where usename = '"+txtUn.getText()+"'");
							//ps.setString(1, txtPw1.getText());
							//ps.setString(2,txtUn.getText());
							ps3.executeUpdate(sql) ;
							
						}catch (Exception ex) {
							System.out.println(ex.toString());
						}
					}
				
				
			}
		});
		btnS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnS.setBounds(134, 205, 193, 33);
		contentPane.add(btnS);
		
		txtUn = new JTextField();
		txtUn.setBounds(176, 20, 178, 27);
		contentPane.add(txtUn);
		txtUn.setColumns(10);
		
		txtPw1 = new JTextField();
		txtPw1.setColumns(10);
		txtPw1.setBounds(176, 73, 178, 27);
		contentPane.add(txtPw1);
		
		txtPw2 = new JTextField();
		txtPw2.setColumns(10);
		txtPw2.setBounds(176, 125, 178, 27);
		contentPane.add(txtPw2);
	}
}
