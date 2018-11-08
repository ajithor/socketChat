package p;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class frup extends JFrame {
    public static int sleenc;
	public static  JPanel contentPane;
	public static JTextField textField1;
	public static  JTextField textField2;
	public static  JTextField textField3;
	public  void somethingwrong()
	{
		dispose();
		JOptionPane.showMessageDialog(null,"Somethings Wrong");
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frup frame = new frup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public frup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(162, 29, 184, 22);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(162, 78, 184, 22);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(12, 81, 56, 16);
		contentPane.add(lblKey);
		
		JLabel lblEncryptionType = new JLabel("Encryption Type");
		lblEncryptionType.setBounds(12, 129, 116, 16);
		contentPane.add(lblEncryptionType);
		
		JRadioButton rd1 = new JRadioButton("T1");
		rd1.setBounds(162, 125, 64, 20);
		contentPane.add(rd1);
		
		JRadioButton rd2 = new JRadioButton("T2");
		rd2.setBounds(243, 126, 64, 19);
		contentPane.add(rd2);
		
		JRadioButton rd3 = new JRadioButton("T3");
		rd3.setBounds(311, 123, 127, 25);
		contentPane.add(rd3);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rd1.isSelected())
				{sleenc=1;
					
				}
				else
					if(rd2.isSelected())
					{sleenc=2;
						
					}
					else
						if(rd3.isSelected())
						{sleenc=3;
							
						}
				clientA cli=new clientA();
			
			try {
				cli.upload();
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
				
				JOptionPane.showMessageDialog(null, "Successfully Uploaded \n Session timed out");
				dispose();
			}
		});
		btnUpload.setBounds(162, 215, 97, 25);
		contentPane.add(btnUpload);
		
		JLabel lblFileLoaction = new JLabel("File Loaction");
		lblFileLoaction.setBounds(12, 32, 84, 16);
		contentPane.add(lblFileLoaction);
		
		JLabel lblSaveAs = new JLabel("Save As");
		lblSaveAs.setBounds(12, 170, 56, 16);
		contentPane.add(lblSaveAs);
		
		textField3 = new JTextField();
		textField3.setBounds(162, 167, 184, 22);
		contentPane.add(textField3);
		textField3.setColumns(10);
	}
}
