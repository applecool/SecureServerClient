import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ServerManagerView implements ActionListener {
	JTextField jobid = new JTextField(20);
	JTextField opnumber = new JTextField(20);
	String job_id;
	String op_number;

	public ServerManagerView(String jobid, String opnumber) {
		job_id = jobid;
		op_number = opnumber;
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

		JLabel jobidLabel = new JLabel("Job Id:");
		jobidLabel.setBounds(50, 40, 80, 25);
		panel.add(jobidLabel);

		jobid.setText(job_id);
		jobid.setBounds(150, 40, 160, 25);
		panel.add(jobid);

		JLabel opnumberLabel = new JLabel("OP Number:");
		opnumberLabel.setBounds(50, 80, 80, 25);
		panel.add(opnumberLabel);

		opnumber.setText(op_number);
		opnumber.setBounds(150, 80, 160, 25);
		panel.add(opnumber);

		JButton DecryptButton = new JButton("Decrypt");
		DecryptButton.setBounds(50, 150, 80, 25);
		DecryptButton.addActionListener(this);
		panel.add(DecryptButton);

		JButton EncryptButton = new JButton("Encrypt");
		EncryptButton.setBounds(150, 150, 80, 25);
		EncryptButton.addActionListener(this);
		panel.add(EncryptButton);

	}

	public void actionPerformed(ActionEvent arg0) {
		EncryptDecryptData ed = new EncryptDecryptData();
		try {
			opnumber.setText(ed.decrypt(opnumber.getText()));
		} catch (Exception e) {
		}
	}

}