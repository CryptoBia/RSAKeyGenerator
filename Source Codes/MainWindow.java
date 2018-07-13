import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JTextPane;

public class MainWindow {

	private JFrame frmRsaEncrypt;
	private newWindow window;
	private String str;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public BigInteger d, e, n;
	/**
	 * Launch the application.
	 */
	private boolean type = false;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmRsaEncrypt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRsaEncrypt = new JFrame();
		frmRsaEncrypt.setTitle("RSA Encryptor");
		frmRsaEncrypt.setBounds(100, 100, 397, 490);
		frmRsaEncrypt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRsaEncrypt.getContentPane().setLayout(null);
		
		JRadioButton rdbtnEncrypt = new JRadioButton("Encrypt");
		rdbtnEncrypt.setSelected(true);
		rdbtnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = false;
			}
		});
		buttonGroup.add(rdbtnEncrypt);
		rdbtnEncrypt.setBounds(8, 289, 127, 25);
		frmRsaEncrypt.getContentPane().add(rdbtnEncrypt);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Decrypt");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = true;
			}
		});
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(8, 319, 127, 25);
		frmRsaEncrypt.getContentPane().add(rdbtnNewRadioButton);
		
		JLabel lblDevelopedBy = new JLabel("Developed by");
		lblDevelopedBy.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDevelopedBy.setBounds(143, 388, 88, 16);
		frmRsaEncrypt.getContentPane().add(lblDevelopedBy);
		
		JLabel lblAhmetBurkayKrnk = new JLabel("Ahmet Burkay KIRNIK");
		lblAhmetBurkayKrnk.setBounds(243, 388, 121, 16);
		frmRsaEncrypt.getContentPane().add(lblAhmetBurkayKrnk);
		
		JLabel lblPublicKey = new JLabel("Public Key:");
		lblPublicKey.setBounds(8, 95, 103, 16);
		frmRsaEncrypt.getContentPane().add(lblPublicKey);
		
		JTextPane nplace = new JTextPane();
		nplace.setBounds(8, 60, 359, 22);
		frmRsaEncrypt.getContentPane().add(nplace);
		
		JLabel lblN = new JLabel("n:");
		lblN.setBounds(8, 31, 56, 16);
		frmRsaEncrypt.getContentPane().add(lblN);
		
		JLabel lblE = new JLabel("e:");
		lblE.setBounds(8, 124, 56, 16);
		frmRsaEncrypt.getContentPane().add(lblE);
		
		JTextPane eplace = new JTextPane();
		eplace.setBounds(8, 153, 359, 22);
		frmRsaEncrypt.getContentPane().add(eplace);
		
		JLabel lblPrivateKey = new JLabel("Private Key:");
		lblPrivateKey.setBounds(8, 188, 81, 16);
		frmRsaEncrypt.getContentPane().add(lblPrivateKey);
		
		JLabel lblD = new JLabel("d:");
		lblD.setBounds(8, 217, 56, 16);
		frmRsaEncrypt.getContentPane().add(lblD);
		
		JTextPane dplace = new JTextPane();
		dplace.setBounds(8, 246, 355, 22);
		frmRsaEncrypt.getContentPane().add(dplace);
		
		
		JMenuBar menuBar = new JMenuBar();
		frmRsaEncrypt.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Tools");
		menuBar.add(mnNewMenu);
		
		JMenuItem mnýtmNewMenuItem = new JMenuItem("Key Generator");
		mnýtmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					KeyGeneratorGUI window = new KeyGeneratorGUI();
					window.frmRsaKeyGenerator.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}
				
			}
		});
		mnNewMenu.add(mnýtmNewMenuItem);
		
		JMenuItem mnýtmTextPane = new JMenuItem("Text Pane");
		mnýtmTextPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window = new newWindow();
				window.textPane.setText(str);
				try {
					window.frame.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}
				window.textPane.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							str = window.gtext();
							window.frame.dispose();
							window.frame.setVisible(false);
						}
					}
				});
			}
		});
		mnNewMenu.add(mnýtmTextPane);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ea) {
				if(str == "") {
					return;
				}
				n = new BigInteger(nplace.getText());
				
				String end = "";
				
				if(type == false) { //Encrypt
					e = new BigInteger(eplace.getText());
					for(int i=0; i<str.length(); i++) {
						char chr = str.charAt(i);
						Long num = (long) (chr);
						BigInteger c = encrypt(num);
						end += c.toString() + " "; 
						
					}
				}
				
				if(type == true) { //Decrypt
					d = new BigInteger(dplace.getText());
					String tstr = "";
					
					for(int i=0; i<str.length(); i++) {
						
						if(str.charAt(i) == ' ') {
							BigInteger num = new BigInteger(tstr);
							tstr = "";
			 				BigInteger D = decrypt(num);
							end += (char)D.byteValue();
						}
						else {
							tstr += str.charAt(i);
							
						}
					}
				}
				window = new newWindow();
				try {
					window.frame.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}
				window.textPane.setText(end);
				str = end;
				window.textPane.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							window.frame.dispose();
							window.frame.setVisible(false);
						}
					}
				});
			}
		});
		btnNewButton.setBounds(178, 292, 166, 55);
		frmRsaEncrypt.getContentPane().add(btnNewButton);
		
		
	}
	public BigInteger encrypt(long message) {
		return BigInteger.valueOf(message).modPow(e, n);
	}
	
	public BigInteger decrypt(BigInteger message) {
		return message.modPow(d, n);
	}
}
