package p;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JButton;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class fr1 extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fr1 frame = new fr1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public fr1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSecureServer = new JLabel("Secure Server");
		lblSecureServer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSecureServer.setBounds(12, 13, 177, 51);
		contentPane.add(lblSecureServer);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				frup up=new frup();
				up.setVisible(true);
			}
		});
		btnUpload.setBounds(12, 153, 108, 25);
		contentPane.add(btnUpload);
		
		JButton btnDownload = new JButton("DOWNLOAD");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frdw dw=new frdw();
				dispose();
				dw.setVisible(true);
				
			}
		});
		btnDownload.setBounds(258, 153, 108, 25);
		contentPane.add(btnDownload);
	}

}
