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
import javax.swing.JPanel;
import javax.swing.Timer;

public class StopWatch extends JFrame implements ActionListener{
	Container cn;
	Timer timer;
	String timeStart = "";
	JButton start_bt, stop_bt;
	JButton wt[] = new JButton[1000];
	JPanel watch_pn, button_pn;
	JPanel pn[] = new JPanel[1000];;
	int startClock = 0;
	public StopWatch(int N) {
		super("StopWatch");
		cn = init(N);
	}
	
	public Container init(int N) {
		Container cn = this.getContentPane();
		int row = getSiZe(N)[0];
		int colum = getSiZe(N)[1];
		watch_pn = new JPanel();
		watch_pn.setLayout(new GridLayout(row, colum));
		for (int i = 0; i < N; i++) {
			pn[i] = new JPanel();
			pn[i].setLayout(new GridLayout(2, 1));
			
			wt[i] = new JButton("00:00:00:000");
			wt[i].setFont(new Font("UTM Nokia", 1, 25));
			wt[i].setBackground(Color.gray);
			
			JLabel lb_temp = new JLabel("Clock " + (i + 1));
			lb_temp.setFont(new Font("UTM Nokia", 1, 20));
			
			JPanel pn_temp = new JPanel();
			pn_temp.setLayout(new FlowLayout());
			pn_temp.add(lb_temp);
			
			int t, b, l, r;
			t = b = l = r = 5;
			if (i / colum == 0)
				t = 10;
			if (i / colum == colum - 1)
				b = 10;
			if (i % colum == 0)
				l = 10;
			if ((i + 1) % colum == 0)
				r = 10;
			pn[i].add(pn_temp);
			pn[i].add(wt[i]);
			pn[i].setBorder(BorderFactory.createMatteBorder(t, l, b, r, Color.red));
			
			watch_pn.add(pn[i]);
		}
		
		start_bt = new JButton("Start");
		start_bt.addActionListener(this);
		start_bt.setFont(new Font("UTM Nokia", 1, 15));
		start_bt.setBackground(Color.white);
		
		stop_bt = new JButton("Stop");
		stop_bt.addActionListener(this);
		stop_bt.setFont(new Font("UTM Nokia", 1, 15));
		stop_bt.setBackground(Color.white);
		
		button_pn = new JPanel();
		button_pn.setLayout(new GridLayout(2, 1));
		button_pn.add(start_bt);
		button_pn.add(stop_bt);
		
		cn.add(watch_pn, "North");
		cn.add(button_pn, "South");
		
		timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
				String t = sdf.format(cal.getTime());
//				System.out.println(getTime(timeStart, t));
				String tt = getTime(timeStart, t);
				for (int i = startClock; i < N; i++)
					wt[i].setText(tt);
			}
		});
		this.setVisible(true);
		this.setSize(colum * 205, row * 135);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		return cn;
	}
	
	public int[] getSiZe(int N) {
		int arr[] = new int[2];
		int a = 1000, b = 1000;
		for (int i = N; i <= 2 * N; i++) {
			for (int j = 1; j <= Math.sqrt(i); j++)
				if (i % j == 0 && j + i / j < a + b) {
					a = j;
					b = i / j;
				}
		}
		arr[0] = a;
		arr[1] = b;
		return arr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(start_bt.getText())) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
			timeStart = sdf.format(cal.getTime());
			timer.start();
			start_bt.disable();
		} else {
			startClock++;
		}
	}
	
	public String getTime(String timeStart, String t) {
		int A[] = new int[4];
		int B[] = new int[4];
		int C[] = new int[4];
		String[] a = timeStart.split(":");
		for (int i = 0; i < a.length; i++)
			A[i] = Integer.parseInt(a[i]);
		a = t.split(":");
		for (int i = 0; i < a.length; i++)
			B[i] = Integer.parseInt(a[i]);
		C[3] = B[3] - A[3];
		if (C[3] < 0) {
			C[3] += 1000;
			B[3]--;
		}
		for (int i = 2; i >= 0; i--) {
			C[i] = B[i] - A[i];
			if (C[i] < 0) {
				C[i] += 60;
				B[i - 1]--;
			}
		}
		for (int i = 0; i < a.length; i++)
			a[i] = String.valueOf(C[i]);
		while (a[3].length() < 3)
				a[3] = "0" +a[3];
		for (int i = 0; i <= 2; i++)
			while (a[i].length() < 2)
				a[i] = "0" +a[i];
		return a[0] + ":" + a[1] + ":" + a[2] + ":" + a[3];
	} 
	
	public static void main(String[] args) {
		new StopWatch(9);
	}
}
