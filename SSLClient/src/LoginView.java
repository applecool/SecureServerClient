import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView implements ActionListener {
	JTextField userText = new JTextField(20);
	JPasswordField passwordText = new JPasswordField(20);

	public LoginView() {
		JFrame frame = new JFrame("Client");
		frame.setSize(400, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel LoginuserLabel = new JLabel("Login User");
		LoginuserLabel.setBounds(150, 10, 80, 25);
		panel.add(LoginuserLabel);

		JLabel userLabel = new JLabel("User Name");
		userLabel.setBounds(50, 40, 80, 25);
		panel.add(userLabel);

		userText.setBounds(150, 40, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 80, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(150, 80, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("submit");
		loginButton.setBounds(150, 150, 80, 25);
		loginButton.addActionListener(this);
		panel.add(loginButton);

	}

	public void actionPerformed(ActionEvent arg0) {
		User user = new User();
		System.out.println("user:" + userText.getText());
		System.out.println("password:" + userText.getText());
		if (userText.getText().equals("") || passwordText.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "User Invalid", "Message",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			boolean status = user.validateUser(userText.getText(),
					passwordText.getText());
			if (status) {				
				ServerConnectView sm = new ServerConnectView(userText.getText(),passwordText.getText());
			} else {
				JOptionPane.showMessageDialog(null, "User Invalid", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			System.out.println("User Status:" + status);
		}

	}
}