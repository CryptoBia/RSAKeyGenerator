import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.Random;

public class KeyGeneratorGUI {

	public JFrame frmRsaKeyGenerator;
	private JTextField pplace;
	private JTextField qplace;
	public BigInteger e, d , n , phi, p , q;
	public int bitlength = 1024;
	private Random r;
	public Double k = (double)2;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyGeneratorGUI window = new KeyGeneratorGUI();
					window.frmRsaKeyGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	

	/**
	 * Initialize the contents of the frame.
	 */
	public Double gcd(Double a, Double h) {
		Double temp;
		while(true) {
			temp = a%h;
			if(temp == 0) {
				return h;
			}
			a = h;
			h = temp;
		}
	}
	private void initialize() {
		
		frmRsaKeyGenerator = new JFrame();
		frmRsaKeyGenerator.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					frmRsaKeyGenerator.dispose();
					frmRsaKeyGenerator.setVisible(false);
				}
			}
		});
		
		frmRsaKeyGenerator.setTitle("RSA Key Generator");
		frmRsaKeyGenerator.setBounds(100, 100, 367, 639);
		frmRsaKeyGenerator.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmRsaKeyGenerator.getContentPane().setLayout(null);
		
		pplace = new JTextField();
		pplace.setBounds(22, 84, 294, 22);
		frmRsaKeyGenerator.getContentPane().add(pplace);
		pplace.setColumns(10);
		
		qplace = new JTextField();
		qplace.setBounds(22, 148, 294, 22);
		frmRsaKeyGenerator.getContentPane().add(qplace);
		qplace.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("p:");
		lblNewLabel.setBounds(22, 55, 56, 16);
		frmRsaKeyGenerator.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("q:");
		lblNewLabel_1.setBounds(22, 119, 56, 16);
		frmRsaKeyGenerator.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPublicKey = new JLabel("Public Key:");
		lblPublicKey.setBounds(12, 294, 69, 28);
		frmRsaKeyGenerator.getContentPane().add(lblPublicKey);
		
		JLabel nlabel = new JLabel("n:");
		nlabel.setBounds(22, 335, 56, 16);
		frmRsaKeyGenerator.getContentPane().add(nlabel);
		
		JLabel lblNewLabel_2 = new JLabel("Private Key:");
		lblNewLabel_2.setBounds(12, 463, 69, 16);
		frmRsaKeyGenerator.getContentPane().add(lblNewLabel_2);
		
		JTextPane errorplace = new JTextPane();
		errorplace.setBounds(200, 212, 116, 69);
		frmRsaKeyGenerator.getContentPane().add(errorplace);
		
		
		JTextPane nplace = new JTextPane();
		nplace.setBounds(22, 364, 294, 22);
		frmRsaKeyGenerator.getContentPane().add(nplace);
		
		JLabel elabel = new JLabel("e:");
		elabel.setBounds(22, 399, 56, 16);
		frmRsaKeyGenerator.getContentPane().add(elabel);
		
		JTextPane eplace = new JTextPane();
		eplace.setBounds(22, 428, 294, 22);
		frmRsaKeyGenerator.getContentPane().add(eplace);
		
		JTextPane dplace = new JTextPane();
		dplace.setBounds(22, 521, 294, 22);
		frmRsaKeyGenerator.getContentPane().add(dplace);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ea) {
				r = new Random();
				p = BigInteger.probablePrime(bitlength, r);
				q = BigInteger.probablePrime(bitlength, r);
				n = p.multiply(q);
				pplace.setText(p.toString());
				qplace.setText(q.toString());
				
				phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
				nplace.setText(n.toString());
				e = BigInteger.probablePrime(bitlength/2, r);
				
				while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
					e.add(BigInteger.ONE);
				}
				eplace.setText(e.toString());
				d = e.modInverse(phi);
				dplace.setText(d.toString());
				nplace.setText(n.toString());
				errorplace.setText("Key generation process is done without errors...");
				
			}
		});
		btnNewButton.setBounds(22, 212, 161, 69);
		frmRsaKeyGenerator.getContentPane().add(btnNewButton);
		
		JLabel lblD = new JLabel("d:");
		lblD.setBounds(22, 492, 56, 16);
		frmRsaKeyGenerator.getContentPane().add(lblD);
		
		JLabel lblErrorLog = new JLabel("Log:");
		lblErrorLog.setBounds(200, 183, 69, 16);
		frmRsaKeyGenerator.getContentPane().add(lblErrorLog);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(282, 213, -34, 16);
		frmRsaKeyGenerator.getContentPane().add(textArea);
		
		JLabel lblAhmetBurkayKrnk = new JLabel("Ahmet Burkay KIRNIK");
		lblAhmetBurkayKrnk.setBounds(195, 563, 121, 16);
		frmRsaKeyGenerator.getContentPane().add(lblAhmetBurkayKrnk);
		
		JLabel lblDevelopedBy = new JLabel("Developed by");
		lblDevelopedBy.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDevelopedBy.setBounds(100, 563, 97, 16);
		frmRsaKeyGenerator.getContentPane().add(lblDevelopedBy);
		
		JLabel lblNewLabel_3 = new JLabel("Prime Numbers:");
		lblNewLabel_3.setBounds(12, 26, 121, 16);
		frmRsaKeyGenerator.getContentPane().add(lblNewLabel_3);
		
	}
	public KeyGeneratorGUI() {
		initialize();
	}
}
