package p;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class frdw extends JFrame {

	private JPanel contentPane;
	public static  JTextField textField4;
	public static  JTextField textField5;
	public static  JTextField textField6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frdw frame = new frdw();
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
	public frdw() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setBounds(12, 34, 87, 16);
		contentPane.add(lblFileName);
		
		textField4 = new JTextField();
		textField4.setBounds(204, 31, 116, 22);
		contentPane.add(textField4);
		textField4.setColumns(10);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(12, 79, 56, 16);
		contentPane.add(lblKey);
		
		textField5 = new JTextField();
		textField5.setBounds(204, 76, 116, 22);
		contentPane.add(textField5);
		textField5.setColumns(10);
		
		JLabel lblSaveTo = new JLabel("Save To");
		lblSaveTo.setBounds(12, 128, 56, 16);
		contentPane.add(lblSaveTo);
		
		textField6 = new JTextField();
		textField6.setBounds(204, 125, 116, 22);
		contentPane.add(textField6);
		textField6.setColumns(10);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				clientA cli=new clientA();
				try {
					cli.download();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Successfully Downloaded \n Session timed out");
				
			}
		});
		btnDownload.setBounds(204, 193, 97, 25);
		contentPane.add(btnDownload);
	}

}
