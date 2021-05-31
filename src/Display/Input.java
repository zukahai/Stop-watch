package Display;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Input extends JFrame implements ActionListener{
	Container cn;
	JLabel lb;
	JTextField tf;
	JButton ok_bt;
	public Input() {
		super("Enter the number of watches");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		lb = new JLabel("Enter the number of watches (1->30)");
		lb.setFont(new Font("UTM Nokia", 1, 25));
		
		JPanel label_pn = new JPanel();
		
		label_pn.setLayout(new FlowLayout());
		label_pn.add(lb);
		
		ok_bt = new JButton("OK");
		ok_bt.addActionListener(this);
		ok_bt.setFont(new Font("UTM Nokia", 1, 15));
		ok_bt.setBackground(Color.white);
		ok_bt.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
		ok_bt.setBackground(Color.gray);
		
		tf = new JTextField("5");
		tf.setFont(new Font("UTM Nokia", 1, 35));
		tf.setHorizontalAlignment(JTextField.CENTER);
		
		cn.add(label_pn, "North");
		cn.add(tf, "Center");
		cn.add(ok_bt, "South");
		
		this.setVisible(true);
		this.setSize(500, 200);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		return cn;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = tf.getText();
		String numbers = "[0-9]+";
		int num = 100;
		if (str.matches(numbers)) {
			num = Integer.parseInt(tf.getText());
			if (num >= 1 && num <= 30)
				new StopWatch(num);
			else
				JOptionPane.showMessageDialog(null, "Number must be an integer between 1 and 30");
		} else {
			JOptionPane.showMessageDialog(null, "Please enter the number");
		}
	}
	
	
	public static void main(String[] args) {
		new Input();
	}
}
