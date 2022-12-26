import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


 public class bookshop {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtauthor;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookshop window = new bookshop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public bookshop() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	
	
	
     public void Connect() {
		
    	 
      try {
			Class.forName ("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop","root","PFH#23kgrw9");
	
	    }

	    catch (ClassNotFoundException ex)	{

		ex.printStackTrace();
		}
		
		catch (SQLException ex)
		{
			ex.printStackTrace();
			
		}

	}
	
     
     public void table_load() {
         try {
    	    pst = con.prepareStatement("select * from book");
    	    rs = pst.executeQuery();
    	    table.setModel(DbUtils.resultSetToTableModel(rs));
     }
     catch    (SQLException e)
         {
     
           e.printStackTrace() ;
     }
    	    
     }
     
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setBounds(256, 20, 138, 32);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 70, 319, 190);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setBounds(10, 35, 102, 21);
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 16));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Author");
		lblNewLabel_1_1.setFont(new Font("Sitka Text", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 78, 102, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Sitka Text", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 124, 102, 21);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(122, 34, 171, 21);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtauthor = new JTextField();
		txtauthor.setColumns(10);
		txtauthor.setBounds(122, 78, 171, 21);
		panel.add(txtauthor);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(122, 124, 171, 21);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		String bname,author,price;	
		bname = txtbname.getText();
		author = txtauthor.getText();
		price = txtprice.getText();
		
		try {
			
		pst = con.prepareStatement("insert into book(name,author,price)values(?,?,?)");
		pst.setString(1,bname);
		pst.setString(2,author);
		pst.setString(3,price);
		pst.executeUpdate();
		JOptionPane.showMessageDialog(null,"Book added");
		table_load();
		
	    txtbname.setText("");
	    txtauthor.setText("");
	    txtprice.setText("");
	    txtbid.setText("");
	    txtbname.requestFocus();
		}	
				
		catch (SQLException em)	{
			
			em.printStackTrace();
		}
				
			}
		});
		btnNewButton.setFont(new Font("Sitka Text", Font.BOLD, 12));
		btnNewButton.setBounds(33, 280, 85, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Sitka Text", Font.BOLD, 12));
		btnNewButton_1.setBounds(144, 280, 85, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtauthor.setText("");
				txtprice.setText("");
				txtbid.setText("");
				txtbname.requestFocus();
			}
		});
		btnNewButton_2.setFont(new Font("Sitka Text", Font.BOLD, 12));
		btnNewButton_2.setBounds(264, 280, 85, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(362, 73, 314, 243);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(33, 326, 319, 77);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1_1.setFont(new Font("Sitka Text", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(20, 35, 69, 21);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
		
			public void keyReleased(KeyEvent e) {
				
			try {
				
			String id = txtbid.getText();
			pst = con.prepareStatement("select name,author,price from book where id=?");
			pst.setString(1,id);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next() == true)
			{ String name = rs.getString(1);
			  String author = rs.getString(2);
			  String price = rs.getString(3);
			  
			  txtbname.setText(name);
			  txtauthor.setText(author);
			  txtprice.setText(price);
			}
			
			else
				
			{ txtbname.setText("");
			  txtauthor.setText("");
			  txtprice.setText("");
			  txtbid.setText("");
			}
			
			
			
	     }	
			catch (SQLException ex){
			  
			}
			
			
		}		
			
			
		});
		txtbid.setColumns(10);
		txtbid.setBounds(124, 34, 171, 21);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String bname,author,price,bid;
			bname = txtbname.getText();
			author = txtauthor.getText();
			price = txtprice.getText();
			bid = txtbid.getText();
			try {
			
			pst = con.prepareStatement("update book set name=?,author=?,price=? where id=?");
			pst.setString(1,bname);
			pst.setString(2,author);
			pst.setString(3,price);
			pst.setString(4,bid);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Book updated");
			table_load();
			
			txtbname.setText("");
            txtauthor.setText("");
            txtprice.setText("");
            txtbid.setText("");
			txtbname.requestFocus();
			
			}
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
				
			
				
			}
		});
		btnUpdate.setFont(new Font("Sitka Text", Font.BOLD, 12));
		btnUpdate.setBounds(400, 343, 85, 36);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_3_1 = new JButton("Delete");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String bid;
			
			try {
			bid = txtbid.getText();
			pst = con.prepareStatement("delete from book where id=?");
			pst.setString(1,bid);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Book deleted");
			table_load();
			
			txtbname.setText("");
			txtauthor.setText("");
			txtprice.setText("");
			txtbid.setText("");
			txtbname.requestFocus();

			}
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
			}
		});
		btnNewButton_3_1.setFont(new Font("Sitka Text", Font.BOLD, 12));
		btnNewButton_3_1.setBounds(532, 343, 85, 36);
		frame.getContentPane().add(btnNewButton_3_1);
	}
}
