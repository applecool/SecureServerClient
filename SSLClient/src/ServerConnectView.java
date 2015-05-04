import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ServerConnectView implements ActionListener {
	JTextField jobid = new JTextField(20);
	JTextField opnumber = new JTextField(20);
	String Username, password;
	int userid;

	public ServerConnectView(String Username, String password) {

		this.Username = Username;
		this.password = password;
		User user = new User();
		userid = user.getUserId(Username, password);
		
		System.out.println("User id From DB :" + userid);
		JFrame frame = new JFrame("Client");
		
		frame.setSize(400, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
		System.out.println("Reading jobId and op number");
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel LoginuserLabel = new JLabel("Welcome:    " + Username);
		LoginuserLabel.setBounds(80, 10, 340, 25);
		panel.add(LoginuserLabel);

		JLabel jobidLabel = new JLabel("Job Id:");
		jobidLabel.setBounds(50, 40, 80, 25);
		panel.add(jobidLabel);

		jobid.setBounds(150, 40, 160, 25);
		panel.add(jobid);

		JLabel opnumberLabel = new JLabel("OP Number:");
		opnumberLabel.setBounds(50, 80, 80, 25);
		panel.add(opnumberLabel);

		opnumber.setBounds(150, 80, 160, 25);
		panel.add(opnumber);

		JButton loginButton = new JButton("submit");
		loginButton.setBounds(150, 150, 80, 25);
		loginButton.addActionListener(this);
		panel.add(loginButton);

	}

	public void actionPerformed(ActionEvent arg0) {
		EncryptDecryptData ed = new EncryptDecryptData();
		try{
	String	encrypteddata=ed.encrypt(opnumber.getText());
		System.out.println(encrypteddata);
		System.out.println(ed.decrypt(encrypteddata));
		SSLConnect sc= new SSLConnect();
		sc.connect(encrypteddata);
		}catch (Exception e)
		{}
	}

}