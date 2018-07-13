import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
public class newWindow {
	public String text;
	public JTextPane textPane;
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newWindow window = new newWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public newWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 566, 672);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		textPane = new JTextPane();
		
		frame.getContentPane().add(textPane, BorderLayout.CENTER);
		
	}
	public String gtext() {
		 return textPane.getText();
	}
}
